package biz.freshcode.learn.gwt2.mod2.client.spike.lanes.reuse;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.chart.series.LineSeriesBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.draw.path.PathSpriteBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.tips.ToolTipConfigBuilder;
import biz.freshcode.learn.gwt2.common.client.util.chart.PointSeries;
import biz.freshcode.learn.gwt2.common.client.util.chart.SeriesMap;
import biz.freshcode.learn.gwt2.common.client.util.chart.SeriesMapChart;
import biz.freshcode.learn.gwt2.common.client.util.data.HasIdTitle;
import biz.freshcode.learn.gwt2.common.client.util.data.StartDurn;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Command;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.event.SeriesItemOutEvent;
import com.sencha.gxt.chart.client.chart.event.SeriesItemOverEvent;
import com.sencha.gxt.chart.client.chart.event.SeriesSelectionEvent;
import com.sencha.gxt.chart.client.chart.series.LineSeries;
import com.sencha.gxt.chart.client.chart.series.Series;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.CircleSprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.data.shared.LabelProvider;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt2.common.client.util.AppObjectUtils.safeEquals;
import static biz.freshcode.learn.gwt2.common.client.util.ExceptionUtil.illegalArg;
import static com.sencha.gxt.chart.client.chart.Chart.Position;

public class LanesChart extends SeriesMapChart implements SeriesSelectionEvent.SeriesSelectionHandler<Integer>, SeriesItemOverEvent.SeriesItemOverHandler<Integer>, SeriesItemOutEvent.SeriesItemOutHandler<Integer> {
    private static final int HR = 60;
    private static final String PRIMER = "primer";
    public static final int STROKE_NON_FOCUSED = 3;
    public static final int STROKE_FOCUSED = 6;
    public static final RGB FOCUSED_PERIOD_COL = RGB.LIGHTGRAY;
    private final ValueProvider<Integer, Double> FOCUSED_PERIOD_FIELD = accessY("Focused Period", Double.NaN);
    private final List<HasIdTitle> resources = newList();
    private Date zeroTime = new Date();
    private String lastFocusIdOrNull;
    private StartDurn focusedPeriodOrNull;
    private final EventBus bus;
    private Iterable<BarInfo> prevBars = newList();
    private String currentBarIdIfAny;

    public LanesChart() {
        this(new SimpleEventBus());
    }

    public LanesChart(EventBus bus) {
        setupChart();
        this.bus = bus;
        primeChart();
    }

    /**
     * Tap focus change events.
     */
    public HandlerRegistration addFocusChangeHandler(BarFocusEvent.Handler h) {
        return bus.addHandler(BarFocusEvent.getType(), h);
    }

    public void reorder(List<HasIdTitle> newOrder) {
        replaceResources(newOrder);
        replaceBars(prevBars);

        chart.redrawChart();
    }

    public void configure(ChartInfo info) {
        clearChart();
        replaceResources(info.getResources());
        zeroTime = info.getZeroTime();

        NumericAxis<Integer> top = getNumericAxis(Position.BOTTOM);
        top.setMaximum(info.getWindowSize());

        primeChart();

        NumericAxis<Integer> left = getNumericAxis(Position.LEFT);
        int resourceCount = info.getResources().size();
        left.setMaximum(Math.max(1, resourceIndexToValue(resourceCount - 1)));
        left.setSteps(Math.max(1, resourceCount));

        chart.redrawChart();
        setLastFocusId(null);
    }

    public void replaceBars(Iterable<BarInfo> bars) {
        clearChart();
        prevBars = bars;

        // compile and post data
        SeriesMap map = SeriesMap.NIL;
        for (BarInfo bar : bars) {
            Integer y = resourceToNumber(bar.getResourceId());
            StartDurn sd = bar.getStartDurn();
            map = map.put(bar.getId(), pointSeries(sd, y));
        }

        if (map.isEmpty()) {
            primeChart();
        }

        NumericAxis<Integer> left = getNumericAxis(Position.LEFT);
        boolean refocus = false;
        for (BarInfo bar : bars) {
            String barId = bar.getId();
            if (barId.equals(lastFocusIdOrNull)) refocus = true;

            ValueProvider<Integer, Double> f = accessY(barId, Double.NaN);
            left.addField(f);

            LineSeries<Integer> s = createSeries(f, bar.getColour());
            chart.addSeries(s);
        }

        // focused period
        if (focusedPeriodOrNull != null) {
            NumericAxis<Integer> bottom = getNumericAxis(Position.BOTTOM);
            left.addField(FOCUSED_PERIOD_FIELD);
            LineSeries<Integer> s = createSeries(FOCUSED_PERIOD_FIELD, FOCUSED_PERIOD_COL);
            s.setFill(FOCUSED_PERIOD_COL);
            // Hmm... would be nice to make stroke fully transparent... but fiddling with rendered doesn't work ?!
            chart.addSeries(s);
            PointSeries focus = pointSeries(focusedPeriodOrNull, (int) (left.getMaximum() + 1));
            Point postStepper = new Point(focus.getMaxX() + 1, 0);
            // need to prevent 'NaN' effects because otherwise fill won't work
            focus = focus
                    .add(postStepper)
                    .stepify((int) bottom.getMinimum(), (int) bottom.getMaximum());
            map = map.put(FOCUSED_PERIOD_FIELD.getPath(), focus);
        }

        replaceAll(map);

        if (refocus) focusBar(lastFocusIdOrNull);
        else focusBar(null);
    }

    public void unfocus() {
        focusBar(null);
    }

    public void focusBar(String idOrNull) {
//        ToolTipConfig ttc = null;
        for (Series<Integer> s : chart.getSeries()) {
            if (!(s instanceof LineSeries)) continue;
            LineSeries<Integer> ls = (LineSeries<Integer>) s;
            String barId = ls.getYField().getPath();
            boolean focused = barId.equals(idOrNull);
            // NOTES: Fill doesn't seem to work, highlight might only be for hover over legend
            // Markers suffer from interpolated points
            ls.setStrokeWidth(focused ? STROKE_FOCUSED : STROKE_NON_FOCUSED);
            ls.setShowMarkers(focused);
//            if (focused) {
//                BarInfo bar = getBar(barId);
//                if (bar != null) {
//                    ttc = new ToolTipConfigBuilder()
//                            .bodyText(bar.getTitle())
//                            .autoHide(false)
//                            .trackMouse(false)
//                            .anchorToTarget(true)
//                            .toolTipConfig;
//                }
//            }
//            ls.setRenderer(focused ? FOCAL_RENDER : NON_FOCAL_RENDER);
//            Seems to have 'sticky' effects that hang around after no longer focused.
//            ls.setLineRenderer(focused ? FOCAL_RENDER : NON_FOCAL_RENDER);
        }
        setLastFocusId(idOrNull);
//        if (chart.getToolTip() != null) chart.getToolTip().hide();
//        chart.setToolTipConfig(ttc);
        chart.redrawChart();
    }

    @Override
    public void onSeriesSelection(SeriesSelectionEvent<Integer> event) {
        String barId = event.getValueProvider().getPath();
        focusBar(barId);
    }

    public void focusPeriod(StartDurn startDurn) {
        this.focusedPeriodOrNull = startDurn;
        replaceBars(prevBars);
    }

    public void unfocusPeriod() {
        focusPeriod(null);
    }

    @Override
    public void onSeriesOverItem(SeriesItemOverEvent<Integer> evt) {
        currentBarIdIfAny = evt.getValueProvider().getPath();
        syncToolTip();
    }

    @Override
    public void onSeriesLeaveItem(SeriesItemOutEvent<Integer> event) {
        currentBarIdIfAny = null;
        syncToolTip();
    }

    protected void setupChart() {
        new ChartBuilder<>(chart)
                .addAxis(new NumericAxisBuilder<Integer>()
                        .position(Position.BOTTOM)
//                        .titleConfig(new TextSprite("Time"))
                        .addField(ACCESS_X)
                        .minimum(0)
                        .interval(2 * HR)
                        .labelProvider(new XLabels())
                        .numericAxis)
                .addAxis(new NumericAxisBuilder<Integer>()
                        .position(Position.LEFT)
                        .titleConfig(new TextSprite("Resources"))
                                // .interval(1) doesn't seem to work.... too many steps!
                        .labelProvider(new YLabels())
                        .maximum(1)
                        .minimum(0)
                                //.hidden(true)... might be useful on occasion
                        .gridEvenConfig(new PathSpriteBuilder()
                                .fill(new RGB("#f8f8f8"))
                                .stroke(new RGB("#f8f8f8"))
                                .strokeWidth(1)
                                .pathSprite)
                        .displayGrid(true)
                        .numericAxis)
        ;
    }

    @Override
    protected void clearChart() {
        super.clearChart();
        clearNumericAxis(Position.LEFT);
        prevBars = newList();
    }

    /**
     * Formal tooltip synchronisation to make it nice and smooth.
     */
    private void syncToolTip() {
        final String altBarId = currentBarIdIfAny;
        Scheduler.get().scheduleDeferred(new Command() {
            @Override
            public void execute() {
                //noinspection StringEquality
                if (altBarId != currentBarIdIfAny) return; // something has changed in the mean time.  Abort.

                BarInfo bar = currentBarIdIfAny == null ? null : getBar(currentBarIdIfAny);

                if (bar == null) {
                    if (chart.getToolTip() != null) chart.getToolTip().disable();
                } else {
                    String body = SafeHtmlUtils.htmlEscape(bar.getTitle());
                    if (chart.getToolTip() == null ||
                            !body.equals(chart.getToolTip().getToolTipConfig().getBodyHtml())) {
                        // need to update tooltip
                        chart.setToolTipConfig(new ToolTipConfigBuilder()
                                .bodyHtml(body)
                                .autoHide(false)
                                .trackMouse(true)
                                .toolTipConfig);
                    }
                    chart.getToolTip().enable();
                    chart.getToolTip().show();
                }
            }
        });
    }

    private BarInfo getBar(String barId) {
        for (BarInfo b : prevBars) {
            if (b.getId().equals(barId)) return b;
        }
        return null;
    }

    private PointSeries pointSeries(StartDurn sd, int y) {
        return PointSeries.NIL.add(
                new Point(sd.getStart(), y),
                new Point(sd.getStart() + sd.getDurn(), y)
        );
    }

    private void replaceResources(List<HasIdTitle> replacements) {
        resources.clear();
        resources.addAll(replacements);
    }

    private void setLastFocusId(String idOrNull) {
        if (!safeEquals(lastFocusIdOrNull, idOrNull)) {
            lastFocusIdOrNull = idOrNull;
            bus.fireEvent(new BarFocusEvent(this, lastFocusIdOrNull));
        }
    }

    // need priming data otherwise chart doesn't show :(
    private void primeChart() {
        ValueProvider<Integer, Double> primer = accessY(PRIMER, Double.NaN);

        // dummy data
        PointSeries dummy = pointSeries(new StartDurn(HR, HR), resourceIndexToValue(0));
        replaceAll(SeriesMap.NIL.put(PRIMER, dummy));

        // add config
        getNumericAxis(Position.LEFT).addField(primer);
        chart.addSeries(createSeries(primer, new RGB("#ffffff")));
    }

    private LineSeries<Integer> createSeries(final ValueProvider<Integer, Double> access, RGB colour) {
        LineSeries<Integer> s = new LineSeriesBuilder<Integer>()
                // Keep marker small
                .markerConfig(new CircleSprite(STROKE_NON_FOCUSED))
                .yAxisPosition(Position.LEFT)
                .yField(access)
                .xAxisPosition(Position.BOTTOM)
                        // needed to orient lines
                .xField(ACCESS_X)
                .stroke(colour)
                        // I think fill is useless (might be legend oriented)
                        // causes strange gray blocks on chart... caused by primer y value '1.0'
//                .fill(RGB.LIGHTGRAY)
                .strokeWidth(STROKE_NON_FOCUSED)
// not necessary                .showMarkers(false)
//                .highlighter(new LineHighlighter())
                // possibly linked to chart 'animation'... doesn't work
//                .lineHighlighter(new LineHighlighter())
//                .highlighting(true)
//                Does nothing.... our series don't have gaps... we want control over colours.
//                .gapless(false)
                        // tighten mouse over / click area
                .selectionTolerance(STROKE_FOCUSED)
                .lineSeries;
        s.addSeriesSelectionHandler(this);
        s.addSeriesItemOverHandler(this);
        s.addSeriesItemOutHandler(this);
        return s;
    }

    private int resourceIndexToValue(int ix) {
        return ix + 1;
    }

    private int numberToResourceIndex(Integer num) {
        return num - 1;
    }

    private HasIdTitle numberToResource(Integer num) {
        int ix = numberToResourceIndex(num);
        return resources.get(ix);
    }

    private Integer resourceToNumber(String id) {
        int index = resourceToIndex(id);
        return resourceIndexToValue(index);
    }

    private int resourceToIndex(String id) {
        if (PRIMER.equals(id)) return 0;

        for (ListIterator<HasIdTitle> iterator = resources.listIterator(); iterator.hasNext(); ) {
            HasIdTitle r = iterator.next();
            if (r.getId().equals(id)) {
                return iterator.previousIndex();
            }
        }
        throw illegalArg("Unknown resource " + id);
    }

    /**
     * Renders labels for the Y axis.
     */
    private class YLabels implements LabelProvider<Number> {
        @Override
        public String getLabel(Number item) {
            int num = item.intValue();
            try {
                return numberToResource(num).getTitle();
            } catch (IndexOutOfBoundsException e) {
                return "";
            }
        }
    }

    private class XLabels implements LabelProvider<Number> {
        @Override
        public String getLabel(Number item) {
            return "+" + item + "mins";
        }
    }
}

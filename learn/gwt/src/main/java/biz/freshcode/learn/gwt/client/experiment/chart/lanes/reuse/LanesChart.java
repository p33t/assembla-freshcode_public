package biz.freshcode.learn.gwt.client.experiment.chart.lanes.reuse;

import biz.freshcode.learn.gwt.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.series.LineSeriesBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.series.SeriesToolTipConfigBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.draw.path.PathSpriteBuilder;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt.reuse.StartDurn;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse.BarFocusEvent;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse.BarInfo;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse.ChartInfo;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse.HasIdTitle;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.PointSeries;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.PointSeriesChart;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.SeriesMap;
import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.event.SeriesSelectionEvent;
import com.sencha.gxt.chart.client.chart.series.LineSeries;
import com.sencha.gxt.chart.client.chart.series.Series;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelProvider;
import com.sencha.gxt.chart.client.chart.series.SeriesRenderer;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt.client.util.AppObjectUtils.safeEquals;
import static biz.freshcode.learn.gwt.client.util.ExceptionUtil.illegalArg;
import static com.sencha.gxt.chart.client.chart.Chart.Position;

public class LanesChart extends PointSeriesChart implements SeriesSelectionEvent.SeriesSelectionHandler<Integer> {
    private static final int HR = 60;
    private static final String PRIMER = "primer";
    public static final int STROKE_NON_FOCUSED = 3;
    public static final int STROKE_FOCUSED = 6;
    public static final SeriesRenderer<ChartElem> FOCAL_RENDER = new FocalRenderer(true);
    public static final SeriesRenderer<ChartElem> NON_FOCAL_RENDER = new FocalRenderer(false);
    private final ValueProvider<Integer, Double> FOCUSED_PERIOD_FIELD = accessY("Focused Period");
    private final List<HasIdTitle> resources = newList();
    private Date zeroTime = new Date();
    private String lastFocusIdOrNull;
    private StartDurn focusedPeriodOrNull;
    private final EventBus bus;
    private Iterable<BarInfo> prevBarsOrNull;

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
        // build a cross reference from old to new index
        final int[] newIndex = new int[resources.size()];
        for (int i = 0; i < newIndex.length; i++) {
            String resourceId = newOrder.get(i).getId();
            int oldIndex = resourceToIndex(resourceId);
            newIndex[oldIndex] = i;
        }

        // TODO: Call replaceAll()
        chart.setAnimated(true);
        // needed for fill?
        chart.setShadowChart(true);

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
        prevBarsOrNull = bars;

        // compile and display data
        SeriesMap map = SeriesMap.NIL;
        for (BarInfo bar : bars) {
            Integer y = resourceToNumber(bar.getResourceId());
            StartDurn sd = bar.getStartDurn();
            map = map.put(bar.getId(), pointSeries(sd, y));
        }

        if (map.isEmpty()) {
            primeChart();
            return;
        }

        NumericAxis<Integer> left = getNumericAxis(Position.LEFT);
        boolean refocus = false;
        for (BarInfo bar : bars) {
            String barId = bar.getId();
            if (barId.equals(lastFocusIdOrNull)) refocus = true;

            ValueProvider<Integer, Double> f = accessY(barId);
            left.addField(f);

            LineSeries<Integer> s = createSeries(f, bar.getColour());
            chart.addSeries(s);
        }

        if (focusedPeriodOrNull != null) {
            left.addField(FOCUSED_PERIOD_FIELD);
            LineSeries<Integer> s = createSeries(FOCUSED_PERIOD_FIELD, RGB.LIGHTGRAY);
            chart.addSeries(s);

            int yMax = resourceIndexToValue(resources.size() - 1);
            PointSeries focus = pointSeries(focusedPeriodOrNull, yMax);
            Point preStepper = new Point(focus.getMinX() - 1, 0);
            Point postStepper = new Point(focus.getMaxX() + 1, 0);
            focus = PointSeries.NIL.add(preStepper).add(focus).add(postStepper);
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
        for (Series<Integer> s : chart.getSeries()) {
            if (!(s instanceof LineSeries)) continue;
            LineSeries<Integer> ls = (LineSeries<Integer>) s;
            String barId = ls.getYField().getPath();
            boolean focused = barId.equals(idOrNull);
            // NOTES: Fill doesn't seem to work, highlight might only be for hover over legend
            // Markers suffer from interpolated points
            ls.setStrokeWidth(focused ? STROKE_FOCUSED : STROKE_NON_FOCUSED);
            ls.setShowMarkers(focused);
//            ls.setRenderer(focused ? FOCAL_RENDER : NON_FOCAL_RENDER);
//            Seems to have 'sticky' effects that hang around after no longer focused.
//            ls.setLineRenderer(focused ? FOCAL_RENDER : NON_FOCAL_RENDER);
        }
        setLastFocusId(idOrNull);
        chart.redrawChart();
    }

    @Override
    public void onSeriesSelection(SeriesSelectionEvent<Integer> event) {
        String barId = event.getValueProvider().getPath();
        focusBar(barId);
    }

    public void focusPeriod(StartDurn startDurn) {
        this.focusedPeriodOrNull = startDurn;
        if (prevBarsOrNull != null) replaceBars(prevBarsOrNull);
        else GWT.log("No previous bars..... TODO!");
    }

    public void unfocusPeriod() {
        focusPeriod(null);
    }

    protected void setupChart() {
        new ChartBuilder<Integer>(chart)
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
        prevBarsOrNull = null;
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
        ValueProvider<Integer, Double> primer = accessY(PRIMER);

        // dummy data
        PointSeries dummy = pointSeries(new StartDurn(HR, HR), resourceIndexToValue(0));
        replaceAll(SeriesMap.NIL.put(PRIMER, dummy));

        // add config
        getNumericAxis(Position.LEFT).addField(primer);
        chart.addSeries(createSeries(primer, new RGB("#000000")));
    }

    private LineSeries<Integer> createSeries(final ValueProvider<Integer, Double> access, RGB colour) {
        LineSeries<Integer> s = new LineSeriesBuilder<Integer>()
                .yAxisPosition(Position.LEFT)
                .yField(access)
                .xAxisPosition(Position.BOTTOM)
                        // Man this is painful
                .toolTipConfig(new SeriesToolTipConfigBuilder<Integer>()
                        .labelProvider(new SeriesLabelProvider<Integer>() {
                            @Override
                            public String getLabel(Integer item, ValueProvider<? super Integer, ? extends Number> provider) {
                                return access.getPath();
                            }
                        })
                        .seriesToolTipConfig)
                        //                                            .highlighter()
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
                .lineSeries;
        s.addSeriesSelectionHandler(this);
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

    private static class FocalRenderer implements SeriesRenderer<ChartElem> {
        private final boolean focused;

        FocalRenderer(boolean focused) {
            this.focused = focused;
        }

        @Override
        public void spriteRenderer(Sprite sprite, int index, ListStore<ChartElem> store) {
            if (focused) {
                sprite.setStroke(RGB.MAGENTA);
                sprite.setStrokeOpacity(.5);
                sprite.setFill(RGB.MAGENTA);
                sprite.setFillOpacity(.5);
            }
        }
    }
}

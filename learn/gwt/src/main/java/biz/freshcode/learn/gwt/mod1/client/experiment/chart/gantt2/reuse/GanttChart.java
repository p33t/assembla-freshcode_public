package biz.freshcode.learn.gwt.mod1.client.experiment.chart.gantt2.reuse;

import biz.freshcode.learn.gwt.mod1.client.experiment.chart.gantt.reuse.StartDurn;
import biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.ChartElem;
import biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.ChartElemChart;
import biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.MapFun;
import biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.SeriesGap;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.chart.series.LineSeriesBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.chart.series.SeriesToolTipConfigBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.draw.path.PathSpriteBuilder;
import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.sencha.gxt.chart.client.chart.Chart;
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
import com.sencha.gxt.core.client.util.PrecisePoint;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import static biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.ChartElem.Access.CE_ACCESS;
import static biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.ChartUtil.interpolate;
import static biz.freshcode.learn.gwt.mod1.client.util.AppCollectionUtil.*;
import static biz.freshcode.learn.gwt.mod1.client.util.AppObjectUtils.safeEquals;
import static biz.freshcode.learn.gwt.mod1.client.util.ExceptionUtil.illegalArg;
import static com.sencha.gxt.chart.client.chart.Chart.Position;

public class GanttChart extends ChartElemChart implements SeriesSelectionEvent.SeriesSelectionHandler<ChartElem> {
    private static final int HR = 60;
    private static final String PRIMER = "primer";
    public static final int STROKE_NON_FOCUSED = 3;
    public static final int STROKE_FOCUSED = 6;
    public static final SeriesRenderer<ChartElem> FOCAL_RENDER = new FocalRenderer(true);
    public static final SeriesRenderer<ChartElem> NON_FOCAL_RENDER = new FocalRenderer(false);
    public static final ChartElem.AccessY FOCUSED_PERIOD_FIELD = new ChartElem.AccessY("Focused Period");
    private final List<HasIdTitle> resources = newList();
    private Date zeroTime = new Date();
    private String lastFocusIdOrNull;
    private StartDurn focusedPeriodOrNull;
    private final EventBus bus;
    private Iterable<BarInfo> prevBarsOrNull;

    public GanttChart() {
        this(new SimpleEventBus());
    }

    public GanttChart(EventBus bus) {
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

        MapFun<Double, Double> reorder = new MapFun<Double, Double>() {
            @Override
            public Double map(Double input) {
                int yVal = (int) Math.round(input);
                // special handling for focus period series
                if (yVal == 0) return 0.0;
                int ixOld = numberToResourceIndex(yVal);
                int ixNew = newIndex[ixOld];
                return (double) resourceIndexToValue(ixNew);
            }
        };
        ListStore<ChartElem> store = chart.getStore();
        List<ChartElem> updated = newList();
        for (ChartElem e : store.getAll()) {
            updated.add(e.mapY(reorder));
        }

        replaceResources(newOrder);
        store.replaceAll(updated);
        chart.setAnimated(true);
        // needed for fill?
        chart.setShadowChart(true);

        chart.redrawChart();
    }

    public void configure(ChartInfo info) {
        clearChart();
        replaceResources(info.getResources());
        zeroTime = info.getZeroTime();

        NumericAxis<ChartElem> top = getNumericAxis(Position.BOTTOM);
        top.setMaximum(info.getWindowSize());

        primeChart();

        NumericAxis<ChartElem> left = getNumericAxis(Position.LEFT);
        int resourceCount = info.getResources().size();
        left.setMaximum(Math.max(1, resourceIndexToValue(resourceCount - 1)));
        left.setSteps(Math.max(1, resourceCount));

        chart.redrawChart();
        setLastFocusId(null);
    }

    public void replaceBars(Iterable<BarInfo> bars) {
        clearChart();
        prevBarsOrNull = bars;
        Map<String, List<PrecisePoint>> map = newMap();
        for (BarInfo bar : bars) {
            Integer y = resourceToNumber(bar.getResourceId());
            StartDurn sd = bar.getStartDurn();
            map.put(bar.getId(), pointSeries(sd, y));
        }

        if (map.isEmpty()) {
            primeChart();
            return;
        }

        NumericAxis<ChartElem> left = getNumericAxis(Position.LEFT);
        boolean refocus = false;
        for (BarInfo bar : bars) {
            String barId = bar.getId();
            if (barId.equals(lastFocusIdOrNull)) refocus = true;

            ChartElem.AccessY f = new ChartElem.AccessY(barId);
            left.addField(f);

            LineSeries<ChartElem> s = createSeries(f, bar.getColour());
            chart.addSeries(s);
        }

        if (focusedPeriodOrNull != null) {
            left.addField(FOCUSED_PERIOD_FIELD);
//            Does not show up ?!
//            AreaSeries<ChartElem> s = new AreaSeriesBuilder<ChartElem>()
//                    .yAxisPosition(Position.LEFT)
//                    .xAxisPosition(Position.BOTTOM)
//                    .xField(CE_ACCESS.x())
//                    .addYField(0, FOCUSED_PERIOD_FIELD)
//                    .addColor(0, RGB.LIGHTGRAY)
//                    .stroke(RGB.BLACK)
//                    .strokeWidth(3)
//                    .areaSeries;

//            Doesn't want to be filled in ?
//            LineSeries<ChartElem> s = new LineSeriesBuilder<ChartElem>()
//                    .yAxisPosition(Position.LEFT)
//                    .yField(FOCUSED_PERIOD_FIELD)
//                    .xAxisPosition(Position.BOTTOM)
//                    .xField(CE_ACCESS.x())
//                    .stroke(RGB.GRAY)
//                    .fill(RGB.LIGHTGRAY)
//                    .lineSeries;

            LineSeries<ChartElem> s = createSeries(FOCUSED_PERIOD_FIELD, RGB.LIGHTGRAY);
            chart.addSeries(s);
            map.put(FOCUSED_PERIOD_FIELD.getPath(), pointSeries(focusedPeriodOrNull, .4));
        }

        List<ChartElem> interpolated = interpolate(map, SeriesGap.GAPS);
        chart.getStore().addAll(interpolated);

        if (refocus) focusBar(lastFocusIdOrNull);
        else focusBar(null);
    }

    public void unfocus() {
        focusBar(null);
    }

    public void focusBar(String idOrNull) {
        Chart<ChartElem> ch = chart;
        for (Series<ChartElem> s : ch.getSeries()) {
            if (!(s instanceof LineSeries)) continue;
            LineSeries<ChartElem> ls = (LineSeries<ChartElem>) s;
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
        ch.redrawChart();
    }

    @Override
    public void onSeriesSelection(SeriesSelectionEvent<ChartElem> event) {
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
        new ChartBuilder<ChartElem>(chart)
                .addAxis(new NumericAxisBuilder<ChartElem>()
                        .position(Position.BOTTOM)
//                        .titleConfig(new TextSprite("Time"))
                        .addField(ChartElem.Access.CE_ACCESS.x())
                        .minimum(0)
                        .interval(2 * HR)
                        .labelProvider(new XLabels())
                        .numericAxis)
                .addAxis(new NumericAxisBuilder<ChartElem>()
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

    private List<PrecisePoint> pointSeries(StartDurn sd, Number y) {
        return newListFrom(
                new PrecisePoint(sd.getStart(), y.doubleValue()),
                new PrecisePoint(sd.getStart() + sd.getDurn(), y.doubleValue())
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
        ChartElem.AccessY primer = new ChartElem.AccessY(PRIMER);

        // dummy data
        ChartElem p1 = new ChartElem(60, Double.NaN);
        p1.setY(primer.getPath(), 1.0);
        chart.getStore().add(p1);

        // add config
        getNumericAxis(Position.LEFT).addField(primer);
        chart.addSeries(createSeries(primer, new RGB("#000000")));
    }

    private LineSeries<ChartElem> createSeries(final ChartElem.AccessY access, RGB colour) {
        LineSeries<ChartElem> s = new LineSeriesBuilder<ChartElem>()
                .yAxisPosition(Position.LEFT)
                .yField(access)
                .xAxisPosition(Position.BOTTOM)
                        // Man this is painful
                .toolTipConfig(new SeriesToolTipConfigBuilder<ChartElem>()
                        .labelProvider(new SeriesLabelProvider<ChartElem>() {
                            @Override
                            public String getLabel(ChartElem item, ValueProvider<? super ChartElem, ? extends Number> provider) {
                                return access.getPath();
                            }
                        })
                        .seriesToolTipConfig)
                        //                                            .highlighter()
                        // needed to orient lines
                .xField(CE_ACCESS.x())
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

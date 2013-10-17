package biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse;

import biz.freshcode.learn.gwt.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.series.LineSeriesBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.series.SeriesToolTipConfigBuilder;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt.reuse.StartDurn;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.LineSeries;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelProvider;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.PrecisePoint;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import static biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem.Access.ACCESS;
import static biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartUtil.interpolate;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.*;
import static biz.freshcode.learn.gwt.client.util.ExceptionUtil.illegalArg;
import static com.sencha.gxt.chart.client.chart.Chart.Position;

public class GanttChart extends Composite {
    private static final int HR = 60;
    private static final double LEFT_MIN = -1;
    private static final String PRIMER = "primer";
    private final List<HasIdTitle> resources = newList();
    private Date zeroTime = new Date();

    public GanttChart() {
        initWidget(new ChartBuilder<ChartElem>()
                .store(new ListStore<ChartElem>(ACCESS.xKey()))
                .addAxis(new NumericAxisBuilder<ChartElem>()
                        .position(Position.TOP)
                        .titleConfig(new TextSprite("Time"))
                        .addField(ChartElem.Access.ACCESS.x())
                        .minimum(0)
                        .interval(2 * HR)
                        .labelProvider(new XLabels())
                        .numericAxis)
                .addAxis(new NumericAxisBuilder<ChartElem>()
                        .position(Position.LEFT)
                        .titleConfig(new TextSprite("Resources"))
//                        .interval(1) doesn't seem to work.... too many steps!
                        .labelProvider(new YLabels())
                        .maximum(0)
                        .minimum(LEFT_MIN)
                        //.hidden(true)... might be useful on occasion
                        .numericAxis)
                .chart
        );
        primeChart();
    }

    public void configure(ChartInfo info) {
        clearChart(true);
        resources.addAll(info.getResources());
        zeroTime = info.getZeroTime();

        Chart<ChartElem> ch = getWidget();
        ch.setTitle(info.getTitle());

        NumericAxis<ChartElem> top = getNumericAxis(Position.TOP);
        top.setMaximum(info.getWindowSize());

        primeChart();

        NumericAxis<ChartElem> left = getNumericAxis(Position.LEFT);
        int resourceCount = info.getResources().size();
        left.setMinimum(Math.min(LEFT_MIN, resourceIndexToValue(resourceCount)));
        left.setSteps(resourceCount + 1);

        // necessary?
        ch.redrawChart();
    }

    public void replaceBars(Iterable<BarInfo> bars) {
        clearChart(false);
        Map<String, List<PrecisePoint>> map = newMap();
        for (BarInfo bar : bars) {
            Integer y = resourceToValue(bar.getResourceId());
            StartDurn sd = bar.getStartDurn();
            map.put(bar.getId(), newListFrom(
                    new PrecisePoint(sd.getStart(), y),
                    new PrecisePoint(sd.getStart() + sd.getDurn(), y)
            ));
        }

        if (map.isEmpty()) {
            primeChart();
            return;
        }

        Chart<ChartElem> ch = getWidget();
        NumericAxis<ChartElem> left = getNumericAxis(Position.LEFT);

        for (BarInfo bar : bars) {
            ChartElem.AccessY f = new ChartElem.AccessY(bar.getId());
            left.addField(f);

            LineSeries<ChartElem> s = createSeries(f, bar.getColour());
            ch.addSeries(s);
        }

        List<ChartElem> interpolated = interpolate(map);
        ch.getStore().addAll(interpolated);

        // necessary?
        ch.redrawChart();
    }

    @Override
    protected Chart<ChartElem> getWidget() {
        //noinspection unchecked
        return (Chart<ChartElem>) super.getWidget();
    }

    private void clearChart(boolean clearResources) {
        Chart<ChartElem> ch = getWidget();
        ch.getStore().clear();
        ch.getSeries().clear();
        if (clearResources) resources.clear();

        NumericAxis<ChartElem> left = getNumericAxis(Position.LEFT);
        left.getFields().clear();
    }

    // need priming data otherwise chart doesn't show :(
    private void primeChart() {
        ChartElem.AccessY primer = new ChartElem.AccessY(PRIMER);
        Chart<ChartElem> ch = getWidget();

        // dummy data
        ListStore<ChartElem> store = ch.getStore();
        ChartElem p1 = new ChartElem(60);
        p1.setY(primer.getPath(), 1.0);
        store.add(p1);

        // add config
        getNumericAxis(Position.LEFT).addField(primer);
        ch.addSeries(createSeries(primer, new RGB("#000000")));
    }

    private LineSeries<ChartElem> createSeries(final ChartElem.AccessY access, RGB colour) {
        return new LineSeriesBuilder<ChartElem>()
                .yAxisPosition(Position.LEFT)
                .yField(access)
                .xAxisPosition(Position.TOP)
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
                .xField(ACCESS.x())
                .stroke(colour)
                .strokeWidth(20)
                .showMarkers(false)
                .gapless(false)
                .lineSeries;
    }

    private NumericAxis<ChartElem> getNumericAxis(Position posn) {
        //noinspection unchecked
        return (NumericAxis<ChartElem>) getWidget().getAxis(posn);
    }

    private int resourceIndexToValue(int ix) {
        return -(ix + 1);
    }

    private int numberToResourceIndex(Integer num) {
        return -num - 1;
    }

    private HasIdTitle numberToResource(Integer num) {
        int ix = numberToResourceIndex(num);
        return resources.get(ix);
    }

    private Integer resourceToValue(String id) {
        if (PRIMER.equals(id)) return resourceIndexToValue(0);

        for (ListIterator<HasIdTitle> iterator = resources.listIterator(); iterator.hasNext(); ) {
            HasIdTitle r = iterator.next();
            if (r.getId().equals(id)) {
                int ix = iterator.previousIndex();
                int value = resourceIndexToValue(ix);
                GWT.log("Resource " + id + " has value " + value);
                return value;
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
                return "N/A";
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
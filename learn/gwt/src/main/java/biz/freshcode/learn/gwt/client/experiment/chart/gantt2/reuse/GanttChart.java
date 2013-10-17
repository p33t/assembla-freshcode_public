package biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse;

import biz.freshcode.learn.gwt.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.series.LineSeriesBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.series.SeriesToolTipConfigBuilder;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt.reuse.StartDurn;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem;
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
    private static final double LEFT_MIN = -1.5;
    private final List<HasIdTitle> resources = newList();
    private Date zeroTime = new Date();

    public GanttChart() {
        ChartElem.AccessY primer = new ChartElem.AccessY("primer");
        ListStore<ChartElem> store = new ListStore<ChartElem>(ACCESS.xKey());

        ChartElem p1 = new ChartElem(60);
        p1.setY("primer", 1.0);
        store.add(p1);

        ChartElem p2 = new ChartElem(120);
        p2.setY("primer", 1.0);
        store.add(p2);

        initWidget(new ChartBuilder<ChartElem>()
                .store(store)
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
                        .interval(1)
                        .addField(primer)
                        .labelProvider(new YLabels())
                        .maximum(-0.5)
                        .minimum(LEFT_MIN)
                        //.hidden(true)... might be useful on occasion
                        .numericAxis)
                .addSeries(createSeries(primer))
                .chart
        );
    }

    private LineSeries<ChartElem> createSeries(final ChartElem.AccessY access) {
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
                .stroke(new RGB("#000000"))
                .strokeWidth(20)
                .showMarkers(false)
                .gapless(false)
                .lineSeries;
    }

    public void configure(ChartInfo info) {
        Chart<ChartElem> ch = getWidget();
        ch.setTitle(info.getTitle());
        NumericAxis<ChartElem> top = getNumericAxis(Position.TOP);
        top.setMaximum(info.getWindowSize());
        zeroTime = info.getZeroTime();
        ch.getStore().clear();
        NumericAxis<ChartElem> left = getNumericAxis(Position.LEFT);
        left.setMinimum(Math.min(LEFT_MIN, -(resources.size() + 0.5)));
        resources.clear();
        resources.addAll(info.getResources());
        ch.redrawChart();
    }

    public void replaceBars(Iterable<BarInfo> bars) {
        Map<String, List<PrecisePoint>> map = newMap();
        for (BarInfo bar : bars) {
            Integer y = resourceToValue(bar.getResourceId());
            StartDurn sd = bar.getStartDurn();
            map.put(bar.getId(), newListFrom(
                    new PrecisePoint(sd.getStart(), y),
                    new PrecisePoint(sd.getStart() + sd.getDurn(), y)
            ));
        }
        List<ChartElem> interpolated = interpolate(map);

        NumericAxis<ChartElem> top = getNumericAxis(Position.LEFT);
        List<ChartElem.AccessY> fields = newList();
        Chart<ChartElem> ch = getWidget();
//        List<Series<ChartElem>> series = ch.getSeries();
//        for (BarInfo bar: bars) {
//
//        }
        // reuse the fields
        // TODO
    }

    @Override
    protected Chart<ChartElem> getWidget() {
        //noinspection unchecked
        return (Chart<ChartElem>) super.getWidget();
    }

    private NumericAxis<ChartElem> getNumericAxis(Position posn) {
        //noinspection unchecked
        return (NumericAxis<ChartElem>) getWidget().getAxis(posn);
    }

    private int resourceIndexToValue(int ix) {
        return -(ix + 1);
    }

    private int numberToResourceIndex(Integer num) {
        return -(num - 1);
    }

    private HasIdTitle numberToResource(Integer num) {
        int ix = numberToResourceIndex(num);
        return resources.get(ix);
    }

    private Integer resourceToValue(String id) {
        for (ListIterator<HasIdTitle> iterator = resources.listIterator(); iterator.hasNext(); ) {
            HasIdTitle r = iterator.next();
            if (r.getId().equals(id)) {
                int ix = iterator.previousIndex();
                return resourceIndexToValue(ix);
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
            try {
                return numberToResource(item.intValue()).getTitle();
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

package biz.freshcode.learn.gwt.client.experiment.chart.gantt;

import biz.freshcode.learn.gwt.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.LegendBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.series.LineSeriesBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt.reuse.GanttBar;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt.reuse.GanttInfo;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt.reuse.StartDurn;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem;
import biz.freshcode.learn.gwt.client.uispike.builder.Construct;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.series.SeriesToolTipConfig;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.util.PrecisePoint;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem.Access.ACCESS;
import static biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartUtil.interpolate;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.*;
import static java.lang.Math.abs;

public class GanttDemo extends AbstractIsWidget<BorderLayoutContainer> {
    private static final int HR = 60;

    @Override
    protected BorderLayoutContainer createWidget() {
        final GanttInfo info = createData();

        ListStore<ChartElem> store = new ListStore<ChartElem>(ACCESS.xKey());
        final List<String> order = newList();
        for (GanttBar bar : info.getBars()) {
            if (!order.contains(bar.getResource())) order.add(bar.getResource());
        }

        List<ChartElem> data = constructSeries(info, order);
        GWT.log("Data: " + data);
        store.addAll(data);

        final List<ChartElem.AccessY> fields = newList();
        LabelProvider<Number> yLabels = new LabelProvider<Number>() {
            @Override
            public String getLabel(Number item) {
                int ix = abs(item.intValue() + 1);
                if (ix < order.size()) return order.get(ix);
                return ""; //"Missing " + ix;
            }
        };
        LabelProvider<Number> xLabels = new LabelProvider<Number>() {
            @Override
            public String getLabel(Number item) {
                return "+" + item.intValue() + "mins";
            }
        };
        return new BorderLayoutContainerBuilder()
                .centerWidget(new ChartBuilder<ChartElem>()
                        .store(store)
                        .addAxis(new NumericAxisBuilder<ChartElem>()
                                .position(Chart.Position.TOP)
                                .titleConfig(new TextSprite("Time"))
                                .addField(ChartElem.Access.ACCESS.x())
                                .labelProvider(xLabels)
                                .numericAxis)
//                                .maximum(info.getWindowSize())
                        .addAxis(new NumericAxisBuilder<ChartElem>()
                                .position(Chart.Position.LEFT)
                                .titleConfig(new TextSprite("Second Axis"))
                                .construct(new Construct<NumericAxisBuilder<ChartElem>>() {
                                    @Override
                                    public void run() {
                                        // need to list them all here
                                        for (GanttBar bar : info.getBars()) {
                                            ChartElem.AccessY field = new ChartElem.AccessY(bar.getId());
                                            fields.add(field);
                                            builder.addField(field);
                                        }
                                    }
                                })
                                .labelProvider(yLabels)
                                .maximum(0)
                                .minimum(-order.size() - 1)
                                //.hidden(true)... might be useful on occasion
                                .numericAxis)
                        .construct(new Construct<ChartBuilder<ChartElem>>() {
                            @Override
                            public void run() {
                                for (ChartElem.AccessY field : fields) {
                                    GanttBar bar = info.getBar(field.getPath());
                                    builder.addSeries(new LineSeriesBuilder<ChartElem>()
                                            .yAxisPosition(Chart.Position.LEFT)
                                            .yField(field)
                                            .xAxisPosition(Chart.Position.TOP)
//                                            .toolTipConfig(new SeriesToolTipConfig<ChartElem>())
                                                    // needed to orient lines
                                            .xField(ChartElem.Access.ACCESS.x())
                                            .stroke(new RGB(bar.getColour()))
                                            .strokeWidth(20)
                                            .showMarkers(false)
//                                            .markerConfig(new PathSpriteBuilder(Primitives.diamond(0, 0, 5))
//                                                    .fill(new RGB("#228822"))
//                                                    .pathSprite)
                                            .gapless(false)
                                            .lineSeries);
                                }
                            }
                        })
                        .legend(new LegendBuilder<ChartElem>()
                                .position(Chart.Position.RIGHT)
                                .itemHighlighting(true)
                                .itemHiding(true)
                                .legend)
                        .chart)
                .borderLayoutContainer;
    }

    private List<ChartElem> constructSeries(GanttInfo info, List<String> order) {
        Map<String, List<PrecisePoint>> series = newMap();
        for (GanttBar bar : info.getBars()) {
            int ix = order.indexOf(bar.getResource());
            StartDurn sd = bar.getStartDurn();
            // using negatives
            int y = -ix - 1;
            series.put(bar.getId(), newListFrom(
                    new PrecisePoint(sd.getStart(), y),
                    new PrecisePoint(sd.getStart() + sd.getDurn(), y)
            ));
        }
        return interpolate(series);
    }

    private GanttInfo createData() {
        List<GanttBar> bars = newListFrom(
                new GanttBar("id1", "Alpha", "Maintenance", "#ff0000", new StartDurn(6 * HR, 4 * HR)),
                new GanttBar("id2", "Bravo", "Minow Inbound", "#00ff00", new StartDurn(8 * HR, 3 * HR)),
                new GanttBar("id3", "Bravo", "Whisky & Coke Outbound", "#0000ff", new StartDurn(12 * HR, 3 * HR))
        );
        return new GanttInfo(bars, new Date(), 24 * HR, "Today");
    }
}

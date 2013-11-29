package biz.freshcode.learn.gwt.client.experiment.chart.mixed;

import biz.freshcode.learn.gwt.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.series.LineSeriesBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.AbstractChart;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartUtil;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.SeriesGap;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.core.client.util.PrecisePoint;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.List;
import java.util.Map;

import static biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem.Access.CE_ACCESS;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newMap;
import static com.sencha.gxt.chart.client.chart.Chart.Position;

public class MixedChartDemo extends AbstractIsWidget<BorderLayoutContainer> {
    private MyChart myChart;

    @Override
    protected BorderLayoutContainer createWidget() {
        return new BorderLayoutContainerBuilder()
                .northWidget(new HorizontalLayoutContainerBuilder()
                        .add(new TextButton("Go", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                myChart.go();
                            }
                        }))
                        .horizontalLayoutContainer)
                .centerWidget(myChart = new MyChart())
                .borderLayoutContainer;
    }

    private static class MyChart extends AbstractChart {
        public static final String L1 = "L1";
        public static final ChartElem.AccessY L1_ACC = new ChartElem.AccessY(L1);

        void go() {
            Map<String, List<PrecisePoint>> map = newMap();
            map.put(L1, newListFrom(new PrecisePoint(1, 20), new PrecisePoint(5, 43)));
            chart.getStore().addAll(ChartUtil.interpolate(map, SeriesGap.ZERO_DEF));

            chart.addSeries(new LineSeriesBuilder<ChartElem>()
                    .fill(RGB.LIGHTGRAY)
                    .stroke(RGB.GRAY)
                    .xAxisPosition(Position.BOTTOM)
                    .xField(CE_ACCESS.x())
                    .yAxisPosition(Position.LEFT)
                    .yField(L1_ACC)
                    .lineSeries);
            chart.redrawChart();
        }

        @Override
        protected void clearChart() {
            super.clearChart();
            clearNumericAxis(Position.LEFT);
        }

        @Override
        protected void setupChart(ChartBuilder<ChartElem> builder) {
            builder
            .addAxis(new NumericAxisBuilder<ChartElem>()
                    .position(Position.LEFT)
                    .addField(L1_ACC)
                    .maximum(100)
                    .minimum(0)
                    .steps(10)
                    .numericAxis)
            .addAxis(new NumericAxisBuilder<ChartElem>()
                    .position(Position.BOTTOM)
                    .addField(CE_ACCESS.x())
                    .maximum(10)
                    .minimum(0)
                    .steps(10)
                    .numericAxis)
            ;
        }
    }
}

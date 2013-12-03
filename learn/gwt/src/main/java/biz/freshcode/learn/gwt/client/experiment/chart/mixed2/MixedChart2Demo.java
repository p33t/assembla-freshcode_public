package biz.freshcode.learn.gwt.client.experiment.chart.mixed2;

import biz.freshcode.learn.gwt.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.series.LineSeriesBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.PointSeries;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.PointSeriesChart;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.SeriesMap;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import static com.sencha.gxt.chart.client.chart.Chart.Position;

public class MixedChart2Demo extends AbstractIsWidget<BorderLayoutContainer> {
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

    private static class MyChart extends PointSeriesChart {
        public static final String L1 = "L1";
        public static final String L2 = "L2";
        public static final int X_MIN = 0;
        public static final int X_MAX = 100;
        private final ValueProvider<Integer, Double> L1_ACC = accessY(L1);
        private final ValueProvider<Integer, Double> L2_ACC = accessY(L2);

        private MyChart() {
            new ChartBuilder<Integer>(chart)
                    .addAxis(new NumericAxisBuilder<Integer>()
                            .position(Position.LEFT)
                            .addField(L1_ACC)
                            .addField(L2_ACC)
                            .maximum(100)
                            .minimum(0)
                            .steps(10)
                            .numericAxis)
                    .addAxis(new NumericAxisBuilder<Integer>()
                            .position(Position.BOTTOM)
                            .addField(SeriesMap.ACCESS_X)
                            .maximum(X_MAX)
                            .minimum(X_MIN)
                            .steps(10)
                            .numericAxis)
            ;
        }

        @Override
        protected void clearChart() {
            super.clearChart();
            clearNumericAxis(Position.LEFT);
        }

        private void setupChart() {
            new ChartBuilder<Integer>(chart)
                    .addAxis(new NumericAxisBuilder<Integer>()
                            .position(Position.LEFT)
                            .addField(L1_ACC)
                            .addField(L2_ACC)
                            .maximum(100)
                            .minimum(0)
                            .steps(10)
                            .numericAxis)
                    .addAxis(new NumericAxisBuilder<Integer>()
                            .position(Position.BOTTOM)
                            .addField(SeriesMap.ACCESS_X)
                            .maximum(X_MAX)
                            .minimum(X_MIN)
                            .steps(10)
                            .numericAxis)
            ;
        }

        void go() {
            SeriesMap map = SeriesMap.NIL
                    .put(L1, PointSeries.NIL.add(new Point(10, 20), new Point(50, 43)).stepify(X_MIN, X_MAX))
                    .put(L2, PointSeries.NIL.add(new Point(40, 50), new Point(70, 31)));
            replaceAll(map);

            chart.addSeries(new LineSeriesBuilder<Integer>()
                    .fill(RGB.LIGHTGRAY)
                    .stroke(RGB.GRAY)
                    .xAxisPosition(Position.BOTTOM)
                    .xField(SeriesMap.ACCESS_X)
                    .yAxisPosition(Position.LEFT)
                    .yField(L1_ACC)
                    .lineSeries);

            chart.addSeries(new LineSeriesBuilder<Integer>()
                    .stroke(RGB.BLUE)
                    .xAxisPosition(Position.BOTTOM)
                    .xField(SeriesMap.ACCESS_X)
                    .yAxisPosition(Position.LEFT)
                    .yField(L2_ACC)
                    .lineSeries);

            chart.redrawChart();
        }
    }
}

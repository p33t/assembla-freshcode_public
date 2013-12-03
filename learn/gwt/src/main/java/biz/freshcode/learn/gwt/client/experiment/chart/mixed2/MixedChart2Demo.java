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
import com.google.inject.Provider;
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

    private static class MyChart extends PointSeriesChart implements Provider<SeriesMap> {
        public static final String L1 = "L1";
        public static final String L2 = "L2";
        private final ValueProvider<Integer, Double> L1_ACC = SeriesMap.accessY(L1, this);
        private final ValueProvider<Integer, Double> L2_ACC = SeriesMap.accessY(L2, this);
        private SeriesMap map = null;

        private MyChart() {
            setupChart();
        }

        @Override
        public SeriesMap get() {
            return map;
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
                            .maximum(10)
                            .minimum(0)
                            .steps(10)
                            .numericAxis)
            ;
        }

        void go() {
            map = SeriesMap.NIL
                    .put(L1, PointSeries.NIL.add(new Point(1, 20), new Point(5, 43)))
                    .put(L2, PointSeries.NIL.add(new Point(4, 50), new Point(7, 31)));
            chart.getStore().clear();
            chart.getStore().addAll(map.allXs());

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

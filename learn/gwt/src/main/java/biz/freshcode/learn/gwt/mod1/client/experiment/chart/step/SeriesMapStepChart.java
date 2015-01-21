package biz.freshcode.learn.gwt.mod1.client.experiment.chart.step;

import biz.freshcode.learn.gwt.mod1.client.builder.Construct;
import biz.freshcode.learn.gwt.mod1.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt.mod1.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt.mod1.client.builder.gxt.chart.series.AreaSeriesBuilder;
import biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.ChartUtil;
import biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.PointSeries;
import biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.SeriesMap;
import biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.SeriesMapChart;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;

import java.util.Map;

import static com.sencha.gxt.chart.client.chart.Chart.Position;

public class SeriesMapStepChart extends SeriesMapChart {
    public SeriesMapStepChart() {
        setupChart();
    }

    public void display(final Map<String, PointSeries> pss) {
        clearChart();

        int maxX = Integer.MIN_VALUE;
        for (String key : pss.keySet()) {
            PointSeries ps = pss.get(key);
            maxX = Math.max(ps.getMaxX(), maxX);
        }

        SeriesMap sm = SeriesMap.NIL;
        for (String key : pss.keySet()) {
            PointSeries stepPlot = ChartUtil.areaChartPrep(pss.get(key), 0, maxX);
            sm = sm.put(key, stepPlot);
        }

        final NumericAxis<Integer> left = getNumericAxis(Position.LEFT);
        chart.addSeries(new AreaSeriesBuilder<Integer>()
                .yAxisPosition(Position.LEFT)
                        // needed for series to pull all x-values from store
                .xField(ACCESS_X)
                .construct(new Construct<AreaSeriesBuilder<Integer>>() {
                    @Override
                    public void run() {
                        int ix = 0;
                        for (String key : pss.keySet()) {
                            ValueProvider<Integer, Double> field = accessY(key, 0.0);
                            left.addField(field);
                            builder.addYField(field);
                            builder.addColor(ix, colour(ix));
                            ix++;
                        }
                    }
                })
                .areaSeries);

        replaceAll(sm);
        chart.redrawChart();
    }

    public void redraw() {
        chart.redrawChart();
    }

    @Override
    protected void clearChart() {
        super.clearChart();
        clearNumericAxis(Position.LEFT);
    }

    private void setupChart() {
        new ChartBuilder<Integer>(chart)
                .addAxis(new NumericAxisBuilder<Integer>()
                        .position(Position.BOTTOM)
                        .minimum(0)
                        .titleConfig(new TextSprite("Time"))
                        .addField(ACCESS_X)
                        .numericAxis)
                .addAxis(new NumericAxisBuilder<Integer>()
                        .position(Position.LEFT)
                        .titleConfig(new TextSprite("Count"))
                        .numericAxis);
    }
}

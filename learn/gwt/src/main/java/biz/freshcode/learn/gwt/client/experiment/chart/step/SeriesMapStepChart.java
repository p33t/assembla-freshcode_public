package biz.freshcode.learn.gwt.client.experiment.chart.step;

import biz.freshcode.learn.gwt.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.series.AreaSeriesBuilder;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartUtil;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.PointSeries;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.SeriesMap;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.SeriesMapChart;
import biz.freshcode.learn.gwt.client.uispike.builder.Construct;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;

import java.util.Map;
import java.util.Set;

import static com.sencha.gxt.chart.client.chart.Chart.Position;

public class SeriesMapStepChart extends SeriesMapChart {
    public SeriesMapStepChart() {
        setupChart();
    }

    public void display(Map<String, PointSeries> pss) {
        clearChart();

        int maxX = Integer.MIN_VALUE;
        SeriesMap sm = SeriesMap.NIL;
        for (String key : pss.keySet()) {
            PointSeries ps = pss.get(key);
            sm = sm.put(key, ps);
            maxX = Math.max(ps.getMaxX(), maxX);
        }

        SeriesMap stepped = SeriesMap.NIL;
        for (String key : sm.keySet()) {
            PointSeries stepPlot = ChartUtil.areaChartPrep(sm.get(key), 0, maxX);
            stepped.put(key, stepPlot);
        }
        stepped.replaceAll(chart.getStore());

        final Set<String> keys = stepped.keySet();
        final NumericAxis<Integer> left = getNumericAxis(Position.LEFT);
        chart.addSeries(new AreaSeriesBuilder<Integer>()
                .yAxisPosition(Position.LEFT)
                        // needed for series to pull all x-values from store
                .xField(ACCESS_X)
                .construct(new Construct<AreaSeriesBuilder<Integer>>() {
                    @Override
                    public void run() {
                        int ix = 0;
                        for (String key : keys) {
                            ValueProvider<Integer,Double> field = accessY(key);
                            left.addField(field);
                            builder.addYField(field);
                            builder.addColor(ix, colour(ix));
                            ix++;
                        }
                    }
                })
                .areaSeries);
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
                        .titleConfig(new TextSprite("Time"))
                        .addField(ACCESS_X)
                        .numericAxis)
                .addAxis(new NumericAxisBuilder<Integer>()
                        .position(Position.LEFT)
                        .titleConfig(new TextSprite("Count"))
                        .numericAxis);
    }
}

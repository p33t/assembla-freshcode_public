package biz.freshcode.learn.gwt.client.experiment.chart.step;

import biz.freshcode.learn.gwt.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.series.AreaSeriesBuilder;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse.AbstractChart;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem;
import biz.freshcode.learn.gwt.client.experiment.chart.step.reuse.PointSeries;
import biz.freshcode.learn.gwt.client.experiment.chart.step.reuse.StepChartUtil;
import biz.freshcode.learn.gwt.client.uispike.builder.IterConstruct;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;

import java.util.List;
import java.util.Map;

import static biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem.Access.CE_ACCESS;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;
import static com.sencha.gxt.chart.client.chart.Chart.Position;

public class MyStepChart extends AbstractChart {
    @Override
    protected void setupChart(ChartBuilder<ChartElem> builder) {
        builder
                .addAxis(new NumericAxisBuilder<ChartElem>()
                        .position(Position.BOTTOM)
                        .titleConfig(new TextSprite("Time"))
                        .addField(CE_ACCESS.x())
                        .numericAxis)
                .addAxis(new NumericAxisBuilder<ChartElem>()
                        .position(Position.LEFT)
                        .titleConfig(new TextSprite("Count"))
                        .numericAxis);
    }

    @Override
    protected void clearChart() {
        super.clearChart();
        clearNumericAxis(Position.LEFT);
    }

    public void display(Map<String, PointSeries> pss) {
        clearChart();

        List<ChartElem> items = StepChartUtil.prepAndInterpolate(pss);
        chart.getStore().replaceAll(items);

        List<ChartElem.AccessY> fields = newList();
        for (String key : pss.keySet()) fields.add(new ChartElem.AccessY(key));

        NumericAxis<ChartElem> left = getNumericAxis(Position.LEFT);
        for (ChartElem.AccessY f : fields) left.addField(f);

        chart.addSeries(new AreaSeriesBuilder<ChartElem>()
                .yAxisPosition(Position.LEFT)
                        // needed for series to pull all x-values from store
                .xField(CE_ACCESS.x())
                .construct(new IterConstruct<AreaSeriesBuilder<ChartElem>, ChartElem.AccessY>(fields) {
                    private int ix;

                    @Override
                    public void runElem(ChartElem.AccessY elem) {
                        builder.addYField(elem);
                        builder.addColor(ix, colour(ix));
                        ix++;
                    }
                })
                .areaSeries);
        chart.redrawChart();
    }
}

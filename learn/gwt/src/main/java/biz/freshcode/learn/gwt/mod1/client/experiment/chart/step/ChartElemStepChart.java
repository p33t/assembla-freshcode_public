package biz.freshcode.learn.gwt.mod1.client.experiment.chart.step;

import biz.freshcode.learn.gwt.mod1.client.builder.IterConstruct;
import biz.freshcode.learn.gwt.mod1.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt.mod1.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt.mod1.client.builder.gxt.chart.series.AreaSeriesBuilder;
import biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.ChartElem;
import biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.ChartElemChart;
import biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.ChartUtil;
import biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.PointSeries;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;

import java.util.List;
import java.util.Map;

import static biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse.ChartElem.Access.CE_ACCESS;
import static biz.freshcode.learn.gwt.mod1.client.util.AppCollectionUtil.newList;
import static com.sencha.gxt.chart.client.chart.Chart.Position;

public class ChartElemStepChart extends ChartElemChart {
    public ChartElemStepChart() {
        setupChart();
    }

    public void display(Map<String, PointSeries> pss) {
        clearChart();

        List<ChartElem> items = ChartUtil.stepPlotPrepare(pss);
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

    @Override
    protected void clearChart() {
        super.clearChart();
        clearNumericAxis(Position.LEFT);
    }

    private void setupChart() {
        new ChartBuilder<ChartElem>(chart)
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
}

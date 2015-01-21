package biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse;

public abstract class ChartElemChart extends AbstractChart<ChartElem> {
    public ChartElemChart() {
        super(ChartElem.Access.CE_ACCESS.xKey());
    }
}

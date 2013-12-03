package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

public abstract class ChartElemChart extends AbstractChart<ChartElem> {
    public ChartElemChart() {
        super(ChartElem.Access.CE_ACCESS.xKey());
    }
}

package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import biz.freshcode.learn.gwt2.common.client.util.chart.AbstractChart;

public abstract class ChartElemChart extends AbstractChart<ChartElem> {
    public ChartElemChart() {
        super(ChartElem.Access.CE_ACCESS.xKey());
    }
}

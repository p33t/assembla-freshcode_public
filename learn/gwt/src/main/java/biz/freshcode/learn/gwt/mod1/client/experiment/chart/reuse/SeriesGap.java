package biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse;

public enum SeriesGap {
    GAPS, ZERO_DEF;

    public Double defVal() {
        if (this == GAPS) return Double.NaN;
        return 0.0;
    }
}

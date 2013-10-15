package biz.freshcode.learn.gwt.client.experiment.chart.gantt.reuse;

import java.util.Date;

public class GanttInfo {
    private final Iterable<GanttBar> bars;

    private final Date zeroTime;
    private final String title;

    public GanttInfo(Iterable<GanttBar> bars, Date zeroTime, String title) {
        this.bars = bars;
        this.zeroTime = zeroTime;
        this.title = title;
    }

    public Iterable<GanttBar> getBars() {
        return bars;
    }

    public String getTitle() {
        return title;
    }

    public Date getZeroTime() {
        return zeroTime;
    }
}

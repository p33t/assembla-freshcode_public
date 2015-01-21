package biz.freshcode.learn.gwt.mod1.client.experiment.chart.gantt.reuse;

import java.util.Date;

import static biz.freshcode.learn.gwt.mod1.client.util.ExceptionUtil.illegalArg;

public class GanttInfo {
    private final Iterable<GanttBar> bars;
    private final Date zeroTime;
    private final int windowSize;
    private final String title;

    public GanttInfo(Iterable<GanttBar> bars, Date zeroTime, int windowSize, String title) {
        this.bars = bars;
        this.zeroTime = zeroTime;
        this.windowSize = windowSize;
        this.title = title;
    }

    public Iterable<GanttBar> getBars() {
        return bars;
    }

    public String getTitle() {
        return title;
    }

    public int getWindowSize() {
        return windowSize;
    }

    public Date getZeroTime() {
        return zeroTime;
    }

    public GanttBar getBar(String id) {
        for (GanttBar bar: bars) {
            if (bar.getId().equals(id)) return bar;
        }
        throw illegalArg("Unknown bar.  Id: " + id);
    }
}

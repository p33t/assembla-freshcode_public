package biz.freshcode.learn.gwt.mod1.client.experiment.chart.gantt2.reuse;

import java.util.Date;
import java.util.List;

public class ChartInfo {
    private final Date zeroTime;
    private final int windowSize;
    private final List<HasIdTitle> resources;

    public ChartInfo(Date zeroTime, int windowSize, List<HasIdTitle> resources) {
        this.zeroTime = zeroTime;
        this.windowSize = windowSize;
        this.resources = resources;
    }

    public List<HasIdTitle> getResources() {
        return resources;
    }

    public int getWindowSize() {
        return windowSize;
    }

    public Date getZeroTime() {
        return zeroTime;
    }
}

package biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse;

import java.util.Date;
import java.util.List;

public class ChartInfo {
    private final String title;
    private final Date zeroTime;
    private final int windowSize;
    private final List<HasIdTitle> resources;
    
    public ChartInfo(String title, Date zeroTime, int windowSize, List<HasIdTitle> resources) {
        this.title = title;
        this.zeroTime = zeroTime;
        this.windowSize = windowSize;
        this.resources = resources;
    }

    public List<HasIdTitle> getResources() {
        return resources;
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
}

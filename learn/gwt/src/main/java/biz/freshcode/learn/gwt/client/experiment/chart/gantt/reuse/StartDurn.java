package biz.freshcode.learn.gwt.client.experiment.chart.gantt.reuse;

/**
 * A start and duration relative to some point in time.  Units are 'minutes'.
 */
public class StartDurn {
    private final int start;
    private final int durn;

    public StartDurn(int start, int durn) {
        this.start = start;
        this.durn = durn;
    }
    
    public int getFinex() {
        return start + durn;
    }

    public int getDurn() {
        return durn;
    }

    public int getStart() {
        return start;
    }
}

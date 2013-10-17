package biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse;

import biz.freshcode.learn.gwt.client.experiment.chart.gantt.reuse.StartDurn;
import com.sencha.gxt.chart.client.draw.RGB;

public class BarInfo {
    private final RGB colour;
    private final StartDurn startDurn;
    private final String resourceId;
    private final String title;
    private final String id;

    public BarInfo(String id, String title, String resourceId, RGB colour, StartDurn startDurn) {
        this.id = id;
        this.title = title;
        this.colour = colour;
        this.startDurn = startDurn;
        this.resourceId = resourceId;
    }
    
    public RGB getColour() {
        return colour;
    }

    public String getId() {
        return id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public StartDurn getStartDurn() {
        return startDurn;
    }
    
    public String getTitle() {
        return title;
    }
}

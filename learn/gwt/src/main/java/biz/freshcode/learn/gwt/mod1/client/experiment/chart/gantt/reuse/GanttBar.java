package biz.freshcode.learn.gwt.mod1.client.experiment.chart.gantt.reuse;

public class GanttBar {
    private final String id;
    private final String resource;
    private final String name;
    private final String colour;
    private final StartDurn startDurn;
    
    public GanttBar(String id, String resource, String name, String colour, StartDurn startDurn) {
        this.id = id;
        this.resource = resource;
        this.name = name;
        this.colour = colour;
        this.startDurn = startDurn;
    }

    public String getColour() {
        return colour;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getResource() {
        return resource;
    }

    public StartDurn getStartDurn() {
        return startDurn;
    }
}

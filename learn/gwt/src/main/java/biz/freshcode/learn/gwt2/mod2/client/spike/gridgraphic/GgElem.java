package biz.freshcode.learn.gwt2.mod2.client.spike.gridgraphic;

public class GgElem {
    private String id;
    private String title;
    private int fromIn;
    private int toEx;
    private String colour;

    public GgElem(String id, String title, int fromIn, int toEx, String colour) {
        this.id = id;
        this.title = title;
        this.fromIn = fromIn;
        this.toEx = toEx;
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public int getFromIn() {
        return fromIn;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getToEx() {
        return toEx;
    }
}

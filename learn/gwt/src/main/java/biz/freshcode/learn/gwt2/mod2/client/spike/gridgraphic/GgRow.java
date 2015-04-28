package biz.freshcode.learn.gwt2.mod2.client.spike.gridgraphic;

import biz.freshcode.learn.gwt2.common.client.util.data.HasIdTitle;

import java.util.List;

public class GgRow implements HasIdTitle {
    private String id;
    private String title;
    private List<GgElem> elems;

    public GgRow(String id, String title, List<GgElem> elems) {
        this.id = id;
        this.title = title;
        this.elems = elems;
    }

    public List<GgElem> getElems() {
        return elems;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }
}

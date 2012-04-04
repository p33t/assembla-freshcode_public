package biz.freshcode.learn.gwt.client.uispike;

import com.sencha.gxt.core.client.util.Margins;

public class MarginsBuilder {
    public final Margins margins = new Margins();

    public MarginsBuilder top(int v) {
        margins.setTop(v);
        return this;
    }

    public MarginsBuilder right(int v) {
        margins.setRight(v);
        return this;
    }

    public MarginsBuilder bottom(int v) {
        margins.setBottom(v);
        return this;
    }

    public MarginsBuilder left(int v) {
        margins.setLeft(v);
        return this;
    }
}

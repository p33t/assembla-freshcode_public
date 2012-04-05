package biz.freshcode.learn.gwt.client.uispike.builder;

import com.sencha.gxt.core.client.util.Margins;

@BeanBuilder(Margins.class)
public class MarginsBuilder {
    public final Margins margins;

    public MarginsBuilder() {
        this(new Margins());
    }

    public MarginsBuilder(Margins m) {
        margins = m;
    }

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

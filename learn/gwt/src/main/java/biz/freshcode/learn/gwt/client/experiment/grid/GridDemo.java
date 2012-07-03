package biz.freshcode.learn.gwt.client.experiment.grid;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;

public class GridDemo extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        return new HtmlLayoutContainer("<p>Hello</p>");
    }
}

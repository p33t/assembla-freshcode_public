package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.uispike.builder.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class DropTargetPanel extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        return new VerticalLayoutContainerBuilder()
                .add(elem("<p>First one</p>"))
                .add(elem("<p>Second</p> <p>One</p>"))
                .add(elem("<p>Third One</p>"))
                .verticalLayoutContainer;
    }

    private Widget elem(String html) {
        HTMLPanel p = new HTMLPanel(html);
        p.setStyleName(Bundle.INSTANCE.style().dropElem(), true);
        return p;
    }
}

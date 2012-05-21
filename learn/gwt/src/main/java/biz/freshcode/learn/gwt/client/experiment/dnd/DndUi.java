package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.uispike.builder.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.sencha.gxt.widget.core.client.container.Container;

/**
 * A Ui driven by Drag 'n Drop.
 */
public class DndUi extends AbstractIsWidget<Container> {
    @Override
    protected Container createWidget() {
        return new BorderLayoutContainerBuilder()
                .westWidget(new StudentMasterPanel())
                .widget(new HTMLPanel("<p>Center hopefully</p>"))
                .borderLayoutContainer;
    }
}

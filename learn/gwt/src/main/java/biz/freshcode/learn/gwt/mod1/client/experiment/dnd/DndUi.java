package biz.freshcode.learn.gwt.mod1.client.experiment.dnd;

import biz.freshcode.learn.gwt.mod1.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.mod1.client.util.AbstractIsWidget;
import com.sencha.gxt.widget.core.client.container.Container;

/**
 * A Ui driven by Drag 'n Drop.
 */
public class DndUi extends AbstractIsWidget<Container> {
    @Override
    protected Container createWidget() {
        return new BorderLayoutContainerBuilder()
                .styleName(Bundle.INSTANCE.style().inheritBgnd(), true)
                .westWidget(new StudentMasterPanel())
                .widget(new DndCenter())
                .borderLayoutContainer;
    }
}

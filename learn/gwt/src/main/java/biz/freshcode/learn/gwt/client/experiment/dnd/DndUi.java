package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
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

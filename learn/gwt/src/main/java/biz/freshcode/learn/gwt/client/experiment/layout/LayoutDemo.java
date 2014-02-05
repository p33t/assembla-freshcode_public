package biz.freshcode.learn.gwt.client.experiment.layout;

import biz.freshcode.learn.gwt.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class LayoutDemo extends AbstractIsWidget<FlowLayoutContainer> {
    @Override
    protected FlowLayoutContainer createWidget() {
        return new FlowLayoutContainerBuilder()
                .add(new TextButton("Dialog", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        dialog();
                    }
                }))
                .flowLayoutContainer;
    }

    private void dialog() {
        new DialogBuilder()
                .modal(true)
                .resizable(true)
                .pixelSize(640, 480)
                .title("Resize this dialog")
                .widget(new BorderLayoutContainerBuilder()
                        .northWidget(new HTML("<p>North</p>"))
                        .centerWidget(new HTML("<p>Centre</p>"))
                        .borderLayoutContainer)
                .dialog.show();
    }
}

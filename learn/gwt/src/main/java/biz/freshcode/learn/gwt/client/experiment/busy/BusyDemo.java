package biz.freshcode.learn.gwt.client.experiment.busy;

import biz.freshcode.learn.gwt.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;

public class BusyDemo extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        return new VerticalLayoutContainerBuilder()
                .add(new TextButton("Go", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        go();
                    }
                }))
                .verticalLayoutContainer;
    }

    private void go() {
        Info.display("Event", "Go");
    }
}

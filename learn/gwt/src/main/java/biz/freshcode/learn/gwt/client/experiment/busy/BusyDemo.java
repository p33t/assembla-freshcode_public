package biz.freshcode.learn.gwt.client.experiment.busy;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.ContentPanelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import static biz.freshcode.learn.gwt.client.experiment.busy.reuse.BusyUtil.clearBusy;
import static biz.freshcode.learn.gwt.client.experiment.busy.reuse.BusyUtil.markBusy;

public class BusyDemo extends AbstractIsWidget {
    private ContentPanel pnlTarget;

    @Override
    protected Widget createWidget() {
        return new VerticalLayoutContainerBuilder()
                .add(new TextButton("Start Busy", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        markBusy(pnlTarget);
                    }
                }))
                .add(space())
                .add(new TextButton("Reset", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        clearBusy(pnlTarget);
                    }
                }))
                .add(space())
                .add(pnlTarget = new ContentPanelBuilder()
                        .heading("The Target")
                        .widget(new ToolButton(ToolButton.REFRESH))
                        .contentPanel)
                .verticalLayoutContainer;
    }

    private HTML space() {
        return new HTML("<p>&nbsp;</p>");
    }

}

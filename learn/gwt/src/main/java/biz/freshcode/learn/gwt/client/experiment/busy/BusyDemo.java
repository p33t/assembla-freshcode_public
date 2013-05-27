package biz.freshcode.learn.gwt.client.experiment.busy;

import biz.freshcode.learn.gwt.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.ContentPanelBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import static biz.freshcode.learn.gwt.client.experiment.busy.Bundle.BUNDLE;

public class BusyDemo extends AbstractIsWidget {
    private ContentPanel pnlTarget;

    @Override
    protected Widget createWidget() {
        return new VerticalLayoutContainerBuilder()
                .add(new TextButton("Start Busy", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        start();
                    }
                }))
                .add(space())
                .add(new TextButton("Reset", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        reset();
                    }
                }))
                .add(space())
                .add(pnlTarget = new ContentPanelBuilder()
                        .headingText("The Target")
                        .widget(new ToolButton(ToolButton.REFRESH))
                        .contentPanel)
                .verticalLayoutContainer;
    }

    private HTML space() {
        return new HTML("<p>&nbsp;</p>");
    }

    private void reset() {
        pnlTarget.enable();
        if (!ensureNotBusy()) {
            GWT.log("No busy spinner to remove");
        }
// By id...   DOM.getElementById("busySpinner").removeFromParent();
    }

    private boolean ensureNotBusy() {
        Element first = pnlTarget.getElement().getFirstChildElement();
        if (first != null && first.removeClassName("busySpinner")) {
            first.removeFromParent();
            return true;
        }
        return false;
    }

    private void start() {
        pnlTarget.disable();
        ensureNotBusy();
        String uri = BUNDLE.spinnerGif().getSafeUri().asString();
        pnlTarget.getElement().insertFirst("<img src='" + uri + "'" +
                " style='position:absolute; top:0px; left:20px;z-index:10000'" + // a really high z-index
                " class='busySpinner' />");
    }
}

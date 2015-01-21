package biz.freshcode.learn.gwt.client.rpc.dispatch;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.mod1.shared.dispatch.DdAction;
import biz.freshcode.learn.gwt.mod1.shared.dispatch.StrResult;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.button.TextButtonBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class DispatchDemo extends AbstractIsWidget {
    @Inject
    protected DispatchAsync dispatch;
    private HTML fb;
    private TextButton btnGo;

    @Override
    protected Widget createWidget() {
        VerticalLayoutContainer c = new VerticalLayoutContainerBuilder()
                .add(btnGo = new TextButtonBuilder()
                        .text("Go")
                        .textButton)
                .add(fb = new HTML("<p>Press Go</p>"))
                .verticalLayoutContainer;

        btnGo.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                run();
            }
        });
        return c;
    }

    /**
     * Run the rpc call.  Post results using displayResult()
     * @see #displayResult(String)
     */
    protected void run() {
        dispatch.execute(new DdAction("Bruce"), new AsyncCallback<StrResult>() {
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("No can do: " + caught);
            }

            @Override
            public void onSuccess(StrResult result) {
                displayResult("<p>Sent 'Bruce'<br/>Result '" + result.getStr() + "'</p>");
            }
        });
    }

    /**
     * Puts html message on screen.  Timestamp is appended.  Html is not escaped.
     */
    protected final void displayResult(String html) {
        fb.setHTML(html + "<p>" + System.currentTimeMillis() + "</p>");
    }
}

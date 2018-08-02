package biz.freshcode.learn.gwt2.mod2.client.rpc.dispatch;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.button.TextButtonBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt2.mod2.shared.StringResult;
import biz.freshcode.learn.gwt2.mod2.shared.spike.rpc.dispatch.DdAction;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class DispatchDemo extends AbstractIsWidget {
    @Inject
    protected DispatchAsync dispatch;
    private HTML fb;

    @Override
    protected Widget createWidget() {
        TextButton btnGo;
        VerticalLayoutContainer c = new VerticalLayoutContainerBuilder()
                .add(btnGo = new TextButtonBuilder()
                        .text("Go")
                        .textButton)
                .add(fb = new HTML("<p>Press Go</p>"))
                .verticalLayoutContainer;

        btnGo.addSelectHandler(event -> run());
        return c;
    }

    /**
     * Run the rpc call.  Post results using displayResult()
     * @see #displayResult(String)
     */
    protected void run() {
        dispatch.execute(new DdAction("Bruce"), new AsyncCallback<StringResult>() {
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("No can do: " + caught);
            }

            @Override
            public void onSuccess(StringResult result) {
                displayResult("<p>Sent 'Bruce'<br/>Result '" + result.getResult() + "'</p>");
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

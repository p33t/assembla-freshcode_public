package biz.freshcode.learn.gwt2.mod2.client.spike.rpc.greet;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class GreetRpcDemo extends AbstractIsWidget {
    private HTML fb;

    @Override
    protected Widget createWidget() {
        TextButton btnGo;
        VerticalLayoutContainer c = new VerticalLayoutContainerBuilder()
                .add(btnGo = new TextButton("Go"))
                .add(fb = new HTML("<p>Press Go</p>"))
                .verticalLayoutContainer;

        btnGo.addSelectHandler(event -> {
            // NOTE: Asking GWT to create the NON-ASYNC interface.
            GreetingServiceAsync s = GWT.create(GreetingService.class);
            s.greetServer("Hello", new AsyncCallback<String>() {
                public void onFailure(Throwable caught) {
                    SafeHtml safeHtml = new SafeHtmlBuilder()
                            .appendEscapedLines(caught.toString())
                            .toSafeHtml();
                    fb.setHTML("<p>Failed: " + safeHtml.asString() + "</p>");
                }

                public void onSuccess(String result) {
                    fb.setHTML("<p>Sent: Hello<br/>Response:" + result + "<br/>" + System.currentTimeMillis() + "</p>");
                }
            });
        });

        return c;
    }
}

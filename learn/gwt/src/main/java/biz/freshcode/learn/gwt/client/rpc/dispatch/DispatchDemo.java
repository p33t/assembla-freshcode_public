package biz.freshcode.learn.gwt.client.rpc.dispatch;

import biz.freshcode.learn.gwt.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.TextButtonBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.shared.dispatch.DdAction;
import biz.freshcode.learn.gwt.shared.dispatch.DdResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class DispatchDemo extends AbstractIsWidget {
    @Inject
    private DispatchAsync dispatch;
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
                dispatch.execute(new DdAction("Bruce"), new AsyncCallback<DdResult>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        GWT.log("No can do: " + caught);
                    }

                    @Override
                    public void onSuccess(DdResult result) {
                        fb.setHTML("<p>Sent 'Bruce'<br/>Result '" + result.getStr() + "'<br/>" + System.currentTimeMillis() + "</p>");
                    }
                });
            }
        });
        return c;
    }
}

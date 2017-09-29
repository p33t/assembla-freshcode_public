package biz.freshcode.learn.gwt2.mod2.client.spike.restcommand;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.form.TextAreaBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import biz.freshcode.learn.gwt2.mod2.shared.pkg.rest.ActionDispatch;
import biz.freshcode.learn.gwt2.mod2.shared.pkg.rest.StringResult;
import biz.freshcode.learn.gwt2.mod2.shared.pkg.rest.TestAction;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.TextArea;

import java.util.Date;

/**
 * Trying to do command pattern over REST.
 */
public class RestCommandSpike extends Presenter<RestCommandSpike.View, RestCommandSpike.Proxy> {
    public static final String TOKEN = "restCommand";

    @Inject
    private ResourceDelegate<ActionDispatch> actionDisp;

    @Inject
    public RestCommandSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);

        view.getBtnGo().addSelectHandler(evt -> go());
    }

    private void go() {
        getView().message("Invoking action...");
        actionDisp
                .withCallback(new AsyncCallback<StringResult>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        getView().message("Failure: " + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(StringResult result) {
                        getView().message("Success: " + result.getResult());
                    }
                })
                .post(new TestAction());
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<RestCommandSpike> {
    }

    public static class View extends ViewImpl {
        private TextArea feedback;

        public TextButton getBtnGo() {
            return btnGo;
        }

        private TextButton btnGo;

        @Inject
        public View() {
            initWidget(new BorderLayoutContainerBuilder()
                    .northWidget(new FlowLayoutContainerBuilder()
                            .add(btnGo = new TextButton("Go"))
                            .flowLayoutContainer)
                    .centerWidget(feedback = new TextAreaBuilder()
                            .readOnly(true)
                            .textArea)
                    .borderLayoutContainer);
        }

        public void message(String msg) {
            feedback.setText(feedback.getText() + "\n " + new Date() + ":" + msg);
        }
    }
}

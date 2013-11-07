package biz.freshcode.learn.gwtp.client.home;

import biz.freshcode.learn.gwtp.client.boot.Root;
import biz.freshcode.learn.gwtp.util.client.HasTitle;
import biz.freshcode.learn.gwtp.shared.dispatch.SdAction;
import biz.freshcode.learn.gwtp.shared.dispatch.StrResult;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.widget.client.TextButton;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;

public class Home extends Presenter<Home.View, Home.Proxy> implements HasTitle {
    public static final String TOKEN = "home";

    @Inject
    DispatchAsync dispatch;

    @Inject
    public Home(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @Override
    public String getTitle() {
        return "Home";
    }

    @Override
    protected void onBind() {
        super.onBind();
        getView().getBtnAction().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                rpc();
            }
        });
    }

    private void rpc() {
        getView().appendMsg("Calling RPC");
        dispatch.execute(new SdAction("Bruce"), new AsyncCallback<StrResult>() {
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("No can do: " + caught);
            }

            @Override
            public void onSuccess(StrResult result) {
                getView().appendMsg("Sent 'Bruce'<br/>Result '" + result.getStr() + "'");
            }
        });
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<Home> {
    }

    public static interface View extends com.gwtplatform.mvp.client.View {
        TextButton getBtnAction();

        void appendMsg(String msg);
    }
}

package biz.freshcode.learn.gwt2.mod2.client.spike.rpc;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.ContentPanelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import biz.freshcode.learn.gwt2.mod2.client.spike.rpc.dispatch.DispatchDemo;
import biz.freshcode.learn.gwt2.mod2.client.spike.rpc.dispatch.SecureDispatchDemo;
import biz.freshcode.learn.gwt2.mod2.client.spike.rpc.greet.GreetRpcDemo;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;

/**
 * Trying to simulate a file download in javascript.
 */
public class RpcSpike extends Presenter<RpcSpike.View, RpcSpike.Proxy> {
    public static final String TOKEN = "rpc";

    @Inject
    public RpcSpike(EventBus eventBus, RpcSpike.View view, RpcSpike.Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<RpcSpike> {
    }

    public static class View extends ViewImpl {

        @Inject
        public View(DispatchDemo dispatchDemo, SecureDispatchDemo secureDispatchDemo) {
            initWidget(new FlowLayoutContainerBuilder()
                    .add(new ContentPanelBuilder()
                            .heading("Greeting GWT RPC")
                            .add(new GreetRpcDemo())
                            .contentPanel)
                    .add(new ContentPanelBuilder()
                            .heading("GWTP Dispatch - Unsecure")
                            .add(dispatchDemo)
                            .contentPanel)
                    .add(new ContentPanelBuilder()
                            .heading("GWTP Dispatch - Secure")
                            .add(secureDispatchDemo)
                            .contentPanel)
                    .flowLayoutContainer);
        }
    }
}

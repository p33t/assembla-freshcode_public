package biz.freshcode.learn.gwtp.client.paginggrid;

import biz.freshcode.learn.gwtp.client.boot.Root;
import biz.freshcode.learn.gwtp.shared.paginggrid.Tyre;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public class PagingGrid extends Presenter<PagingGrid.View, PagingGrid.Proxy> {
    public static final String TOKEN = "pagingGrid";

    @Inject
    private DispatchAsync dispatch;

    @Inject
    public PagingGrid(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<PagingGrid> {
    }

    public static interface View extends com.gwtplatform.mvp.client.View {
        void setRpcProx(RpcProxy<PagingLoadConfig, PagingLoadResult<Tyre>> proxy);
    }
}

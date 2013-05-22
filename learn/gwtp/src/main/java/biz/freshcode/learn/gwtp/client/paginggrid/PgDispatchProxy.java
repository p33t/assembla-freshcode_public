package biz.freshcode.learn.gwtp.client.paginggrid;

import biz.freshcode.learn.gwtp.client.util.gwtp.dispatch.GdCallbackAdapter;
import biz.freshcode.learn.gwtp.shared.paginggrid.PgListAction;
import biz.freshcode.learn.gwtp.shared.paginggrid.Tyre;
import biz.freshcode.learn.gwtp.shared.util.gwtp.dispatch.GdPagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@Singleton
public class PgDispatchProxy extends RpcProxy<PagingLoadConfig, PagingLoadResult<Tyre>> {
    @Inject
    private DispatchAsync dispatch;

    @Override
    public void load(PagingLoadConfig loadConfig, final AsyncCallback<PagingLoadResult<Tyre>> callback) {
        dispatch.execute(createAction(loadConfig), new GdCallbackAdapter<Tyre>(callback));
    }

    // TODO: This is the only non-reusable bit.
    private Action<GdPagingLoadResult<Tyre>> createAction(PagingLoadConfig loadConfig) {
        return new PgListAction(loadConfig);
    }
}

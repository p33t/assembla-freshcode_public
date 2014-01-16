package biz.freshcode.learn.gwtp.client.paginggrid;

import biz.freshcode.learn.gwtp.util.client.gwtp.dispatch.GdCallbackAdapter;
import biz.freshcode.learn.gwtp.shared.paginggrid.PgListAction;
import biz.freshcode.learn.gwtp.shared.paginggrid.Tyre;
import biz.freshcode.learn.gwtp.util.shared.dispatch.GdPagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.rpc.shared.Action;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * A Gwtp Dispatch flavoured RpcProxy for 'Paging Grid' demo.
 */
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

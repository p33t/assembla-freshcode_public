package biz.freshcode.learn.gwtp.client.util.gwtp.dispatch;

import biz.freshcode.learn.gwtp.shared.util.gwtp.dispatch.GdPagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * Gwtp Dispatch callback adapter that enables used of GWTP dispatch from GXT loaders.
 */
public class GdCallbackAdapter<T extends IsSerializable> implements AsyncCallback<GdPagingLoadResult<T>> {
    private final AsyncCallback<PagingLoadResult<T>> callback;

    public GdCallbackAdapter(AsyncCallback<PagingLoadResult<T>> callback) {
        this.callback = callback;
    }

    @Override
    public void onFailure(Throwable caught) {
        callback.onFailure(caught);
    }

    @Override
    public void onSuccess(GdPagingLoadResult<T> result) {
        callback.onSuccess(result);
    }
}

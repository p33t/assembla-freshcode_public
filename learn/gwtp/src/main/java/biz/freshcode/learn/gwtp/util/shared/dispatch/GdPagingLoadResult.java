package biz.freshcode.learn.gwtp.util.shared.dispatch;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.gwtplatform.dispatch.rpc.shared.Result;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

/**
 * Gwtp Dispatch adapter for the gxt's PagingLoadResultBean.
 */
public class GdPagingLoadResult<Data extends IsSerializable> extends PagingLoadResultBean<Data> implements Result {
}

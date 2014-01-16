package biz.freshcode.learn.gwtp.shared.paginggrid;

import biz.freshcode.learn.gwtp.util.shared.dispatch.GdPagingLoadResult;
import com.gwtplatform.dispatch.rpc.shared.ActionImpl;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;

public class PgListAction extends ActionImpl<GdPagingLoadResult<Tyre>> {
    private PagingLoadConfig args;

    @SuppressWarnings("UnusedDeclaration")
    private PgListAction() {
        // for ser'n
    }

    @SuppressWarnings("UnusedDeclaration")
    public PgListAction(PagingLoadConfig args) {
        this.args = args;
    }

    public PagingLoadConfig getArgs() {
        return args;
    }
}

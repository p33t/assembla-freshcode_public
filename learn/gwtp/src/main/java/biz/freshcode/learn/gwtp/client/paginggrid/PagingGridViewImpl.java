package biz.freshcode.learn.gwtp.client.paginggrid;

import biz.freshcode.learn.gwtp.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwtp.shared.paginggrid.Tyre;
import com.google.gwt.core.shared.GWT;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import static biz.freshcode.learn.gwtp.client.util.AppCollectionUtil.newListFrom;

public class PagingGridViewImpl extends ViewImpl implements PagingGrid.View {
    private static final Tyre.Access TYRE_ACCESS = GWT.create(Tyre.Access.class);

    @Inject
    public PagingGridViewImpl() {
        ListStore<Tyre> store = new ListStore<Tyre>(TYRE_ACCESS.id());
        //noinspection unchecked
        Grid<Tyre> grid = new Grid<Tyre>(store, new ColumnModel(newListFrom(
                new ColumnConfigBuilder(TYRE_ACCESS.diameter())
                        .header("Diameter")
                        .columnConfig,
                new ColumnConfigBuilder(TYRE_ACCESS.thickness())
                        .header("Thickness")
                        .columnConfig
        )));
        grid.getView().setForceFit(true);
        initWidget(grid);
    }

    @Override
    public void setRpcProx(RpcProxy<PagingLoadConfig, PagingLoadResult<Tyre>> proxy) {
        throw new RuntimeException("setRpcProx not implemented");
    }
}

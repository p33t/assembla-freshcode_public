package biz.freshcode.learn.gwtp.client.paginggrid;

import biz.freshcode.learn.gwtp.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwtp.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwtp.shared.paginggrid.Tyre;
import com.google.gwt.core.shared.GWT;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

import static biz.freshcode.learn.gwtp.shared.util.AppCollectionUtil.newListFrom;
import static com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

public class PagingGridViewImpl extends ViewImpl implements PagingGrid.View {
    private static final TyreAccess TYRE_ACCESS = GWT.create(TyreAccess.class);
    private final PagingLoader<PagingLoadConfig, PagingLoadResult<Tyre>> loader;

    @Inject
    public PagingGridViewImpl(PgDispatchProxy proxy) {
        ListStore<Tyre> store = new ListStore<Tyre>(TYRE_ACCESS.id());
        Grid<Tyre> grid;
        PagingToolBar tb;
        //noinspection unchecked
        initWidget(new VerticalLayoutContainerBuilder()
                .add(grid = new Grid<Tyre>(store, new ColumnModel(newListFrom(
                        new ColumnConfigBuilder(TYRE_ACCESS.diameter())
                                .header("Diameter")
                                .columnConfig,
                        new ColumnConfigBuilder(TYRE_ACCESS.thickness())
                                .header("Thickness")
                                .columnConfig
                ))), new VerticalLayoutData(1, 1))
                .add(tb = new PagingToolBar(5), new VerticalLayoutData(1, -1))
                .verticalLayoutContainer);
        grid.getView().setForceFit(true);

        // setup remote loading
        loader = pagingLoader(proxy);
        loader.setRemoteSort(true);
        loader.addLoadHandler(listStoreBind(store));
        tb.bind(loader);
    }

    private static <T> LoadResultListStoreBinding<PagingLoadConfig, T, PagingLoadResult<T>> listStoreBind(ListStore<T> store) {
        return new LoadResultListStoreBinding<PagingLoadConfig, T, PagingLoadResult<T>>(store);
    }

    private static <T> PagingLoader<PagingLoadConfig, PagingLoadResult<T>> pagingLoader(RpcProxy<PagingLoadConfig, PagingLoadResult<T>> proxy) {
        return new PagingLoader<PagingLoadConfig, PagingLoadResult<T>>(proxy);
    }

    @Override
    public void load() {
        loader.load();
    }
}

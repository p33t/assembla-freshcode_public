package biz.freshcode.learn.gwt2.mod2.client.spike.gridinteract;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.menu.MenuBuilder;
import biz.freshcode.learn.gwt2.common.client.util.ClientUtil;
import biz.freshcode.learn.gwt2.common.client.util.data.UnityAccess;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import biz.freshcode.learn.gwt2.mod2.client.spike.gridinteract.reuse.MultiCellSelectionModel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import static biz.freshcode.learn.gwt2.common.client.util.AppObjectUtils.ifNull;
import static biz.freshcode.learn.gwt2.common.client.util.data.UnityAccess.unityAccess;
import static java.util.Arrays.asList;

/**
 * Illustrate how to change order of grid elements using drag/drop
 */
public class GridInteractSpike extends Presenter<GridInteractSpike.View, GridInteractSpike.Proxy> {
    public static final String TOKEN = "gridInteract";

    @Inject
    public GridInteractSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
        view.getGrid().getStore().addAll(asList("one", "two", "three", "four"));
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<GridInteractSpike> {
    }

    public static class View extends ViewImpl {
        private final UnityAccess<String> access = unityAccess();
        private final Grid<String> grid;

        @Inject
        public View() {
            initWidget(new BorderLayoutContainerBuilder()
                            .centerWidget(grid = ClientUtil.createGrid(access,
                                    new ColumnConfigBuilder<>(access)
                                            .header("Name")
                                            .menuDisabled(true)
                                            .sortable(false)
                                            .columnConfig,
                                    new ColumnConfigBuilder<>(new CharCount())
                                            .header("Length")
                                            .menuDisabled(true)
                                            .sortable(false)
                                            .columnConfig,
                                    new ColumnConfigBuilder<>(access)
                                            .header("Name")
                                            .menuDisabled(true)
                                            .sortable(false)
                                            .columnConfig
                            ))
                            .borderLayoutContainer
            );
            MultiCellSelectionModel.setup(grid);
            grid.setContextMenu(new MenuBuilder()
                    .add(new MenuItem("Hello"))
                    .menu);
        }

        public Grid<String> getGrid() {
            return grid;
        }
    }

    private static class CharCount implements ValueProvider<String, Integer> {

        @Override
        public Integer getValue(String object) {
            return ifNull(object, "").length();
        }

        @Override
        public void setValue(String object, Integer value) {
            throw new RuntimeException("setValue not implemented");
        }

        @Override
        public String getPath() {
            return "length";
        }
    }
}

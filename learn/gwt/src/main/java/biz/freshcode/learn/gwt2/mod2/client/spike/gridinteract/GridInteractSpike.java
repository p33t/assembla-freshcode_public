package biz.freshcode.learn.gwt2.mod2.client.spike.gridinteract;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.menu.MenuBuilder;
import biz.freshcode.learn.gwt2.common.client.util.ClientUtil;
import biz.freshcode.learn.gwt2.common.client.util.data.UnityAccess;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import biz.freshcode.learn.gwt2.mod2.client.spike.gridinteract.reuse.MultiCellSelectionModel;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import static biz.freshcode.learn.gwt2.common.client.util.AppObjectUtils.ifNull;
import static biz.freshcode.learn.gwt2.common.client.util.AppStringUtil.lines;
import static biz.freshcode.learn.gwt2.common.client.util.data.UnityAccess.unityAccess;
import static java.util.Arrays.asList;

/**
 * Illustrate how to change order of grid elements using drag/drop.
 * NOTE !!!!!!!!!!!!!!!!!!: The menu width is decided the first time it is shown.
 * Workaround for dynamic menu width is to create a new menu every time.
 */
public class GridInteractSpike extends Presenter<GridInteractSpike.View, GridInteractSpike.Proxy> {
    public static final String TOKEN = "gridInteract";
    private static final String NOTES = lines(
            "A grid with:",
            "- multi-cell selection",
            "- context menu"
    );

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
        private final MenuItem menuItem;

        @Inject
        public View() {
            initWidget(new BorderLayoutContainerBuilder()
                            .northWidget(new HTML(new SafeHtmlBuilder().appendEscapedLines(NOTES).toSafeHtml()),
                                    new BorderLayoutContainer.BorderLayoutData(40))
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
                    .add(menuItem = new MenuItem("Hello"))
                    .menu);
            grid.addBeforeShowContextMenuHandler(new BeforeShowContextMenuEvent.BeforeShowContextMenuHandler() {
                @Override
                public void onBeforeShowContextMenu(BeforeShowContextMenuEvent event) {
                    MultiCellSelectionModel<String> sm = (MultiCellSelectionModel<String>) grid.getSelectionModel();
                    int size = sm.getSelections().size();
                    menuItem.setText(size + " item" + (size == 1 ? "" : "s"));
                }
            });
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

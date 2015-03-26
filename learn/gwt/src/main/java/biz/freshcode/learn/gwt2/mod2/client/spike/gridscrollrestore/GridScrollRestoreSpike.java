package biz.freshcode.learn.gwt2.mod2.client.spike.gridscrollrestore;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.toolbar.ToolBarBuilder;
import biz.freshcode.learn.gwt2.common.client.util.ClientUtil;
import biz.freshcode.learn.gwt2.common.client.util.data.UnityAccess;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.List;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt2.common.client.util.data.UnityAccess.unityAccess;

/**
 * Illustrate how to restore the scroll position after replacing data.
 */
public class GridScrollRestoreSpike extends Presenter<GridScrollRestoreSpike.View, GridScrollRestoreSpike.Proxy> {
    public static final String TOKEN = "gridScrollRestore";

    @Inject
    public GridScrollRestoreSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<GridScrollRestoreSpike> {
    }

    public static class View extends ViewImpl {
        private final UnityAccess<String> access = unityAccess();
        private final Grid<String> grid;

        @Inject
        public View() {
            initWidget(new BorderLayoutContainerBuilder()
                            .northWidget(new ToolBarBuilder()
                                            .add(new TextButton("New Data", new SelectEvent.SelectHandler() {
                                                @Override
                                                public void onSelect(SelectEvent event) {
                                                    List<String> ss = genData(50);
                                                    grid.getStore().replaceAll(ss);
                                                }
                                            }))
                                            .toolBar,
                                    new BorderLayoutContainer.BorderLayoutData(40))
                            .centerWidget(grid = ClientUtil.createGrid(access,
                                    new ColumnConfigBuilder<>(access)
                                            .header("Name")
                                            .menuDisabled(true)
                                            .sortable(false)
                                            .columnConfig
                            ))
                            .borderLayoutContainer
            );
        }

        private List<String> genData(int rowCount) {
            List<String> l = newList();
            for (int i = 0; i < rowCount; i++) {
                l.add("ix-" + i);
            }
            return l;
        }
    }
}

package biz.freshcode.learn.gwt2.mod2.client.spike.dragorder;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt2.common.client.util.ClientUtil;
import biz.freshcode.learn.gwt2.common.client.util.data.UnityAccess;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.dnd.core.client.DND;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.Arrays;

import static biz.freshcode.learn.gwt2.common.client.util.data.UnityAccess.unityAccess;

/**
 * Illustrate how to change order of grid elements using drag/drop
 */
public class DragOrderSpike extends Presenter<DragOrderSpike.View, DragOrderSpike.Proxy> {
    public static final String TOKEN = "dragOrder";

    @Inject
    public DragOrderSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
        view.getGrid().getStore().addAll(Arrays.asList("one", "two", "three", "four"));
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<DragOrderSpike> {
    }

    public static class View extends ViewImpl {
        private final UnityAccess<String> access = unityAccess();
        private final Grid<String> grid;

        @Inject
        public View() {
            initWidget(grid = ClientUtil.<String>createGrid(access,
                    new ColumnConfigBuilder<>(access)
                            .header("Name")
                            .menuDisabled(true)
                            .sortable(false)
                            .columnConfig));
            new GridDragSource<>(grid);
            GridDropTarget<String> target = new GridDropTarget<>(grid);
            target.setAllowSelfAsSource(true);
            target.setFeedback(DND.Feedback.BOTH);
        }

        public Grid<String> getGrid() {
            return grid;
        }
    }
}

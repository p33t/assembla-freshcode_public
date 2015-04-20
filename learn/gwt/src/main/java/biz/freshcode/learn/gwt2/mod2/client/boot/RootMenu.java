package biz.freshcode.learn.gwt2.mod2.client.boot;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.button.TextButtonBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.menu.MenuBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.toolbar.ToolBarBuilder;
import biz.freshcode.learn.gwt2.common.client.util.IsWidgetImpl;
import biz.freshcode.learn.gwt2.mod2.client.home.Home;
import biz.freshcode.learn.gwt2.mod2.client.spike.adapterfieldgrid.AdapterFieldGridSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.checkboxcellicon.CheckBoxCellIconSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.dragorder.DragOrderSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.gridinteract.GridInteractSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.gridscrollrestore.GridScrollRestoreSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.lanes.LanesChartSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.radiobutton.RadioSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.resize.ResizeSpike;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class RootMenu extends IsWidgetImpl {
    @Inject
    private PlaceManager placeManager;

    @Inject
    public RootMenu() {
        initWidget(new ToolBarBuilder()
                .height(30)
                .add(new TextButton("Home", new Handler(Home.TOKEN)))
                .add(new TextButtonBuilder()
                        .text("Spike")
                        .menu(new MenuBuilder()
                                .add(menuItem("Lanes Chart", LanesChartSpike.TOKEN))
                                .add(menuItem("Grid Scroll Restore", GridScrollRestoreSpike.TOKEN))
                                .add(menuItem("Radio", RadioSpike.TOKEN))
                                .add(menuItem("Check Box Cell Icon", CheckBoxCellIconSpike.TOKEN))
                                .add(menuItem("Grid Interact", GridInteractSpike.TOKEN))
                                .add(menuItem("Drag Order", DragOrderSpike.TOKEN))
                                .add(menuItem("Resize", ResizeSpike.TOKEN))
                                .add(menuItem("Adapter Field Grid", AdapterFieldGridSpike.TOKEN))
                                .menu)
                        .textButton)
                .toolBar);
    }

    private MenuItem menuItem(String text, String token) {
        return new MenuItem(text, new Handler<MenuItem>(token));
    }

    private class Handler<T> implements SelectEvent.SelectHandler, SelectionHandler<T> {
        private final String token;

        private Handler(String token) {
            this.token = token;
        }

        @Override
        public void onSelect(SelectEvent event) {
            select();
        }

        @Override
        public void onSelection(SelectionEvent<T> event) {
            select();
        }

        private void select() {
            PlaceRequest req = new PlaceRequest.Builder().nameToken(token).build();
            placeManager.revealPlace(req);
        }
    }
}

package biz.freshcode.learn.gwt2.mod2.client.boot;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.button.TextButtonBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.menu.MenuBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.toolbar.ToolBarBuilder;
import biz.freshcode.learn.gwt2.common.client.util.IsWidgetImpl;
import biz.freshcode.learn.gwt2.mod2.client.home.Home;
import biz.freshcode.learn.gwt2.mod2.client.spike.adapterfieldgrid.AdapterFieldGridSpike;
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
                                .add(new MenuItem("Resize", new Handler<MenuItem>(ResizeSpike.TOKEN)))
                                .add(new MenuItem("Adapter Field Grid", new Handler<MenuItem>(AdapterFieldGridSpike.TOKEN)))
                                .menu)
                        .textButton)
                .toolBar);
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

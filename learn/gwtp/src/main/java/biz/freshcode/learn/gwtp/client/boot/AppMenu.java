package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.client.builder.gxt.toolbar.ToolBarBuilder;
import biz.freshcode.learn.gwtp.client.compound.Child1;
import biz.freshcode.learn.gwtp.client.compound.Child2;
import biz.freshcode.learn.gwtp.client.compound.Compound;
import biz.freshcode.learn.gwtp.client.editform.EditForm;
import biz.freshcode.learn.gwtp.client.home.Home;
import biz.freshcode.learn.gwtp.client.paginggrid.PagingGrid;
import biz.freshcode.learn.gwtp.client.util.IsWidgetImpl;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;

@Singleton
public class AppMenu extends IsWidgetImpl<Widget> {
    @Inject
    private PlaceManager placeManager;

    @Inject
    public AppMenu(PageTitle titler) {
        initWidget(new ToolBarBuilder()
                .add(btn("Home", Home.TOKEN))
                .add(btn("Compound", Compound.TOKEN))
                .add(btn("Child1", Child1.TOKEN))
                .add(btn("Child2", Child2.TOKEN))
                .add(btn("Paging Grid", PagingGrid.TOKEN))
                .add(btn("Edit Form", EditForm.TOKEN))
                .add(new FillToolItem())
                .add(titler)
                .toolBar);
    }

    private TextButton btn(String label, final String token) {
        return new TextButton(label, new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                placeManager.revealPlace(placeRequest(token));
            }
        });
    }

    private PlaceRequest placeRequest(String token) {
        return new PlaceRequest.Builder().nameToken(token).build();
    }
}
package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.client.boot.inject.PartiallyInjected;
import biz.freshcode.learn.gwtp.client.bug.ComboBoxDebrisBug;
import biz.freshcode.learn.gwtp.client.builder.Construct;
import biz.freshcode.learn.gwtp.client.builder.gxt.toolbar.ToolBarBuilder;
import biz.freshcode.learn.gwtp.client.compound.Child1;
import biz.freshcode.learn.gwtp.client.compound.Child2;
import biz.freshcode.learn.gwtp.client.compound.Compound;
import biz.freshcode.learn.gwtp.client.editform.EditForm;
import biz.freshcode.learn.gwtp.client.ext.Extensible;
import biz.freshcode.learn.gwtp.client.home.Home;
import biz.freshcode.learn.gwtp.client.paginggrid.PagingGrid;
import biz.freshcode.learn.gwtp.client.popup.PopupDemo;
import biz.freshcode.learn.gwtp.client.slotless.SlotlessDemo;
import biz.freshcode.learn.gwtp.shared.generate.SomeBean;
import biz.freshcode.learn.gwtp.util.client.IsWidgetImpl;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;

@Singleton
public class AppMenu extends IsWidgetImpl<Widget> {
    // Triggers invocation of MetaGenerator
    @SuppressWarnings("UnusedDeclaration")
    SomeBean sb = GWT.create(SomeBean.class);

    @Inject
    private PlaceManager placeManager;

    @Inject
    private PartiallyInjected.Factory factory;

    @Inject
    public AppMenu(PageTitle titler, final ExtensionsProvider extProvider) {
        initWidget(new ToolBarBuilder()
                .add(btn("Home", Home.TOKEN))
                .add(btn("Extensible (orig)", Extensible.TOKEN))
                .construct(new Construct<ToolBarBuilder>() {
                    @Override
                    public void run() {
                        for (AppMenuItem itm : extProvider.get().customMenuItems()) {
                            builder.add(btn(itm.getText(), itm.getToken()));
                        }
                    }
                })
                .add(btn("Compound", Compound.TOKEN))
                .add(btn("Child1", Child1.TOKEN))
                .add(btn("Child2", Child2.TOKEN))
                .add(btn("Paging Grid", PagingGrid.TOKEN))
                .add(btn("Edit Form", EditForm.TOKEN))
                .add(btn("Popup", PopupDemo.TOKEN))
                .add(btn("Slotless", SlotlessDemo.TOKEN))
                .add(new TextButton("Partial Inject", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        String assist = "S" + Math.random();
                        factory.create(assist);
                        Info.display("Partial Inject", "See log for result");
                    }
                }))
                .add(btn("Combo Box Debris", ComboBoxDebrisBug.TOKEN))
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

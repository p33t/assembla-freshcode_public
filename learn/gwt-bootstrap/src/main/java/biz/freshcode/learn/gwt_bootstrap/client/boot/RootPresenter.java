package biz.freshcode.learn.gwt_bootstrap.client.boot;

import biz.freshcode.learn.gwt_bootstrap.client.alt.Alt;
import biz.freshcode.learn.gwt_bootstrap.client.builder.com.google.gwt.user.client.ui.ScrollPanelBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.*;
import biz.freshcode.learn.gwt_bootstrap.client.home.Home;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import org.gwtbootstrap3.client.ui.constants.NavbarPosition;
import org.gwtbootstrap3.client.ui.constants.Toggle;

public class RootPresenter extends Presenter<View, RootPresenter.Proxy> {
    @ContentSlot
    public static final GwtEvent.Type<RevealContentHandler<?>> SLOT = new GwtEvent.Type<>();

    @Inject
    public RootPresenter(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RevealType.Root);
    }

    @ProxyStandard
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.Proxy<RootPresenter> {
    }

    public static class View extends ViewImpl {
        public static final String NAVBAR_COLLAPSE = "navbar-collapse";
        private final SimplePanel slotPanel;

        @Inject
        public View() {
            initWidget(
                    new ScrollPanelBuilder()
                            .widget(new ContainerBuilder()
                                    .add(new NavbarBuilder()
                                            .position(NavbarPosition.FIXED_TOP)
                                            .add(new ContainerBuilder()
                                                    .add(new NavbarHeaderBuilder()
                                                            .add(new NavbarBrandBuilder()
                                                                    .targetHistoryToken(Home.TOKEN)
                                                                    .hTML("Learn GWTBootstrap3")
                                                                    .navbarBrand)
                                                            .add(new NavbarCollapseButtonBuilder()
                                                                    // TODO: Try dataTargetWidget instead.
                                                                    .dataTarget("#" + NAVBAR_COLLAPSE)
                                                                    .navbarCollapseButton)
                                                            .navbarHeader)
                                                    .add(new NavbarCollapseBuilder()
                                                            .id(NAVBAR_COLLAPSE)
                                                            .add(new NavbarNavBuilder()
                                                                    .add(new AnchorListItemBuilder()
                                                                            .text("Alt Page")
                                                                            .targetHistoryToken(Alt.TOKEN)
                                                                            .anchorListItem)
                                                                    .add(new ListDropDownBuilder()
                                                                            .add(new AnchorButtonBuilder()
                                                                                    .dataToggle(Toggle.DROPDOWN)
                                                                                    .text("Sub Menu")
                                                                                    .anchorButton)
                                                                            .add(new DropDownMenuBuilder()
                                                                                    .add(new AnchorListItemBuilder()
                                                                                            .text("Alt Page Too")
                                                                                            .targetHistoryToken(Alt.TOKEN)
                                                                                            .anchorListItem)
                                                                                    .dropDownMenu)
                                                                            .listDropDown)
                                                                    .navbarNav)
                                                            .navbarCollapse)
                                                    .container)
                                            .navbar)
                                    .add(slotPanel = new SimplePanel())
                                    .container)
                            .scrollPanel
            );

            // Making the window scroll to top on every page change
            History.addValueChangeHandler(new ValueChangeHandler<String>() {
                @Override
                public void onValueChange(ValueChangeEvent<String> event) {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            Window.scrollTo(0, 0);
                        }
                    });
                }
            });
        }

        @Override
        public void setInSlot(Object slot, IsWidget content) {
            if (slot == RootPresenter.SLOT) {
                slotPanel.setWidget(content);
            } else {
                super.setInSlot(slot, content);
            }
        }
    }
}

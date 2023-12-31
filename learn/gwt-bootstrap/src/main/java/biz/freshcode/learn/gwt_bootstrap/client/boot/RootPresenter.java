package biz.freshcode.learn.gwt_bootstrap.client.boot;

import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.*;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Navbar;
import org.gwtbootstrap3.client.ui.NavbarCollapse;
import org.gwtbootstrap3.client.ui.NavbarCollapseButton;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.NavbarPosition;
import org.gwtbootstrap3.client.ui.constants.Toggle;

import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_BUNDLE;
import static biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken.*;

public class RootPresenter extends Presenter<View, RootPresenter.Proxy> {
    public static final NestedSlot SLOT = new NestedSlot();
    @Inject
    private PlaceManager placeManager;

    @Inject
    public RootPresenter(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RevealType.Root);

        view.getBtnHomePlace().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // changes place, sets history (does NOT fire history change event)
                // is OK now because don't use History.valueChange event.  Now use NavigationEvent.
                placeManager.revealPlace(new PlaceRequest.Builder()
                        .nameToken(TOK_HOME)
                        .build());
            }
        });

        view.getBtnHomeHistory().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // changes place, sets history and fires history change event
                History.newItem(TOK_HOME);
            }
        });
    }

    @ProxyStandard
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.Proxy<RootPresenter> {
    }

    public static class View extends ViewImpl {
        private final SimplePanel slotPanel = new SimplePanel();
        private final Navbar navbar;
        private final Button btnHomePlace;
        private final Button btnHomeHistory;
        private NavbarCollapse navbarCollapse;

        @Inject
        public View(EventBus bus) {
            bindSlot(SLOT, slotPanel);

            NavbarCollapseButton collapseButton;
            initWidget(new ScrollPanel(new ContainerBuilder()
                    .add(navbar = new NavbarBuilder()
                            .position(NavbarPosition.FIXED_TOP)
                            .addStyleName("navbar-custom")
                            .add(new ContainerBuilder()
                                    .add(new NavbarHeaderBuilder()
                                            .add(new ImageAnchorBuilder()
                                                    .alt("Logo")
                                                    .targetHistoryToken(TOK_HOME)
                                                    .url(BOOT_BUNDLE.logoSml().getSafeUri().asString())
                                                    .height("50px")
                                                    .imageAnchor)
                                            .add(collapseButton = new NavbarCollapseButton())
                                            .navbarHeader)
                                    .add(navbarCollapse = new NavbarCollapseBuilder()
                                            .add(new NavbarNavBuilder()
                                                    .add(new ListDropDownBuilder()
                                                            .add(new AnchorButtonBuilder()
                                                                    .dataToggle(Toggle.DROPDOWN)
                                                                    .text("Demo")
                                                                    .anchorButton)
                                                            .add(new DropDownMenuBuilder()
                                                                    .add(new AnchorListItemBuilder()
                                                                            .text("Forms")
                                                                            .targetHistoryToken(TOK_FORMS)
                                                                            .anchorListItem)
                                                                    .add(new AnchorListItemBuilder()
                                                                            .text("Carousels")
                                                                            .targetHistoryToken(TOK_CAROUSELS)
                                                                            .anchorListItem)
                                                                    .add(new AnchorListItemBuilder()
                                                                            .text("Timed Display")
                                                                            .targetHistoryToken(TOK_TIMED)
                                                                            .anchorListItem)
                                                                    .add(new AnchorListItemBuilder()
                                                                            .text("Graphic")
                                                                            .targetHistoryToken(TOK_GRAPHIC)
                                                                            .anchorListItem)
                                                                    .add(new AnchorListItemBuilder()
                                                                            .text("Grids")
                                                                            .targetHistoryToken(TOK_GRIDS)
                                                                            .anchorListItem)
                                                                    .dropDownMenu)
                                                            .listDropDown)
                                                    .add(new AnchorListItemBuilder()
                                                            .text("Alt Page")
                                                            .targetHistoryToken(TOK_ALT)
                                                            .anchorListItem)
                                                    .add(new ListDropDownBuilder()
                                                            .add(new AnchorButtonBuilder()
                                                                    .dataToggle(Toggle.DROPDOWN)
                                                                    .text("Sub Menu")
                                                                    .anchorButton)
                                                            .add(new DropDownMenuBuilder()
                                                                    .add(new AnchorListItemBuilder()
                                                                            .text("Alt Page Too")
                                                                            .targetHistoryToken(TOK_ALT)
                                                                            .anchorListItem)
                                                                    .add(new AnchorListItemBuilder()
                                                                            .text("Home Too")
                                                                            .targetHistoryToken(TOK_HOME)
                                                                            .anchorListItem)
                                                                    .dropDownMenu)
                                                            .listDropDown)
                                                    .add(new ListItemBuilder()
                                                            .add(btnHomePlace = new ButtonBuilder()
                                                                    .text("Home via Place")
                                                                    .marginTop(8)
                                                                    .type(ButtonType.WARNING)
                                                                    .button)
                                                            .listItem)
                                                    .add(new ListItemBuilder()
                                                            .add(btnHomeHistory = new ButtonBuilder()
                                                                    .text("Home via History")
                                                                    .marginTop(8)
                                                                    .type(ButtonType.SUCCESS)
                                                                    .button)
                                                            .listItem)
                                                    .navbarNav)
                                            .navbarCollapse)
                                    .container)
                            .navbar)
                    .add(slotPanel)
                    .container
            ));

            // setup responsive nav bar
            collapseButton.setDataTargetWidget(navbarCollapse);

            bus.addHandler(NavigationEvent.getType(), new NavigationHandler() {
                @Override
                public void onNavigation(NavigationEvent navigationEvent) {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            // Making the window scroll to top on every page change
                            Window.scrollTo(0, 0);
                            // and collapse any nav menus
                            hideNavbarCollapse();
                        }
                    });
                }
            });

            Window.addWindowScrollHandler(new Window.ScrollHandler() {
                @Override
                public void onWindowScroll(Window.ScrollEvent event) {
                    hideNavbarCollapse();
                    boolean nearTop = event.getScrollTop() <= 50;
                    StyleHelper.toggleStyleName(navbar, !nearTop, "top-nav-collapse");
                }
            });
        }

        private void hideNavbarCollapse() {
            String ariaExpanded = navbarCollapse.getElement().getAttribute("aria-expanded");
            if (Boolean.parseBoolean(ariaExpanded)) {
                navbarCollapse.toggle();
            }
        }

        public Button getBtnHomePlace() {
            return btnHomePlace;
        }

        public Button getBtnHomeHistory() {
            return btnHomeHistory;
        }
    }
}

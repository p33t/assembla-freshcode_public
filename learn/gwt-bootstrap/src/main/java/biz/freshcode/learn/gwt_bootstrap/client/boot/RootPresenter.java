package biz.freshcode.learn.gwt_bootstrap.client.boot;

import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.*;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
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
import org.gwtbootstrap3.client.ui.Navbar;
import org.gwtbootstrap3.client.ui.NavbarCollapse;
import org.gwtbootstrap3.client.ui.NavbarCollapseButton;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.NavbarPosition;
import org.gwtbootstrap3.client.ui.constants.Toggle;

import static biz.freshcode.learn.gwt_bootstrap.client.boot.BootBundle.BOOT_BUNDLE;
import static biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken.TOK_ALT;
import static biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken.TOK_HOME;

public class RootPresenter extends Presenter<View, RootPresenter.Proxy> {
    public static final NestedSlot SLOT = new NestedSlot();

    @Inject
    public RootPresenter(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RevealType.Root);
    }

    @ProxyStandard
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.Proxy<RootPresenter> {
    }

    public static class View extends ViewImpl {
        private final SimplePanel slotPanel = new SimplePanel();
        private final Navbar navbar;
        private NavbarCollapse navbarCollapse;

        @Inject
        public View() {
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
                                                    .navbarNav)
                                            .navbarCollapse)
                                    .container)
                            .navbar)
                    .add(slotPanel)
                    .container
            ));

            // setup responsive nav bar
            collapseButton.setDataTargetWidget(navbarCollapse);

            // Making the window scroll to top on every page change
            History.addValueChangeHandler(new ValueChangeHandler<String>() {
                @Override
                public void onValueChange(ValueChangeEvent<String> event) {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            Window.scrollTo(0, 0);
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
    }
}

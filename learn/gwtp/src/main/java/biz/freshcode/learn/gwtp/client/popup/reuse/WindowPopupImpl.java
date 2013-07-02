package biz.freshcode.learn.gwtp.client.popup.reuse;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PopupViewCloseHandler;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.HideEvent;

public class WindowPopupImpl implements PopupView {
    private Window window;
    private HandlerRegistration hideRego;
    private final EventBus eventBus;
    private final NavigationHandler hideOnNavigate = new NavigationHandler() {
        @Override
        public void onNavigation(NavigationEvent evt) {
            hide();
        }
    };
    private HandlerRegistration closeRego;
    private boolean needCenter;

    public WindowPopupImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    protected void initWidget(Window w) {
        this.window = w;
    }

    @Override
    public Widget asWidget() {
        return window;
    }

    @Override
    public void center() {
        if (window.isVisible()) {
            window.center();
            needCenter = false;
        } else {
            // cannot call window.center() when not visible.
            needCenter = true;
        }
    }

    @Override
    public void hide() {
        window.hide();
    }

    @Override
    public void setAutoHideOnNavigationEventEnabled(boolean autoHide) {
        if (autoHide) {
            if (hideRego == null) hideRego = eventBus.addHandler(NavigationEvent.getType(), hideOnNavigate);
        } else if (hideRego != null) {
            hideRego.removeHandler();
            hideRego = null;
        }
    }

    @Override
    public void setCloseHandler(final PopupViewCloseHandler handler) {
        if (closeRego != null) {
            closeRego.removeHandler();
            closeRego = null;
        }

        if (handler != null) {
            closeRego = window.addHideHandler(new HideEvent.HideHandler() {
                @Override
                public void onHide(HideEvent event) {
                    handler.onClose();
                }
            });
        }
    }

    @Override
    public void setPosition(int left, int top) {
        window.setPosition(left, top);
    }

    @Override
    public void show() {
        window.show();
        if (needCenter) {
            needCenter = false;
            window.center();
        }
    }

    @Override
    public void addToSlot(Object slot, IsWidget content) {
        // nothing
    }

    @Override
    public void removeFromSlot(Object slot, IsWidget content) {
        // nothing
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        // nothing
    }
}

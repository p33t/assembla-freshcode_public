package biz.freshcode.learn.gwtp.client.boot;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

/**
 * A surrogate 'Root' that would use a ViewPort if using GXT.
 */
public class Root extends Presenter<Root.View, Root.Proxy> {
    @ContentSlot
    public static final GwtEvent.Type<RevealContentHandler<?>> SLOT = new GwtEvent.Type<RevealContentHandler<?>>();

    @Inject
    public Root(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RevealType.Root);
    }

    @ProxyStandard
    public static interface Proxy extends com.gwtplatform.mvp.client.proxy.Proxy<Root> {
    }

    public static class View extends ViewImpl {
        // NOTE: This would probably be a ViewPort in GXT.
        private final SimplePanel pnl = new SimplePanel();

        @Inject
        public View() {
            pnl.setHeight("100%");
            pnl.setWidth("100%");
            initWidget(pnl);
        }

        @Override
        public void setInSlot(Object slot, IsWidget content) {
            if (slot == SLOT) {
               pnl.clear();
                if (content != null) pnl.setWidget(content);
            }
            else super.setInSlot(slot, content);
        }
    }
}

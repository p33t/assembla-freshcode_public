package biz.freshcode.learn.gwtp.client.slotless;

import biz.freshcode.learn.gwtp.client.boot.Root;
import biz.freshcode.learn.gwtp.client.builder.gxt.ContentPanelBuilder;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.sencha.gxt.widget.core.client.ContentPanel;

public class SlotlessDemo extends Presenter<SlotlessDemo.View, SlotlessDemo.Proxy> {
    public static final String TOKEN = "slotlessDemo";

    @ContentSlot
    public static final GwtEvent.Type<RevealContentHandler<?>> SLOT = new GwtEvent.Type<RevealContentHandler<?>>();

    @Override
    protected void onReveal() {
        GWT.log("SlotlessDemo.onReveal()");
        super.onReveal();
    }

    @Inject
    public SlotlessDemo(EventBus eventBus, View view, Proxy proxy, SlotlessChild child) {
        super(eventBus, view, proxy, Root.SLOT);
        setInSlot(SLOT, child);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<SlotlessDemo> {
    }

    public static class View extends ViewImpl {
        private final ContentPanel pnl;

        @Inject
        public View() {
            initWidget(pnl = new ContentPanelBuilder()
                    .headingText("Demo title")
                    // Prevents lifecycle methods... .widget(child)
                    .contentPanel);
        }

        @Override
        public void setInSlot(Object slot, IsWidget content) {
            if (slot == SLOT) {
                if (content == null) pnl.clear();
                else pnl.setWidget(content);
                pnl.forceLayout();
            }
            super.setInSlot(slot, content);
        }
    }
}

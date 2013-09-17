package biz.freshcode.learn.gwtp.client.slotless;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;

public class SlotlessChild extends Presenter<SlotlessChild.View, SlotlessChild.Proxy> {
    @Inject
    public SlotlessChild(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy);
    }

    @Override
    protected void onReveal() {
        // NOTE: This is not being called when not part of a slot!!!
        GWT.log("SlotlessChild.onReveal()");
        super.onReveal();
    }

    @ProxyStandard
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.Proxy<SlotlessChild> {
    }

    public static class View extends ViewImpl {
        @Inject
        public View() {
            initWidget(new HTML("<p>Slotless Child</p>"));
        }
    }
}

package biz.freshcode.learn.gwtp.client.slotless;

import biz.freshcode.learn.gwtp.client.boot.Root;
import biz.freshcode.learn.gwtp.client.builder.gxt.ContentPanelBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.ContentPanel;

public class SlotlessDemo extends Presenter<SlotlessDemo.View, SlotlessDemo.Proxy> {
    public static final String TOKEN = "slotlessDemo";

    @Inject
    public SlotlessDemo(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<SlotlessDemo> {
    }

    public static class View extends ViewImpl {
        @Inject
        public View(SlotlessChild child) {
            initWidget(new ContentPanelBuilder()
                    .headingText("Demo title")
                    .widget(child)
                    .contentPanel);
        }
    }
}

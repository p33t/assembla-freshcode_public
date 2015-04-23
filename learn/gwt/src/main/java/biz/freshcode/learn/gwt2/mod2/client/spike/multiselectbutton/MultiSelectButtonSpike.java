package biz.freshcode.learn.gwt2.mod2.client.spike.multiselectbutton;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import biz.freshcode.learn.gwt2.mod2.client.spike.multiselectbutton.reuse.MultiSelectButton;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import java.util.HashMap;
import java.util.Map;

public class MultiSelectButtonSpike extends Presenter<MultiSelectButtonSpike.View, MultiSelectButtonSpike.Proxy> {
    public static final String TOKEN = "multiSelectButtonSpike";

    @Inject
    public MultiSelectButtonSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends ProxyPlace<MultiSelectButtonSpike> {
    }

    public static class View extends ViewImpl {

        @Inject
        public View() {
            MultiSelectButton x0 = new MultiSelectButton("Checks0: ");

            MultiSelectButton x1 = new MultiSelectButton("Checks1: ");
            Map<String, String> opts1 = new HashMap<>();
            opts1.put("1", "One");
            x1.setOptions(opts1);

            MultiSelectButton x2 = new MultiSelectButton("Checks2: ");
            Map<String, String> opts2 = new HashMap<>();
            opts2.put("1", "One");
            opts2.put("2", "Two");
            x2.setOptions(opts2);

            initWidget(new HorizontalLayoutContainerBuilder()
                                .add(x0)
                                .add(x1)
                                .add(x2)
                    .horizontalLayoutContainer);
        }
    }
}

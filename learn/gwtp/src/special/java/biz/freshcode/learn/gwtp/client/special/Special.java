package biz.freshcode.learn.gwtp.client.special;

import biz.freshcode.learn.gwtp.client.boot.Root;
import biz.freshcode.learn.gwtp.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwtp.client.builder.gxt.form.FieldLabelBuilder;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class Special extends Presenter<Special.View, Special.Proxy> {
    public static final String TOKEN = "special";

    @Inject
    public Special(EventBus bus, View view, Proxy proxy) {
        super(bus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends ProxyPlace<Special> {
    }

    public static class View extends ViewImpl {
        @Inject
        public View(SpecialFields fields) {
            initWidget(new VerticalLayoutContainerBuilder()
                    .add(new FieldLabelBuilder()
                            .text("Vanilla-ish Field")
                            .widget(fields.vanilla)
                            .fieldLabel, new VerticalLayoutContainer.VerticalLayoutData(1, -1))
                    .add(new FieldLabelBuilder()
                            .text("Special Field")
                            .widget(fields.special)
                            .fieldLabel, new VerticalLayoutContainer.VerticalLayoutData(1, -1))
                    .verticalLayoutContainer);
        }
    }
}

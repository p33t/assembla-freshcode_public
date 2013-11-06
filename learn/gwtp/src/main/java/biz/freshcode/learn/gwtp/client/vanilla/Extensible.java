package biz.freshcode.learn.gwtp.client.vanilla;

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
import com.sencha.gxt.widget.core.client.form.TextField;

import static com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

/**
 * A feature that is part of a vanilla installation.
 */
public class Extensible extends Presenter<Extensible.View, Extensible.Proxy> {
    public static final String TOKEN = "extensible";

    @Inject
    public Extensible(EventBus bus, View view, Proxy proxy) {
        super(bus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends ProxyPlace<Extensible> {
    }

    public static class View extends ViewImpl {
        @Inject
        public View() {
            initWidget(new VerticalLayoutContainerBuilder()
                    .add(new FieldLabelBuilder()
                            .text("Vanilla Field")
                            .widget(new TextField())
                            .fieldLabel, new VerticalLayoutData(1, -1))
                    .verticalLayoutContainer);
        }
    }
}

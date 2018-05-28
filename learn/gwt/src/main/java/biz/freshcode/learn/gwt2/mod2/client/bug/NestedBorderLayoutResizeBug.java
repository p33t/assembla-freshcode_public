package biz.freshcode.learn.gwt2.mod2.client.bug;

import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;

public class NestedBorderLayoutResizeBug extends Presenter<NestedBorderLayoutResizeBug.View, NestedBorderLayoutResizeBug.Proxy> {
    public static final String TOKEN = "nestedBorderLayoutResizeBug";

    @Inject
    public NestedBorderLayoutResizeBug(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<NestedBorderLayoutResizeBug> {
    }

    public static class View extends ViewImpl {

        @Inject
        public View() {
            BorderLayoutContainer blc = new BorderLayoutContainer();
            blc.setNorthWidget(new HtmlLayoutContainer(
                    new SafeHtmlBuilder()
                            .appendEscapedLines("Nested border layouts worked with resizable panels in 4.0.2, but not in 4.0.3")
                            .toSafeHtml()),
                    new BorderLayoutContainer.BorderLayoutData(0.3)
            );
            initWidget(blc);
        }
    }
}

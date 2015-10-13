package biz.freshcode.learn.gwt2.mod2.client.boot;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.ViewportBuilder;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

public class Root extends Presenter<Root.View, Root.Proxy> {
    public static final NestedSlot SLOT = new NestedSlot();

    @Inject
    public Root(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RevealType.Root);
    }

    @ProxyStandard
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.Proxy<Root> {
    }

    public static class View extends ViewImpl {
        private final BorderLayoutContainer blc;

        @Inject
        public View(RootMenu menu) {
            // NOTE: Don't bindSlot... not sure if forceLayout() will be called.
            initWidget(new ViewportBuilder()
                    .add(blc = new BorderLayoutContainerBuilder()
                            .northWidget(menu, new BorderLayoutData(30))
                            .borderLayoutContainer)
                    .viewport);
        }

        @Override
        public void setInSlot(Object slot, IsWidget content) {
            if (slot == SLOT) {
                if (content == null) {
                    Widget center = blc.getCenterWidget();
                    if (center != null) center.removeFromParent();
                } else
                    blc.setCenterWidget(content);
                blc.forceLayout();
            } else
                super.setInSlot(slot, content);
        }
    }
}

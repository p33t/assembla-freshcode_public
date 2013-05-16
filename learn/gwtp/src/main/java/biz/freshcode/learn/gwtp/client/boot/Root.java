package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwtp.client.builder.gxt.toolbar.ToolBarBuilder;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

import static biz.freshcode.learn.gwtp.client.util.WidgetUtil.removeFromParent;
import static com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

/**
 * A surrogate 'Root' that facilitates GXT Viewport usage.
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
        private final BorderLayoutContainer blc;

        @Inject
        public View(AppMenu mnu) {
            Viewport v = new Viewport();
            v.add(blc = new BorderLayoutContainerBuilder()
                    .northWidget(mnu, new BorderLayoutData(25))
                    .southWidget(new ToolBarBuilder()
                            .add(new FillToolItem())
                            .add(new LabelToolItem("The Footer Message"))
                            .add(new FillToolItem())
                            .toolBar, new BorderLayoutData(20))
                    .borderLayoutContainer);
            initWidget(v);
        }

        @Override
        public void setInSlot(Object slot, IsWidget content) {
            if (slot == SLOT) {
                removeFromParent(blc.getCenterWidget());
                if (content != null) blc.setCenterWidget(content);
            } else super.setInSlot(slot, content);
        }
    }
}

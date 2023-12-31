package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwtp.client.builder.gxt.toolbar.ToolBarBuilder;
import biz.freshcode.learn.gwtp.util.client.HasTitle;
import com.google.gwt.core.client.GWT;
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

import java.util.Date;

import static biz.freshcode.learn.gwtp.util.client.WidgetUtil.removeFromParent;
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
        private PageTitle titler;

        @Inject
        public View(AppMenu mnu, RoughServerTime serverTime) {
            Viewport v = new Viewport();
            GWT.log("Viewport created at " + new Date(serverTime.get()));
            v.add(blc = new BorderLayoutContainerBuilder()
                    .northWidget(mnu, new BorderLayoutData(30))
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
                String title = "(no title)";
                if (content != null) {
                    blc.setCenterWidget(content);
                    if (content instanceof HasTitle) {
                        title = ((HasTitle) content).getTitle();
                    }
                }
                titler.setTitle(title);
                blc.forceLayout();
            } else super.setInSlot(slot, content);
        }
    }
}

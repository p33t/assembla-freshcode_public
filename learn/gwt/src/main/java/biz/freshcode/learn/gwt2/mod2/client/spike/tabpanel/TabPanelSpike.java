package biz.freshcode.learn.gwt2.mod2.client.spike.tabpanel;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.TabPanelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import biz.freshcode.learn.gwt2.mod2.client.spike.tabpanel.reuse.DynamicTabPanelSupport;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.TextArea;

public class TabPanelSpike extends Presenter<TabPanelSpike.View, TabPanelSpike.Proxy> {
    public static final String TOKEN = "tabPanel";
    private Dynamic dynamic = new Dynamic();

    @Inject
    public TabPanelSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
        dynamic.ensureAddTab();
    }

    private class Dynamic extends DynamicTabPanelSupport {
        @Override
        protected TabPanel getTabPanel() {
            return getView().getTabPanel();
        }

        @Override
        protected Widget createTabWidget(int ix) {
            return new TextArea();
        }

        @Override
        protected String calcTabText(int ix, Widget w) {
            return "Tab " + ix;
        }
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<TabPanelSpike> {
    }

    public static class View extends ViewImpl {
        private TabPanel tabPanel;

        @Inject
        public View() {
            initWidget(new VerticalLayoutContainerBuilder()
                    .scrollMode(ScrollSupport.ScrollMode.AUTOY)
                    .add(tabPanel = new TabPanelBuilder()
//                          .closeContextMenu(true) // enables the 'Close other tabs' menu
                            .tabPanel, new VerticalLayoutData(1.0, 150))
                    .verticalLayoutContainer);
        }

        public TabPanel getTabPanel() {
            return tabPanel;
        }
    }
}

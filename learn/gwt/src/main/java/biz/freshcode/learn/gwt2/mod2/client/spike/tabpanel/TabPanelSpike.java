package biz.freshcode.learn.gwt2.mod2.client.spike.tabpanel;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.TabItemConfigBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.TabPanelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CloseEvent;
import com.sencha.gxt.widget.core.client.form.TextArea;

public class TabPanelSpike extends Presenter<TabPanelSpike.View, TabPanelSpike.Proxy> {
    public static final String TOKEN = "tabPanel";
    private static final String ADD_TAB = "(Add)";

    @Inject
    public TabPanelSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
        view.getTabPanel().add(new TextArea(), new TabItemConfigBuilder()
                .text("Tab 0")
//                                    First tab closure causes a white rectangle
//                                    .closable(true)
                .tabItemConfig);
        ensureAddTab();
        view.getTabPanel().addSelectionHandler(new SelectionHandler<Widget>() {
            @Override
            public void onSelection(SelectionEvent<Widget> event) {
                TabPanel tp = tp();
                Widget w = event.getSelectedItem();
                TabItemConfig config = tp.getConfig(w);
                if (ADD_TAB.equals(config.getText())) {
                    config.setClosable(true);
                    config.setText("Tab " + (tp.getWidgetCount() - 1));
                    tp.update(w, config);
                    ensureAddTab();
                }
            }
        });
        view.getTabPanel().addCloseHandler(new CloseEvent.CloseHandler<Widget>() {
            @Override
            public void onClose(CloseEvent<Widget> event) {
                TabPanel tp = tp();
                int count = tp.getWidgetCount();
                for (int i = 0; i < count; i++) {
                    Widget w = tp.getWidget(i);
                    TabItemConfig config = tp.getConfig(w);
                    if (!ADD_TAB.equals(config.getText())) {
                        // a conventional tab
                        String desiredText = "Tab " + i;
                        if (!config.getText().equals(desiredText)) {
                            // need to update text
                            config.setText(desiredText);
                            tp.update(w, config);
                        }
                    }
                }
            }
        });
    }

    private TabPanel tp() {
        return getView().getTabPanel();
    }

    private void ensureAddTab() {
        TabPanel tp = tp();
        Widget w = tp.findItem(ADD_TAB, true);
        if (w == null) {
            tp.add(new TextArea(), new TabItemConfigBuilder()
                    .text(ADD_TAB)
                    .tabItemConfig);
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

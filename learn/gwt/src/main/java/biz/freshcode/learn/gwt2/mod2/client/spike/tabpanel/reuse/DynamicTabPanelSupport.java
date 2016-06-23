package biz.freshcode.learn.gwt2.mod2.client.spike.tabpanel.reuse;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.TabItemConfigBuilder;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.event.CloseEvent;

/**
 * Support for adding panels dynamically.  There must be at least 1 genuine tab, otherwise
 */
public abstract class DynamicTabPanelSupport {
    private static final String ADD_TAB = "(Add)";
    private boolean needInit = true;

    /**
     * Ensures there is a tab with '(Add)' as the text for adding new tabs.
     */
    public void ensureAddTab() {
        TabPanel tp = getTabPanel();
        ensureInit();

        Widget w = tp.findItem(ADD_TAB, true);
        if (w == null) {
            int ix = tp.getWidgetCount();
            Widget wAdd = createTabWidget(ix);
            tp.add(wAdd, new TabItemConfigBuilder()
                    .text(ADD_TAB)
                    // NOTE: Not closable
                    .tabItemConfig);
        }
    }

    /**
     * Traverse the tabs, ensuring their text values are up-to-date.
     */
    public void updateTabTexts() {
        TabPanel tp = getTabPanel();
        int count = tp.getWidgetCount();
        for (int i = 0; i < count; i++) {
            Widget w = tp.getWidget(i);
            TabItemConfig config = tp.getConfig(w);
            if (!ADD_TAB.equals(config.getText())) {
                // a conventional tab
                String desiredText = calcTabText(i, w);
                if (!config.getText().equals(desiredText)) {
                    // need to update text
                    config.setText(desiredText);
                    tp.update(w, config);
                }
            }
        }
    }

    /**
     * The tab panel being managed.
     */
    protected abstract TabPanel getTabPanel();

    /**
     * Creates a widget to place on a fresh tab.
     */
    protected abstract Widget createTabWidget(int ix);

    /**
     * Calculates the text to place at the top of a tab.
     * Tab text's are recalculated with a tab is removed.
     */
    protected abstract String calcTabText(int ix, Widget w);

    private void ensureInit() {
        if (needInit) {
            needInit = false;

            // need tab 0
            final TabPanel tp = getTabPanel();
            if (tp.getWidgetCount() == 0) {
                Widget w0 = createTabWidget(0);
                String text = calcTabText(0, w0);
                // NOTE: tab 0 cannot be closed (no tabs causes white rectangle)
                tp.add(w0, new TabItemConfig(text));
            }

            // removing tabs
            tp.addCloseHandler(new CloseEvent.CloseHandler<Widget>() {
                @Override
                public void onClose(CloseEvent<Widget> event) {
                    // NOTE: Don't need to worry about (Add) tab becomin 'active'.
                    // TabPanel seems to remember history and all other tabs have priority.
                    updateTabTexts();
                }
            });

            // adding tabs
            tp.addSelectionHandler(new SelectionHandler<Widget>() {
                @Override
                public void onSelection(SelectionEvent<Widget> event) {
                    Widget w = event.getSelectedItem();
                    if (w != null) {
                        TabItemConfig config = tp.getConfig(w);
                        if (ADD_TAB.equals(config.getText())) {
                            // selected the add tab
                            // convert to conventional tab
                            config.setClosable(true);
                            int ix = tp.getWidgetCount() - 1;
                            String text = calcTabText(ix, w);
                            config.setText(text);
                            tp.update(w, config);
                            ensureAddTab();
                        }
                    }
                }
            });
        }
    }
}

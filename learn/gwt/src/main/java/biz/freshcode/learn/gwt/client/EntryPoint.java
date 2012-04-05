package biz.freshcode.learn.gwt.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import biz.freshcode.learn.gwt.client.uispike.UiSpikePanel;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint {
    @Override
    public void onModuleLoad() {
        UiSpikePanel main = new UiSpikePanel();
        // RootLayoutPanel implements ProvidesResize
        RootLayoutPanel.get().add(main);
        DOM.removeChild(RootPanel.getBodyElement(), DOM.getElementById("loading"));
    }
}

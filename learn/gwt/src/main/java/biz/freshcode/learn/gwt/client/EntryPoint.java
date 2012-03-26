package biz.freshcode.learn.gwt.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint {
    @Override
    public void onModuleLoad() {
        MainPanel main = new MainPanel();
        // RootLayoutPanel causes an error here... not sure why.
        RootPanel.get().add(main);
        DOM.removeChild(RootPanel.getBodyElement(), DOM.getElementById("loading"));
    }
}

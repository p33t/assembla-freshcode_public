package biz.freshcode.learn.gwt.client;

import com.google.gwt.user.client.ui.RootPanel;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint {
    @Override
    public void onModuleLoad() {
        MainPanel main = new MainPanel();
        RootPanel.get().add(main);
    }
}

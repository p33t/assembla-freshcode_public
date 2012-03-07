package biz.freshcode.learn.gwt.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.layout.VLayout;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint {
    @Override
    public void onModuleLoad() {
        MainPanel main = new MainPanel();
        main.setHeight100();
        main.setWidth100();
        RootPanel.get().add(main);
    }
}

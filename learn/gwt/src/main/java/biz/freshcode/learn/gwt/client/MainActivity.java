package biz.freshcode.learn.gwt.client;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HTMLPanel;

public class MainActivity extends AbstractActivity {
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        panel.setWidget(new HTMLPanel("<p>Default Activity!</p>"));
    }
}

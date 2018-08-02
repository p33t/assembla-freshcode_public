package biz.freshcode.learn.gwt.client;

import biz.freshcode.learn.gwt2.common.shared.SessionInfo;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;

public class MainActivity extends AbstractActivity {
    @Inject
    private SessionInfo session;

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        panel.setWidget(new HTMLPanel("<p>Default Activity!</p><p>User Name is '" + session.getUserName() + "'</p>"));
    }
}

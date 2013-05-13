package biz.freshcode.learn.gwt.client.inject;

import biz.freshcode.learn.gwt.client.GreetingService;
import biz.freshcode.learn.gwt.client.GreetingServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import javax.inject.Provider;

import static biz.freshcode.learn.gwt.client.util.ExceptionUtil.illegalState;

public class SessionInfoProvider implements Provider<SessionInfo> {
    private static SessionInfo.Bean sessionInfo;

    /**
     * Needs to be called before the module is used.
     */
    public static void init(final Runnable onCompletion) {
        GreetingServiceAsync s = GWT.create(GreetingService.class);
        s.loadSessionInfo(new AsyncCallback<SessionInfo.Bean>() {
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("Failed to retrieve Session Info");
            }

            @Override
            public void onSuccess(SessionInfo.Bean result) {
                GWT.log("Successfully retrieved session info bean.  Username:" + result.getUserName());
                sessionInfo = result;
                onCompletion.run();
            }
        });
    }

    @Override
    public SessionInfo get() {
        if (sessionInfo == null) throw illegalState("Session Info not ready.");
        return sessionInfo;
    }
}
package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.shared.boot.SessionInfo;

import javax.inject.Provider;

import static biz.freshcode.learn.gwtp.client.util.ExceptionUtil.illegalState;

public class SessionInfoProvider implements Provider<SessionInfo> {
    private static SessionInfo sessionInfo;

    /**
     * Needs to be called before the module is used.
     */
    public static void init(SessionInfo sessionInfo) {
        if (SessionInfoProvider.sessionInfo != null) throw illegalState("Can only init once.");
        SessionInfoProvider.sessionInfo = sessionInfo;
    }

    @Override
    public SessionInfo get() {
        if (sessionInfo == null) throw illegalState("Session Info not ready.");
        return sessionInfo;
    }
}

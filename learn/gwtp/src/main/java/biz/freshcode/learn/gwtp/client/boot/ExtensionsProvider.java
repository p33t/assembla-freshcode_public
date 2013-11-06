package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.shared.boot.Extensions;
import biz.freshcode.learn.gwtp.shared.boot.SessionInfo;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ExtensionsProvider implements Provider<Extensions> {
    @Inject
    private SessionInfo sessionInfo;

    @Override
    public Extensions get() {
        return sessionInfo.getExtensions();
    }
}

package biz.freshcode.learn.gwt.server.dispatch.guice;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.rpc.server.AbstractRandomSecurityCookieFilter;
import com.gwtplatform.dispatch.shared.SecurityCookie;

import java.security.SecureRandom;

@Singleton
public class DummyRandomSecurityCookieFilter extends AbstractRandomSecurityCookieFilter {
    @Inject
    public DummyRandomSecurityCookieFilter(@SecurityCookie String securityCookieName) {
        super(securityCookieName, new SecureRandom(){
            @Override
            public synchronized void nextBytes(byte[] bytes) {
                // do nothing
            }
        });
    }
}

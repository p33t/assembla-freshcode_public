package biz.freshcode.learn.gwtp.client.boot.inject;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.SecurityCookie;

public class FullyInjected {
    @Inject
    @SecurityCookie
    private String securityCookie;

    @Override
    public String toString() {
        return securityCookie;
    }
}

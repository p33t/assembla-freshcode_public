package biz.freshcode.learn.gwtp.shared.boot;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Fundamental information required at the client.
 * This will be a singleton in DI.
 */
public interface SessionInfo extends IsSerializable {
    /**
     * Time at server when session info was sent.
     */
    long getBootTime();
}

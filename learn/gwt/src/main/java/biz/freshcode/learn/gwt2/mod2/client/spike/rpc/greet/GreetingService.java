package biz.freshcode.learn.gwt2.mod2.client.spike.rpc.greet;

import biz.freshcode.learn.gwt2.common.shared.SessionInfo;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
    String greetServer(String name) throws IllegalArgumentException;

    String readCookie(String cookieName);

    /**
     * Dodgy hack to experiment with session info loading.
     */
    SessionInfo.Bean loadSessionInfo();
}

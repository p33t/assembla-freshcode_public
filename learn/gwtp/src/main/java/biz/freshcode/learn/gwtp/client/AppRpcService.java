package biz.freshcode.learn.gwtp.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 * NOTE: Pretty sure gwt:generateAsync is useless and remote services need to end in 'Service'.
 */
@RemoteServiceRelativePath(AppRpcService.PATH)
public interface AppRpcService extends RemoteService {
    String PATH = "appRpc";

    /**
     * Dodgy hack to experiment with session info loading.
     */
    String loadSessionInfo();
}

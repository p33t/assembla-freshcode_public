package biz.freshcode.learn.gwtp.server;

import biz.freshcode.learn.gwtp.client.AppRpcService;
import biz.freshcode.learn.gwtp.client.boot.SessionInfo;
import biz.freshcode.learn.gwtp.client.boot.SessionInfoImpl;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("GwtServiceNotRegistered")
public class AppRpcServiceImpl extends RemoteServiceServlet implements AppRpcService {
    @Override
    public SessionInfo loadSessionInfo() {
        return new SessionInfoImpl(System.currentTimeMillis());
    }
}

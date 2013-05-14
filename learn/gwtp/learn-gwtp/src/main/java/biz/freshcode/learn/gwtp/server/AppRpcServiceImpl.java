package biz.freshcode.learn.gwtp.server;

import biz.freshcode.learn.gwtp.client.AppRpcService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("GwtServiceNotRegistered")
public class AppRpcServiceImpl extends RemoteServiceServlet implements AppRpcService {
    @Override
    public String loadSessionInfo() {
        return "Session-" + System.currentTimeMillis();
    }
}

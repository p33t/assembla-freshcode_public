package biz.freshcode.learn.gwtp.server;

import biz.freshcode.learn.gwtp.shared.AppRpcService;
import biz.freshcode.learn.gwtp.shared.boot.SessionInfo;
import biz.freshcode.learn.gwtp.shared.boot.SessionInfoImpl;
import biz.freshcode.learn.gwtp.shared.boot.SpecialExtensions;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("GwtServiceNotRegistered")
public class AppRpcServiceImpl extends RemoteServiceServlet implements AppRpcService {
    @Override
    public SessionInfo loadSessionInfo() {
        return new SessionInfoImpl(System.currentTimeMillis(),
//                Extensions.VANILLA
                SpecialExtensions.SPECIAL
        );
    }
}

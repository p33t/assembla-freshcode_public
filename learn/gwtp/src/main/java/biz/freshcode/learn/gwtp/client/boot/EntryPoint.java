package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.client.AppRpcService;
import biz.freshcode.learn.gwtp.client.AppRpcServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.ApplicationController;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint {
    @Override
    public void onModuleLoad() {
        // NOTE: Would hide 'Loading..' element here.

        GWT.log("In onModuleLoad().");
        AppRpcServiceAsync rpc = GWT.create(AppRpcService.class);
        rpc.loadSessionInfo(new AsyncCallback<SessionInfo>() {
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("loadSessionInfo() failed.  Cannot start app.", caught);
            }

            @Override
            public void onSuccess(SessionInfo info) {
                long localBootTime = System.currentTimeMillis();
                GWT.log("Session info loaded.  Launching app...");
                AppModule.init(info, localBootTime);
                launch();
            }
        });
    }

    private void launch() {
        // NOTE: This is exactly like MvpWithEntryPoint alternative but we have control over when to bootstrap.
        ApplicationController c = GWT.create(ApplicationController.class);
        c.init();
    }
}

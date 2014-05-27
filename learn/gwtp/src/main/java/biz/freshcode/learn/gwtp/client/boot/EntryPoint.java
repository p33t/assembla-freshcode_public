package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.shared.AppRpcService;
import biz.freshcode.learn.gwtp.shared.AppRpcServiceAsync;
import biz.freshcode.learn.gwtp.shared.boot.SessionInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtplatform.mvp.client.ApplicationController;

import java.util.logging.Level;

import static biz.freshcode.learn.gwtp.client.util.AppClientUtil.LOG;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint {
    @Override
    public void onModuleLoad() {
        LOG.info("In onModuleLoad().");
        AppRpcServiceAsync rpc = GWT.create(AppRpcService.class);
        rpc.loadSessionInfo(new AsyncCallback<SessionInfo>() {
            @Override
            public void onFailure(Throwable caught) {
                removeLoading();
                LOG.log(Level.SEVERE, "loadSessionInfo() failed.  Cannot start app.", caught);
                Window.alert("Failed to start application.");
            }

            @Override
            public void onSuccess(SessionInfo info) {
                // NOTE: Time capture needs to be first command
                long localBootTime = System.currentTimeMillis();
                RoughServerTime.init(localBootTime, info.getBootTime());
                SessionInfoProvider.init(info);
                LOG.info("Session info loaded.  Launching app...");
                removeLoading();
                launch();
            }
        });
    }

    private void removeLoading() {
        DOM.removeChild(RootPanel.getBodyElement(), DOM.getElementById("loading"));
    }

    private void launch() {
        // NOTE: This is exactly like MvpWithEntryPoint alternative but we have control over when to bootstrap.
        ApplicationController c = GWT.create(ApplicationController.class);
        c.init();
    }
}

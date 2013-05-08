package biz.freshcode.learn.gwt.client;

import biz.freshcode.learn.gwt.client.inject.AppInjector;
import biz.freshcode.learn.gwt.client.inject.SessionInfoProvider;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;

import javax.inject.Inject;

import static biz.freshcode.learn.gwt.client.AppActivityMapper.DEFAULT_PLACE;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint {
    // The root layout panel being used.
    public static RootLayoutPanel getRoot() {
        return RootLayoutPanel.get();
    }

    @Override
    public void onModuleLoad() {
        SessionInfoProvider.init(new Runnable() {
            @Override
            public void run() {
                // can now complete setup
                AppInjector injector = GWT.create(AppInjector.class);
//                SessionInfoBean bean = injector.sessionBean();
                injector.bootstrap();
            }
        });
    }

    @Singleton
    public static class Bootstrap {
        @Inject
        public Bootstrap(ActivityManager mgr,
                         PlaceHistoryHandler handler,
                         PlaceController ctlr,
                         EventBus bus,
                         MainPanel pnl) {
            GWT.log("Bootstrapping Module");
            mgr.setDisplay(pnl);
            handler.register(ctlr, bus, DEFAULT_PLACE);

            // Remove 'loading' spinner.
            DOM.removeChild(RootPanel.getBodyElement(), DOM.getElementById("loading"));

            getRoot().add(pnl);
            // Goes to the place represented on URL else default place
            handler.handleCurrentHistory();
        }
    }
}

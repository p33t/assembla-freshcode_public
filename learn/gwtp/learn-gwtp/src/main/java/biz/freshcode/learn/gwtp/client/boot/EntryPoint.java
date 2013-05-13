package biz.freshcode.learn.gwtp.client.boot;

import biz.freshcode.learn.gwtp.shared.dispatch.SdAction;
import biz.freshcode.learn.gwtp.shared.dispatch.StrResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.widget.client.TextButton;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.ApplicationController;
import com.gwtplatform.mvp.client.DelayedBindRegistry;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint {
    //    public final ClientGinjector ginjector = GWT.create(ClientGinjector.class);
    private HTML fb;
    private DispatchAsync dispatch;

    @Override
    public void onModuleLoad() {
//        Not used in puzzzlebazaar...
//        ApplicationController c = GWT.create(ApplicationController.class);
//        c.init();

        AppInjector injector = GWT.create(AppInjector.class);
        DelayedBindRegistry.bind(injector);


//        dispatch = injector.getDispatch();
        PlaceManager mgr = injector.getPlaceManager();
        mgr.revealDefaultPlace();

//        RootPanel root = RootPanel.get();
//        TextButton btn;
//        root.add(btn = new TextButton("RPC"));
//        btn.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                go();
//            }
//        });
//        root.add(fb = new HTML("<p>It loaded</p>"));

    }

    private void go() {
        feedback("Calling RPC");
        dispatch.execute(new SdAction("Bruce"), new AsyncCallback<StrResult>() {
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("No can do: " + caught);
            }

            @Override
            public void onSuccess(StrResult result) {
                feedback("Sent 'Bruce'<br/>Result '" + result.getStr() + "'");
            }
        });
    }

    private void feedback(String msg) {
        fb.setHTML(fb.getHTML() + "<p>" + msg + "</p>");
    }
}

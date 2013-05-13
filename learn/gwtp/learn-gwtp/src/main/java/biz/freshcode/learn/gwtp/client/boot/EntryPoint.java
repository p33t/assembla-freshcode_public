package biz.freshcode.learn.gwtp.client.boot;

import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.ApplicationController;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint {
//    private HTML fb;
//    private DispatchAsync dispatch;

    @Override
    public void onModuleLoad() {
        ApplicationController c = GWT.create(ApplicationController.class);
        c.init();

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
//
//    private void go() {
//        feedback("Calling RPC");
//        dispatch.execute(new SdAction("Bruce"), new AsyncCallback<StrResult>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                GWT.log("No can do: " + caught);
//            }
//
//            @Override
//            public void onSuccess(StrResult result) {
//                feedback("Sent 'Bruce'<br/>Result '" + result.getStr() + "'");
//            }
//        });
//    }
//
//    private void feedback(String msg) {
//        fb.setHTML(fb.getHTML() + "<p>" + msg + "</p>");
//    }
}

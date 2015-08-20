package biz.freshcode.learn.gwt_bootstrap.client.boot;

import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.ApplicationController;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint {
    @Override
    public void onModuleLoad() {
        ApplicationController ctrl = GWT.create(ApplicationController.class);
        ctrl.init();
    }
}

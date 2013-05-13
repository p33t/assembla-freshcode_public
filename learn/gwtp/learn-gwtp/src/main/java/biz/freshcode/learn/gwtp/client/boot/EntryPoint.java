package biz.freshcode.learn.gwtp.client.boot;

import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.ApplicationController;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint {
    @Override
    public void onModuleLoad() {
        // NOTE: This is exactly like MvpWithEntryPoint alternative but we have control over when to bootstrap.
        ApplicationController c = GWT.create(ApplicationController.class);
        c.init();
    }
}

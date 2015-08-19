package biz.freshcode.learn.gwt_bootstrap.client.boot;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface BootBundle extends ClientBundle {
    BootBundle BOOT_BUNDLE = GWT.create(BootBundle.class);
//    Style STYLE = INSTANCE.style();
    // Needed
//    boolean DOM_MUTATED = STYLE.ensureInjected();

    @Source("media/logo-sml.png")
    ImageResource logoSml();
}

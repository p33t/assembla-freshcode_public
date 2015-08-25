package biz.freshcode.learn.gwt_bootstrap.client.boot;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface BootBundle extends ClientBundle {
    BootBundle BOOT_BUNDLE = GWT.create(BootBundle.class);
    Style BOOT_STYLE = BOOT_BUNDLE.style();
    // Needed
    boolean DOM_MUTATED = BOOT_STYLE.ensureInjected();

    @Source("media/graphic.png")
    ImageResource graphic();

    @Source("media/logo-sml.png")
    ImageResource logoSml();

    @Source("media/strategy8.png")
    ImageResource strategy8();

    @Source("style.css")
    Style style();

    interface Style extends CssResource {
        String lightBgnd();

        String graphicBgnd();
    }
}

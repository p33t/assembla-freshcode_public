package biz.freshcode.learn.gwt.client.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface Bundle extends ClientBundle {
    // NOTE: These are for use in Java client code (not UI Binder)
    Bundle INSTANCE = GWT.create(Bundle.class);
    // Need to make sure style is injected into module
    boolean DOM_MUTATED = INSTANCE.style().ensureInjected();

    @Source("logo-med.jpg")
    ImageResource logo();

    @Source("style.css")
    Style style();

    interface Style extends CssResource {
        String redText();
    }
}

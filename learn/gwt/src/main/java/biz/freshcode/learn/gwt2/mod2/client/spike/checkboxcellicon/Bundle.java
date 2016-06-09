package biz.freshcode.learn.gwt2.mod2.client.spike.checkboxcellicon;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface Bundle extends ClientBundle {
    Bundle BUNDLE = GWT.create(Bundle.class);
    // Need to make sure style is injected into module
    boolean DOM_MUTATED = BUNDLE.style().ensureInjected();
    Style STYLE = BUNDLE.style();

    @Source("style.gss")
    Style style();

    interface Style extends CssResource {
        String centerAlign();
    }
}

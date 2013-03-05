package biz.freshcode.learn.gwt.client.experiment.flextable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface Bundle extends ClientBundle {
    Bundle BUNDLE = GWT.create(Bundle.class);
    Style STYLE = BUNDLE.style();
    // Needed
    boolean DOM_MUTATED = BUNDLE.style().ensureInjected();

    @Source("icon.png")
    ImageResource iconPng();

    @Source("style.css")
    Style style();

    interface Style extends CssResource {
        String discreteButton();
    }
}

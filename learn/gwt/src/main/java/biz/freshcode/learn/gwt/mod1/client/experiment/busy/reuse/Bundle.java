package biz.freshcode.learn.gwt.mod1.client.experiment.busy.reuse;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface Bundle extends ClientBundle {
    Bundle BUNDLE = GWT.create(Bundle.class);
    Style STYLE = BUNDLE.style();

    // Need to make sure style is injected into module
    boolean DOM_MUTATED = STYLE.ensureInjected();

    @Source("style.css")
    Style style();

    interface Style extends CssResource {
        String busySpinner();
    }

    @Source("spinner.gif")
    ImageResource spinnerGif();
}

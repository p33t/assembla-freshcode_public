package biz.freshcode.learn.gwt.mod1.client.experiment.hoverwidget.reuse;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface Bundle extends ClientBundle {
    Bundle INSTANCE = GWT.create(Bundle.class);
    Style STYLE = INSTANCE.style();
    // Needed
    boolean DOM_MUTATED = STYLE.ensureInjected();

    @Source("style.css")
    Style style();

    interface Style extends CssResource {
        String hoverWidgetPopup();
    }
}

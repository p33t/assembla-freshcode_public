package biz.freshcode.learn.gwt.client.experiment.grid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface Bundle2 extends ClientBundle {
    Bundle2 INSTANCE = GWT.create(Bundle2.class);
    Style STYLE = INSTANCE.style();
    // Needed
    boolean DOM_MUTATED = STYLE.ensureInjected();

    @Source("drag-sml.jpeg")
    ImageResource drag();

    @Source("style2.css")
    Style style();

    interface Style extends CssResource {
        String hoverWidgets();

        String debug();
    }
}

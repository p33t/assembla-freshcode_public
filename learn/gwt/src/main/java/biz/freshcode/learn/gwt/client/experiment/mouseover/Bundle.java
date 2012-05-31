package biz.freshcode.learn.gwt.client.experiment.mouseover;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface Bundle extends ClientBundle {
    Bundle INSTANCE = GWT.create(Bundle.class);
    // Needed
    boolean DOM_MUTATED = INSTANCE.style().ensureInjected();

//    @Source("dropNotAllowed.gif")
//    ImageResource dropNotAllowed();
//
    @Source("style.css")
    Style style();

    interface Style extends CssResource {
        String blackBorder();
    }
}

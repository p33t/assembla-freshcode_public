package biz.freshcode.learn.gwt.client.uibinder;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Bundle extends ClientBundle {
    @Source("logo-med.jpg")
    public ImageResource logo();

    // YIKES: This isn't working...
//From style.css    .redText {
//        color: red;
//    }
//
//    @Source("style.css")
//    public Style style();
//
//    public interface Style extends CssResource {
//        // I think this is checked by the compiler.
//        String redText();
//    }
}

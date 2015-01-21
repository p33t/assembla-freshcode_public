package biz.freshcode.learn.gwt.client.experiment.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ImageResource;

public interface Bundle extends ClientBundle {
    Bundle INSTANCE = GWT.create(Bundle.class);
    Style STYLE = INSTANCE.style();

    // Need to make sure style is injected into module
    boolean DOM_MUTATED = INSTANCE.style().ensureInjected();

    @Source("dirty.gif")
//No effect because usage converts to an uri string...@ImageResource.ImageOptions(repeatStyle = Vertical)
    ImageResource dirtyGif();

    @Source("dirty.gif")
// No effect because cannot ImageResource...@ImageResource.ImageOptions(repeatStyle = Horizontal)
//  NOTE: Used in style.css via gwtmvp-extensions
    DataResource dirtyGif2();

    @Source("dirty.gif")
//  Used by @sprite
//    @ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
    ImageResource dirtyGif3();

    @Source("dirty-right.gif")
    ImageResource dirtyGif4();


    @Source("style.css")
    Style style();

    interface Style extends CssResource {
        String highlight();

        String invalidBgnd();

        String dirtyBgnd();

        String dirtyBgnd2();

        String dirtyBgnd3();

        String dirtyBgnd4();
    }
}

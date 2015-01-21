package biz.freshcode.learn.gwt.mod1.client.experiment.window;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface Bundle extends ClientBundle {
    Bundle BUNDLE = GWT.create(Bundle.class);
    Style STYLE = BUNDLE.style();

    // Need to make sure style is injected into module
    boolean DOM_MUTATED = BUNDLE.style().ensureInjected();

    @Source("bgnd-tile.jpg")
    @ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.Both) // !!! Does nothing
    ImageResource bgndTileJpg();

    @Source("style.css")
    Style style();

    interface Style extends CssResource {
        String whiteBgnd();

        String bgndTile();
    }
}

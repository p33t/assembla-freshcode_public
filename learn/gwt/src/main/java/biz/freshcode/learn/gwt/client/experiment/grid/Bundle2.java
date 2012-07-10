package biz.freshcode.learn.gwt.client.experiment.grid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ImageResource;

public interface Bundle2 extends ClientBundle {
    Bundle2 INSTANCE = GWT.create(Bundle2.class);
    Style STYLE = INSTANCE.style();
    // Needed
    boolean DOM_MUTATED = STYLE.ensureInjected();

    @Source("drag-sml.jpeg")
    ImageResource drag();

    // Cell dirty indicator.  From .../gxt-3.0.0/src/gxt/com/sencha/gxt/theme/base/public/base/images/grid/dirty.gif
    @Source("dirty.gif")
    DataResource dirtyGif();

    @Source("style2.css")
    Style style();

    interface Style extends CssResource {
        String hoverWidgets();

        String debug();

        String dirtyBgnd();

        String centerBgnd();
    }
}

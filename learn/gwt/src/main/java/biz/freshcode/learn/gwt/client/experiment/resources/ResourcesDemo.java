package biz.freshcode.learn.gwt.client.experiment.resources;

import biz.freshcode.learn.gwt.client.uispike.builder.HTMLPanelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;

import static biz.freshcode.learn.gwt.client.experiment.resources.Bundle.STYLE;

public class ResourcesDemo extends AbstractIsWidget {

    private static String dirtyGifUri() {
        // NOTE: Need to serve this up in a method.  Constants aren't resolved yet.
        return Bundle.INSTANCE.dirtyGif().getSafeUri().asString();
    }

    // NOTE: Used in style.css which uses gwt extensions
    public static String dirtyGifCssUrl() {
        return  "URL('" + dirtyGifUri() + "')";
    }

    @Override
    protected Widget createWidget() {
        return new VerticalLayoutContainerBuilder()
                .add(new HTMLPanelBuilder("<p>Style Example</p>")
                        .addStyleName(STYLE.highlight())
                        .hTMLPanel)
                .add(new HTMLPanelBuilder("<p>Background Image</p>")
                        .addStyleName(STYLE.invalidBgnd())
                        .hTMLPanel)
                .add(new HTMLPanelBuilder("<p>Image: <img src='" + dirtyGifUri() + "'/> </p>")
                        .hTMLPanel)
                .add(new HTMLPanelBuilder("<p>Background Image Alternative 1</p>")
                        .addStyleName(STYLE.dirtyBgnd())
                        .hTMLPanel)
                .add(new HTMLPanelBuilder("<p>Background Image Alternative 2</p>")
                        .addStyleName(STYLE.dirtyBgnd2())
                        .hTMLPanel)
                        .verticalLayoutContainer;
    }
}

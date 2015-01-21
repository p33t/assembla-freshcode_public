package biz.freshcode.learn.gwt.mod1.client.experiment.resources;

import biz.freshcode.learn.gwt.mod1.client.builder.gwt.HTMLPanelBuilder;
import biz.freshcode.learn.gwt.mod1.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.mod1.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;

import static biz.freshcode.learn.gwt.mod1.client.experiment.resources.Bundle.STYLE;

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
                .add(new HTMLPanelBuilder("<p>Background Image Alternative 1 @eval</p>")
                        .addStyleName(STYLE.dirtyBgnd())
                        .hTMLPanel)
                .add(new HTMLPanelBuilder("<p>Background Image Alternative 2 @url</p>")
                        .addStyleName(STYLE.dirtyBgnd2())
                        .hTMLPanel)
                .add(new HTMLPanelBuilder("<p>Background Image Alternative 3 @sprite</p>")
                        .addStyleName(STYLE.dirtyBgnd3())
                        .hTMLPanel)
                .add(new HTMLPanelBuilder("<p>Background Image Alternative 4 positioned right</p>")
                        .addStyleName(STYLE.dirtyBgnd4())
                        .hTMLPanel)
                        .verticalLayoutContainer;
    }
}

package biz.freshcode.learn.gwt2.mod2.client.spike.resources;

import biz.freshcode.learn.gwt2.common.client.builder.gwt.HTMLPanelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;

import static biz.freshcode.learn.gwt2.mod2.client.spike.resources.Bundle.STYLE;

/**
 * Trying to figure out why a text area is not resizing automatically.
 * Turns out the width on a FieldLabel and TextArea should not be used.
 * The VerticalLayoutData of width 1.0 ensures the contents are resized.
 */
public class ResourcesSpike extends Presenter<View, ResourcesSpike.Proxy> {
    public static final String TOKEN = "resources";


    private static String dirtyGifUri() {
        // NOTE: Need to serve this up in a method.  Constants aren't resolved yet.
        return Bundle.INSTANCE.dirtyGif().getSafeUri().asString();
    }

    // NOTE: Used in style.gss which uses gwt extensions
    @SuppressWarnings("unused")
    public static String dirtyGifCssUrl() {
        return "URL('" + dirtyGifUri() + "')";
    }


    @Inject
    public ResourcesSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }


    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<ResourcesSpike> {
    }

    public static class View extends ViewImpl {
        @Inject
        public View() {
            initWidget(new VerticalLayoutContainerBuilder()
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
                    .verticalLayoutContainer);
        }
    }
}

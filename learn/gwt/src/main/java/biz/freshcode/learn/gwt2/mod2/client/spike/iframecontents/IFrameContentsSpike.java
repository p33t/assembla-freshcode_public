package biz.freshcode.learn.gwt2.mod2.client.spike.iframecontents;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.toolbar.ToolBarBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Frame;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import static com.google.gwt.core.client.GWT.log;

/**
 * Illustrate how to change order of grid elements using drag/drop
 */
public class IFrameContentsSpike extends Presenter<IFrameContentsSpike.View, IFrameContentsSpike.Proxy> {
    public static final String TOKEN = "iFrameContents";

    @Inject
    public IFrameContentsSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);

        view.getGo().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                go();
            }
        });

        view.getLoad().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                load();
            }
        });

        view.getClear().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                getView().getFrame().setUrl("");
            }
        });

        view.getFrame().addLoadHandler(new LoadHandler() {
            @Override
            public void onLoad(LoadEvent event) {
                // NOTE: onLoad() does not appear to be called if user has navigated away (in Chromium at least).
                // If visible then even loading empty uri will cause event.
                getView().getBlc().unmask();
                log("onLoad");
            }
        });
    }

    private void load() {
        View v = getView();
        v.getBlc().mask("Working...");
        String url = v.getFrame().getUrl();
        log("url: " + url);
        String alt;
        if (url != null && url.endsWith("/style.css")) alt = "/";
        else alt = "/style.css";
        v.getFrame().setUrl(alt);
    }

    private void go() {
        Frame frame = getView().getFrame();
        Element element = frame.getElement();
        describe("Element", element);
        describe("firstChild", element.getFirstChild());
        describe("innerHtml", element.getInnerHTML());
        // NOTE: Owner Document is the outer one.
        describe("ownerDocument", element.getOwnerDocument());
        log(element.getOwnerDocument().getURL());


        // No effect...element.setInnerSafeHtml(SafeHtmlUtils.fromTrustedString("<p>Hello</p>"));
        log("Child count: " + element.getChildCount());
    }

    private void describe(String prefix, Object o) {
        log(prefix + ": " + o + " of type " + (o == null ? null : o.getClass()));
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<IFrameContentsSpike> {
    }

    public static class View extends ViewImpl {
        private final TextButton go;
        private final TextButton load;
        private final Frame frame;
        private final BorderLayoutContainer blc;
        private final TextButton clear;

        @Inject
        public View() {
            initWidget(blc = new BorderLayoutContainerBuilder()
                    .northWidget(new ToolBarBuilder()
                            .add(go = new TextButton("Go"))
                            .add(load = new TextButton("Load"))
                            .add(clear = new TextButton("Clear"))
                            .toolBar)
                    .centerWidget(frame = new Frame("/style.css"))
                    .borderLayoutContainer);
        }

        public BorderLayoutContainer getBlc() {
            return blc;
        }

        public TextButton getClear() {
            return clear;
        }

        public Frame getFrame() {
            return frame;
        }

        public TextButton getGo() {
            return go;
        }

        public TextButton getLoad() {
            return load;
        }
    }
}

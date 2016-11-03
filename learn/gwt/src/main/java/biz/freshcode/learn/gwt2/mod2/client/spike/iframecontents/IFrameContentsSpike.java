package biz.freshcode.learn.gwt2.mod2.client.spike.iframecontents;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.toolbar.ToolBarBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.user.client.ui.Frame;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.button.TextButton;

/**
 * Illustrate how to change order of grid elements using drag/drop
 */
public class IFrameContentsSpike extends Presenter<IFrameContentsSpike.View, IFrameContentsSpike.Proxy> {
    public static final String TOKEN = "iFrameContents";

    @Inject
    public IFrameContentsSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<IFrameContentsSpike> {
    }

    public static class View extends ViewImpl {
        private final TextButton go;
        private final Frame frame;

        @Inject
        public View() {
            initWidget(new BorderLayoutContainerBuilder()
                    .northWidget(new ToolBarBuilder()
                            .add(go = new TextButton("Go"))
                            .toolBar)
                    .centerWidget(frame = new Frame("\\"))
                    .borderLayoutContainer);
        }

        public Frame getFrame() {
            return frame;
        }

        public TextButton getGo() {
            return go;
        }
    }
}

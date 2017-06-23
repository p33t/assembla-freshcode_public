package biz.freshcode.learn.gwt2.mod2.client.bug;

import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;

import java.util.Collections;

public class ToolTipVsErrorMessageBug extends Presenter<ToolTipVsErrorMessageBug.View, ToolTipVsErrorMessageBug.Proxy> {
    public static final String TOKEN = "toolTipVsErrorMessageBug";

    @Inject
    public ToolTipVsErrorMessageBug(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<ToolTipVsErrorMessageBug> {
    }

    public static class View extends ViewImpl {

        @Inject
        public View() {
            TextField txt = new TextField();
//            txt.setToolTip("A tool tip that gets in the way");
            ToolTipConfig config = new ToolTipConfig("A tool tip that gets in the way");
            config.setShowDelay(100); // want this to appear sooner than validation error message
            txt.setToolTipConfig(config);
            txt.setAutoValidate(true);
            txt.addValidator((editor, value) -> {
                if (value != null && value.length() > 0) {
                    return Collections.singletonList(new DefaultEditorError(editor, "Only blank allowed", value));
                }
                return Collections.emptyList();
            });

            FlowLayoutContainer flc = new FlowLayoutContainer();
            flc.add(new HTML("<p>Any text will trigger an invalid message however the tooltip interferes<br/>" +
                    "NOTE: Fixed by setting tooltip config 'showDelay' to something small.  The error message shows last and on top.</p>"));
            flc.add(txt);
            initWidget(flc);
        }
    }
}

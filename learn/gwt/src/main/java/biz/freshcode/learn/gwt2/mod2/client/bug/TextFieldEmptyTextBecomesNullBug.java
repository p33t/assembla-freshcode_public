package biz.freshcode.learn.gwt2.mod2.client.bug;

import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextField;

public class TextFieldEmptyTextBecomesNullBug extends Presenter<TextFieldEmptyTextBecomesNullBug.View, TextFieldEmptyTextBecomesNullBug.Proxy> {
    public static final String TOKEN = "textFieldEmptyTextBecomesNullBug";
    private static final String DEF = "def";

    @Inject
    public TextFieldEmptyTextBecomesNullBug(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<TextFieldEmptyTextBecomesNullBug> {
    }


    public static class View extends ViewImpl {
        private final TextField txt;
        private final TextButton btn;

        @Inject
        public View() {
            FlowLayoutContainer flc = new FlowLayoutContainer();
            flc.add(new HTML("<p>Type '" + DEF + "' into the text field.<br/>" +
                    "Click the button (which clears and then assigns the same value.<br/>" +
                    "The value becomes grey indicating there is no value present.  Try this for some other value.<br/>" +
                    "===> It appears TextField is mapping text that matches 'empty text' to null.</p>"));
            flc.add(txt = new TextField());
            txt.setEmptyText(DEF);
            flc.add(btn = new TextButton("Re-assign same value"));
            initWidget(flc);

            btn.addSelectHandler(event -> {
                String s = txt.getValue();
                txt.setValue(null);
                Scheduler.get().scheduleDeferred(() -> txt.setValue(s));
            });
        }
    }
}

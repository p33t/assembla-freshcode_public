package biz.freshcode.learn.gwt2.mod2.client.spike.validatedtextarea;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.form.TextAreaBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * Illustrate how to change order of grid elements using drag/drop
 */
public class ValidatedTextAreaSpike extends Presenter<ValidatedTextAreaSpike.View, ValidatedTextAreaSpike.Proxy> {
    public static final String TOKEN = "validatedTextArea";

    @Inject
    public ValidatedTextAreaSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<ValidatedTextAreaSpike> {
    }

    public static class View extends ViewImpl {
        private TextArea textArea;
        private TextButton btn;

        @Inject
        public View() {
            initWidget(new VerticalLayoutContainerBuilder()
                    .add(btn = new TextButton("Go"))
                    .add(textArea = new TextAreaBuilder()
                            .textArea, new VerticalLayoutData(1, -30))
                    .verticalLayoutContainer);
            btn.addSelectHandler(new SelectEvent.SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    Info.display("Go", "Go");
                }
            });
        }

        public TextButton getBtn() {
            return btn;
        }

        public TextArea getTextArea() {
            return textArea;
        }
    }
}

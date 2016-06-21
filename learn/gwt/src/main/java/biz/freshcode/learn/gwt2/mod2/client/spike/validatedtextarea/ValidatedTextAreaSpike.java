package biz.freshcode.learn.gwt2.mod2.client.spike.validatedtextarea;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.form.TextAreaBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.AdapterField;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Illustrate how to change order of grid elements using drag/drop
 */
public class ValidatedTextAreaSpike extends Presenter<ValidatedTextAreaSpike.View, ValidatedTextAreaSpike.Proxy> {
    public static final String TOKEN = "validatedTextArea";

    @Inject
    public ValidatedTextAreaSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    // Emulating a rich value with 'Boolean'.
    static class FieldWrapper extends AdapterField<Boolean> {
        private final TextArea textArea;

        public FieldWrapper(TextArea textArea) {
            super(textArea);
            this.textArea = textArea;
            setErrorSupport(textArea.getErrorSupport());
            textArea.addValidator(new Validator<String>() {
                @Override
                public List<EditorError> validate(Editor<String> editor, String value) {
                    List<EditorError> errs = new ArrayList<>();
                    if ("bad".equals(value)) errs.add(new DefaultEditorError(editor, "'bad' not allowed", value));
                    return errs;
                }
            });
        }

        @Override
        public void setValue(Boolean value) {
            // Ugh, where is the value change event support?
            if (value != getValue()) {
                String text = value == Boolean.FALSE ? "bad" : "good";
                textArea.setText(text);
            }
        }

        @Override
        public Boolean getValue() {
            String tav = textArea.getValue();
            if (tav == null) return null;
            if (tav.equals("bad")) return Boolean.FALSE;
            return true;
        }
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<ValidatedTextAreaSpike> {
    }

    public static class View extends ViewImpl {
        private TextArea textArea = new TextAreaBuilder()
                .emptyText("Anything but 'bad'")
                // these two options are nice together
                .validationDelay(1000).autoValidate(true)
                // NOTE: We still have the problem of what's visible is not what is saved.
                .textArea;
        private TextButton btn;
        private FieldWrapper wrapper;

        @Inject
        public View() {
            wrapper = new FieldWrapper(textArea);
            initWidget(new VerticalLayoutContainerBuilder()
                    .add(btn = new TextButton("Go"))
                    .add(wrapper, new VerticalLayoutData(1, -30))
                    .verticalLayoutContainer);

            btn.addSelectHandler(new SelectEvent.SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    Info.display("Go",
                            "Wrapper Value: " + wrapper.getValue() +
                                    "\nErrors: " + wrapper.getErrors().size() +
                                    "\nText: " + textArea.getValue()
                    );
                }
            });
        }

// Didn't work out... focus problems etc.
//        class ChangedTimer extends Timer {
//            ChangedTimer() {
//                timerOrNull = this;
//                schedule(1000);
//            }
//
//            @Override
//            public void run() {
//                if (timerOrNull == this) {
//                    // has not been replaced
//                    // proceed
//                    textArea.setValue(textArea.getText()); // a pseudo 'blur' event?
//                    textArea.focus();
//                }
//            }
//        }

        public TextButton getBtn() {
            return btn;
        }

        public TextArea getTextArea() {
            return textArea;
        }
    }
}

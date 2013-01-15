package biz.freshcode.learn.gwt.client.experiment.popfield;

import biz.freshcode.learn.gwt.client.builder.gxt.PopupBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.form.TextFieldBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.TextButtonBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Popup;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

import java.util.List;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;

/**
 * A text field that pops up at the cursor for quick read of text from user.
 */
public class PopFieldDemo extends AbstractIsWidget {
    private static final Validator<String> VALIDATOR = new Validator<String>() {
        @Override
        public List<EditorError> validate(Editor<String> editor, String value) {
            List<EditorError> l = newList();
            if ("error".equals(value)) {
                l.add(new DefaultEditorError(editor, "Cannot be 'error'", value));
            }
            return l;
        }
    };
    String backingVar = "";

    @Override
    protected Widget createWidget() {
        TextButton btn = new TextButtonBuilder()
                .text("Pop up field")
                .textButton;
        btn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                popup();
            }
        });
        return btn;
    }

    private void popup() {
        final TextField field;
        final Popup popup = new PopupBuilder()
                .add(field = new TextFieldBuilder()
                        .width(200)
                        .emptyText("Anything but 'error'")
                        .autoValidate(true)
                        .validationDelay(150) // default is a little slow
                        .addValidator(VALIDATOR)
                        .value(backingVar)
                        .textField)
                .popup;
//        Nope: enter key not registering here
//        field.addKeyPressHandler(new KeyPressHandler() {
//            @Override
//            public void onKeyPress(KeyPressEvent event) {
//                GWT.log("Char: " + ((int) event.getCharCode()));
//            }
//        });
        field.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
//                GWT.log("Down: " + event.getNativeKeyCode());
                int keyCode = event.getNativeKeyCode();
                if (keyCode == KeyCodes.KEY_ENTER) {
                    // NOTE: field.validate() required more form plumbing I think.... just use the 'text'.
                    String txt = field.getText();

                    if (VALIDATOR.validate(field, txt).isEmpty()) {
                        backingVar = txt;
                        GWT.log("Validated " + backingVar);
                        popup.hide();
                    }
                    else {
                        GWT.log("Not valid");
                        field.selectAll();
                        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                            @Override
                            public void execute() {
                                field.focus();
                            }
                        });
                    }
                }
                else if (keyCode == KeyCodes.KEY_ESCAPE || keyCode == KeyCodes.KEY_TAB) {
                    popup.hide();
                }
            }
        });
        popup.showAt(200, 200);
        field.focus();
        field.selectAll();
    }
}

package biz.freshcode.learn.gwt.client.experiment.forms3;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.AdapterField;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

import java.util.List;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt.client.util.AppObjectUtils.safeEquals;

/**
 * A field for editing a password, which requires user to enter the password twice.
 * This class suffers from a bug.
 * See http://www.sencha.com/forum/showthread.php?265590-AdapterField-validation-errors
 * AND bug 'EXTGWT-3066'
 */
public class PasswordEditor extends AdapterField<String> {
    private PasswordField pwd = new PasswordField();
    private PasswordField pwdRepeat = new PasswordField();

    public PasswordEditor() {
        super(new VerticalLayoutContainer());
        VerticalLayoutContainer vlc = (VerticalLayoutContainer) getWidget();
        vlc.add(pwd);
        vlc.add(pwdRepeat);

        // trigger for validation
        ValueChangeHandler<String> handler = new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                validate();
            }
        };
        pwd.addValueChangeHandler(handler);
        pwdRepeat.addValueChangeHandler(handler);

        // setup validation
        addValidator(new Validator<String>() {
            @Override
            public List<EditorError> validate(Editor<String> editor, String value) {
                List<EditorError> l = newList();
                if (!safeEquals(value, pwdRepeat.getValue())) {
                    l.add(new DefaultEditorError(editor, "Repeat doesn't match", value));
                }
                return l;
            }
        });
    }

    @Override
    public void setValue(String value) {
        GWT.log("Setting value to " + value);
        pwd.setValue(value);
        pwdRepeat.setValue(value);
    }

    @Override
    public String getValue() {
        return pwd.getValue();
    }
}

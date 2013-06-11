package biz.freshcode.learn.gwt.client.experiment.forms3;

import biz.freshcode.learn.gwt.client.builder.gxt.container.VerticalLayoutContainerBuilder;
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

public class PasswordEditor extends AdapterField<String> {
    private PasswordField pwd = new PasswordField();
    private PasswordField pwdRepeat = new PasswordField();

    public PasswordEditor() {
        super(new VerticalLayoutContainer());
        new VerticalLayoutContainerBuilder((VerticalLayoutContainer) getWidget())
                .add(pwd)
                .add(pwdRepeat)
        ;

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
        pwd.setValue(value);
        pwdRepeat.setValue(value);
    }

    @Override
    public String getValue() {
        return pwd.getValue();
    }
}

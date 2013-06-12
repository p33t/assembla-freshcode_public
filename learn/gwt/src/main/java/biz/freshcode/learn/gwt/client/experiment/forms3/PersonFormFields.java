package biz.freshcode.learn.gwt.client.experiment.forms3;

import biz.freshcode.learn.gwt.client.builder.gxt.form.TextFieldBuilder;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;

/**
 * Fields for editing a Person.
 */
public class PersonFormFields implements Editor<Person> {
    TextField name = new TextFieldBuilder()
            .addValidator(new MinLengthValidator(3))
            .textField;
    PasswordEditorAlt newPassword = new PasswordEditorAlt();
}

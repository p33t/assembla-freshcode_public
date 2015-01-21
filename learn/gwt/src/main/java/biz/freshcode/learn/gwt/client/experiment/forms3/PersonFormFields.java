package biz.freshcode.learn.gwt.client.experiment.forms3;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.form.TextFieldBuilder;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;

import java.util.Arrays;

/**
 * Fields for editing a Person.
 */
public class PersonFormFields implements Editor<Person> {
    TextField name = new TextFieldBuilder()
            .addValidator(new MinLengthValidator(3))
            .textField;
    PasswordEditor newPassword = new PasswordEditor();
    ListBoxField<Flavour> favourite = new ListBoxField<Flavour>();

    public PersonFormFields() {
        favourite.init(Arrays.asList(Flavour.values()), new StringLabelProvider<Flavour>());
    }
}

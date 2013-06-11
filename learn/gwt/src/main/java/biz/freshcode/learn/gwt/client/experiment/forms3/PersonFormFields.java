package biz.freshcode.learn.gwt.client.experiment.forms3;

import com.google.gwt.editor.client.Editor;

/**
 * Fields for editing a Person.
 */
public class PersonFormFields implements Editor<Person> {
    PasswordEditor newPassword = new PasswordEditor();
}

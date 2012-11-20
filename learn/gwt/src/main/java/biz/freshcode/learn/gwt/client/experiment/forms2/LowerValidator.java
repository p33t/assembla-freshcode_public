package biz.freshcode.learn.gwt.client.experiment.forms2;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

import java.util.List;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;

public class LowerValidator implements Validator<String> {
    public static final LowerValidator INSTANCE = new LowerValidator();

    @Override
    public List<EditorError> validate(Editor<String> editor, String value) {
        List<EditorError> l = newList();
        if ("error".equals(value)) l.add(new DefaultEditorError(editor, "Cannot be 'error'.", value));
        return l;
    }
}

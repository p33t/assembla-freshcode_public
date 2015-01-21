package biz.freshcode.learn.gwt.mod1.client.experiment.forms3;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.AdapterField;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

import java.util.List;

import static biz.freshcode.learn.gwt.mod1.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt.mod1.client.util.AppObjectUtils.safeEquals;

/**
 * This class employs a work-around for a bug pre GXT 3.1.0.
 * Specifically it caches errors and records them in the delegate during a 'flush'... but it didn't work.
 *
 * @see PasswordEditor
 *      AND http://www.sencha.com/forum/showthread.php?265590-AdapterField-validation-errors
 */
public class PasswordEditorAlt extends AdapterField<String> implements ValueAwareEditor<String> {
    private PasswordField pwd = new PasswordField();
    private PasswordField pwdRepeat = new PasswordField();

    public PasswordEditorAlt() {
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
                GWT.log("Validating");
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

    /********************************* WORK AROUND ATTEMPT BELOW ***************************************/
    private EditorDelegate<String> delegate;
    private List<EditorError> errorCache;

    @Override
    public void flush() {
        if (delegate == null) return;
        if (errorCache != null) {
            GWT.log("Flushing " + errorCache.size() + " errors");
            for (EditorError e : errorCache) {
                delegate.recordError(e.getMessage(), e.getValue(), this);
            }
        }
    }

    @Override
    public void clearInvalid() {
        super.clearInvalid();
        GWT.log("Clearing errors.");
        errorCache = null;
    }

    @Override
    public void onPropertyChange(String... paths) {
        // ignore
        GWT.log("onPropertyChange()");
    }

    @Override
    protected void markInvalid(List<EditorError> errs) {
        super.markInvalid(errs);
        GWT.log("Caching errors: " + errs);
        errorCache = errs;
    }

    @Override
    public void setDelegate(EditorDelegate<String> delegate) {
        super.setDelegate(delegate);
        GWT.log("Setting delegate");
        this.delegate = delegate;
    }
}

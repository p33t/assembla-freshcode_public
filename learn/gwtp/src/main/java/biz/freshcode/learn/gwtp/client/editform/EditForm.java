package biz.freshcode.learn.gwtp.client.editform;

import biz.freshcode.learn.gwtp.client.boot.Root;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.sencha.gxt.widget.core.client.event.InvalidEvent;
import com.sencha.gxt.widget.core.client.event.ValidEvent;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

import java.util.List;
import java.util.Random;

import static biz.freshcode.learn.gwtp.shared.util.AppCollectionUtil.newList;

public class EditForm extends Presenter<EditForm.View, EditForm.Proxy> {
    public static final String TOKEN = "editForm";
    private final Driver driver = GWT.create(Driver.class);
    private final Random random = new Random();

    @Inject
    public EditForm(EventBus eventBus, final View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
        driver.initialize((EditFormViewImpl) view);

        Field<Integer> left = view.numLeft();
        final Field<Integer> right = view.numRight();
        left.addValidator(new CustomValidator(right, "Right"));
        right.addValidator(new CustomValidator(left, "Left"));

        ValidationPropagate toRight = new ValidationPropagate(right);
        left.addValidHandler(toRight);
        left.addInvalidHandler(toRight);

        ValidationPropagate toLeft = new ValidationPropagate(left);
        right.addValidHandler(toLeft);
        right.addInvalidHandler(toLeft);
    }

    @Override
    protected void onReset() {
        super.onReset();
        NumBean b = new NumBean();
        b.setNumLeft(random.nextInt(50));
        GWT.log("Num is " + b.getNumLeft());
        driver.edit(b);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends ProxyPlace<EditForm> {
    }

    public interface View extends com.gwtplatform.mvp.client.View {
        Field<Integer> numLeft();

        Field<Integer> numRight();
    }

    interface Driver extends SimpleBeanEditorDriver<NumBean, EditFormViewImpl> {
    }

    /**
     * Propagates validation state changes to an alternate field,
     * whose validation state depends on the field being listened to.
     */
    private static class ValidationPropagate implements ValidEvent.ValidHandler, InvalidEvent.InvalidHandler {
        private final Field<Integer> altField;
        private boolean busy = false;

        private ValidationPropagate(Field<Integer> altField) {
            this.altField = altField;
        }

        @Override
        public void onValid(ValidEvent event) {
            altValidate();
        }

        @Override
        public void onInvalid(InvalidEvent event) {
            altValidate();
        }

        private void altValidate() {
            // needed to prevent 'OutOfMemory' error.
            if (busy) return;
            busy = true;
            try {
                altField.validate();
            } finally {
                busy = false;
            }
        }
    }

    /**
     * Some weird validation requirements.  Cannot be 'odd' and cannot be the same as alt field.
     */
    private static class CustomValidator implements Validator<Integer> {
        private final Field<Integer> compareTo;
        private final String compareToName;

        CustomValidator(Field<Integer> compareTo, String compareToName) {
            this.compareTo = compareTo;
            this.compareToName = compareToName;
        }

        @Override
        public List<EditorError> validate(Editor<Integer> editor, Integer value) {
            List<EditorError> l = newList();

            if (value != null) {
                if (value % 2 == 1) {
                    l.add(new DefaultEditorError(editor, "Cannot be odd", value));
                }
                if (value.equals(compareTo.getValue())) {
                    l.add(new DefaultEditorError(editor, "Cannot be same as " + compareToName, value));
                }
            }

            return l;
        }
    }
}

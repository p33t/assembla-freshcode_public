package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.builder.gxt.form.TextFieldBuilder;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.BlurEvent;
import com.sencha.gxt.widget.core.client.form.ConverterEditorAdapter;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * Combines a Converter and a Validator to that an HrMin 'Long' typed field can be edited with a TextField.
 */
// NOTE: Trying to use a TwinTriggerField (or subclass of) seems to be a dead end... only a single button is shown!
public class HrMinField2 extends ConverterEditorAdapter<Long, String, TextField> implements IsWidget {
    private TextField textField;
    public HrMinField2() {
        this(new TextFieldBuilder()
                .allowBlank(false)
                .addValidator(HrMinConverter.VALIDATOR)
                .textField);
    }

    private HrMinField2(final TextField tf) {
        super(tf, HrMinConverter.INSTANCE);

        textField = tf;

        // format the text on blur
        tf.addBlurHandler(new BlurEvent.BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

                    @Override
                    public void execute() {
                        String text = tf.getText();
//                        Doesn't work... l is aways null
//                        Long l = hrMin2.getValue();
                        Long l = HrMinConverter.INSTANCE.convertFieldValue(text);
                        if (l != null) {
                            // no errors preventing re-format
                            String s = HrMinConverter.INSTANCE.convertModelValue(l);
                            if (!text.equals(s)) tf.setText(s);
                        }

                    }
                });
            }
        });
    }

    @Override
    public Widget asWidget() {
        return textField;
    }
}

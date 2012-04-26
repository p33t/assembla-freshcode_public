package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.FieldLabelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.HrMinFieldBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.TextFieldBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.BlurEvent;
import com.sencha.gxt.widget.core.client.form.ConverterEditorAdapter;
import com.sencha.gxt.widget.core.client.form.TextField;

import java.util.logging.Logger;

public class PeriodBeanEditor extends AbstractIsWidget implements Editor<PeriodBean> {
    private Logger logger = Logger.getLogger(getClass().getName());

    // This clears the value if it doesn't parse (?!)
    HrMinField hrMin1;

    // TODO: Convert to another HrMinField
    ConverterEditorAdapter<Long, String, TextField> hrMin2;

    @Override
    protected Widget createWidget() {
        // This doesn't reformat the text on blur
        TextField textField;
        FlowLayoutContainer c = new FlowLayoutContainerBuilder()
                .add(new FieldLabelBuilder()
                        .text("Hr Min 2 (Field)")
                        .widget(hrMin1 = new HrMinFieldBuilder()
                                .allowBlank(false)
                                .hrMinField)
                        .fieldLabel)
                .add(new FieldLabelBuilder()
                        .text("Hr Min (Converter)")
                        .widget(textField = new TextFieldBuilder()
                                .allowBlank(false)
                                .addValidator(HrMinConverter.VALIDATOR)
                                // TODO: Put in some logging / blur formatting?
                                .textField)
                        .fieldLabel)
                .flowLayoutContainer;
        hrMin2 = new ConverterEditorAdapter(textField, HrMinConverter.INSTANCE);

        final TextField tf = textField;

        tf.addBlurHandler(new BlurEvent.BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                // format the text if possible
                logger.info("Blur hrMinField");
                Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

                    @Override
                    public void execute() {
                        logger.info("Scheduled hrMinField blur");
                        String text = tf.getText();
//                        Doesn't work... l is aways null
//                        Long l = hrMin2.getValue();
                        Long l = HrMinConverter.INSTANCE.convertFieldValue(text);
                        if (l != null) {
                            // no errors
                            logger.info("No errors");
                            String s = HrMinConverter.INSTANCE.convertModelValue(l);
                            if (!text.equals(s)) tf.setText(s);
                        } else {
                            logger.info("No long value available.  Text is " + text);
                        }

                    }
                });
            }
        });

        return c;
    }
}

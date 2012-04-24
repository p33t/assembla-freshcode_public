package biz.freshcode.learn.gwt.client.experiment.forms;

import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.form.NumberField;

import java.util.logging.Logger;


public class HrMinField extends NumberField<Long> {
    private Logger logger = Logger.getLogger(getClass().getName());

    public HrMinField() {
        super(new HrMinPropertyEditor());
        setDecimalSeparator(":"); // permit the separator
        setAllowDecimals(true);
//        This shows the single / drop-down trigger
//        setHideTrigger(false);

        // NOTE: There is already a SideErrorHandler in errorSupport field.
        addParseErrorHandler(new ParseErrorEvent.ParseErrorHandler() {
            @Override
            public void onParseError(ParseErrorEvent event) {
                logger.info("Parse error: " + event.getErrorValue() + "\n" + event);
            }
        });
    }
}

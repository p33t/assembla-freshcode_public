package biz.freshcode.learn.gwt.client.experiment.forms;

import com.sencha.gxt.cell.core.client.form.TriggerFieldCell;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;

import java.util.logging.Logger;


public class HrMinField extends ValueBaseField<Long> {
    private Logger logger = Logger.getLogger(getClass().getName());

    public HrMinField() {
        super(new TriggerFieldCell<Long>(), new HrMinPropertyEditor());
        // NOTE: There is already a SideErrorHandler in errorSupport field.
        addParseErrorHandler(new ParseErrorEvent.ParseErrorHandler() {
            @Override
            public void onParseError(ParseErrorEvent event) {
                logger.info("Parse error: " + event.getErrorValue() + "\n" + event);
            }
        });
    }
}

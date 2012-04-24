package biz.freshcode.learn.gwt.client.experiment.forms;

import com.sencha.gxt.cell.core.client.form.SpinnerFieldCell;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.form.NumberField;

import java.util.logging.Logger;

// NOTE: Can't use SpinnerField<Long> as parent class because it won't accept ':' char.
public class HrMinField extends NumberField<Long> {
    private Logger logger = Logger.getLogger(getClass().getName());

    public HrMinField() {
        this(new SpinnerFieldCell(new HrMinPropertyEditor()));
    }

    private HrMinField(SpinnerFieldCell<Long> cell) {
        super(cell, cell.getPropertyEditor());
        cell.setHideTrigger(false); // show the spin buttons
        setDecimalSeparator(":"); // permit the separator
        setAllowDecimals(true);

        // NOTE: There is already a SideErrorHandler in errorSupport field.
        addParseErrorHandler(new ParseErrorEvent.ParseErrorHandler() {
            @Override
            public void onParseError(ParseErrorEvent event) {
                logger.info("Parse error: " + event.getErrorValue() + "\n" + event);
            }
        });
    }
}

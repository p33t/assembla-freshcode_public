package biz.freshcode.learn.gwt.mod1.client.experiment.forms;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.sencha.gxt.cell.core.client.form.SpinnerFieldCell;
import com.sencha.gxt.widget.core.client.event.BlurEvent;
import com.sencha.gxt.widget.core.client.event.InvalidEvent;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.event.ValidEvent;
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

        addBlurHandler(new BlurEvent.BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                logger.info("Blur while value is " + getValue());
            }
        });

        addInvalidHandler(new InvalidEvent.InvalidHandler() {
            @Override
            public void onInvalid(InvalidEvent event) {
                logger.info("Invalid with event errors " + event.getErrors());
            }
        });

        addValidHandler(new ValidEvent.ValidHandler() {
            @Override
            public void onValid(ValidEvent event) {
                logger.info("Valid with value " + getValue());
            }
        });

        addValueChangeHandler(new ValueChangeHandler<Long>() {
            @Override
            public void onValueChange(ValueChangeEvent<Long> evt) {
                logger.info("Value changed to " + evt.getValue());
            }
        });
    }
}

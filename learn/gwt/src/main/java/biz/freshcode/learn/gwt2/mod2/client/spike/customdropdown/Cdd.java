package biz.freshcode.learn.gwt2.mod2.client.spike.customdropdown;

import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TriggerField;

public class Cdd extends TriggerField<String> implements ExpandEvent.HasExpandHandlers, CollapseEvent.HasCollapseHandlers {
    public Cdd() {
        this(new CddCell());
    }

    public Cdd(CddCell cell) {
        super(cell);
    }

    /**
     * Returns the field's date picker.
     *
     * @return the date picker
     */
    public TextArea getTextArea() {
        return getCell().getTextArea();
    }

    @Override
    public CddCell getCell() {
        return (CddCell) super.getCell();
    }

    @Override
    public HandlerRegistration addExpandHandler(ExpandEvent.ExpandHandler handler) {
        return addHandler(handler, ExpandEvent.getType());
    }

    @Override
    public HandlerRegistration addCollapseHandler(CollapseEvent.CollapseHandler handler) {
        return addHandler(handler, CollapseEvent.getType());
    }

    protected void expand() {
        getCell().expand(createContext(), getElement(), getValue(), valueUpdater);
    }

    @Override
    protected void onCellParseError(ParseErrorEvent event) {
        super.onCellParseError(event);
        @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
        String value = event.getException().getMessage();
        forceInvalid(value);
    }
}

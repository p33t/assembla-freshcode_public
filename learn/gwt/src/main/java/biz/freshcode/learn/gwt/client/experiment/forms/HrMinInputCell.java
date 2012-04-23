package biz.freshcode.learn.gwt.client.experiment.forms;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.cell.core.client.form.ValueBaseInputCell;

// This is NOT working.
public class HrMinInputCell extends ValueBaseInputCell<Long> {

    public HrMinInputCell(ValueBaseFieldAppearance appearance) {
        super(appearance);
    }

    @Override
    public void render(Context context, Long value, SafeHtmlBuilder sb) {
        if (value != null) {
            sb.appendEscaped(HrMinPropertyEditor.INSTANCE.render(value));
        }
    }
}

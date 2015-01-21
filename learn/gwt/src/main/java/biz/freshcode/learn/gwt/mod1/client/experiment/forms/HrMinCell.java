package biz.freshcode.learn.gwt.mod1.client.experiment.forms;


import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class HrMinCell extends AbstractCell<Long> {
    @Override
    public void render(Context context, Long millis, SafeHtmlBuilder sb) {
        if (millis == null) sb.appendEscaped("-");
        else sb.appendEscaped(HrMinConverter.INSTANCE.convertModelValue(millis));
    }
}

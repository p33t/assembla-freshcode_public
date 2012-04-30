package biz.freshcode.learn.gwt.client.experiment.forms;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * Renders a would-be finish time for a 'StartDuration' type of Bean.
 */
public class FinishCell extends AbstractCell<StartDuration> {
    @Override
    public void render(Cell.Context context, StartDuration sd, SafeHtmlBuilder sb) {
        if (sd.getStart() != null && sd.getDuration() != null) {
            long finish = sd.getStart() + sd.getDuration();
            sb.appendEscaped(HrMinConverter.INSTANCE.convertModelValue(finish));
        } else sb.appendEscaped("-");
    }
}

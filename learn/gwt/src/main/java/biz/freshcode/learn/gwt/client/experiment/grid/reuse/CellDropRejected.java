package biz.freshcode.learn.gwt.client.experiment.grid.reuse;

import com.google.gwt.cell.client.Cell;

/**
 * Used by a GXT grid to reject a drop option but retains the
 * target cell in order to detect when a re-assessment is needed.
 */
public class CellDropRejected extends HasTarget {
    private final String reason;

    public CellDropRejected(String reason, Cell.Context targetOrNull) {
        super(targetOrNull);
        this.reason = reason;
    }

    @Override
    public String toString() {
        return reason;
    }
}

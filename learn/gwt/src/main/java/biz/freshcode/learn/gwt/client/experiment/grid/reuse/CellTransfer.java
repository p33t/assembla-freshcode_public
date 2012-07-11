package biz.freshcode.learn.gwt.client.experiment.grid.reuse;

import com.google.gwt.cell.client.Cell;

/**
 * Subclass to implement a cell drop operation on a GXT grid.
 */
public abstract class CellTransfer extends HasTarget implements Runnable {
    protected final Cell.Context origin;

    public CellTransfer(Cell.Context origin, Cell.Context target) {
        super(target);
        this.origin = origin;
    }
}

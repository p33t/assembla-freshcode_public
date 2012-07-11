package biz.freshcode.learn.gwt.client.experiment.grid.reuse;

import com.google.gwt.cell.client.Cell;

/**
 * Possessing a target field.
 */
public class HasTarget {
    public final Cell.Context target;

    public HasTarget(Cell.Context target) {
        this.target = target;
    }
}

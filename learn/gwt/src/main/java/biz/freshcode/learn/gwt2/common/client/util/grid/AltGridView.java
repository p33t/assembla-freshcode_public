package biz.freshcode.learn.gwt2.common.client.util.grid;

import com.sencha.gxt.widget.core.client.grid.GridView;

/**
 * Makes 'protected' methods available to this package.
 */
public class AltGridView<M> extends GridView<M> {
    @Override
    protected void onCellDeselect(int row, int col) {
        super.onCellDeselect(row, col);
    }

    @Override
    protected void onCellSelect(int row, int col) {
        super.onCellSelect(row, col);
    }
}

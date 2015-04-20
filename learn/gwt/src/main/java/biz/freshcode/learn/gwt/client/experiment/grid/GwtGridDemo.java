package biz.freshcode.learn.gwt.client.experiment.grid;

import biz.freshcode.learn.gwt2.common.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;

/**
 * Well that was quick.... there is no scrolling ?!
 */
public class GwtGridDemo extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        // Create a grid
        Grid grid = new Grid(40, 4);

        // Add images to the grid
        int numRows = grid.getRowCount();
        int numColumns = grid.getColumnCount();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                grid.setWidget(row, col, new HtmlLayoutContainer("<p>" + row + "," + col + "</p>"));
            }
        }
        return grid;
    }
}

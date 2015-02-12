package biz.freshcode.learn.gwt2.mod2.client.spike.gridinteract.reuse;

import com.google.gwt.dom.client.NativeEvent;
import com.sencha.gxt.widget.core.client.event.CellMouseDownEvent;
import com.sencha.gxt.widget.core.client.grid.CellSelectionModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.selection.CellSelection;

import java.util.ArrayList;
import java.util.List;

/**
 * Enables multiple individual cell selection in a grid.
 * Note that the GridView.onCellSelect() and .onCellDeselect() must be available.
 */
public class MultiCellSelectionModel<M> extends CellSelectionModel<M> {
    //Allows us to support multiple cell selections.
    protected List<CellSelection<M>> cellSelections = new ArrayList<>();
    protected boolean isShiftSelected = false;
    protected boolean isCtrlSelected = false;
    private final CellSelectOps ops;

    public MultiCellSelectionModel(CellSelectOps ops) {
        this.ops = ops;
    }

    public static <M> void setup(Grid<M> grid) {
        AltGridView<M> view = new AltGridView<M>();
        grid.setView(view);
        grid.setSelectionModel(new MultiCellSelectionModel<M>(view));
    }

    @Override
    public void deselectAll() {
        super.deselectAll();
        deselectAllSelections(true);
    }

    @Override
    public void selectCell(int row, int cell) {
        if (!isMulti()) {
            deselectAllSelections(true);
        } else if (selection != null && cellSelections.size() == 0) { //need to push the current selection on the stack.
            cellSelections.add(selection);
        }

        M m = listStore.get(row);
        selection = new CellSelection<>(m, row, cell);

        if (grid.isViewReady()) {
            ops.onCellSelect(row, cell);
            grid.getView().focusCell(row, cell, true);
        }
        if (isMulti()) {
            cellSelections.add(selection); //Push the current cell on the selection stack.

            if (isCtrlSelected) {
                doCtrlSelect();
            } else {
                doShiftSelect();
            }
        }
    }

    public List<CellSelection<M>> getSelections() {
        return new ArrayList<>(cellSelections);
    }

    @Override
    protected void onKeyPress(NativeEvent e) {
        setFlags(e.getCtrlKey(), e.getShiftKey());
        super.onKeyPress(e);
        clearFlags();
    }

    @Override
    protected void handleMouseDown(CellMouseDownEvent e) {
        setFlags(e.getEvent().getCtrlKey(), e.getEvent().getShiftKey());
        super.handleMouseDown(e);
        clearFlags();
    }

    /**
     * Loop through all cellselections on the stack and unselect them, then pop them off the stack.
     * <p/>
     * If clearCurrent is set to true, then the current selection is also cleared.
     */
    private void deselectAllSelections(boolean clearCurrent) {
        for (CellSelection<M> cell : cellSelections) {
            if (cell == selection && !clearCurrent) { //we will deal with the current selection seperatly.
                continue;
            }
            int row = listStore.indexOf(cell.getModel());
            if (grid.isViewReady()) {
                ops.onCellDeselect(row, cell.getCell());
            }
        }
        cellSelections.clear();


        if (clearCurrent && selection != null) {
            int row = listStore.indexOf(selection.getModel());
            if (grid.isViewReady()) {
                ops.onCellDeselect(row, selection.getCell());
            }
            selection = null;
        }
    }

    private void selectAllSelections() {
        for (CellSelection cell : cellSelections) {
            if (grid.isViewReady()) {
                ops.onCellSelect(cell.getRow(), cell.getCell());
            }
        }
    }

    private void doShiftSelect() {
        if (cellSelections.size() == 0) return;
        //Assume the last one on the list was shift selected.
        CellSelection<M> cell = cellSelections.get(cellSelections.size() - 1);
        CellSelection<M> anchor = cellSelections.get(0);
        ArrayList<CellSelection<M>> newCellSelections = new ArrayList<>();

        //NOTE: This algorithm makes sure that the anchor is the first one written to the list, and the last cell is the last one written to the list.
        if (anchor.getRow() < cell.getRow()) {
            for (int r = anchor.getRow(); r <= cell.getRow(); r++) {
                if (anchor.getCell() <= cell.getCell()) { //anchor col < cell col
                    for (int c = anchor.getCell(); c <= cell.getCell(); c++) {
                        newCellSelections.add(createCell(r, c));
                    }
                } else { //anchor col > cell col
                    for (int c = anchor.getCell(); c >= cell.getCell(); c--) {
                        newCellSelections.add(createCell(r, c));
                    }
                }
            }
        } else { //anchor row > cell row.
            for (int r = anchor.getRow(); r >= cell.getRow(); r--) {
                if (anchor.getCell() <= cell.getCell()) { //anchor col < cell col
                    for (int c = anchor.getCell(); c <= cell.getCell(); c++) {
                        newCellSelections.add(createCell(r, c));
                    }
                } else { //anchor col > cell coll
                    for (int c = anchor.getCell(); c >= cell.getCell(); c--) {
                        newCellSelections.add(createCell(r, c));
                    }
                }
            }
        }

        //This should probable calculate a merge, to minimize redraws.
        deselectAllSelections(false);
        cellSelections = newCellSelections;
        selection = cellSelections.get(cellSelections.size() - 1);
        selectAllSelections();
    }

    private void doCtrlSelect() {
        //Assume the last one on the cellSelection list was ctrl - selected......
        CellSelection cell = (cellSelections.size() > 0) ? cellSelections.get(cellSelections.size() - 1) : null;
        if (cell == null) { //shouldn't happen.
            return;
        }

        //See if the cell is already in cell selection list, if so need to unselect it.
        int index = findCell(cell.getRow(), cell.getCell(), cellSelections);
        if (index != (cellSelections.size() - 1)) { //we need to "Unselect this cell;
            if (grid.isViewReady()) {
                ops.onCellDeselect(cell.getRow(), cell.getCell());
            }
            cellSelections.remove(cellSelections.size() - 1);
            cellSelections.remove(index);
        }
    }

    private void setFlags(boolean ctrlSelected, boolean shiftSelected) {
        isShiftSelected = shiftSelected;
        isCtrlSelected = ctrlSelected;
    }

    private boolean isMulti() {
        return isShiftSelected || isCtrlSelected;
    }

    private void clearFlags() {
        isShiftSelected = false;
        isCtrlSelected = false;
    }

    private int findCell(int row, int col, List<CellSelection<M>> cells) {
        for (int i = 0; i < cells.size(); i++) {
            CellSelection cell = cells.get(i);
            if ((cell.getRow() == row) && (cell.getCell() == col)) {
                return i;
            }
        }
        return -1;
    }

    private CellSelection<M> createCell(int row, int col) {
        M m = listStore.get(row);
        return new CellSelection<>(m, row, col);
    }

    public interface CellSelectOps {
        void onCellDeselect(int row, int col);

        void onCellSelect(int row, int col);
    }

    /**
     * Exposes cell select methods for use by selection models.
     */
    public static class AltGridView<M> extends GridView<M> implements CellSelectOps {
        @Override
        public void onCellDeselect(int row, int col) {
            super.onCellDeselect(row, col);
        }

        @Override
        public void onCellSelect(int row, int col) {
            super.onCellSelect(row, col);
        }
    }
}

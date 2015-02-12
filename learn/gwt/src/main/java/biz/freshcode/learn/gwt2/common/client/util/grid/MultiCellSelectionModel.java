package biz.freshcode.learn.gwt2.common.client.util.grid;

import com.google.gwt.dom.client.NativeEvent;
import com.sencha.gxt.widget.core.client.event.CellMouseDownEvent;
import com.sencha.gxt.widget.core.client.grid.CellSelectionModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.CellSelection;

import java.util.ArrayList;

/**
 * The cellSelections ArrayList is filled while the shift or Ctrl key is being pressed and
 * the user is selecting cells.   A couple of key notes:
 * <p/>
 * The first entry in cellSelections is the "anchor", so when a Shift-Select is selected, we calculate the selected box of cells
 * based on distance from the anchor to the selected cell.
 */
public class MultiCellSelectionModel<M> extends CellSelectionModel<M> {
    //Allows us to support multiple cell selections.
    protected ArrayList<CellSelection<M>> cellSelections = new ArrayList<>();
    protected boolean isInMultiSelect = false;
    protected boolean isShiftSelected = false;
    protected boolean isCtrlSelected = false;

    public static <M> void setup(Grid<M> grid) {
        grid.setView(new AltGridView<M>());
        grid.setSelectionModel(new MultiCellSelectionModel<M>());
    }

    @Override
    public void deselectAll() {
        if (!isInMultiSelect) {
            super.deselectAll();
            deselectAllSelections(true);
        }
    }

    @Override
    public void selectCell(int row, int cell) {
        if (!isInMultiSelect) {
            deselectAllSelections(true);
        } else if ((selection != null) && (cellSelections.size() == 0)) { //need to push the current selection on the stack.
            cellSelections.add(selection);
        }

        M m = listStore.get(row);
//    if (GXT.isAriaEnabled() && selectedHeader != null) {
//      selectedHeader = null;
//      FocusFrame.get().frame(grid);
//    }

        selection = new CellSelection(m, row, cell);

        if (grid.isViewReady()) {
            getView().onCellSelect(row, cell);
            getView().focusCell(row, cell, true);
        }
        if (isInMultiSelect) {
            cellSelections.add(selection); //Push the current cell on the selection stack.

            if (isCtrlSelected) {
                doCtrlSelect();
            } else {
                doShiftSelect();
            }
        }
    }

    public ArrayList<CellSelection<M>> getSelections() {
        return cellSelections;
    }

    /**
     * Loop through all cellselections on the stack and unselect them, then pop them off the stack.
     * <p/>
     * If clearCurrent is set to true, then the current selection is also cleared.
     */
    protected void deselectAllSelections(boolean clearCurrent) {
        for (CellSelection<M> cell : cellSelections) {
            if ((cell == selection) && (!clearCurrent)) { //we will deal with the current selection seperatly.
                continue;
            }
            int row = listStore.indexOf(cell.getModel());
            if (grid.isViewReady()) {
                getView().onCellDeselect(row, cell.getCell());
            }
        }
        cellSelections.clear();


        if ((clearCurrent) && (selection != null)) {
            int row = listStore.indexOf(selection.getModel());
            if (grid.isViewReady()) {
                getView().onCellDeselect(row, selection.getCell());
            }
            selection = null;
        }
    }

    protected void selectAllSelections() {
        for (CellSelection cell : cellSelections) {
            if (grid.isViewReady()) {
                getView().onCellSelect(cell.getRow(), cell.getCell());
            }
        }
    }

    protected void doShiftSelect() {
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

    protected void doCtrlSelect() {
        //Assume the last one on the cellSelection list was ctrl - selected......
        CellSelection cell = (cellSelections.size() > 0) ? cellSelections.get(cellSelections.size() - 1) : null;
        if (cell == null) { //shouldn't happen.
            return;
        }

        //See if the cell is already in cell selection list, if so need to unselect it.
        int index = findCell(cell.getRow(), cell.getCell(), cellSelections);
        if (index != (cellSelections.size() - 1)) { //we need to "Unselect this cell;
            if (grid.isViewReady()) {
                getView().onCellDeselect(cell.getRow(), cell.getCell());
            }
            cellSelections.remove(cellSelections.size() - 1);
            cellSelections.remove(index);
        }
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

    private void setFlags(boolean ctrlSelected, boolean shiftSelected) {
        isShiftSelected = shiftSelected;
        isCtrlSelected = ctrlSelected;
        isInMultiSelect = (shiftSelected || ctrlSelected);
    }

    private void clearFlags() {
        isShiftSelected = false;
        isCtrlSelected = false;
        isInMultiSelect = false;
    }

    private AltGridView<M> getView() {
        return (AltGridView<M>) grid.getView();
    }

    private int findCell(int row, int col, ArrayList<CellSelection<M>> cells) {
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
}
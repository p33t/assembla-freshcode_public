package biz.freshcode.learn.gwt.client.experiment.grid.reuse;

import biz.freshcode.learn.gwt.client.experiment.hoverwidget.reuse.HoverWidgetSupport;
import biz.freshcode.learn.gwt.client.experiment.mouseover.reuse.MouseOverState;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.dnd.core.client.DropTarget;

/**
 * Handles popup of a widget when mouse-over a cell (excluding drag gestures).
 */
public abstract class PopOverCell<T, U extends IsWidget> extends MouseOverCell<T> {
    private final MouseOverState mosGrid;
    private final Hoverer hoverSupp;
    private Context popupCell = null;
    private Point popupCoord = null;

    public PopOverCell(DropTarget dropper, U hoverWidget) {
        hoverSupp = new Hoverer(hoverWidget);
        mosGrid = new MouseOverState(dropper, new MouseOverState.Callback() {
            @Override
            public void stateChange(MouseOverState mos) {
                if (mos.isHover()) hoverSupp.checkPopup(); // not sure this is necessary but doesn't hurt
                else hoverSupp.disablePopup();
            }
        });
    }

    @Override
    protected void cellChange(Context cell, Element element) {
        // show the popup
        if (mosGrid.isDraggingOver()) {
            hoverSupp.disablePopup();
        } else {
            Point popupCoord = new Point(element.getAbsoluteLeft(), element.getAbsoluteTop());
            enablePopup(popupCoord, cell);
        }
    }

    @Override
    protected void noCell() {
        hoverSupp.disablePopup();
    }

    public boolean isCurrentCell(Context cell) {
        return MouseOverCell.isSameContext(cell, getCurrentCell());
    }

    public Context getCurrentCell() {
        Context superCell = super.getCurrentCell();
        if (superCell != null) return superCell;
        return popupCell;
    }

    /**
     * Subclasses can customise the hoverWidget just before it is shown.
     */
    protected void customizeHoverWidget(U hoverWidget, Context cell) {
        // do nothing by default
    }

    private void enablePopup(Point popupCoord, Context cell) {
        this.popupCoord = popupCoord;
        popupCell = cell;
        hoverSupp.enablePopup(popupCoord);
    }

    private class Hoverer extends HoverWidgetSupport<U> {
        public Hoverer(U hoverWidget) {
            super(hoverWidget);
        }

        @Override
        protected void customizeHoverWidget(U hoverWidget) {
            popupCell = getCurrentCell();
            PopOverCell.this.customizeHoverWidget(hoverWidget, popupCell);
        }

        @Override
        protected void popupHidden(Point coord) {
            if (coord.equals(popupCoord)) {
                // event not echoing.  So popupCell is not stale.
                popupCell = null;
                popupCoord = null;
            }
        }
    }
}

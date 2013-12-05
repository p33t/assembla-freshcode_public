package biz.freshcode.learn.gwt.client.experiment.grid.reuse;

import biz.freshcode.learn.gwt.client.experiment.hoverwidget.reuse.HoverWidgetSupport;
import biz.freshcode.learn.gwt.client.experiment.mouseover.reuse.MouseOverState;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.dnd.core.client.DropTarget;

/**
 * Handles popup of a widget when mouse-over a cell (excluding drag gestures).
 * TODO: Introduce lazily called 'createHoverWidget()' method so subclasses don't have to supply to constructor.
 *
 * @param <C> The type of the column data.
 * @param <W> The type of widget to shown during mouse hover.
 */
public abstract class PopOverCell<C, W extends IsWidget> extends MouseOverCell<C> {
    private final MouseOverState mosGrid;
    private final Hoverer hoverSupp;
    private Context popupCell = null;
    private Point popupCoord = null;

    public PopOverCell(DropTarget dtGrid, W hoverWidget) {
        hoverSupp = new Hoverer(hoverWidget);
        mosGrid = new MouseOverState(dtGrid, new MouseOverState.Callback() {
            @Override
            public void stateChange(MouseOverState mos) {
                if (mos.isHover()) hoverSupp.checkPopup(); // not sure this is necessary but doesn't hurt
                else hoverSupp.disablePopup();
            }
        });
    }

    @Override
    protected void cellChange(Context cell, Element element) {
        if (mosGrid.isDraggingOver()) {
            hoverSupp.disablePopup();
        } else {
            // show the popup
            Point popupCoord = new Point(element.getAbsoluteLeft(), element.getAbsoluteTop());
            enablePopup(popupCoord, cell);
        }
    }

    @Override
    protected void noCell() {
        hoverSupp.disablePopup();
    }

    @Override
    public Context getCurrentCell() {
        Context superCell = super.getCurrentCell();
        if (superCell != null) return superCell;
        return popupCell;
    }

    /**
     * Subclasses can customise the hoverWidget just before it is shown.
     */
    protected void customizeHoverWidget(W hoverWidget, Context cell) {
        // do nothing by default
    }

    private void enablePopup(Point popupCoord, Context cell) {
        this.popupCoord = popupCoord;
        popupCell = cell;
        hoverSupp.enablePopup(popupCoord);
    }

    private class Hoverer extends HoverWidgetSupport<W> {
        public Hoverer(W hoverWidget) {
            super(hoverWidget);
        }

        @Override
        protected void customizeHoverWidget(W hoverWidget) {
            popupCell = getCurrentCell();
            // TODO: Generify type of Grid row so it can be looked up in the store and supplied to customiseHoverWidget().
            // NOTE: Cannot automagically call 'updateCurrent()' on would-be hover widget because it may not match <G>
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

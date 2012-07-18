package biz.freshcode.learn.gwt.client.experiment.grid.reuse;

import biz.freshcode.learn.gwt.client.experiment.hoverwidget.reuse.HoverWidgetSupport;
import biz.freshcode.learn.gwt.client.experiment.mouseover.MouseOverState;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.dnd.core.client.DropTarget;

import java.util.Set;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newSetFrom;

/**
 * Handles popup of a widget when mouse-over a cell (excluding drag gestures).
 */
public abstract class PopOverCell<T, U extends Widget> extends AbstractCell<T> {
    private final MouseOverState mosGrid;
    private final Hoverer hoverSupp;
    private Context lastMouseOverCell = null;
    private Context popupCell = null;
    private Point popupCoord = null;

    public PopOverCell(DropTarget dropper, U hoverWidget) {
        hoverSupp = new Hoverer(hoverWidget);
        mosGrid = new MouseOverState(dropper, new MouseOverState.Callback() {
            @Override
            public void stateChange(MouseOverState mos) {
                if (mos.isDraggingOver()) hoverSupp.disablePopup();
                else hoverSupp.checkPopup(); // not sure this is necessary but doesn't hurt
            }
        });
    }

    /**
     * Subclasses can customise the hoverWidget just before it is shown.
     */
    protected void customizeHoverWidget(U hoverWidget, Context cell) {
        // do nothing by default
    }

    @Override
    public Set<String> getConsumedEvents() {
        return newSetFrom(
                MouseOutEvent.getType().getName(),
                MouseOverEvent.getType().getName()

                // Not received... might need more plumbing
//                                        DragStartEvent.getType().getName(),
//                                        DragOverEvent.getType().getName(),
//                                        DragEndEvent.getType().getName()
        );
    }

    @Override
    public void onBrowserEvent(final Context context,
                               final Element parent,
                               T value,
                               NativeEvent event,
                               ValueUpdater<T> updater) {
        if (isType(event, MouseOutEvent.getType())) {
            // manage 'currentCell'
            if (isCurrentCell(context)) {
                // exiting current cell
                lastMouseOverCell = null;
            }
            // hide popup if necessary
            hoverSupp.disablePopup();

        } else if (isType(event, MouseOverEvent.getType())) {
            // track current cell
            lastMouseOverCell = context;

            // show the popup
            if (!mosGrid.isDraggingOver()) {
                Point popupCoord = new Point(parent.getAbsoluteLeft(), parent.getAbsoluteTop());
                enablePopup(popupCoord, context);
            }
//                    Causes: AssertionError: A widget that has an existing parent widget may not be added to the detach list
//                    HTML html = HTML.wrap(parent);
        }
    }

    private void enablePopup(Point popupCoord, Context cell) {
        this.popupCoord = popupCoord;
        popupCell = cell;
        hoverSupp.enablePopup(popupCoord);

    }

    public boolean isCurrentCell(Context cell) {
        return isSameContext(cell, getCurrentCell());
    }

    public static boolean isSameContext(Context c1, Context c2) {
        return c1 != null &&
                c2 != null &&
                c1.getColumn() == c2.getColumn() &&
                c1.getIndex() == c2.getIndex();
    }

    public Context getCurrentCell() {
        if (lastMouseOverCell != null) return lastMouseOverCell;
        return popupCell;
    }

    private boolean isType(NativeEvent event, DomEvent.Type type) {
        return event.getType().equals(type.getName());
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

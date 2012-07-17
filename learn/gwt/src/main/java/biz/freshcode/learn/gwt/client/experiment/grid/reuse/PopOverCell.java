package biz.freshcode.learn.gwt.client.experiment.grid.reuse;

import biz.freshcode.learn.gwt.client.experiment.mouseover.MouseOverState;
import biz.freshcode.learn.gwt.client.uispike.builder.container.PopupPanelBuilder;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.dnd.core.client.DropTarget;

import java.util.Set;

import static biz.freshcode.learn.gwt.client.experiment.grid.Bundle2.STYLE;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newSetFrom;

/**
 * Handles popup of a widget when mouse-over a cell (excluding drag gestures).
 */
public abstract class PopOverCell<T, U extends Widget> extends AbstractCell<T> {
    protected final U hoverWidget;
    private final PopupPanel popup;
    private final MouseOverState mosPopup;
    private final MouseOverState mosGrid;
    private Point popupCoord = null;
    private Context lastMouseOverCell = null;
    private Context popupCell = null;

    public PopOverCell(DropTarget dropper, U hoverWidget) {
        this.hoverWidget = hoverWidget;
        mosGrid = new MouseOverState(dropper, new MouseOverState.Callback() {
            @Override
            public void stateChange(MouseOverState mos) {
                if (mos.isDraggingOver()) disablePopup();
                checkPopup();
            }
        });
        popup = new PopupPanelBuilder()
                .addStyleName(STYLE.hoverWidgets())
                .popupPanel;

        mosPopup = new MouseOverState(popup, new MouseOverState.Callback() {
            @Override
            public void stateChange(MouseOverState mos) {
                checkPopup();
            }
        });
    }

    /**
     * Subclasses can customise the hoverWidget just before it is shown.
     */
    protected void customizeHoverWidget(U hoverWidget, Context cell) {
        // do nothing by default
    }

    private void disablePopup() {
        popupCoord = null;
        // NOTE: Don't clear popupCell here.  It is still needed.
    }

    /**
     * Checks and updates the state of the popup.  This is designed to not repeat echo'd operations.
     * So the popup state can be jittery but will still display cleanly.
     */
    private void checkPopup() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                if (mosPopup.isOver()) {
                    // nothing
                } else if (popupEnabled()) {
                    int left = popupCoord.getX();
                    int top = popupCoord.getY();
                    if (popup.getPopupLeft() == left && popup.getPopupTop() == top) {
                        // location is accurate
                        if (!popup.isShowing()) showPopup();
                    } else {
                        // different location
                        hidePopupIfNecessary();
                        popup.setPopupPosition(left, top);
                        showPopup();
                    }
                } else {
                    hidePopupIfNecessary();
                    popupCell = null;
                }
            }
        });
    }

    private void showPopup() {
        customizeHoverWidget(hoverWidget, popupCell);
        popup.setWidget(hoverWidget);
        popup.show();
    }

    private boolean popupEnabled() {
        return popupCoord != null;
    }

    private void hidePopupIfNecessary() {
        if (popup.isShowing()) popup.hide();
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
            disablePopup();
            checkPopup();
        } else if (isType(event, MouseOverEvent.getType())) {
            // track current cell
            lastMouseOverCell = context;

            // show the popup
            if (!mosGrid.isDraggingOver()) {
                enablePopup(parent.getAbsoluteLeft(), parent.getAbsoluteTop(), context);
                checkPopup();
            }

//                    Causes: AssertionError: A widget that has an existing parent widget may not be added to the detach list
//                    HTML html = HTML.wrap(parent);
        }
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

    private void enablePopup(int left, int top, Context cell) {
        popupCoord = new Point(left, top);
        popupCell = cell;
    }
}

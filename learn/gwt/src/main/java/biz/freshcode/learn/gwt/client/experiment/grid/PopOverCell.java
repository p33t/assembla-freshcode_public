package biz.freshcode.learn.gwt.client.experiment.grid;

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
import com.sencha.gxt.dnd.core.client.DropTarget;

import java.util.Set;

import static biz.freshcode.learn.gwt.client.experiment.grid.Bundle2.STYLE;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newSetFrom;

/**
 * Handles popup of a widget when mouse-over a cell (excluding drag gestures).
 */
public abstract class PopOverCell<T> extends AbstractCell<T> {
    private PopupPanel popup;
    private MouseOverState mosPopup;
    private int[] popupCoord = null;
    private Context lastMouseOverCell = null;
    private Context popupCell = null;
    private MouseOverState mosGrid;

    PopOverCell(DropTarget dropper, Widget hoverWidget) {
        mosGrid = new MouseOverState(dropper, new MouseOverState.Callback() {
            @Override
            public void stateChange(MouseOverState mos) {
                if (mos.isDraggingOver()) disablePopup();
                checkPopup();
            }
        });
        popup = new PopupPanelBuilder()
                .widget(hoverWidget)
                .addStyleName(STYLE.hoverWidgets())
                .popupPanel;

        mosPopup = new MouseOverState(popup, new MouseOverState.Callback() {
            @Override
            public void stateChange(MouseOverState mos) {
                checkPopup();
            }
        });
    }

    public static boolean isSameContext(Context c1, Context c2) {
        return c1 != null &&
                c2 != null &&
                c1.getColumn() == c2.getColumn() &&
                c1.getIndex() == c2.getIndex();
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

    boolean isCurrentCell(Context cell) {
        return isSameContext(cell, getCurrentCell());
    }


    private boolean isType(NativeEvent event, DomEvent.Type type) {
        return event.getType().equals(type.getName());
    }

    private void hidePopupIfNecessary() {
        if (popup.isShowing()) popup.hide();
    }

    Context getCurrentCell() {
        if (lastMouseOverCell != null) return lastMouseOverCell;
        return popupCell;
    }

    private int popupTop() {
        return popupCoord[1];
    }

    private int popupLeft() {
        return popupCoord[0];
    }

    void enablePopup(int left, int top, Context cell) {
        popupCoord = new int[]{left, top};
        popupCell = cell;
    }

    void disablePopup() {
        popupCoord = null;
        // NOTE: Don't clear popupCell here.  It is still needed.
    }

    private boolean popupEnabled() {
        return popupCoord != null;
    }

    private void checkPopup() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                if (mosPopup.isOver()) {
                    // nothing
                } else if (popupEnabled()) {
                    int left = popupLeft();
                    int top = popupTop();
                    if (popup.getPopupLeft() == left && popup.getPopupTop() == top) {
                        // location is accurate
                        if (!popup.isShowing()) popup.show();
                    } else {
                        // different location
                        hidePopupIfNecessary();
                        popup.setPopupPosition(left, top);
                        popup.show();
                    }
                } else {
                    hidePopupIfNecessary();
                    popupCell = null;
                }
            }
        });
    }
}

package biz.freshcode.learn.gwt.client.experiment.grid.reuse;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;

/**
 * A cell that monitors mouse movements and facilitates hover operations.
 *
 * @param <T> The type of the column it can be assigned to.  See parent class.
 */
public abstract class MouseOverCell<T> extends AbstractCell<T> {
    private static final String MOUSE_OUT_EVENT_TYPE = MouseOutEvent.getType().getName();
    private static final String MOUSE_OVER_EVENT_TYPE = MouseOverEvent.getType().getName();
    private Context lastCell;
    private Element lastElement;
    private Context prevailingCell;

    protected MouseOverCell() {
        super(MOUSE_OUT_EVENT_TYPE, MOUSE_OVER_EVENT_TYPE);
    }

    public static boolean isSameContext(Context c1, Context c2) {
        return c1 != null &&
                c2 != null &&
                c1.getColumn() == c2.getColumn() &&
                c1.getIndex() == c2.getIndex();
    }

    @Override
    public void onBrowserEvent(final Context context,
                               final Element parent,
                               T value,
                               NativeEvent event,
                               ValueUpdater<T> updater) {
        String eventType = event.getType();
        if (MOUSE_OUT_EVENT_TYPE.equals(eventType)) {
            // manage 'currentCell'
            if (isCurrentCell(context)) {
                // exiting current cell
                lastCell = null;
                lastElement = null;
            }
            // else... probably getting events out of order.  This event can be ignored.

            checkState();
        } else if (MOUSE_OVER_EVENT_TYPE.equals(eventType)) {
            // track current cell
            lastCell = context;
            lastElement = parent;

            checkState();
        }
    }

    public boolean isCurrentCell(Context cell) {
        return isSameContext(cell, getCurrentCell());
    }

    /**
     * Returns the current cell that the mouse is over, if any.
     * This will be the parameter of the last call 'cellChange()' or null.
     */
    public Context getCurrentCell() {
        return prevailingCell;
    }

    /**
     * Indicates the current mouse over cell has changed.
     */
    protected abstract void cellChange(Context newCell, Element element);

    /**
     * Indicates the mouse is no longer over a cell.
     */
    protected abstract void noCell();

    private void checkState() {
        // need to defer to compensate for jittery events (?)
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                // NOTE: This relies on Javascript to be single threaded.
                if (lastCell == null) {
                    if (prevailingCell != null) {
                        prevailingCell = null;
                        noCell();
                    }
                }
                else {
                    if (!lastCell.equals(prevailingCell)) {
                        prevailingCell = lastCell;
                        cellChange(prevailingCell, lastElement);
                    }
                }
            }
        });
    }
}

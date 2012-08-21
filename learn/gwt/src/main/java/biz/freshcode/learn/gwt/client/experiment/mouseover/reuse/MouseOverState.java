package biz.freshcode.learn.gwt.client.experiment.mouseover.reuse;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.dnd.core.client.DndDragEnterEvent;
import com.sencha.gxt.dnd.core.client.DndDragLeaveEvent;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.DropTarget;


/**
 * Retains state about the mouse location with regards to a given control.
 * Notification of a potential state change is achieved using the 'callback' arg.
 */
public class MouseOverState {
    private boolean over = false;
    private boolean draggingOver = false;
    private boolean disableStatus = false;
    private final Callback callback;
    private final Widget widget;

    public MouseOverState(DropTarget dropTarget, Callback callback) {
        this.callback = callback;
        Widget w = dropTarget.getWidget();
        MouseHandler mh = new MouseHandler();
        w.addDomHandler(mh, MouseOverEvent.getType());
        w.addDomHandler(mh, MouseOutEvent.getType());

        // NOTE: Dnd for GWT is separate to Dnd for GXT.
        DndHandler dh = new DndHandler();
        dropTarget.addDragEnterHandler(dh);
        dropTarget.addDragLeaveHandler(dh);
        dropTarget.addDropHandler(dh);
        widget = w;
    }

    public MouseOverState(IsWidget iw, Callback callback) {
        this(new DropTarget(iw.asWidget()), callback);
        disableStatus = true;
    }

    /**
     * The widget being monitored.
     */
    public Widget getWidget() {
        return widget;
    }

    /**
     * Returns 'true' if the mouse is over but NOT dragging over the widget.
     */
    public boolean isHover() {
        return isOver() && !isDraggingOver();
    }

    /**
     * Returns 'true' if the mouse is currently 'over' the associated widget (including drag-over).
     */
    public boolean isOver() {
        return over;
    }

    /**
     * Returns 'true' if something is being 'dragged' over the associated widget.
     */
    public boolean isDraggingOver() {
        return draggingOver;
    }

    private void callback() {
        callback.stateChange(this);
    }

    class MouseHandler implements MouseOverHandler, MouseOutHandler {
        @Override
        public void onMouseOver(MouseOverEvent event) {
            over = true;
            callback();
        }

        @Override
        public void onMouseOut(MouseOutEvent event) {
            over = false;
            callback();
        }
    }

    class DndHandler implements DndDragEnterEvent.DndDragEnterHandler, DndDragLeaveEvent.DndDragLeaveHandler, DndDropEvent.DndDropHandler {
        @Override
        public void onDragEnter(DndDragEnterEvent event) {
            draggingOver = true;
            // don't give impression it can handle 'drops' if it does not
            if (disableStatus) event.getStatusProxy().setStatus(false);
            callback();
        }

        @Override
        public void onDragLeave(DndDragLeaveEvent event) {
            draggingOver = false;
            callback();
        }

        @Override
        public void onDrop(DndDropEvent event) {
            draggingOver = false;
            callback();
        }
    }

    public interface Callback {
        void stateChange(MouseOverState mos);
    }
}

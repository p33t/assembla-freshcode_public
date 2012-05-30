package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.experiment.dnd.DragData.DropOp;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.dnd.core.client.*;

public abstract class DropSupport extends DropTarget {
    private DropOp currentOpOrNull;

    public DropSupport(Widget target) {
        super(target);
        setOperation(DND.Operation.COPY); // otherwise 'MOVE' is the default
        setOverStyle(Bundle.INSTANCE.style().dragOver()); // visual feedback
        addDragEnterHandler(new DndDragEnterEvent.DndDragEnterHandler() {
            @Override
            public void onDragEnter(DndDragEnterEvent event) {
                DragData data = (DragData) event.getDragSource().getData();
                DropOp opOrNull = dropOpOrNull(data);
                if (opOrNull == null) {
                    // cannot drop
                    // TODO: Work around style problem.... Maybe get message too?
                    // NOTE: setStatus(false) seems to cause a bug when the item is dropped.  The dragOver style is not cleared.
                    event.getStatusProxy().setStatus(false);
                } else {
                    // can drop
                    currentOpOrNull = opOrNull;
                    event.getStatusProxy().update(opOrNull.getHoverMessage());
                }
            }
        });
        addDropHandler(new DndDropEvent.DndDropHandler() {
            @Override
            public void onDrop(DndDropEvent event) {
                if (currentOpOrNull != null) {
                    DropOp op = currentOpOrNull;
                    currentOpOrNull = null;
                    op.run();
                }
            }
        });
        addDragLeaveHandler(new DndDragLeaveEvent.DndDragLeaveHandler() {
            @Override
            public void onDragLeave(DndDragLeaveEvent event) {
                if (currentOpOrNull != null) {
                    currentOpOrNull = null;
                    DragData data = (DragData) event.getDragSource().getData();
                    // NOTE: Changing status text is permanent for the entire drag operation.
                    data.restoreOriginalMessage(event);
                }
            }
        });
    }



    protected abstract DropOp dropOpOrNull(DragData data);
}

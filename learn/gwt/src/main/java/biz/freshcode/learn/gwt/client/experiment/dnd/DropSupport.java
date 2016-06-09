package biz.freshcode.learn.gwt.client.experiment.dnd;

import biz.freshcode.learn.gwt.client.experiment.dnd.dragdata.DragData;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.dnd.core.client.*;

import static biz.freshcode.learn.gwt.client.experiment.dnd.Bundle.STYLE;
import static biz.freshcode.learn.gwt.client.experiment.dnd.DropAssessment.NOT_HANDLED;
import static biz.freshcode.learn.gwt.client.experiment.dnd.DropAssessment.NOT_HANDLED_MSG;
import static com.google.gwt.safehtml.shared.SafeHtmlUtils.fromTrustedString;

/**
 * Subclass of DropTarget that specifically handles 'DropData'.  Client code must subclass and implement dropQuery().
 *
 * @see #dropQuery(DragData)
 */
public abstract class DropSupport extends DropTarget {
    private DropAssessment currentAssessment = NOT_HANDLED;

    public DropSupport(Widget target) {
        super(target);
        setOperation(DND.Operation.COPY); // otherwise 'MOVE' is the default
        setOverStyle(STYLE.dragOver()); // visual feedback
        addDragEnterHandler(new EnterHandler());
        addDropHandler(new DropHandler());
        addDragMoveHandler(new MoveHandler());
        addDragLeaveHandler(new LeaveHandler());
    }

    /**
     * Assess whether or not the given OldDragData can be dropped onto the target.
     */
    protected abstract DropAssessment dropQuery(DragData dd);

    /**
     * Gives subclass an opportunity to invalidate the previous drop assessment.
     * This is called as the mouse moves.
     */
    protected boolean isStillAccurate(DropAssessment assessment) {
        return true;
    }

    private void updateAssessment(DragData data, StatusProxy statusProxy) {
        currentAssessment = dropQuery(data);
        if (currentAssessment.isDroppable()) {
            statusProxy.update(fromTrustedString(currentAssessment.getDescriptionString()));
            statusProxy.setStatus(true);
        } else {
            // cannot drop
            // NOTE: setStatus(false) seems to cause a bug when the item is dropped.  The dragOver style is not cleared.
//                    event.getStatusProxy().setStatus(false);
            // just put 'not allowed' icon up and ignore the 'drop' event
            statusProxy.setStatus(true, Bundle.INSTANCE.dropNotAllowed());
            SafeHtml reason = SafeHtmlUtils.fromString(currentAssessment.getDescriptionString());
            // Use original message if data not handled
            if (NOT_HANDLED_MSG.equals(reason)) reason = data.getOriginalMessage();
            statusProxy.update(reason);
        }
    }

    private class EnterHandler implements DndDragEnterEvent.DndDragEnterHandler {
        @Override
        public void onDragEnter(DndDragEnterEvent event) {
            Object raw = event.getDragSource().getData();
            if (!(raw instanceof DragData)) return; // only handling known data
            DragData data = (DragData) raw;
            updateAssessment(data, event.getStatusProxy());
        }
    }

    private class DropHandler implements DndDropEvent.DndDropHandler {
        @Override
        public void onDrop(DndDropEvent event) {
            DropAssessment da = currentAssessment;
            currentAssessment = NOT_HANDLED;
            if (da.isDroppable()) da.drop();
        }
    }

    private class LeaveHandler implements DndDragLeaveEvent.DndDragLeaveHandler {
        @Override
        public void onDragLeave(DndDragLeaveEvent event) {
            currentAssessment = NOT_HANDLED;
            Object raw = event.getDragSource().getData();
            if (raw instanceof DragData) {
                DragData data = (DragData) raw;
                // NOTE: Changing status text is permanent for the entire drag operation.
                data.restoreOriginalMessage(event);
            }
        }
    }

    private class MoveHandler implements DndDragMoveEvent.DndDragMoveHandler {
        @Override
        public void onDragMove(DndDragMoveEvent event) {
            Object raw = event.getDragSource().getData();
            if (!(raw instanceof DragData)) return; // only handling known data
            if (!isStillAccurate(currentAssessment)) {
                // need to reassess
                DragData data = (DragData) raw;
                StatusProxy statusProxy = event.getStatusProxy();
                updateAssessment(data, statusProxy);
            }
        }
    }
}

package biz.freshcode.learn.gwt.client.experiment.dnd;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.dnd.core.client.*;

public abstract class DropSupport extends DropTarget {
    private DropAssessment currentAssessment = DropAssessment.BLANK;

    public DropSupport(Widget target) {
        super(target);
        setOperation(DND.Operation.COPY); // otherwise 'MOVE' is the default
        setOverStyle(Bundle.INSTANCE.style().dragOver()); // visual feedback
        addDragEnterHandler(new DndDragEnterEvent.DndDragEnterHandler() {
            @Override
            public void onDragEnter(DndDragEnterEvent event) {
                Object raw = event.getDragSource().getData();
                if (!(raw instanceof DragData)) return; // only handling known data
                DragData data = (DragData) raw;
                currentAssessment = dropQuery(data);
                StatusProxy statusProxy = event.getStatusProxy();
                if (currentAssessment.isDroppable()) statusProxy.update(currentAssessment.getDescription());
                else {
                    // cannot drop
                    // NOTE: setStatus(false) seems to cause a bug when the item is dropped.  The dragOver style is not cleared.
//                    event.getStatusProxy().setStatus(false);
                    // just put 'not allowed' icon up and ignore the 'drop' event
                    statusProxy.setStatus(true, Bundle.INSTANCE.dropNotAllowed());
                    statusProxy.update(currentAssessment.getReason());
                }
            }
        });
        addDropHandler(new DndDropEvent.DndDropHandler() {
            @Override
            public void onDrop(DndDropEvent event) {
                DropAssessment da = currentAssessment;
                currentAssessment = DropAssessment.BLANK;
                if (da.isDroppable()) da.getRunnable().run();
            }
        });
        addDragLeaveHandler(new DndDragLeaveEvent.DndDragLeaveHandler() {
            @Override
            public void onDragLeave(DndDragLeaveEvent event) {
                currentAssessment = DropAssessment.BLANK;
                DragData data = (DragData) event.getDragSource().getData();
                // NOTE: Changing status text is permanent for the entire drag operation.
                data.restoreOriginalMessage(event);
            }
        });
    }

    /**
     * Assess whether or not the given DragData can be dropped onto the target.
     */
    protected abstract DropAssessment dropQuery(DragData dd);

    /**
     * The result of querying whether or not something can be dropped onto a target.
     */
    public static final class DropAssessment {
        public static final DropAssessment BLANK = new DropAssessment("Not Ready");

        private String reason;
        private String description;
        private Runnable runnable;

        public DropAssessment(String reason) {
            this.reason = reason;
        }

        public DropAssessment(String description, Runnable runnable) {
            this.description = description;
            this.runnable = runnable;
        }

        /**
         * Returns 'true' if the data can be dropped.
         */
        public boolean isDroppable() {
            return runnable != null;
        }

        /**
         * The reason the data cannot be dropped.
         */
        public String getReason() {
            return reason;
        }

        /**
         * The description of the would-be drop operation.
         */
        public String getDescription() {
            return description;
        }

        /**
         * The operation to run when the drop is performed.
         */
        public Runnable getRunnable() {
            return runnable;
        }
    }
}

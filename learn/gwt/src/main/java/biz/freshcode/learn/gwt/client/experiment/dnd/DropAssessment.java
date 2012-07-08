package biz.freshcode.learn.gwt.client.experiment.dnd;

/**
 * The result of querying whether or not something can be dropped onto a target.
 */
public interface DropAssessment {
    /**
     * Default message for widgets that don't handle any drops.
     */
    String NOT_HANDLED_MSG = "Not Handled";

    /**
     * Return 'true' if the dragged item can be dropped in which case the 'drop()' method may be called later.
     */
    boolean isDroppable();

    /**
     * Describe the drop operation OR a reason why it is not droppable.
     */
    String getSummary();

    /**
     * Actually perform the drop. Throws an exception if the assessment is not 'Droppable'.
     */
    void drop();
}

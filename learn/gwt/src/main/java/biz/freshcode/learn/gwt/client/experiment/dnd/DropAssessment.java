package biz.freshcode.learn.gwt.client.experiment.dnd;

/**
 * Indicates whether or not a given dragged data can be dropped on a widget.
 */
public final class DropAssessment {
    public static final DropAssessment NOT_HANDLED = new DropAssessment("Not Handled");

    private Runnable runnable;
    private Object description;

    public DropAssessment(Object descriptionOfRejection) {
        this.description = descriptionOfRejection;
    }

    public DropAssessment(Object descriptionOfOperation, Runnable runnable) {
        this.description = descriptionOfOperation;
        this.runnable = runnable;
    }

    /**
     * The runnable command that will be called during a 'drop'.
     */
    public Runnable getRunnable() {
        return runnable;
    }

    /**
     * Returns 'true' if the data can be dropped.
     */
    public boolean isDroppable() {
        return runnable != null;
    }

    /**
     * The operation to run when the drop is performed.
     */
    public void drop() {
        runnable.run();
    }

    /**
     * A description of the would-be drop operation OR a reason for the drop rejection.
     * 'toString()' is called on this result to show to the user.
     *
     * @see DropSupport#isStillAccurate(DropAssessment)
     */
    public Object getDescription() {
        return description;
    }

    public String getDescriptionString() {
        return description.toString();
    }
}

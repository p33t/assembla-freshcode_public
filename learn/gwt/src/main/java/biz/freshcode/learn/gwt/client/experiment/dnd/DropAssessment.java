package biz.freshcode.learn.gwt.client.experiment.dnd;

/**
 * Indicates whether or not a given dragged data can be dropped on a widget.
 */
public final class DropAssessment {
    public static final DropAssessment NOT_HANDLED = new DropAssessment("Not Handled");

    private String description;
    private Runnable runnable;
    private Object reason;

    public DropAssessment(Object reason) {
        this.reason = reason;
    }

    public DropAssessment(String description, Runnable runnable) {
        this.description = description;
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
     * The reason the data cannot be dropped is supplied via 'toString()' on this result.
     * This facilitates efficient use of 'DropSupport.hasExpired()' to determine if drop can now be allowed.
     * @see DropSupport#isStillAccurate(DropAssessment)
     */
    public Object getReason() {
        return reason;
    }

    /**
     * The operation to run when the drop is performed.
     */
    public void drop() {
        runnable.run();
    }

    /**
     * A description of the would-be drop operation.
     */
    public String getDescription() {
        return description;
    }

}

package biz.freshcode.learn.gwt.client.experiment.dnd;

/**
 * Indicates whether or not a given dragged data can be dropped on a widget.
 */
public final class DropAssessment<T extends Runnable, R> {
    public static final DropAssessment NOT_HANDLED = new DropAssessment("Not Handled");

    private String description;
    private T runnable;
    private R reason;

    public DropAssessment(R reason) {
        this.reason = reason;
    }

    public DropAssessment(String description, T runnable) {
        this.description = description;
        this.runnable = runnable;
    }

    /**
     * The runnable command that will be called during a 'drop'.
     */
    public T getRunnable() {
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
     * @see DropSupport#hasExpired(DropAssessment)
     */
    public R getReason() {
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

package biz.freshcode.learn.gwt.client.experiment.dnd;

/**
 * Default implementation of DropAssessment.  Should be enough for 90% of cases.
 */
public final class DefaultDropAssessment<T extends Runnable> implements DropAssessment {
    public static final DefaultDropAssessment NOT_HANDLED = new DefaultDropAssessment(NOT_HANDLED_MSG);

    private String summary;

    private T runnable;

    public DefaultDropAssessment(String reason) {
        this.summary = reason;
    }

    public DefaultDropAssessment(String description, T runnable) {
        this.summary = description;
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
     * The reason the data cannot be dropped OR the description of the would-be drop operation.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * The operation to run when the drop is performed.
     */
    public void drop() {
        runnable.run();
    }
}

package biz.freshcode.learn.gwt.common.client.builder;

/**
 * A builder construct that iterates over a collection.
 * @param <T> The Builder type.
 * @param <U> The collection element type.
 */
public abstract class IterConstruct<T, U> extends Construct<T> {
    private final Iterable<U> elems;

    public IterConstruct(Iterable<U> elems) {
        this.elems = elems;
    }

    @Override
    public void run() {
        for (U e : elems) {
            runElem(e);
        }
    }

    public abstract void runElem(U elem);
}

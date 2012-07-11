package biz.freshcode.learn.gwt.client.experiment.grid;

class RowEntity {
    static int counter = 1;
    final int id = counter++;
    int flags = 0;

    @Override
    public String toString() {
        return "#" + id;
    }

    /**
     * Enumeration of flags shown in a cell.
     */
    static enum Flag {
        RED(1), GREEN(2);

        final int mask;

        Flag(int mask) {
            this.mask = mask;
        }

        boolean isSet(int flags) {
            return (flags & mask) > 0;
        }
    }
}

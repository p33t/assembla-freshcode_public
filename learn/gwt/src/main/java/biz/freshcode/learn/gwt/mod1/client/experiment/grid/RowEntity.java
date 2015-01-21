package biz.freshcode.learn.gwt.mod1.client.experiment.grid;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;

class RowEntity {
    static int counter = 1;
    final int id = counter++;
    int flags = 0;
    String str; // editable field

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

    static class IdProvider implements ModelKeyProvider<RowEntity> {
        @Override
        public String getKey(RowEntity item) {
            return "" + item.id;
        }
    }

    static class StrProvider implements ValueProvider<RowEntity, String> {
        @Override
        public String getValue(RowEntity object) {
            return object.str;
        }

        @Override
        public void setValue(RowEntity object, String value) {
            object.str = value;
        }

        @Override
        public String getPath() {
            return "";
        }
    }
}

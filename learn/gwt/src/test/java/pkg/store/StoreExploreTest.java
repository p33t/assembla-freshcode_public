package pkg.store;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Collection;

public class StoreExploreTest {
    ListStore store = new ListStore(new ModelKeyProvider<Battery>() {
        @Override
        public String getKey(Battery item) {
            return item.getId();
        }
    });

    @Test
    public void basics() {
        /*
        NOTES:
        - Added records are not 'modifications'
        - Autocommit default false
        */


        store.add(battery("a", .5));
        store.add(battery("b", .6));
        Collection mods = store.getModifiedRecords();
        // new records are not modifications
        Assert.assertEquals(0, mods.size());
    }

    Battery battery(String id, double charge) {
        Battery b = new Battery();
        b.setId(id);
        b.setCharge(charge);
        return b;
    }

    static class Battery {
        private String id;
        private double charge;

        /**
         * Return a value indicating portion charged.  Typically 0-1 inclusive.
         */
        public double getCharge() {
            return charge;
        }

        public void setCharge(double charge) {
            this.charge = charge;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    static interface BatteryAccess extends PropertyAccess<Battery> {
        ModelKeyProvider<Battery> id();
    }
}

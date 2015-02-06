package biz.freshcode.learn.gwt2.common.client.util;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ClientUtil {
    public static final Logger LOG = Logger.getLogger("App");

    public static void log(String msg) {
        LOG.info(msg);
    }

    public static Grid<String> createGrid(ModelKeyProvider<String> keyer, ColumnConfig<String, ?>... cols) {
        return createGrid(keyer, Arrays.asList(cols));
    }

    public static Grid<String> createGrid(ModelKeyProvider<String> keyer, List<ColumnConfig<String, ?>> cols) {
        Grid<String> g = new Grid<>(new ListStore<>(keyer), new ColumnModel<>(cols));
        g.setBorders(true);
        g.getView().setForceFit(true);
        return g;
    }
}

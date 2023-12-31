package biz.freshcode.learn.gwt2.common.client.util;

import com.google.gwt.user.client.Window;
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

    @SuppressWarnings("unchecked")
    public static <T> Grid<T> createGrid(ModelKeyProvider<? super T> keyer, ColumnConfig<T, ?>... cols) {
        return createGrid(keyer, Arrays.asList(cols));
    }

    public static <T> Grid<T> createGrid(ModelKeyProvider<? super T> keyer, List<ColumnConfig<T, ?>> cols) {
        Grid<T> g = new Grid<>(new ListStore<>(keyer), new ColumnModel<>(cols));
        g.setBorders(true);
        g.getView().setForceFit(true);
        return g;
    }

    /**
     * Alternative to System.lineSeparator() in the browser.
     */
    public static String clientLineSeparator() {
        String platform = Window.Navigator.getPlatform();
        if (platform.toLowerCase().contains("windows")) return "\r\n";
        return "\n";
    }
}

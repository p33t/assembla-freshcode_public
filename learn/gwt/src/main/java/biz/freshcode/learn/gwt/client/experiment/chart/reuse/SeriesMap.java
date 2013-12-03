package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import com.google.inject.Provider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static biz.freshcode.learn.gwt.client.experiment.chart.reuse.UnityAccess.unityAccess;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.*;

/**
 * A collection of named and configured PointSeries.  This is an immutable structure.
 */
public class SeriesMap {
    public static final SeriesMap NIL = new SeriesMap();
    public static final ValueProvider<Integer, Integer> ACCESS_X = unityAccess();
    public static final ModelKeyProvider<Integer> KEY_X = unityAccess();
    private Map<String, PointSeries> map = newMap();

    private SeriesMap() {
    }

    /**
     * Creates a ValueProvider that supplies y values for the given series.
     */
    public static ValueProvider<Integer, Double> accessY(String seriesName, Provider<SeriesMap> mapVar) {
        return new AccessY(seriesName, mapVar);
    }

    /**
     * Returns a copy of this SeriesMap with the given series added.
     */
    public SeriesMap put(String seriesName, PointSeries series) {
        SeriesMap sm = new SeriesMap();
        sm.map.putAll(map);
        sm.map.put(seriesName, series);
        return sm;
    }

    /**
     * Returns the corresponding y value in the specified series.
     * If the x value is outside the range of the series or the series does not exist then NaN is returned.
     *
     * @see PointSeries#resolve(int, double)
     */
    public double lookup(String seriesName, int x) {
        PointSeries ps = map.get(seriesName);
        if (ps == null) return Double.NaN;
        return ps.resolve(x, Double.NaN);
    }

    /**
     * Replace the contents of the given list store with an ordered,
     * non-duplicating list of all x values present in the map.
     */
    public void replaceAll(ListStore<Integer> store) {
        List<Integer> xs = newListFrom(allXs());
        Collections.sort(xs);
        store.replaceAll(xs);
    }

    /**
     * Return a complete set of x values.
     */
    private Set<Integer> allXs() {
        Set<Integer> s = newSet();
        for (PointSeries ps : map.values()) ps.addXs(s);
        return s;
    }

    /**
     * Provides access to a Y value for a predefined series.
     */
    private static class AccessY implements ValueProvider<Integer, Double> {
        private final String seriesName;
        private final Provider<SeriesMap> mapVar;

        public AccessY(String seriesName, Provider<SeriesMap> mapVar) {
            this.seriesName = seriesName;
            this.mapVar = mapVar;
        }

        @Override
        public Double getValue(Integer x) {
            return mapVar.get().lookup(seriesName, x);
        }

        @Override
        public void setValue(Integer x, Double y) {
            // nothing
        }

        @Override
        public String getPath() {
            return seriesName;
        }
    }
}
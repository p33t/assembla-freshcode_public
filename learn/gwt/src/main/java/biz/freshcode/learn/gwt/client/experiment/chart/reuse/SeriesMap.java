package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;

import java.util.Map;
import java.util.Set;

import static biz.freshcode.learn.gwt.client.experiment.chart.reuse.UnityAccess.unityAccess;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newMap;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newSet;

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
     * Creates a ValueProvider that supplies y values for the given series.
     */
    public ValueProvider<Integer, Double> accessY(String seriesName) {
        return new AccessY(seriesName);
    }

    /**
     * Return a complete set of x values.
     */
    public Set<Integer> allXs() {
        Set<Integer> s = newSet();
        for (PointSeries ps: map.values()) ps.addXs(s);
        return s;
    }
    
    /**
     * Provides access to a Y value for a predefined series.
     */
    private class AccessY implements ValueProvider<Integer, Double> {
        private final String seriesName;

        public AccessY(String seriesName) {
            this.seriesName = seriesName;
        }

        @Override
        public Double getValue(Integer x) {
            return lookup(seriesName, x);
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

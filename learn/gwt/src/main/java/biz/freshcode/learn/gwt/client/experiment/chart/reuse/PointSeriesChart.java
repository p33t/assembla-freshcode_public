package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import com.google.inject.Provider;
import com.sencha.gxt.core.client.ValueProvider;

public abstract class PointSeriesChart extends AbstractChart<Integer> implements Provider<SeriesMap> {
    private SeriesMap map = null;

    public PointSeriesChart() {
        super(SeriesMap.KEY_X);
    }

    @Override
    public SeriesMap get() {
        return map;
    }

    /**
     * Instanciates a value provider for the specified series that is or will be present in the
     * {@code SeriesMap} associated with this chart.
     * @see SeriesMap
     */
    protected ValueProvider<Integer, Double> accessY(String seriesName) {
        return SeriesMap.accessY(seriesName, this);
    }

    protected void replaceAll(SeriesMap map) {
        this.map = map;
        map.replaceAll(chart.getStore());
    }
}

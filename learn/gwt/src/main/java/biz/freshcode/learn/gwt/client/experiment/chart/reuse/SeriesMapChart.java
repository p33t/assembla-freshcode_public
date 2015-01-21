package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import com.google.inject.Provider;
import com.sencha.gxt.core.client.ValueProvider;

public abstract class SeriesMapChart extends AbstractChart<Integer> implements Provider<SeriesMap> {
    public static final ValueProvider<Integer,Integer> ACCESS_X = SeriesMap.ACCESS_X;
    private SeriesMap map = null;

    public SeriesMapChart() {
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
    protected ValueProvider<Integer, Double> accessY(String seriesName, Double defVal) {
        return SeriesMap.accessY(seriesName, this, defVal);
    }

    protected void replaceAll(SeriesMap map) {
        this.map = map;
        map.replaceAll(chart.getStore());
    }
}

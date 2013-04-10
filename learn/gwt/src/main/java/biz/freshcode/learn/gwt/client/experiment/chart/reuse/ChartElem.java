package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import java.util.Map;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newMap;
import static biz.freshcode.learn.gwt.client.util.ExceptionUtil.illegalArg;

/**
 * An element in a GXT Chart.  This class illustrates the short-coming of GXT charting.
 * Specifically, every x coord needs a y coord for each series on the chart.
 */
public class ChartElem {
    private final Double x;
    private final Map<String, Double> ys = newMap();

    public ChartElem(Double x) {
        this.x = x;
    }

    public void setY(String key, Double value) {
        if (value == null) throw illegalArg("y value cannot be 'null'");
        if (value.equals(Double.NaN)) ys.remove(key);
        else ys.put(key, value);
    }

    /**
     * Returns the y value for the specified series.  If no value is specified then NaN is returned.
     * This assumes that lines have been interpolated as necessary to populate all x values between two end points.
     */
    public Double getY(String key) {
        Double y = ys.get(key);
        if (y == null) y = Double.NaN;
        return y;
    }

    public Double getX() {
        return x;
    }
}

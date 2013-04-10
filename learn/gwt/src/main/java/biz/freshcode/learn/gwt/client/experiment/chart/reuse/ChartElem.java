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

    @Override
    public String toString() {
        return x + ":" + ys;
    }

    /**
     * NOTE: Primitive means int cast works
     */
    public ChartElem(double x) {
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChartElem chartElem = (ChartElem) o;

        if (!x.equals(chartElem.x)) return false;
        //noinspection RedundantIfStatement
        if (!ys.equals(chartElem.ys)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + ys.hashCode();
        return result;
    }

    public void setY(String key, Double value) {
        if (value == null) throw illegalArg("y value cannot be 'null'");
        if (value.equals(Double.NaN)) ys.remove(key);
        else ys.put(key, value);
    }

    /**
     * Convenience for chaining.
     *
     * @see #setY(String, Double)
     */
    public ChartElem addY(String key, double value) {
        setY(key, value);
        return this;
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

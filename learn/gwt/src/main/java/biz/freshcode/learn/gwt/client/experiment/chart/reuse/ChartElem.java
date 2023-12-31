package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import biz.freshcode.learn.gwt2.common.client.util.chart.SeriesMapChart;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import java.util.Map;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newMap;
import static biz.freshcode.learn.gwt2.common.client.util.ExceptionUtil.illegalArg;

/**
 * An element in a GXT Chart.  This class illustrates the short-coming of GXT charting.
 * Specifically, every x coord needs a y coord for each series on the chart.
 * <br/>
 * DEPRECATED.
 * @see SeriesMapChart
 */
@Deprecated // Use the SeriesMapChart approach
public class ChartElem {
    private final Double x;
    private final Map<String, Double> ys = newMap();
    private final Double defVal;

    /**
     * NOTE: Primitive means int cast works
     */
    public ChartElem(double x, Double defVal) {
        this.x = x;
        this.defVal = defVal;
    }

    @Override
    public String toString() {
        return x + ":" + ys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChartElem)) return false;

        ChartElem chartElem = (ChartElem) o;

        if (!defVal.equals(chartElem.defVal)) return false;
        if (!x.equals(chartElem.x)) return false;
        //noinspection RedundantIfStatement
        if (!ys.equals(chartElem.ys)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return x.hashCode();
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
     * Returns the y value for the specified series.  If no value is specified then defValue is returned.
     * This assumes that lines have been interpolated as necessary to populate all x values between two end points.
     */
    public Double getY(String key) {
        Double y = ys.get(key);
        if (y == null) y = defVal;
        return y;
    }

    public ChartElem mapY(MapFun<Double, Double> mapper) {
        ChartElem e = new ChartElem(x, defVal);
        for (String key: ys.keySet()) {
            Double i = getY(key);
            Double o = mapper.map(i);
            e.setY(key, o);
        }
        return e;
    }

    public static interface Access extends PropertyAccess<ChartElem> {
        Access CE_ACCESS = GWT.create(Access.class);

        @Editor.Path("x")
        ModelKeyProvider<ChartElem> xKey();

        ValueProvider<ChartElem, Double> x();
    }

    public static class AccessY implements ValueProvider<ChartElem, Double> {
        private final String key;

        public AccessY(String key) {
            this.key = key;
        }

        @Override
        public Double getValue(ChartElem e) {
            return e.getY(key);
        }

        @Override
        public void setValue(ChartElem object, Double value) {
            // nothing
        }

        @Override
        public String getPath() {
            return key;
        }
    }

    public Double getX() {
        return x;
    }
}

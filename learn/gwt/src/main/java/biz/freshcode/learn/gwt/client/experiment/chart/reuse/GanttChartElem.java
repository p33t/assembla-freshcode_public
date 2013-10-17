package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import java.util.Map;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newMap;
import static biz.freshcode.learn.gwt.client.util.ExceptionUtil.illegalArg;

/**
 * An element in a Gantt style GXT Chart.  This class illustrates the short-coming of GXT charting.
 * Specifically, every x coord needs a y coord for each series on the chart.
 */
public class GanttChartElem {
    private final Integer x;
    private final Map<String, Integer> ys = newMap();

    public GanttChartElem(Integer x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return x + ":" + ys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GanttChartElem chartElem = (GanttChartElem) o;

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

    public void setY(String key, Integer value) {
        if (value == null) throw illegalArg("y value cannot be 'null'");
//        if (value.equals(Double.NaN)) ys.remove(key);
        else ys.put(key, value);
    }

    /**
     * Convenience for chaining.
     *
     * @see #setY(String, Integer)
     */
    public GanttChartElem addY(String key, Integer value) {
        setY(key, value);
        return this;
    }

    /**
     * Returns the y value for the specified series.  If no value is specified then NaN is returned.
     * This assumes that lines have been interpolated as necessary to populate all x values between two end points.
     */
    public Integer getY(String key) {
        //noinspection UnnecessaryLocalVariable
        Integer y = ys.get(key);
//        if (y == null) y = Double.NaN;
        return y;
    }

    public static interface Access extends PropertyAccess<GanttChartElem> {
        Access ACCESS = GWT.create(Access.class);

        @Editor.Path("x")
        ModelKeyProvider<GanttChartElem> xKey();

        ValueProvider<GanttChartElem, Integer> x();
    }

    public static class AccessY implements ValueProvider<GanttChartElem, Integer> {
        private final String key;

        public AccessY(String key) {
            this.key = key;
        }

        @Override
        public Integer getValue(GanttChartElem e) {
            return e.getY(key);
        }

        @Override
        public void setValue(GanttChartElem object, Integer value) {
            // nothing
        }

        @Override
        public String getPath() {
            return key;
        }
    }

    public Integer getX() {
        return x;
    }
}

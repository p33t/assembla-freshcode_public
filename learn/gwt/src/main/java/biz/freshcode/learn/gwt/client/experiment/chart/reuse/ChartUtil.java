package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import com.sencha.gxt.core.client.util.PrecisePoint;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.*;
import static biz.freshcode.learn.gwt.client.util.ExceptionUtil.illegalArg;
import static biz.freshcode.learn.gwt.client.util.ExceptionUtil.illegalState;

public class ChartUtil {
    /**
     * Take the series' and interpolate their values so that they can be plotted on a GXT Chart.
     * Specifically, all x values from any series must be populated
     * for all other series within the bounds of the desired line.
     */
    public static List<ChartElem> interpolate(Map<String, List<PrecisePoint>> lines, SeriesGap gaps) {
        Map<Double, ChartElem> m = newMap();
        for (Double x : allXs(lines)) {
            for (String key : lines.keySet()) {
                // for each series and x
                List<PrecisePoint> line = lines.get(key);
                int ix = findPos(line, x);
                if (ix >= 0) {
                    PrecisePoint p = line.get(ix);
                    double y;
                    if (p.getX() == x) y = p.getY();
                    else if (ix == 0) continue; // x is before the line's domain
                    else y = interpolateY(line.get(ix - 1), p, x);
                    setY(m, x, key, y, gaps);
                }
                // else x after line domain
            }
        }

        return orderedList(m);
    }

    /**
     * Converts a map of lines to a List of ChartElems.
     */
    public static List<ChartElem> convertToElems(Map<String, List<PrecisePoint>> lines, SeriesGap gap) {
        Map<Double, ChartElem> m = newMap();
        for (String key : lines.keySet()) {
            // for each series
            List<PrecisePoint> line = lines.get(key);
            for (PrecisePoint p : line) {
                // for each point
                // ensure 'Y' is set
                setY(m, p.getX(), key, p.getY(), gap);
            }
        }

        return orderedList(m);
    }

    /**
     * Find the first index of the first point 'p' in the line where 'px >= x'.
     * Or return -1 if no point exists.
     */
    private static int findPos(List<PrecisePoint> line, Double x) {
        int size = line.size();
        PrecisePoint pn = line.get(size - 1);

        if (x <= pn.getX()) {
            // x is definitely in range (optimisation)
            for (int i = 0; i < size; i++) {
                PrecisePoint p = line.get(i);
                if (p.getX() >= x) return i;
            }
        }
        return -1;
    }

    /**
     * Interpolates a y value for the specified straight line at position x.
     */
    private static double interpolateY(PrecisePoint a, PrecisePoint b, Double x) {
        if (a.getX() >= b.getX()) throw illegalArg("a & b must be in order and not the same. a:" + a + ", b:" + b);
        if (!(a.getX() < x && x < b.getX()))
            throw illegalArg("x must be between a & b.  x: " + x + ", a:" + a + ", b:" + b);
        if (a.getY() == b.getY()) return a.getY();
        double ratio = (x - a.getX()) / (b.getX() - a.getX());
        if (ratio > 1.0)
            throw illegalState("Ratio should be <= 1.  x = " + x + ", ratio: " + ratio);
        return a.getY() + (ratio * (b.getY() - a.getY()));
    }

    /**
     * Returns the set of all x values in the given series'.
     */
    private static Set<Double> allXs(Map<String, List<PrecisePoint>> lines) {
        Set<Double> xs = newSet();
        for (List<PrecisePoint> line : lines.values()) {
            for (PrecisePoint e : line) {
                xs.add(e.getX());
            }
        }
        return xs;
    }

    /**
     * Converts the map of ChartElems into an ordered list.
     */
    private static List<ChartElem> orderedList(Map<Double, ChartElem> m) {
        List<Double> keys = newListFrom(m.keySet());
        Collections.sort(keys);
        List<ChartElem> l = newList();
        for (Double key : keys) l.add(m.get(key));
        return l;
    }

    /**
     * Lazily puts a CharElem in the given map and sets the specified 'y' value.
     */
    private static void setY(Map<Double, ChartElem> m, Double x, String key, Double y, SeriesGap gap) {
        ChartElem target = m.get(x);
        if (target == null) {
            target = new ChartElem(x, gap.defVal());
            m.put(x, target);
        }
//        GWT.log("Setting " + key + " " + x + "," + y);
        target.setY(key, y);
    }
}

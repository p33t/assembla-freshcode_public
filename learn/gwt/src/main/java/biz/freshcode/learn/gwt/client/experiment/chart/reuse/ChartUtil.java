package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.core.client.util.PrecisePoint;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static biz.freshcode.learn.gwt.client.experiment.chart.reuse.SeriesGap.ZERO_DEF;
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
     * Convert a group of PointSeries to a group of PrecisePoint list.  Most likely so that interpolate can be used.
     * @see #interpolate(java.util.Map, SeriesGap)
     */
    public static Map<String, List<PrecisePoint>> convertToPps(Map<String, PointSeries> series) {
        Map<String, List<PrecisePoint>> m = newMap();
        for (String key : series.keySet()) {
            List<PrecisePoint> pps = newList();
            for (Point p : series.get(key)) {
                PrecisePoint pp = new PrecisePoint((double) p.getX(), p.getY());
                pps.add(pp);
            }
            m.put(key, pps);
        }
        return m;
    }

    /**
     * Converts the given step plots into a datastructure ready for display.
     *
     * @see #areaChartPrep(PointSeries, int, int)
     * @see #interpolate(java.util.Map, SeriesGap)
     */
    public static List<ChartElem> stepPlotPrepare(Map<String, PointSeries> series) {
        // determine max X value
        int max = Integer.MIN_VALUE;
        for (PointSeries ps: series.values()) {
            int x = ps.getMaxX();
            if (max < x) max = x;
        }

        // prepare series for area charting
        Map<String, PointSeries> prep = newMap();
        for (String key: series.keySet()) {
            PointSeries orig = series.get(key);
            PointSeries alt = areaChartPrep(orig, 0, max);
            prep.put(key, alt);
        }

        Map<String, List<PrecisePoint>> m = convertToPps(prep);

        return interpolate(m, ZERO_DEF);
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

    /**
     * Adds extra points to the series so it can be used on an area chart, and remove any superfluous points.
     * Specifically, an extra point is added just prior to each x value so that it appears to be a step plot.
     * This depends on x-axis being sufficiently wide so that changes of y in a single unit of 'x' appear vertical.
     *
     * @param ps       The points to use
     * @param defaultY The prevailing Y value before the first point.  This is typically '0'.
     * @param maxX The maximum value of x used in surrounding PointSeries which needs to be present.
     */
    private static PointSeries areaChartPrep(PointSeries ps, int defaultY, int maxX) {
        if (ps.isEmpty()) return ps;
        PointSeries result = PointSeries.NIL;
        int lastY = defaultY;
        for (Point p : ps) {
            if (p.getY() == lastY) {
                if (result.isEmpty()) {
                    // the first point in the series has same 'Y' value
                    // need to add it
                    result = result.add(p);
                }
//                else {
                // no Y value change
                // do nothing
//                }
            } else {
                // Y value has changed
                Point pseudo = new Point(p.getX() - 1, lastY);
                result = result.add(pseudo, p);
            }
            lastY = p.getY();
        }
        if (ps.getMaxX() < maxX) {
            // need terminating point
            Point pseudo = new Point(maxX, lastY);
            result = result.add(pseudo);
        }
        return result;
    }
}

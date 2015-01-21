package biz.freshcode.learn.gwt.mod1.client.experiment.chart.reuse;

import com.sencha.gxt.core.client.util.Point;

import java.util.*;

import static biz.freshcode.learn.gwt.mod1.client.util.AppCollectionUtil.newListFrom;
import static biz.freshcode.learn.gwt.mod1.client.util.AppCollectionUtil.newMap;
import static biz.freshcode.learn.gwt.mod1.client.util.ExceptionUtil.illegalArg;

/**
 * Holds an ordered series of points where x can only have one value.
 * This is an 'immutable' data structure.
 */
public class PointSeries implements Iterable<Point> {
    public static final PointSeries NIL = new PointSeries();
    private final Map<Integer, Point> points = newMap();
    private List<Integer> orderedPointsOrNull;

    private PointSeries() {
        // nothing
    }

    public PointSeries add(Point... arr) {
        PointSeries ps = copy();
        for (Point p : arr) ps.put(p);
        return ps;
    }

    public PointSeries add(PointSeries ps) {
        PointSeries result = copy();
        result.points.putAll(ps.points);
        return result;
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    /**
     * Returns the largest value of 'x' in the series.
     * If the series is empty then Integer.MIN_VALUE is returned.
     */
    public int getMaxX() {
        if (isEmpty()) return Integer.MIN_VALUE;
        List<Integer> l = lazyGetOrderedPoints();
        return l.get(l.size() - 1);
    }

    /**
     * Returns the smallest value of 'x' in the series.
     * If the series is empty then Integer.MIN_VALUE is returned.
     */
    public int getMinX() {
        if (isEmpty()) return Integer.MIN_VALUE;
        List<Integer> l = lazyGetOrderedPoints();
        return l.get(0);
    }

    /**
     * Enables iteration of points in x-order.
     */
    @Override
    public Iterator<Point> iterator() {
        List<Integer> xs = lazyGetOrderedPoints();
        final Iterator<Integer> itX = xs.iterator();
        return new Iterator<Point>() {
            @Override
            public boolean hasNext() {
                return itX.hasNext();
            }

            @Override
            public Point next() {
                Integer x = itX.next();
                Point p = points.get(x);
                // defensive copy
                return new Point(p.getX(), p.getY());
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Read only iteration");
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointSeries)) return false;

        PointSeries points1 = (PointSeries) o;

        //noinspection RedundantIfStatement
        if (!points.equals(points1.points)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return points.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("[");
        boolean first = true;
        for (Point p : this) {
            if (!first) sb.append(" ");
            sb.append("(").append(p.getX()).append(",").append(p.getY()).append(")");
            first = false;
        }
        return sb.append("]").toString();
    }

    /**
     * Lookup the 'y' value for the given 'x' value.
     * If x falls outside the domain of the series then return 'defVal'.
     * Result will be interpolated if necessary assuming linear transitions.
     */
    public double resolve(int x, double defVal) {
        Point point = points.get(x);
        if (point != null) return point.getY();

        Integer before = null;
        Integer after = null;
        for (Integer i : lazyGetOrderedPoints()) {
            if (i < x) before = i;
            else if (i > x) {
                after = i;
                break;
            }
        }
        if (before == null || after == null) return defVal;

        double ratio = (x - before) / (double) (after - before);
        int yBefore = points.get(before).getY();
        int yDiff = points.get(after).getY() - yBefore;
        return ratio * yDiff + yBefore;
    }

    /**
     * Add all the X values to the given collection.
     */
    public void addXs(Collection<Integer> l) {
        l.addAll(lazyGetOrderedPoints());
    }

    /**
     * Convert this series to step-like points with a zero-value lead-in and sustained trailing value (if necessary).
     * The resulting point series is guaranteed to have a point at xFrom and xTo.
     * Step-like means having extra points just prior to a y-value change to make the plot appear to 'step'.
     */
    public PointSeries stepify(int xFrom, int xTo) {
        if (xFrom >= xTo) throw illegalArg("Need positive domain.");
        int prevY = 0;
        PointSeries ps = PointSeries.NIL;
        for (Integer x : lazyGetOrderedPoints()) {
            Point p = points.get(x);
            if (x > xTo) {
                // beyond domain
                // point series is effectively exhausted
                break;
            } else if (x == xFrom) {
                // point has landed on desired start of domain
                // elegant initiator point
                ps = ps.add(p);
            } else if (x > xFrom) {
                Point stepper = new Point(x - 1, prevY);
                if (ps.isEmpty() && stepper.getX() > xFrom) {
                    // an artificial initiator point is needed
                    ps = ps.add(new Point(xFrom, prevY));
                }
                ps = ps.add(stepper, p);
            }
//            else if (x < xFrom); // ignore prelude point
            prevY = p.getY();
        }

        if (ps.isEmpty()) {
            // domain of line is outside of desired domain
            ps = ps.add(new Point(xFrom, prevY), new Point(xTo, prevY));
        } else if (ps.getMaxX() < xTo) {
            // need terminator point
            ps = ps.add(new Point(xTo, prevY));
        }
//        else the requisite points are already present
        return ps;
    }

    private List<Integer> lazyGetOrderedPoints() {
        if (orderedPointsOrNull == null) {
            List<Integer> xs = newListFrom(points.keySet());
            Collections.sort(xs);
            orderedPointsOrNull = xs;
        }
        return orderedPointsOrNull;
    }

    private PointSeries copy() {
        PointSeries result = new PointSeries();
        result.points.putAll(points);
        return result;
    }

    private Point put(Point p) {
        return this.points.put(p.getX(), p);
    }
}

package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import com.sencha.gxt.core.client.util.Point;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newMap;

/**
 * Holds an ordered series of points where x can only have one value.  
 * This is an 'immutable' data structure.
 */
public class PointSeries implements Iterable<Point> {
    public static final PointSeries NIL = new PointSeries();
    private final Map<Integer, Point> points = newMap();
    
    private PointSeries() {
        // nothing
    }
    
    public PointSeries add(Point... arr) {
        PointSeries ps = copy();
        for (Point p: arr) ps.put(p);
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
        int max = Integer.MIN_VALUE;
        for (Integer i: points.keySet()) if (max < i) max = i;
        return max;
    }

    /**
     * Enables iteration of points in x-order.
     */
    @Override
    public Iterator<Point> iterator() {
        List<Integer> xs = newListFrom(points.keySet());
        Collections.sort(xs);
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
        StringBuilder sb  = new StringBuilder().append("[");
        boolean first = true;
        for (Point p: this) {
            if (!first) sb.append(" ");
            sb.append("(").append(p.getX()).append(",").append(p.getY()).append(")");
            first = false;
        }
        return sb.append("]").toString();
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

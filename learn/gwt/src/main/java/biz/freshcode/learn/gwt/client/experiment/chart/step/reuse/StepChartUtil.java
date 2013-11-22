package biz.freshcode.learn.gwt.client.experiment.chart.step.reuse;

import biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartUtil;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.core.client.util.PrecisePoint;

import java.util.List;
import java.util.Map;

import static biz.freshcode.learn.gwt.client.experiment.chart.reuse.SeriesGap.ZERO_DEF;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newMap;

public class StepChartUtil {


    /**
     * Adds extra points to the series so it can be used on an area chart, and remove any superfluous points.
     * Specifically, an extra point is added just prior to each x value so that it appears to be a step plot.
     * This depends on x-axis being sufficiently wide so that changes of y in a single unit of 'x' appear vertical.
     *
     * @param ps       The points to use
     * @param defaultY The prevailing Y value before the first point.  This is typically '0'.
     */
    public static PointSeries areaChartPrep(PointSeries ps, int defaultY) {
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
        return result;
    }

    /**
     * Converts the given step plots into a datastructre ready for display.
     *
     * @see #areaChartPrep(PointSeries, int)
     * @see ChartUtil#interpolate(java.util.Map, biz.freshcode.learn.gwt.client.experiment.chart.reuse.SeriesGap)
     */
    public static List<ChartElem> prepAndInterpolate(Map<String, PointSeries> series) {
        double maxX = Double.MIN_VALUE;
        Map<String, PrecisePoint> endPoints = newMap();
        Map<String, List<PrecisePoint>> m = newMap();
        for (String key : series.keySet()) {
            PointSeries ps = series.get(key);
            List<PrecisePoint> pps = newList();
            PointSeries prep = areaChartPrep(ps, 0);
            for (Point p : prep) {
                double x = p.getX();
                if (maxX < x) maxX = x;
                PrecisePoint pp = new PrecisePoint(x, p.getY());
                pps.add(pp);

                // capture the end point of each series
                // this assume points are ordered
                endPoints.put(key, pp);
            }
            m.put(key, pps);
        }

        // ensure end point for each series has same X value
        // Otherwise the default 'zero' interferes
        for (String key : endPoints.keySet()) {
            PrecisePoint end = endPoints.get(key);
            if (end.getX() < maxX) {
                // need artifical end point
                PrecisePoint pseudo = new PrecisePoint(maxX, end.getY());
                m.get(key).add(pseudo);
            }
        }

        return ChartUtil.interpolate(m, ZERO_DEF);
    }
}

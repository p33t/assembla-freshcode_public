package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import com.sencha.gxt.core.client.util.PrecisePoint;
import junit.framework.TestCase;

import java.util.List;
import java.util.Map;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newMap;

public class ChartUtilTest extends TestCase {
    public static final String ANGLE = "angle";
    public static final String STRAIGHT = "straight";

    public void testInterpolate() {
/*
        |
        |
        |    o--o         straight
        |
        |
        |
        |  o--o
        |      \
        |       \         angle
        |        \
        +---------o
*/
        List<PrecisePoint> straight = newListFrom(
                new PrecisePoint(4, 8),
                new PrecisePoint(8, 8)
        );

        List<PrecisePoint> angle = newListFrom(
                new PrecisePoint(2, 4),
                new PrecisePoint(6, 4),
                new PrecisePoint(10, 0)
        );
        Map<String, List<PrecisePoint>> seriesMap = newMap();
        seriesMap.put(STRAIGHT, straight);
        seriesMap.put(ANGLE, angle);

        List<ChartElem> expected = newListFrom(
                new ChartElem(2)
                        .addY(ANGLE, 4),
                new ChartElem(4)
                        .addY(ANGLE, 4)
                        .addY(STRAIGHT, 8),
                new ChartElem(6)
                        .addY(ANGLE, 4)
                        .addY(STRAIGHT, 8),
                new ChartElem(8)
                        .addY(ANGLE, 2)
                        .addY(STRAIGHT, 8),
                new ChartElem(10)
                        .addY(ANGLE, 0)
        );

        assertEquals(expected, ChartUtil.interpolate(seriesMap));
    }

    public void testInterpolateNegative() {
        List<PrecisePoint> straight = newListFrom(
                new PrecisePoint(4, -8),
                new PrecisePoint(8, -8)
        );

        List<PrecisePoint> angle = newListFrom(
                new PrecisePoint(2, -4),
                new PrecisePoint(6, -4),
                new PrecisePoint(10, 0)
        );
        Map<String, List<PrecisePoint>> seriesMap = newMap();
        seriesMap.put(STRAIGHT, straight);
        seriesMap.put(ANGLE, angle);

        List<ChartElem> expected = newListFrom(
                new ChartElem(2)
                        .addY(ANGLE, -4),
                new ChartElem(4)
                        .addY(ANGLE, -4)
                        .addY(STRAIGHT, -8),
                new ChartElem(6)
                        .addY(ANGLE, -4)
                        .addY(STRAIGHT, -8),
                new ChartElem(8)
                        .addY(ANGLE, -2)
                        .addY(STRAIGHT, -8),
                new ChartElem(10)
                        .addY(ANGLE, 0)
        );

        assertEquals(expected, ChartUtil.interpolate(seriesMap));
    }
}

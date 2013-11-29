package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import com.sencha.gxt.core.client.util.Point;
import junit.framework.TestCase;

import static java.lang.Double.NaN;

public class PointSeriesTest extends TestCase {
    public void testResolve() {
        PointSeries subj = PointSeries.NIL.add(new Point(2, 4), new Point(4, 3), new Point(6, 6));
        assertEquals(NaN, subj.resolve(1, NaN));
        assertEquals(4.0, subj.resolve(2, NaN));
        assertEquals(3.5, subj.resolve(3, NaN));
        assertEquals(4.5, subj.resolve(5, NaN));
        assertEquals(NaN, subj.resolve(7, NaN));

        subj = PointSeries.NIL.add(new Point(-4, -4), new Point(-2, -2));
        assertEquals(-3.0, subj.resolve(-3, NaN));
    }
}

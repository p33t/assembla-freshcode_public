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

    public void testStepify() {
        Point P2_4 = new Point(2, 4);
        Point P4_3 = new Point(4, 3);
        // stepper point (causes the 'step' effect)
        Point S3_4 = new Point(3, 4);
        Point S0_1 = new Point(1, 0);

        PointSeries subj = PointSeries.NIL.add(P2_4, P4_3);
        // matching domain
        checkStepify(subj, 2, 4, P2_4, S3_4, P4_3);
        // stepper point matches initiator point
        checkStepify(subj, 3, 4, S3_4, P4_3);
        // artificial initiator point
        checkStepify(subj, 0, 4, new Point(0, 0), S0_1, P2_4, S3_4, P4_3);
        // terminator point
        checkStepify(subj, 2, 5, P2_4, S3_4, P4_3, new Point(5, 3));
        // desired domain is before series domain
        checkStepify(subj, 0, 1, new Point(0, 0), new Point(1, 0));
        // desired domain is after series domain
        checkStepify(subj, 5, 6, new Point(5, 3), new Point(6, 3));

        // stepper point unnecessary
        checkStepify(PointSeries.NIL.add(P2_4, S3_4, P4_3), 2, 4, P2_4, S3_4, P4_3);
    }

    private void checkStepify(PointSeries subj, int xFrom, int xTo, Point... ps) {
        assertEquals(PointSeries.NIL.add(ps), subj.stepify(xFrom, xTo));
    }
}

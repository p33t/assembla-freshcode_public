package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import junit.framework.TestCase;

public class ChartElemTest extends TestCase {
    public void testYSimple() {
        ChartElem e = new ChartElem(1.0);
        assertEquals(1.0, e.getX());
        e.setY("a", 1.0);
        assertEquals(1.0, e.getY("a"));
        assertEquals(Double.NaN, e.getY("b"));
    }
}

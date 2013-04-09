package biz.freshcode.learn.gwt.client.experiment.chart;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public class XyBean {
    double x, y;

    public XyBean(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public interface Access extends PropertyAccess<XyBean> {
        static Access ACCESS_XY = GWT.create(Access.class);

        ValueProvider<XyBean, Double> x();

        ValueProvider<XyBean, Double> y();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

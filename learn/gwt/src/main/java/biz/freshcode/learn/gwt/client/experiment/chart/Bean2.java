package biz.freshcode.learn.gwt.client.experiment.chart;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public class Bean2 {
    double x, y;

    public Bean2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public interface Access extends PropertyAccess<Bean2> {
        static Access ACCESS_2 = GWT.create(Access.class);

        ValueProvider<Bean2, Double> x();

        ValueProvider<Bean2, Double> y();
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

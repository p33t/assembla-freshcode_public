package biz.freshcode.learn.gwt.mod1.client.experiment.chart;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface PointAccess extends PropertyAccess<Point> {
    PointAccess ACCESS = GWT.create(PointAccess.class);

    ValueProvider<Point, Integer> x();

    ValueProvider<Point, Integer> y();
}

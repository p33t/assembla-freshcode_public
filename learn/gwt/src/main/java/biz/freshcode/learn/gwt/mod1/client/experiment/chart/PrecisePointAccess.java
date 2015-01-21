package biz.freshcode.learn.gwt.mod1.client.experiment.chart;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.PrecisePoint;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface PrecisePointAccess extends PropertyAccess<PrecisePoint> {
    PrecisePointAccess ACCESS = GWT.create(PrecisePointAccess.class);

    ValueProvider<PrecisePoint, Double> x();

    ValueProvider<PrecisePoint, Double> y();
}

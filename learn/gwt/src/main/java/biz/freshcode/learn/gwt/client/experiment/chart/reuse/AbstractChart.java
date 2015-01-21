package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.Composite;

import java.util.List;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newListFrom;

public abstract class AbstractChart<T> extends Composite {
    protected final Chart<T> chart = new Chart<T>();

    /**
     * Colour scheme for elements.  This can be customised.
     * 
     * @see #colour(int)
     */
    protected final List<RGB> colours = newListFrom(
            RGB.BLUE, RGB.GRAY, RGB.PURPLE,
            RGB.RED, RGB.YELLOW, RGB.GREEN,
            RGB.MAGENTA, RGB.PINK, RGB.ORANGE, 
            RGB.CYAN
    );

    protected AbstractChart(ModelKeyProvider<T> keyProvider) {
        chart.setStore(new ListStore<T>(keyProvider));
        super.initWidget(chart);
    }

    /**
     * Return a colour to use for the given index.  This uses the 'colours' field and wraps when it is exhausted.
     * @see #colours
     */
    protected RGB colour(int ix) {
        return colours.get(ix % colours.size());
    }

    @Override
    protected final void initWidget(Widget widget) {
        throw new UnsupportedOperationException();
    }

    protected NumericAxis<T> getNumericAxis(Chart.Position posn) {
        //noinspection unchecked
        return (NumericAxis<T>) chart.getAxis(posn);
    }

    protected void clearNumericAxis(Chart.Position posn) {
        NumericAxis<T> left = getNumericAxis(posn);
        List<ValueProvider> l = newList();
        l.addAll(left.getFields());
        for (ValueProvider vp : l) {
            //noinspection unchecked
            left.removeField(vp);
        }
    }

    protected void clearSeries() {
        for (int i = chart.getSeries().size() - 1; i >= 0; i--) {
            chart.removeSeries(i);
        }
    }

    /**
     * Clears data from the chart.  Subclasses should override and clear relevant numeric axes.
     */
    protected void clearChart() {
        chart.getStore().clear();
        clearSeries();
    }    
}

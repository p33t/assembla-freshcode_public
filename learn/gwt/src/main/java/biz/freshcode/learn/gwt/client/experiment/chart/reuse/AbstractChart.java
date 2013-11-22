package biz.freshcode.learn.gwt.client.experiment.chart.reuse;

import biz.freshcode.learn.gwt.client.builder.gxt.chart.ChartBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;

import java.util.List;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;

public abstract class AbstractChart extends Composite {
    protected final Chart<ChartElem> chart = new Chart<ChartElem>();
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

    public AbstractChart() {
        ChartBuilder<ChartElem> builder = new ChartBuilder<ChartElem>(chart)
                .store(new ListStore<ChartElem>(ChartElem.Access.CE_ACCESS.xKey()));
        setupChart(builder);
        super.initWidget(chart);
    }

    /**
     * Return a colour to use for the given index.  This uses the 'colours' field and wraps when it is exhausted.
     * @see #colours
     */
    protected RGB colour(int ix) {
        return colours.get(ix % colours.size());
    }
    
    /**
     * Add and configure axes.
     */
    protected abstract void setupChart(ChartBuilder<ChartElem> builder);

    @Override
    protected final void initWidget(Widget widget) {
        throw new UnsupportedOperationException();
    }

    protected NumericAxis<ChartElem> getNumericAxis(Chart.Position posn) {
        //noinspection unchecked
        return (NumericAxis<ChartElem>) chart.getAxis(posn);
    }

    protected void clearNumericAxis(Chart.Position posn) {
        NumericAxis<ChartElem> left = getNumericAxis(posn);
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
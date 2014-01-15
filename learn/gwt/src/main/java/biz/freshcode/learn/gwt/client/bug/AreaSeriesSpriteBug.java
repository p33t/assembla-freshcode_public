package biz.freshcode.learn.gwt.client.bug;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.AreaSeries;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.sencha.gxt.chart.client.chart.Chart.Position;

public class AreaSeriesSpriteBug implements IsWidget {
    PointAccess ACCESS = GWT.create(PointAccess.class);
    private Widget widget;
    private Chart<Point> chart;
    private Random random = new Random(System.currentTimeMillis());

    @Override
    public Widget asWidget() {
        if (widget == null) widget = createWidget();
        return widget;
    }

    Widget createWidget() {
        BorderLayoutContainer blc = new BorderLayoutContainer();
        HorizontalLayoutContainer hlc = new HorizontalLayoutContainer();
        blc.setNorthWidget(hlc);
        hlc.add(new TextButton("Populate", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                populate();
            }
        }));
        hlc.add(new TextButton("Clear (is broke)", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                clear();
            }
        }));
        blc.setCenterWidget(chart = createChart());
        return blc;
    }

    private Chart<Point> createChart() {
        Chart<Point> ch = new Chart<Point>();
        ch.setStore(new ListStore<Point>(ACCESS.xKey()));

        NumericAxis<Point> left = new NumericAxis<Point>();
        left.setPosition(Position.LEFT);
        left.setMinimum(0);
        left.addField(ACCESS.y());
        ch.addAxis(left);

        NumericAxis<Point> bottom = new NumericAxis<Point>();
        bottom.setPosition(Position.BOTTOM);
        bottom.setMinimum(0);
        bottom.addField(ACCESS.x());
        ch.addAxis(bottom);
        return ch;
    }

    private void addSeries(Chart<Point> ch) {
        AreaSeries<Point> ser = new AreaSeries<Point>();
        //noinspection SuspiciousNameCombination
        ser.setYAxisPosition(Position.LEFT);
        ser.addYField(ACCESS.y());
        ser.addColor(0, RGB.GREEN);
        ser.setXField(ACCESS.x());
        ch.addSeries(ser);
    }

    private void clear() {
        chart.getStore().clear();
        // no better...chart.getStore().replaceAll(new ArrayList<Point>());
        chart.redrawChart();
    }

    private void populate() {
        // NOTE: It appears clearing the store does not remove the sprites.
        chart.getStore().clear();
        for (int i = chart.getSeries().size(); i > 0; i--) {
            chart.removeSeries(0);
        }

        addSeries(chart);

        List<Point> ps = getPoints();
        chart.getStore().replaceAll(ps);
        chart.redrawChart();
    }

    private List<Point> getPoints() {
        int y = random.nextInt(5) + 1;
        int x = 10 * (random.nextInt(10) + 1);
        List<Point> ps = new ArrayList<Point>();
        ps.add(new Point(x += 99, 0));
        ps.add(new Point(x += 1, y));
        ps.add(new Point(x += 49, y));
        ps.add(new Point(x += 1, 0));
        ps.add(new Point(300, 0));
        return ps;
    }

    public static interface PointAccess extends PropertyAccess<Point> {
        ValueProvider<Point, Integer> x();

        ValueProvider<Point, Integer> y();

        @Editor.Path("x")
        ModelKeyProvider<Point> xKey();
    }
}

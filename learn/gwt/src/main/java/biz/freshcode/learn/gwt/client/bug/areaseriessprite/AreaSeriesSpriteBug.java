package biz.freshcode.learn.gwt.client.bug.areaseriessprite;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.AreaSeries;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static biz.freshcode.learn.gwt.client.bug.areaseriessprite.PointAccess.ACCESS;
import static com.sencha.gxt.chart.client.chart.Chart.Position;

public class AreaSeriesSpriteBug implements IsWidget {
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
        hlc.add(new TextButton("Clear", new SelectEvent.SelectHandler() {
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

        AreaSeries<Point> ser = new AreaSeries<Point>();
        //noinspection SuspiciousNameCombination
        ser.setYAxisPosition(Position.LEFT);
        ser.addYField(ACCESS.y());
        ser.addYField(ACCESS.y());
        ser.addColor(0, RGB.GREEN);
        ser.addColor(1, RGB.LIGHTGRAY);
        ser.setXField(ACCESS.x());
        ch.addSeries(ser);
        return ch;
    }

    private void clear() {
        chart.getStore().clear();
        chart.redrawChart();
    }

    private void populate() {
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
}

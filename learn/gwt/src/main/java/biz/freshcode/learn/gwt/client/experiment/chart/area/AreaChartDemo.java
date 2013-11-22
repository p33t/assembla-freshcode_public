package biz.freshcode.learn.gwt.client.experiment.chart.area;

import biz.freshcode.learn.gwt.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.axis.CategoryAxisBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.series.AreaSeriesBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.IdentityHashProvider;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import static com.sencha.gxt.chart.client.chart.Chart.Position;
import static com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

public class AreaChartDemo extends AbstractIsWidget<BorderLayoutContainer> {
    private static final ElemAccess E_ACCESS = GWT.create(ElemAccess.class);
    private Chart<Elem> chart;
    private ListStore<Elem> store = new ListStore<Elem>(new IdentityHashProvider<Elem>());

    public AreaChartDemo() {
        store.add(new Elem("Bruce", 3, 4));
        store.add(new Elem("Sam", 4, 3));
        store.add(new Elem("Victor", 8, 2));
    }

    @Override
    protected BorderLayoutContainer createWidget() {
        return new BorderLayoutContainerBuilder()
                .northWidget(new HorizontalLayoutContainerBuilder()
                        .add(new TextButton("Redraw", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                chart.redrawChart();
                            }
                        }))
                        .horizontalLayoutContainer, new BorderLayoutData(40))
                .centerWidget(chart = new ChartBuilder<Elem>()
                        .store(store)
                        .addAxis(new NumericAxisBuilder<Elem>()
                                .position(Position.LEFT)
                                .addField(E_ACCESS.appleCount())
                                .addField(E_ACCESS.orangeCount())
                                .numericAxis)
                        .addAxis(new CategoryAxisBuilder<Elem, String>()
                                .position(Position.BOTTOM)
                                .field(E_ACCESS.student())
                                .categoryAxis)
                        .addSeries(new AreaSeriesBuilder<Elem>()
                                .yAxisPosition(Position.LEFT)
                                .addYField(0, E_ACCESS.appleCount())
                                .addYField(1, E_ACCESS.orangeCount())
                                .addColor(0, RGB.CYAN)
                                .addColor(1, RGB.BLUE)
                                .addColor(2, RGB.GREEN)
                                .areaSeries)
                        .chart)
                .borderLayoutContainer;
    }

    public static class Elem {
        private String student;
        private int appleCount;
        private int orangeCount;

        public Elem(String student, int appleCount, int orangeCount) {
            this.student = student;
            this.appleCount = appleCount;
            this.orangeCount = orangeCount;
        }

        public int getAppleCount() {
            return appleCount;
        }

        public int getOrangeCount() {
            return orangeCount;
        }

        public String getStudent() {
            return student;
        }
    }

    interface ElemAccess extends PropertyAccess<Elem> {
        ValueProvider<Elem, String> student();

        ValueProvider<Elem, Integer> appleCount();

        ValueProvider<Elem, Integer> orangeCount();
    }
}
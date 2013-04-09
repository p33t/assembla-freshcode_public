package biz.freshcode.learn.gwt.client.experiment.chart;

import biz.freshcode.learn.gwt.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.axis.CategoryAxisBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.series.BarSeriesBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.Construct;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.IdentityHashProvider;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import static biz.freshcode.learn.gwt.client.experiment.chart.ChartDemo.PointAccess.ACCESS;
import static biz.freshcode.learn.gwt.client.util.ExceptionUtil.illegalArg;
import static com.sencha.gxt.chart.client.chart.Chart.Position;

public class ChartDemo extends AbstractIsWidget<BorderLayoutContainer> {
    private int attemptCount = 1;

    @Override
    protected BorderLayoutContainer createWidget() {
        return new BorderLayoutContainerBuilder()
                .northWidget(new HorizontalLayoutContainerBuilder()
                        .construct(new Construct<HorizontalLayoutContainerBuilder>() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= attemptCount; i++) {
                                    builder.add(btn(i));
                                    builder.add(new HTML("&nbsp;"));
                                }
                            }
                        })
                        .horizontalLayoutContainer)
                .borderLayoutContainer;
    }

    private TextButton btn(final int buttonNum) {
        return new TextButton("#" + buttonNum, new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                BorderLayoutContainer blc = asWidget();
                blc.setCenterWidget(chartX(buttonNum));
                blc.forceLayout();
            }
        });
    }

    private IsWidget chartX(int buttonNum) {
        switch (buttonNum) {
            case 1:
                return chart1();
            case 2:
                return chart2();
            default:
                throw illegalArg("Bad button number " + buttonNum);
        }
    }

    private IsWidget chart2() {
        throw new RuntimeException("chart2 not implemented");
    }

    /**
     * This doco helped...
     * http://www.sencha.com/blog/ext-gwt-3-drawing-and-charting/
     */
    private IsWidget chart1() {
        ListStore<Point> store = new ListStore<Point>(new IdentityHashProvider<Point>());
        store.add(new Point(1, 2));
        store.add(new Point(2, 3));
        store.add(new Point(3, 2));
        store.add(new Point(4, 3));
        store.add(new Point(5, 2));
        store.add(new Point(6, 3));

        return new ChartBuilder<Point>()
                .store(store)
                .addAxis(new NumericAxisBuilder<Point>()
                        .position(Position.BOTTOM)
                        .titleConfig(new TextSprite("First Axis"))
                        .addField(ACCESS.x())
                        .steps(6) // prevents extra labels which get rounded: 1 2 2 3 3 4 4...
                        .numericAxis)
//                        Nope....
//                .addAxis(new NumericAxisBuilder<Point>()
//                        .position(Position.LEFT)
//                        .titleConfig(new TextSprite("Second Axis"))
//                        .addField(ACCESS.y())
//                        .numericAxis)
//                NOTE: CategoryAxis does NOT interpolate
                .addAxis(new CategoryAxisBuilder<Point, Integer>()
                        .position(Position.LEFT)
                        .titleConfig(new TextSprite("Second Axis"))
                        .field(ACCESS.x())
                        .categoryAxis)
                .addSeries(new BarSeriesBuilder<Point>()
                        .yAxisPosition(Position.LEFT)
                        .addYField(ACCESS.y())
                        .column(true)
                        .barSeries)
                .chart;
    }

    public interface PointAccess extends PropertyAccess<Point> {
        PointAccess ACCESS = GWT.create(PointAccess.class);

        ValueProvider<Point, Integer> x();

        ValueProvider<Point, Integer> y();
    }
}

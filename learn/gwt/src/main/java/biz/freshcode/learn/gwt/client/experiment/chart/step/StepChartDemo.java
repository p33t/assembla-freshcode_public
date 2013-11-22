package biz.freshcode.learn.gwt.client.experiment.chart.step;

import biz.freshcode.learn.gwt.client.builder.gxt.chart.ChartBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.axis.NumericAxisBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.chart.series.AreaSeriesBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem;
import biz.freshcode.learn.gwt.client.experiment.chart.step.reuse.PointSeries;
import biz.freshcode.learn.gwt.client.experiment.chart.step.reuse.StepChartUtil;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.List;
import java.util.Map;

import static biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem.Access.CE_ACCESS;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newMap;
import static com.sencha.gxt.chart.client.chart.Chart.Position;
import static com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

public class StepChartDemo extends AbstractIsWidget<BorderLayoutContainer> {
    private final Chart<ChartElem> chart = new Chart<ChartElem>();

    @Override
    protected BorderLayoutContainer createWidget() {
//        ChartElem.AccessY accessC = new ChartElem.AccessY("C");
        return new BorderLayoutContainerBuilder()
                .northWidget(new HorizontalLayoutContainerBuilder()
                        .add(new TextButton("Prime", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                primeChart();
                            }
                        }))
                        .add(new TextButton("Redraw", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                chart.redrawChart();
                            }
                        }))
                        .horizontalLayoutContainer, new BorderLayoutData(40))
                .centerWidget(new ChartBuilder<ChartElem>(chart)
                        .store(new ListStore<ChartElem>(CE_ACCESS.xKey()))
                        .addAxis(new NumericAxisBuilder<ChartElem>()
                                .position(Position.BOTTOM)
                                .titleConfig(new TextSprite("Time"))
                                .addField(CE_ACCESS.x())
                                .numericAxis)
                        .addAxis(new NumericAxisBuilder<ChartElem>()
                                .position(Position.LEFT)
                                .titleConfig(new TextSprite("Count"))
                                .numericAxis)
//                        .addAxis(new CategoryAxisBuilder<ChartElem, String>()
//                                .position(Position.BOTTOM)
//                                .field(new ValueProvider<ChartElem, String>() {
//                                    @Override
//                                    public String getValue(ChartElem e) {
//                                        Double a = e.getY("A");
//                                        GWT.log("A = " + a);
//                                        if (a.equals(Double.NaN)) return "";
//                                        return a + "";
//                                    }
//
//                                    @Override
//                                    public void setValue(ChartElem object, String value) {
//
//                                    }
//
//                                    @Override
//                                    public String getPath() {
//                                        return "";
//                                    }
//                                })
////                                .addField(accessC)
//                                .categoryAxis)
//                        .addSeries(new AreaSeriesBuilder<ChartElem>()
//                                .yAxisPosition(Position.LEFT)
//                                .addYField(0, accessC)
//                                .addColor(0, RGB.PINK)
//                                .areaSeries)
                        .chart)
                .borderLayoutContainer;
    }

    private void primeChart() {
        Map<String, PointSeries> pss = newMap();
        pss.put("A", PointSeries.NIL.add(new Point(100, 5), new Point(200, 7), new Point(300, 0)));
        // ends with non-zero y-val
        pss.put("B", PointSeries.NIL.add(new Point(50, 3), new Point(200, 1)));
        List<ChartElem> items = StepChartUtil.prepAndInterpolate(pss);
        chart.getStore().replaceAll(items);

        ChartElem.AccessY accessA = new ChartElem.AccessY("A");
        ChartElem.AccessY accessB = new ChartElem.AccessY("B");

        NumericAxis<ChartElem> left = getAxis(Position.LEFT);
        for (ValueProvider<? super ChartElem, ? extends Number> f : left.getFields()) left.removeField(f);
        left.addField(accessA);
        left.addField(accessB);

        while (chart.getSeries().size() != 0) chart.removeSeries(0);
        chart.addSeries(new AreaSeriesBuilder<ChartElem>()
                .yAxisPosition(Position.LEFT)
                .addYField(0, accessA)
                .addColor(0, RGB.BLUE)
                .addYField(accessB)
                .addColor(1, RGB.GREEN)
                .areaSeries);
    }

    private NumericAxis<ChartElem> getAxis(Position posn) {
        //noinspection unchecked
        return (NumericAxis<ChartElem>) chart.getAxis(posn);
    }
}

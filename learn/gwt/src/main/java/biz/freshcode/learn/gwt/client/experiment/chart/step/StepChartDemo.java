package biz.freshcode.learn.gwt.client.experiment.chart.step;

import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.experiment.chart.step.reuse.PointSeries;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.Map;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newMap;
import static com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

public class StepChartDemo extends AbstractIsWidget<BorderLayoutContainer> {
    private MyStepChart myStep;

    @Override
    protected BorderLayoutContainer createWidget() {
//        ChartElem.AccessY accessC = new ChartElem.AccessY("C");
        return new BorderLayoutContainerBuilder()
                .northWidget(new HorizontalLayoutContainerBuilder()
                        .add(new TextButton("Go", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                go();
                            }
                        }))
                        .horizontalLayoutContainer, new BorderLayoutData(40))
                .centerWidget(myStep = new MyStepChart())
                .borderLayoutContainer;
    }

    private void go() {
        Map<String, PointSeries> pss = newMap();
        pss.put("A", PointSeries.NIL.add(new Point(100, 5), new Point(200, 7), new Point(300, 0)));
        // ends with non-zero y-val
        pss.put("B", PointSeries.NIL.add(new Point(50, 3), new Point(200, 1), new Point(250, 2)));


        myStep.display(pss);
    }
}

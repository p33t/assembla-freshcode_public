package biz.freshcode.learn.gwt.client.experiment.chart.step;

import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.PointSeries;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.AppCollectionUtil;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.Map;
import java.util.Random;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newMap;
import static com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

public class StepChartDemo extends AbstractIsWidget<BorderLayoutContainer> {
    private ChartElemStepChart myStep;
    private Random random = new Random(System.currentTimeMillis());

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
                        .add(new TextButton("Clear", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                myStep.display(AppCollectionUtil.<String, PointSeries>newMap());
                            }
                        }))
                        .horizontalLayoutContainer, new BorderLayoutData(40))
                .centerWidget(myStep = new ChartElemStepChart())
                .borderLayoutContainer;
    }

    private void go() {
        Map<String, PointSeries> pss = newMap();
        pss.put("A", PointSeries.NIL.add(new Point(100, randomY(5)), new Point(200, randomY(7)), new Point(300, 0)));
        // ends with non-zero y-val
        pss.put("B", PointSeries.NIL.add(new Point(50, randomY(3)), new Point(200, randomY(1)), new Point(250, randomY(2))));
        myStep.display(pss);
    }

    private int randomY(int max) {
        return random.nextInt(max + 1);
    }
}

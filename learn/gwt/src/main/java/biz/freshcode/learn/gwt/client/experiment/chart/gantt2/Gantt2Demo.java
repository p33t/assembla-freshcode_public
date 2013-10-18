package biz.freshcode.learn.gwt.client.experiment.chart.gantt2;

import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt.reuse.StartDurn;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse.*;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.*;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;

public class Gantt2Demo extends AbstractIsWidget<BorderLayoutContainer> implements GanttBarFocusEvent.Handler {
    private static final int HR = 60;
    private static final List<BarInfo> BARS = newListFrom(
            new BarInfo("id1", "Maintenance", "Alpha", new RGB("#ff0000"), new StartDurn(6 * HR, 4 * HR)),
            new BarInfo("id2", "Minow Inbound", "Bravo", new RGB("#00ff00"), new StartDurn(8 * HR, 3 * HR)),
            new BarInfo("id3", "Whisky & Coke Outbound", "Bravo", new RGB("#0000ff"), new StartDurn(12 * HR, 3 * HR)),
            new BarInfo("id4", "Whisky & Coke Outbound", "Charlie", new RGB("#0000ff"), new StartDurn(12 * HR, 3 * HR)),
            new BarInfo("id5", "Maintenance", "Delta", new RGB("#8f0000"), new StartDurn(10 * HR, 4 * HR)),
            new BarInfo("id6", "Dreamy Inbound", "Echo", new RGB("#008f00"), new StartDurn(13 * HR, 3 * HR)),
            new BarInfo("id7", "Rocker Outbound", "Echo", new RGB("#00008f"), new StartDurn(16 * HR, 4 * HR)),
            new BarInfo("id8", "Rocker Outbound", "Foxtrot", new RGB("#00008f"), new StartDurn(16 * HR, 4 * HR))
    );
    private GanttChart chart;

    @Override
    protected BorderLayoutContainer createWidget() {
        chart = new GanttChart();
        chart.addFocusChangeHandler(this);
        return new BorderLayoutContainerBuilder()
                .northWidget(new HorizontalLayoutContainerBuilder()
                        .add(new TextButton("Config", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                final ChartInfo info = createData();
                                chart.configure(info);
                            }
                        }))
                        .add(new TextButton("Data", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                chart.replaceBars(BARS);
                            }
                        }))
                        .add(new TextButton("Focus", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                int ix = (int) (Math.random() * BARS.size());
                                chart.focusBar(BARS.get(ix).getId());
                            }
                        }))
                        .add(new TextButton("UnFocus", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                chart.unfocus();
                            }
                        }))
                        .horizontalLayoutContainer)
                .centerWidget(chart)
                .borderLayoutContainer;
    }

    private ChartInfo createData() {
        List<HasIdTitle> resources = newList();
        resources.add(new HasIdTitle.Impl("Alpha"));
        resources.add(new HasIdTitle.Impl("Bravo"));
        resources.add(new HasIdTitle.Impl("Charlie"));
        resources.add(new HasIdTitle.Impl("Delta"));
        resources.add(new HasIdTitle.Impl("Echo"));
        resources.add(new HasIdTitle.Impl("Foxtrot"));

        int count = resources.size();
        int ix = (int) (Math.random() * count);
        if (ix > 0) {
            List<HasIdTitle> cut = newListFrom(resources.subList(ix, count));
            cut.addAll(resources.subList(0, ix));
            resources = cut;
        }

        return new ChartInfo(new Date(), 24 * HR, resources);
    }

    @Override
    public void focusChanged(GanttBarFocusEvent evt) {
        GWT.log("Focus changed to " + evt.getBarIdOrNull());
    }
}

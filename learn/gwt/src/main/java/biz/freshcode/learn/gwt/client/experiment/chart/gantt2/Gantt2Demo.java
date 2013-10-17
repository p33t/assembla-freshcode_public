package biz.freshcode.learn.gwt.client.experiment.chart.gantt2;

import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt.reuse.StartDurn;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse.BarInfo;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse.ChartInfo;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse.GanttChart;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse.HasIdTitle;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.Date;
import java.util.List;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;

public class Gantt2Demo extends AbstractIsWidget<BorderLayoutContainer> {
    private static final int HR = 60;
    private GanttChart chart;

    @Override
    protected BorderLayoutContainer createWidget() {
        return new BorderLayoutContainerBuilder()
                .northWidget(new HorizontalLayoutContainerBuilder()
                        .add(new TextButton("Force Layout", new SelectEvent.SelectHandler() {
                            @Override
                            public void onSelect(SelectEvent event) {
                                asWidget().forceLayout();
                            }
                        }))
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
                                List<BarInfo> bars = newListFrom(
                                        new BarInfo("id1", "Maintenance", "Alpha", new RGB("#ff0000"), new StartDurn(6 * HR, 4 * HR)),
                                        new BarInfo("id2", "Minow Inbound", "Bravo", new RGB("#00ff00"), new StartDurn(8 * HR, 3 * HR)),
                                        new BarInfo("id3", "Whisky & Coke Outbound", "Bravo", new RGB("#0000ff"), new StartDurn(12 * HR, 3 * HR)),
                                        new BarInfo("id4", "Whisky & Coke Outbound", "Charlie", new RGB("#0000ff"), new StartDurn(12 * HR, 3 * HR)),
                                        new BarInfo("id5", "Maintenance", "Delta", new RGB("#8f0000"), new StartDurn(10 * HR, 4 * HR)),
                                        new BarInfo("id6", "Dreamy Inbound", "Echo", new RGB("#008f00"), new StartDurn(13 * HR, 3 * HR)),
                                        new BarInfo("id7", "Rocker Outbound", "Echo", new RGB("#00008f"), new StartDurn(16 * HR, 4 * HR)),
                                        new BarInfo("id8", "Rocker Outbound", "Foxtrot", new RGB("#00008f"), new StartDurn(16 * HR, 4 * HR))
                                );
                                chart.replaceBars(bars);
                            }
                        }))
                        .horizontalLayoutContainer)
                .centerWidget(chart = new GanttChart())
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
        return new ChartInfo("Today", new Date(), 24 * HR, resources);
    }
}

package biz.freshcode.learn.gwt.client.experiment.chart.gantt;

import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt.reuse.GanttBar;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt.reuse.GanttInfo;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt.reuse.StartDurn;
import biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.core.client.util.PrecisePoint;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartElem.Access.ACCESS;
import static biz.freshcode.learn.gwt.client.experiment.chart.reuse.ChartUtil.interpolate;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newMap;

public class GanttDemo extends AbstractIsWidget<BorderLayoutContainer> {
    private static final int HR = 60;

    @Override
    protected BorderLayoutContainer createWidget() {
        List<GanttBar> bars = newListFrom(
                new GanttBar("1", "Alpha", "Maintenance", "#ff0000", new StartDurn(6 * HR, 4 * HR)),
                new GanttBar("2", "Bravo", "Minow Inbound", "#00ff00", new StartDurn(8 * HR, 3 * HR)),
                new GanttBar("3", "Bravo", "Whisky & Coke Outbound", "#0000ff", new StartDurn(12 * HR, 3 * HR))
        );
        GanttInfo info = new GanttInfo(bars, new Date(), "Today");

        ListStore<ChartElem> store = new ListStore<ChartElem>(ACCESS.xKey());
        Map<String, List<PrecisePoint>> series = newMap();
        List<String> resourceOrder = newList();
        for (GanttBar bar: info.getBars()) {
            int ix = resourceOrder.indexOf(bar.getResource());
            if (ix < 0) {
                ix = resourceOrder.size();
                resourceOrder.add(bar.getResource());
            }
            StartDurn sd = bar.getStartDurn();
            series.put(bar.getId(), newListFrom(
                    new PrecisePoint(sd.getStart(), ix + 1),
                    new PrecisePoint(sd.getStart() + sd.getDurn(), ix + 1)
            ));
        }
        List<ChartElem> data = interpolate(series);
        GWT.log("Data: " + data);
        store.addAll(data);

        return new BorderLayoutContainerBuilder()
                .centerWidget(new HTML("<p>Hi</p>"))
                .borderLayoutContainer;
    }
}

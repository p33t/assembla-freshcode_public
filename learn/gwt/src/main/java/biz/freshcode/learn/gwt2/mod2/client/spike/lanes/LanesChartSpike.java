package biz.freshcode.learn.gwt2.mod2.client.spike.lanes;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.util.data.HasIdTitle;
import biz.freshcode.learn.gwt2.common.client.util.data.StartDurn;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import biz.freshcode.learn.gwt2.mod2.client.spike.lanes.reuse.BarFocusEvent;
import biz.freshcode.learn.gwt2.mod2.client.spike.lanes.reuse.BarInfo;
import biz.freshcode.learn.gwt2.mod2.client.spike.lanes.reuse.ChartInfo;
import biz.freshcode.learn.gwt2.mod2.client.spike.lanes.reuse.LanesChart;
import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import java.util.Date;
import java.util.List;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newListFrom;

public class LanesChartSpike extends Presenter<LanesChartSpike.View, LanesChartSpike.Proxy> implements BarFocusEvent.Handler {
    public static final String TOKEN = "lanesChartSpike";
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


    @Inject
    public LanesChartSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
        view.getChart().addFocusChangeHandler(this);
    }

    @Override
    public void focusChanged(BarFocusEvent evt) {
        GWT.log("Focus changed to " + evt.getBarIdOrNull());
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<LanesChartSpike> {
    }

    public static class View extends ViewImpl {
        private LanesChart chart = new LanesChart();

        @Inject
        public View() {
            initWidget(new BorderLayoutContainerBuilder()
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
                                    .add(new TextButton("Focus Bar", new SelectEvent.SelectHandler() {
                                        @Override
                                        public void onSelect(SelectEvent event) {
                                            int ix = (int) (Math.random() * BARS.size());
                                            chart.focusBar(BARS.get(ix).getId());
                                        }
                                    }))
                                    .add(new TextButton("UnFocus Bar", new SelectEvent.SelectHandler() {
                                        @Override
                                        public void onSelect(SelectEvent event) {
                                            chart.unfocus();
                                        }
                                    }))
                                    .add(new TextButton("Reorder", new SelectEvent.SelectHandler() {
                                        @Override
                                        public void onSelect(SelectEvent event) {
                                            chart.reorder(createResourceList());
                                        }
                                    }))
                                    .add(new TextButton("Focus Period", new SelectEvent.SelectHandler() {
                                        @Override
                                        public void onSelect(SelectEvent event) {
                                            chart.focusPeriod(new StartDurn(5 * HR, 90));
                                        }
                                    }))
                                    .add(new TextButton("Unfocus Period", new SelectEvent.SelectHandler() {
                                        @Override
                                        public void onSelect(SelectEvent event) {
                                            chart.unfocusPeriod();
                                        }
                                    }))
                                    .horizontalLayoutContainer)
                            .centerWidget(chart)
                            .borderLayoutContainer
            );
        }

        private ChartInfo createData() {
            List<HasIdTitle> resources = createResourceList();
            return new ChartInfo(new Date(), 24 * HR, resources);
        }

        private List<HasIdTitle> createResourceList() {
            List<HasIdTitle> resources = newList();
            resources.add(new HasIdTitle.Impl("Alpha"));
            resources.add(new HasIdTitle.Impl("Bravo"));
            resources.add(new HasIdTitle.Impl("Charlie"));
            resources.add(new HasIdTitle.Impl("Delta"));
            resources.add(new HasIdTitle.Impl("Echo"));
            resources.add(new HasIdTitle.Impl("Foxtrot"));

            // slightly random order
            int count = resources.size();
            int ix = (int) (Math.random() * count);
            if (ix > 0) {
                List<HasIdTitle> cut = newListFrom(resources.subList(ix, count));
                cut.addAll(resources.subList(0, ix));
                resources = cut;
            }
            return resources;
        }

        public LanesChart getChart() {
            return chart;
        }
    }
}

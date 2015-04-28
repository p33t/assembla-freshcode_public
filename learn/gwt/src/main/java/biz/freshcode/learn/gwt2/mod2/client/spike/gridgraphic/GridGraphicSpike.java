package biz.freshcode.learn.gwt2.mod2.client.spike.gridgraphic;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.draw.DrawComponentBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt2.common.client.util.ClientUtil;
import biz.freshcode.learn.gwt2.common.client.util.data.HasId;
import biz.freshcode.learn.gwt2.common.client.util.data.HasIdTitle;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import biz.freshcode.learn.gwt2.mod2.client.spike.gridgraphic.reuse.StretchDrawComponent;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.sencha.gxt.chart.client.chart.series.Primitives;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.List;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newListFrom;
import static biz.freshcode.learn.gwt2.common.client.util.data.HasId.Util.nextId;

/**
 * Trying to display gannt-style bars in a grid.
 */
public class GridGraphicSpike extends Presenter<GridGraphicSpike.View, GridGraphicSpike.Proxy> {
    public static final String TOKEN = "gridGraphicSpike";

    @Inject
    public GridGraphicSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends ProxyPlace<GridGraphicSpike> {
    }

    public static class View extends ViewImpl {
        @Inject
        public View() {
            initWidget(new HorizontalLayoutContainerBuilder()
                    .add(new TextButton("Scaling Test", new SelectEvent.SelectHandler() {
                        @Override
                        public void onSelect(SelectEvent event) {
                            scalingTest();
                        }
                    }))
                    .add(new TextButton("Grid", new SelectEvent.SelectHandler() {
                        @Override
                        public void onSelect(SelectEvent event) {
                            grid();
                        }
                    }))
                    .horizontalLayoutContainer);
        }

        private void grid() {
            Grid<GgRow> g = createGrid();
            dialog("Grid", g);
            g.getStore().addAll(rows());
        }

        @SuppressWarnings("unchecked")
        private Grid<GgRow> createGrid() {
            GgCell cell0 = new GgCell(0, 10);
            return ClientUtil.createGrid(HasId.ID_PROVIDER,
                    new ColumnConfigBuilder(HasIdTitle.TITLE_PROVIDER)
                            .width(1)
                            .header("Title")
                            .columnConfig,
                    new ColumnConfigBuilder(new IdentityValueProvider<GgRow>())
                            .cell(cell0)
                            .width(10)
                            .header(cell0.linesHeader())
//                            .columnHeaderClassName(Bundle.STYLE.noPad())
                            .cellPadding(false)
                            .columnConfig
            );
        }


        private void scalingTest() {
            dialog("Scaling Test", new DrawComponentBuilder(new StretchDrawComponent())
                    // NOTE: dimensions are expressed as ratio of container.
                    .addSprite(Primitives.triangle(.5, .5, .2))
                    .drawComponent);
        }

        private void dialog(String title, Component component) {
            new DialogBuilder()
                    .modal(true)
                    .title(title)
                    .width(600)
                    .height(400)
                    .add(component)
                    .dialog.show();
        }

        private List<GgRow> rows() {
            return newListFrom(
                    new GgRow(nextId(), "Bruce", newListFrom(
                            new GgElem(nextId(), "B1", 3, 5, "blue"),
                            new GgElem(nextId(), "B2", 5, 9, "green"),
                            new GgElem(nextId(), "B3", 8, 15, "orange"),
                            new GgElem(nextId(), "B4", 15, 20, "red")
                    )),
                    new GgRow(nextId(), "Lee", newListFrom(
                            new GgElem(nextId(), "B1", 2, 5, "pink"),
                            new GgElem(nextId(), "B2", 7, 9, "yellow")
                    )),
                    new GgRow(nextId(), "Bruce", newListFrom(
                            new GgElem(nextId(), "B1", 3, 5, "blue"),
                            new GgElem(nextId(), "B2", 5, 9, "green"),
                            new GgElem(nextId(), "B3", 8, 15, "orange"),
                            new GgElem(nextId(), "B4", 15, 20, "red")
                    )),
                    new GgRow(nextId(), "Lee", newListFrom(
                            new GgElem(nextId(), "B1", 2, 5, "pink"),
                            new GgElem(nextId(), "B2", 7, 9, "yellow")
                    ))
            );
        }

        static class Info {
            private String text;
            private int width;
            private String colour;
        }

    }

}

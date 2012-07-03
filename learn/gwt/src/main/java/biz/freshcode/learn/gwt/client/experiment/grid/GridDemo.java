package biz.freshcode.learn.gwt.client.experiment.grid;

import biz.freshcode.learn.gwt.client.uispike.builder.table.ColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ToStringValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.List;
import java.util.Set;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newSetFrom;

/**
 * It seems we cannot achieve widget effects for a cell like drag-drop and mouse-over buttons.
 * Tapping into Dom native events is difficult and probably not that portable.
 */
public class GridDemo extends AbstractIsWidget {
    private ListStore<RowEntity> store = new ListStore<RowEntity>(new ModelKeyProvider<RowEntity>() {
        @Override
        public String getKey(RowEntity item) {
            return "" + item.id;
        }
    });

    @Override
    protected Widget createWidget() {
        List<ColumnConfig> configs = newListFrom(
                new ColumnConfigBuilder(new ColumnConfig(new ToStringValueProvider<RowEntity>()))
                        .header("To String")
                        .columnTextStyle(SafeStylesUtils.fromTrustedString("color:blue; text-align:center;"))
                        .cell(new AbstractCell<String>() {
                            @Override
                            public void render(Context context, String value, SafeHtmlBuilder sb) {
                                // Div causes events to echo
//                                sb.appendHtmlConstant("<div style='color:blue; text-align:center;'>");
                                sb.appendEscaped(value);
//                                sb.appendHtmlConstant("</div>");
                            }

                            @Override
                            public Set<String> getConsumedEvents() {
                                return newSetFrom(
                                        MouseOutEvent.getType().getName(),
                                        MouseOverEvent.getType().getName()

                                        // Not received... might need more plumbing
//                                        DragStartEvent.getType().getName(),
//                                        DragOverEvent.getType().getName(),
//                                        DragEndEvent.getType().getName()
                                );
                            }

                            @Override
                            public void onBrowserEvent(final Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> stringValueUpdater) {
                                String msg = event.getType() + " on " + value;
                                GWT.log(msg);

                                if (event.getType().equals(MouseOverEvent.getType().getName())) {

//                                    Causes: AssertionError: A widget that has an existing parent widget may not be added to the detach list
//                                    HTML html = HTML.wrap(parent);
//                                    new DragSource(html) {
//                                        @Override
//                                        protected void onDragStart(DndDragStartEvent event) {
//                                            GWT.log("Drag started for " + context.getKey() + " col " + context.getColumn());
//                                            super.onDragStart(event);
//                                        }
//                                    };

//                                    Don't know how to make panel appear...
//                                    AbsolutePanel pnl = new AbsolutePanelBuilder()
//                                            .add(new ToolButtonBuilder(new ToolButton(ToolButton.SEARCH,
//                                                    new SelectEvent.SelectHandler() {
//                                                        @Override
//                                                        public void onSelect(SelectEvent event) {
//                                                            Info.display("Event", "Go");
//                                                        }
//                                                    }))
//                                                    .toolButton, 0, 0)
////                                            .size("100%", "100%")
//                                            .pixelSize(parent.getClientWidth(), parent.getClientHeight())
//                                            .absolutePanel;
                                }
                            }
                        })
                        .columnConfig,
                new ColumnConfigBuilder(new ColumnConfig(
                        new ValueProvider<RowEntity, Integer>() {
                            @Override
                            public Integer getValue(RowEntity object) {
                                return object.id;
                            }

                            @Override
                            public void setValue(RowEntity object, Integer value) {
                                GWT.log("Ignoring 'setValue' on custom value provider.");
                            }

                            @Override
                            public String getPath() {
                                return null;
                            }
                        }))
                        .header("Dots")
                        .cell(new AbstractCell<Integer>() {
                            @Override
                            public void render(Context context, Integer value, SafeHtmlBuilder sb) {
                                for (int i = 0; i < value; i++) {
                                    sb.appendEscaped(".");
                                }
                            }
                        })
                        .columnConfig,
                // Fancy widget in column header
                new ColumnConfigBuilder(new ColumnConfig(new ToStringValueProvider()))
                        .header("Widget")
                        .widget(new HTMLPanel("<p style='color:purple;'>WidgetX</p>"), SafeHtmlUtils.fromString("WidgetXX"))
                        .columnConfig
        );
        ColumnModel colModel = new ColumnModel(configs);
        for (int i = 0; i < 16; i++) store.add(new RowEntity());
        return new Grid(store, colModel);
    }

    static class RowEntity {
        static int counter = 1;
        final int id = counter++;

        @Override
        public String toString() {
            return "#" + id;
        }
    }
}

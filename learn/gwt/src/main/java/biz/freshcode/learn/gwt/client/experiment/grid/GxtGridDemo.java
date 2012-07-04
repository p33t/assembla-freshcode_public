package biz.freshcode.learn.gwt.client.experiment.grid;

import biz.freshcode.learn.gwt.client.experiment.mouseover.MouseOverState;
import biz.freshcode.learn.gwt.client.uispike.builder.GridBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.container.PopupPanelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.table.ColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ToStringValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.List;
import java.util.Set;

import static biz.freshcode.learn.gwt.client.experiment.grid.Bundle2.STYLE;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newSetFrom;

/**
 * It seems we cannot achieve widget effects for a cell like drag-drop and mouse-over buttons.
 * Tapping into Dom native events is difficult and probably not that portable.
 */
public class GxtGridDemo extends AbstractIsWidget {
    private static final SelectEvent.SelectHandler GO_HANDLER = new SelectEvent.SelectHandler() {
        public void onSelect(SelectEvent event) {
            Info.display("Event", "Go pushed");
        }
    };

    final PopupPanel popup = new PopupPanelBuilder()
            .widget(new ToolButton(ToolButton.SEARCH, GO_HANDLER))
            .addStyleName(STYLE.hoverWidgets())
            .popupPanel;

    MouseOverState mosPopup = new MouseOverState(popup, new MouseOverState.Callback() {
        @Override
        public void stateChange(MouseOverState mos) {
            GWT.log("Mouse is" + (mos.isOver() ? "" : " NOT") + " over");
            checkPopup();
        }
    });

    int[] popupCoord = null;

    private void showPopup(int left, int top) {
        if (popup.getPopupLeft() == left && popup.getPopupTop() == top) {
            if (!popup.isShowing()) popup.show();
        }
        else {
            if (popup.isShowing()) popup.hide();
            popup.setPopupPosition(left, top);
            popup.show();
        }
    }

    private void hidePopup() {
        if (popup.isShowing()) popup.hide();
    }

    private void checkPopup() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                if (!popupEnabled() && !mosPopup.isOver()) hidePopup();
                if (popupEnabled()) showPopup(popupLeft(), popupTop());
            }
        });
    }

    private int popupTop() {
        return popupCoord[1];
    }

    private int popupLeft() {
        return popupCoord[0];
    }

    void enablePopup(int left, int top) {
        popupCoord = new int[] {left, top};
    }

    void disablePopup() {
        popupCoord = null;
    }

    private boolean popupEnabled() {
        return popupCoord != null;
    }

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

                                if (isType(event, MouseOutEvent.getType())) {
                                    // hide popup if necessary
                                    disablePopup();
                                    checkPopup();
                                } else if (isType(event, MouseOverEvent.getType())) {
                                    // show the popup
                                    enablePopup(parent.getAbsoluteLeft(), parent.getAbsoluteTop());
                                    checkPopup();

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
        for (int i = 0; i < 24; i++) store.add(new RowEntity());
        return new GridBuilder(new Grid(store, colModel))
                .selectionModel(null) // no select
                .grid;
    }

    private boolean isType(NativeEvent event, DomEvent.Type type) {
        return event.getType().equals(type.getName());
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

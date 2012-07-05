package biz.freshcode.learn.gwt.client.experiment.grid;

import biz.freshcode.learn.gwt.client.experiment.dnd.DropSupport;
import biz.freshcode.learn.gwt.client.experiment.dnd.dragdata.DragData;
import biz.freshcode.learn.gwt.client.experiment.mouseover.MouseOverState;
import biz.freshcode.learn.gwt.client.uispike.builder.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.container.PopupPanelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.table.ColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ToStringValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DragSource;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

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
    private static final String NOT_RELEVANT = "Not a relevant cell";
    private static final ValueProvider DOTS_PROVIDER = new ValueProvider<RowEntity, String>() {
        @Override
        public String getValue(RowEntity object) {
            String s = "";
            for (int i = 0; i < object.id; i++) s += ".";
            return s;
        }

        @Override
        public void setValue(RowEntity object, String value) {
            GWT.log("Ignoring 'setValue' on custom value provider.");
        }

        @Override
        public String getPath() {
            return null;
        }
    };

    final Image dragImg;
    final PopupPanel popup = new PopupPanelBuilder()
            .widget(new HorizontalLayoutContainerBuilder()
                    .add(new ToolButton(ToolButton.SEARCH, GO_HANDLER))
                    .add(dragImg = new Image(Bundle2.INSTANCE.drag()))
                    .horizontalLayoutContainer)
            .addStyleName(STYLE.hoverWidgets())
            .popupPanel;

    MouseOverState mosPopup = new MouseOverState(popup, new MouseOverState.Callback() {
        @Override
        public void stateChange(MouseOverState mos) {
            checkPopup();
        }
    });

    int[] popupCoord = null;
    Cell.Context lastMouseOverCell = null;
    Cell.Context popupCell = null;
    private MouseOverState mosGrid;

    private void hidePopupIfNecessary() {
        if (popup.isShowing()) popup.hide();
    }

    private void checkPopup() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
//  Seems solid...    GWT.log("Mouse is" + (mosPopup.isOver() ? "" : " NOT") + " over the popup.");
                if (mosPopup.isOver()) {
                    // nothing
                } else if (popupEnabled()) {
                    int left = popupLeft();
                    int top = popupTop();
                    if (popup.getPopupLeft() == left && popup.getPopupTop() == top) {
                        // location is accurate
                        if (!popup.isShowing()) popup.show();
                    } else {
                        // different location
                        hidePopupIfNecessary();
                        popup.setPopupPosition(left, top);
                        popup.show();
                    }
                } else {
                    hidePopupIfNecessary();
                    popupCell = null;
                }
            }
        });
    }

    private int popupTop() {
        return popupCoord[1];
    }

    private int popupLeft() {
        return popupCoord[0];
    }

    void enablePopup(int left, int top, Cell.Context cell) {
        popupCoord = new int[]{left, top};
        popupCell = cell;
    }

    void disablePopup() {
        popupCoord = null;
        // NOTE: Don't clear popupCell here.  It is still needed.
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

    {
        // initializer
        for (int i = 0; i < 24; i++) store.add(new RowEntity());
    }

    private Cell.Context getCurrentCell() {
        if (lastMouseOverCell != null) return lastMouseOverCell;
        return popupCell;
    }

    @Override
    protected Widget createWidget() {
        final AbstractCell<String> mega = new AbstractCell<String>() {
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
            public void onBrowserEvent(final Context context, final Element parent, String value, NativeEvent event, ValueUpdater<String> stringValueUpdater) {
                GWT.log(event.getType() + " on " + value);

                if (isType(event, MouseOutEvent.getType())) {
                    // manage 'currentCell'
                    if (isCurrentCell(context)) {
                        // exiting current cell
                        lastMouseOverCell = null;
//                        GWT.log("lastMouseOverCell cleared.");
                    }

                    // hide popup if necessary
                    disablePopup();
                    checkPopup();

                } else if (isType(event, MouseOverEvent.getType())) {
                    // track current cell
                    lastMouseOverCell = context;

                    // show the popup
                    if (!mosGrid.isDraggingOver()) {
                        enablePopup(parent.getAbsoluteLeft(), parent.getAbsoluteTop(), context);
                        checkPopup();
                    }

//                    Causes: AssertionError: A widget that has an existing parent widget may not be added to the detach list
//                    HTML html = HTML.wrap(parent);
                }
            }
        };

        Grid grid = new Grid(store, new ColumnModel(newListFrom(
                new ColumnConfigBuilder(new ColumnConfig(new ToStringValueProvider<RowEntity>()))
                        .header("To String")
                        .columnTextStyle(SafeStylesUtils.fromTrustedString("color:blue; text-align:center;"))
                        .cell(mega)
                        .columnConfig,
                new ColumnConfigBuilder(new ColumnConfig(DOTS_PROVIDER))
                        .header("Dots")
                        .columnConfig,
                // Fancy widget in column header
                new ColumnConfigBuilder(new ColumnConfig(new ToStringValueProvider()))
                        .header("Widget")
                        .widget(new HTMLPanel("<p style='color:purple;'>WidgetX</p>"), SafeHtmlUtils.fromString("WidgetXX"))
                        .columnConfig
        )));
        grid.setSelectionModel(null); // no select

        // DROP ===========================================
        final DropSupport dropper = new DropSupport(grid) {
            {
                // initializer
                setAllowSelfAsSource(true); // to facilitate cell to cell
            }

            @Override
            protected DropAssessment dropQuery(DragData dd) {
                Cell.Context cc = getCurrentCell();
                if (cc == null) return new DropAssessment(NOT_RELEVANT);
                Set<Cell.Context> payload = dd.getPayload(Cell.Context.class);
                if (payload.isEmpty()) return DropAssessment.NOT_HANDLED; // no cell context in payload
                Cell.Context origin = payload.iterator().next();
                if (isSameContext(cc, origin)) {
                    // same cell
                    return new DropAssessment("Cannot drop on same cell " + cc.getIndex());
                } else {
                    // a different cell
                    final String msg = "Transfer from " + origin.getKey() + " to " + cc.getKey();
                    return new DropAssessment(msg, new CellTransfer(origin, cc));
                }
            }

            @Override
            protected boolean hasExpired(DropAssessment assessment) {
                if (assessment.isDroppable()) {
                    if (assessment.getRunnable() instanceof CellTransfer) {
                        CellTransfer ct = (CellTransfer) assessment.getRunnable();
                        return !isCurrentCell(ct.target);
                    } else {
                        // safe fallback
                        return true;
                    }
                } else {
                    // TODO: Cannot reproduce reasons why assessment failed.
                    // assume we're hovering over another cell (?!)
                    return true; // expensive
                }
            }
        };

        mosGrid = new MouseOverState(dropper, new MouseOverState.Callback() {
            @Override
            public void stateChange(MouseOverState mos) {
                if (mos.isDraggingOver()) disablePopup();
                checkPopup();
            }
        });

        // DRAG ===========================================
        new DragSource(dragImg).addDragStartHandler(new DndDragStartEvent.DndDragStartHandler() {
            @Override
            public void onDragStart(DndDragStartEvent event) {
                // Note that hover widgets are not shown when dragging.
                Cell.Context origin = getCurrentCell();
                if (origin == null) {
                    GWT.log("Nothing to drag.");
                    return;
                }
                DragData.setup(event, Cell.Context.class, newSetFrom(origin));
            }
        });
        return grid;
    }

    private boolean isCurrentCell(Cell.Context cell) {
        return isSameContext(cell, getCurrentCell());
    }

    private class CellTransfer implements Runnable {
        final Cell.Context origin;
        final Cell.Context target;

        CellTransfer(Cell.Context origin, Cell.Context target) {
            this.origin = origin;
            this.target = target;
        }

        @Override
        public void run() {
            Info.display("Transfer", "From " + origin.getKey() + " to " + target.getKey());
        }
    }

    private boolean isSameContext(Cell.Context c1, Cell.Context c2) {
        if (c1 == null) return false;
        if (c2 == null) return false;
        return c2.getColumn() == c1.getColumn() &&
                c2.getIndex() == c1.getIndex();
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

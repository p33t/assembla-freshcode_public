package biz.freshcode.learn.gwt.client.experiment.grid;

import biz.freshcode.learn.gwt.client.builder.gxt.PopupBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.button.ToolButtonBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.menu.MenuBuilder;
import biz.freshcode.learn.gwt.client.experiment.dnd.DropAssessment;
import biz.freshcode.learn.gwt.client.experiment.dnd.DropSupport;
import biz.freshcode.learn.gwt.client.experiment.dnd.dragdata.DragData;
import biz.freshcode.learn.gwt.client.experiment.grid.reuse.CellDropRejected;
import biz.freshcode.learn.gwt.client.experiment.grid.reuse.CellTransfer;
import biz.freshcode.learn.gwt.client.experiment.grid.reuse.HasTarget;
import biz.freshcode.learn.gwt.client.experiment.grid.reuse.PopOverCell;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.DummySelectHandler;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.ToStringValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DragSource;
import com.sencha.gxt.widget.core.client.Popup;
import com.sencha.gxt.widget.core.client.button.IconButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CellClickEvent;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.grid.CellSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.tips.QuickTip;

import java.util.Set;

import static biz.freshcode.learn.gwt.client.experiment.dnd.DropAssessment.NOT_HANDLED;
import static biz.freshcode.learn.gwt.client.experiment.grid.Bundle2.STYLE;
import static biz.freshcode.learn.gwt.client.experiment.grid.RowEntity.Flag.GREEN;
import static biz.freshcode.learn.gwt.client.experiment.grid.RowEntity.Flag.RED;
import static biz.freshcode.learn.gwt.client.experiment.grid.reuse.MouseOverCell.isSameContext;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.*;

/**
 * Hover widgets, background images for cells, cell-to-cell drag-drop.
 */
public class GxtGridDemo extends AbstractIsWidget {
    private static final int STR_COL = 3;
    private MegaPopOverCell megaCell;
    private ListStore<RowEntity> store = new ListStore<RowEntity>(new RowEntity.IdProvider());
    private HtmlLayoutContainer hlc;
    private Popup popup = new PopupBuilder()
            .borders(true)
            .add(hlc = new HtmlLayoutContainer("<p>x</p>"))
            .popup;
    private Grid<RowEntity> grid;

    public GxtGridDemo() {
        for (int i = 0; i < 24; i++) store.add(new RowEntity());
    }

    @Override
    protected Widget createWidget() {
        ColumnConfig megaCol;
        ColumnConfig strCol;
        //noinspection unchecked
        grid = new Grid(store, new ColumnModel(newListFrom(
                new ColumnConfigBuilder(megaCol = new ColumnConfig(new ToStringValueProvider<RowEntity>()))
                        .header("To String")
                        .columnTextStyle(SafeStylesUtils.fromTrustedString("color:blue; text-align:center;"))
                        .columnConfig,
                new ColumnConfigBuilder(new ColumnConfig(new DotsProvider()))
                        .header("Dots")
                        .columnConfig,
                // Fancy widget in column header
                new ColumnConfigBuilder(new ColumnConfig(new ToStringValueProvider()))
                        .header("Widget")
                        .widget(new HTMLPanel("<p style='color:purple;'>WidgetX<br/>Two Lines</p>"), SafeHtmlUtils.fromString("WidgetXX"))
                        .columnConfig,
                new ColumnConfigBuilder(strCol = new ColumnConfig(new RowEntity.StrProvider()))
                        .header("Str")
                        .columnConfig
        )));
        grid.setSelectionModel(new CellSelectionModel());
        grid.addCellClickHandler(new CellClickEvent.CellClickHandler() {
            @Override
            public void onCellClick(CellClickEvent evt) {
                int col = evt.getCellIndex();
                if (col != STR_COL) cellPopup(evt.getRowIndex(), col);
            }
        });

        // register qtip effects
        new QuickTip(grid);

        // Set up cell last otherwise a chicken and egg problem
        megaCol.setCell(megaCell = new MegaPopOverCell(grid));

        setupEditing(strCol);

        grid.getView().setColumnLines(true); // Fixed in 3.0.0b
        return grid;
    }

    private void setupEditing(ColumnConfig strCol) {
        SimpleComboBox<String> combo = new SimpleComboBox<String>(new StringLabelProvider<String>());
        combo.add("One");
        combo.add("Two");
        combo.add("Three");
        combo.setTriggerAction(ComboBoxCell.TriggerAction.ALL); // not sure why but stupid behaviour if not set
        combo.setForceSelection(true); // useless
        GridInlineEditing<RowEntity> editing = new GridInlineEditing<RowEntity>(grid);
        // Note: String-String converter does nothing to help free-form string entry.

        // NOTE: Highlight hover issue only happens when field has text in it
        editing.addEditor(strCol, combo);

        editing.addCompleteEditHandler(new CompleteEditEvent.CompleteEditHandler<RowEntity>() {
            @Override
            public void onCompleteEdit(CompleteEditEvent<RowEntity> evt) {
                ListStore<RowEntity> store = grid.getStore();
                int rowIx = evt.getEditCell().getRow();
                String before = store.get(rowIx).str;
                store.commitChanges(); // alternatively use store.setAutoCommit(true)
                RowEntity after = store.get(rowIx);
                Info.display("Result", "Str changed from " + before + " to " + after.str);
            }
        });
    }

    private void cellPopup(int row, int col) {
        Element cell0 = grid.getView().getCell(row, col);
        int left = cell0.getAbsoluteLeft();
        int top = cell0.getAbsoluteTop();

        hlc.setHTML(SafeHtmlUtils.fromTrustedString("<p style='background: yellow;'>Row: " + row + "<br/> Cell:" + col + "<p>"));
        if (popup.isVisible()) {
            if (left == popup.getAbsoluteLeft() && top == popup.getAbsoluteTop()) {
                // visible but in correct place
                // do nothing
            } else {
                popup.hide();
                popup.showAt(left, top);
            }
        } else {
            popup.showAt(left, top);
        }
    }

    private ToolButton toggleButton(String icon, RowEntity.Flag flag) {
        return new ToolButtonBuilder(new ToolButton(
                new IconButton.IconConfig(icon), new ToggleFlag(flag)))
                .addStyleName(STYLE.centerBgnd())
                .toolButton;
    }

    class ToggleFlag implements SelectEvent.SelectHandler {
        final RowEntity.Flag key;

        public ToggleFlag(RowEntity.Flag key) {
            this.key = key;
        }

        @Override
        public void onSelect(SelectEvent event) {
            Cell.Context cc = megaCell.getCurrentCell();
            if (cc == null) return;
            RowEntity rowEntity = store.get(cc.getIndex());
            rowEntity.flags ^= key.mask; // flip the bit
            store.update(rowEntity);
        }
    }

    private class MegaPopOverCell extends PopOverCell<String, HorizontalLayoutContainer> {
        Dropper dropper;

        public MegaPopOverCell(Grid grid) {
            // TODO: Factor out a 'HoverWidget' parent which is a HorizLayCont, manages a 'getCurrentIfAny()' method and has opt-in 'More' menu.
            this(new Dropper(grid), new HorizontalLayoutContainer());
        }

        private MegaPopOverCell(Dropper dropper, HorizontalLayoutContainer hoverWidget) {
            super(dropper, hoverWidget);
            this.dropper = dropper;

            // HOVER TOOL BAR ==============================================================
            Widget dragImg;
            new HorizontalLayoutContainerBuilder(hoverWidget)
                    .add(new ToolButton(ToolButton.SEARCH, new DummySelectHandler("Go pushed")))
                    .add(toggleButton(STYLE.redOnlyBnd(), RED))
                    .add(toggleButton(STYLE.greenOnlyBgnd(), GREEN))
                    .add(dragImg = new Image(Bundle2.INSTANCE.drag()))
                    .add(new ToolButton(ToolButton.MAXIMIZE, new SelectEvent.SelectHandler() {
                        @Override
                        public void onSelect(SelectEvent event) {
                            Widget w = (Widget) event.getSource();
                            Menu menu = createMenu();
                            menu.showAt(w.getAbsoluteLeft(), w.getAbsoluteTop() + w.getOffsetHeight());
                        }
                    }));

            // DRAGGING ====================================================================
            new DragSource(dragImg).addDragStartHandler(new DndDragStartEvent.DndDragStartHandler() {
                @Override
                public void onDragStart(DndDragStartEvent event) {
                    // Note that hover widgets are not shown when dragging.
                    Cell.Context origin = getCurrentCell();
                    if (origin == null) GWT.log("Nothing to drag.");
                    else DragData.setup(event, Cell.Context.class, newSetFrom(origin));
                }
            });
        }

        @Override
        public void render(Context context, String value, SafeHtmlBuilder sb) {
            // Div causes events to echo...sb.appendHtmlConstant("<div style='color:blue; text-align:center;'>");
            RowEntity rowEntity = store.get(context.getIndex());
            String cls;
            // NOTE: IE Does not like multiple background urls OR does not do transparent layers properly.
            //       Green shows only if red is not selected.
            boolean isRed = RED.isSet(rowEntity.flags);
            boolean isGreen = GREEN.isSet(rowEntity.flags);
            if (isRed) {
                if (isGreen) cls = STYLE.redGreenBgnd();
                else cls = STYLE.redOnlyBnd();
            } else {
                if (isGreen) cls = STYLE.greenOnlyBgnd();
                else cls = "";
            }
            if (!cls.isEmpty()) cls = " class='" + cls + "'";

            // TODO: Rework this to use natural tooltip and MouseOverCell... qtip keeps getting hidden inappropriately
            //       The trick is to not remove the existing tooltip once it is set... just 'disable' it.

            // Reference for tooltips: http://ui-programming.blogspot.com.au/2010/01/gxt-how-to-set-tooltip-for-grid-cell.html
            // NOTE: Need to register the widget first with 'new QuickTip(w)'
            String qtip = "";
            if (!isEven(context)) qtip = " qtip='" + context.getColumn() + "," + context.getIndex() + "'";
            sb.appendHtmlConstant("<p" + cls + qtip + ">");
            sb.appendEscaped(value);
            sb.appendHtmlConstant("</p>");
        }

        @Override
        protected void customizeHoverWidget(HorizontalLayoutContainer hoverWidget, Context cell) {
            // demo of custom hover widget.  Enabled only for every second row.
            if (isEven(cell)) hoverWidget.enable();
            else hoverWidget.disable();
        }

        private boolean isEven(Context cell) {
            return cell.getIndex() % 2 == 0;
        }

        private Menu createMenu() {
            MenuItem mi;
            Menu m = new MenuBuilder()
                    .add(mi = new MenuItem("Item 1", Bundle2.INSTANCE.drag()))
                    .menu;
            mi.addSelectionHandler(new SelectionHandler<Item>() {
                @Override
                public void onSelection(SelectionEvent<Item> event) {
                    Info.display("Event", "Item 1 clicked");
                }
            });
            return m;
        }
    }

    private class Dropper extends DropSupport {
        public Dropper(Grid grid) {
            super(grid);
            setAllowSelfAsSource(true); // to facilitate cell to cell
        }

        @Override
        protected DropAssessment dropQuery(DragData dd) {
            Cell.Context cc = megaCell.getCurrentCell();
            if (cc == null) {
                //noinspection NullableProblems
                return cellDropRejected(null, "Not a relevant cell");
            }

            Set<Cell.Context> payload = dd.getPayload(Cell.Context.class);
            if (payload.isEmpty()) return NOT_HANDLED; // no cell context in payload

            Cell.Context origin = firstElem(payload);
            if (isSameContext(cc, origin)) return cellDropRejected(cc, "Cannot drop on same cell " + cc.getKey());

            return transferCellOp(cc, origin);
        }

        @Override
        protected boolean isStillAccurate(DropAssessment assessment) {
            Object desc = assessment.getDescription();
            if (desc instanceof HasTarget) {
                Cell.Context target = ((HasTarget) desc).target;
                return megaCell.isCurrentCell(target);
            }
            // force reassessment
            return false;
        }

        private DropAssessment transferCellOp(final Cell.Context cc, Cell.Context origin) {
            final String msg = "Transfer from " + origin.getKey() + " to " + cc.getKey();
            return new DropAssessment(msg, new CellTransfer(origin, cc) {
                @Override
                public void run() {
                    Info.display("Event", msg);
                }
            });
        }

        private DropAssessment cellDropRejected(Cell.Context targetOrNull, String msg) {
            CellDropRejected reason = new CellDropRejected(msg, targetOrNull);
            return new DropAssessment(reason);
        }
    }
}

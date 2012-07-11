package biz.freshcode.learn.gwt.client.experiment.grid;

import biz.freshcode.learn.gwt.client.experiment.dnd.DropAssessment;
import biz.freshcode.learn.gwt.client.experiment.dnd.DropSupport;
import biz.freshcode.learn.gwt.client.experiment.dnd.dragdata.DragData;
import biz.freshcode.learn.gwt.client.uispike.builder.ToolButtonBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.table.ColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.DummySelectHandler;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ToStringValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DragSource;
import com.sencha.gxt.widget.core.client.button.IconButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.Set;

import static biz.freshcode.learn.gwt.client.experiment.dnd.DropAssessment.NOT_HANDLED;
import static biz.freshcode.learn.gwt.client.experiment.grid.Bundle2.STYLE;
import static biz.freshcode.learn.gwt.client.experiment.grid.PopOverCell.isSameContext;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newSetFrom;

/**
 * Hover widgets, background images for cells, cell-to-cell drag-drop.
 */
public class GxtGridDemo extends AbstractIsWidget {
    private static final SelectEvent.SelectHandler GO_HANDLER = new DummySelectHandler("Go pushed");
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

    private ListStore<RowEntity> store = new ListStore<RowEntity>(new ModelKeyProvider<RowEntity>() {
        @Override
        public String getKey(RowEntity item) {
            return "" + item.id;
        }
    });
    private Image dragImg;
    private MegaPopOverCell megaCell;
    private Grid grid;
    private DropSupport dropper;

    public GxtGridDemo() {
        for (int i = 0; i < 24; i++) store.add(new RowEntity());
        dragImg = new Image(Bundle2.INSTANCE.drag());
    }

    @Override
    protected Widget createWidget() {
        ColumnConfig megaCol;
        grid = new Grid(store, new ColumnModel(newListFrom(
                new ColumnConfigBuilder(megaCol = new ColumnConfig(new ToStringValueProvider<RowEntity>()))
                        .header("To String")
                        .columnTextStyle(SafeStylesUtils.fromTrustedString("color:blue; text-align:center;"))
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
        // This must come after grid is initialized
        dropper = new Dropper();
        // MegaCell must construct after dropper and dragImg
        megaCol.setCell(megaCell = new MegaPopOverCell());
        new DragSource(dragImg).addDragStartHandler(new DndDragStartEvent.DndDragStartHandler() {
            @Override
            public void onDragStart(DndDragStartEvent event) {
                // Note that hover widgets are not shown when dragging.
                Cell.Context origin = megaCell.getCurrentCell();
                if (origin == null) GWT.log("Nothing to drag.");
                else DragData.setup(event, Cell.Context.class, newSetFrom(origin));
            }
        });
        return grid;
    }

    private ToolButton toggleButton(String icon, FlagEnum flag) {
        return new ToolButtonBuilder(new ToolButton(
                new IconButton.IconConfig(icon), new ToggleFlag(flag)))
                .addStyleName(STYLE.centerBgnd())
                .toolButton;
    }

    enum FlagEnum {
        RED(1), GREEN(2);

        final int mask;

        private FlagEnum(int mask) {
            this.mask = mask;
        }

        boolean isSet(int flags) {
            return (flags & mask) > 0;
        }
    }

    private class CellDropRejected extends TargetBased {
        final String reason;

        CellDropRejected(String reason, Cell.Context targetOrNull) {
            this.reason = reason;
            this.target = targetOrNull;
        }

        @Override
        public String toString() {
            return reason;
        }
    }

    static class TargetBased {
        Cell.Context target;
    }

    static class CellTransfer extends TargetBased implements Runnable {
        final Cell.Context origin;

        CellTransfer(Cell.Context origin, Cell.Context target) {
            this.origin = origin;
            this.target = target;
        }

        @Override
        public void run() {
            Info.display("Transfer", "From " + origin.getKey() + " to " + target.getKey());
        }
    }

    class ToggleFlag implements SelectEvent.SelectHandler {
        final FlagEnum key;

        public ToggleFlag(FlagEnum key) {
            this.key = key;
        }

        @Override
        public void onSelect(SelectEvent event) {
            Cell.Context cc = megaCell.getCurrentCell();
            if (cc == null) return;
            RowEntity rowEntity = store.get(cc.getIndex());
            rowEntity.flags ^= key.mask;
            store.update(rowEntity);
        }
    }

    static class RowEntity {
        static int counter = 1;
        final int id = counter++;
        int flags = 0;

        @Override
        public String toString() {
            return "#" + id;
        }
    }

    private class MegaPopOverCell extends PopOverCell<String> {
        public MegaPopOverCell() {
            super(dropper, new HorizontalLayoutContainerBuilder()
                    .add(new ToolButton(ToolButton.SEARCH, GO_HANDLER))
                    .add(toggleButton(STYLE.redOnlyBnd(), FlagEnum.RED))
                    .add(toggleButton(STYLE.greenOnlyBgnd(), FlagEnum.GREEN))
                    .add(dragImg)
                    .horizontalLayoutContainer);
        }

        @Override
        public void render(Context context, String value, SafeHtmlBuilder sb) {
            // Div causes events to echo...sb.appendHtmlConstant("<div style='color:blue; text-align:center;'>");
            RowEntity rowEntity = store.get(context.getIndex());
            String cls;
            boolean isRed = FlagEnum.RED.isSet(rowEntity.flags);
            boolean isGreen = FlagEnum.GREEN.isSet(rowEntity.flags);
            if (isRed) {
                if (isGreen) cls = STYLE.redGreenBgnd();
                else cls = STYLE.redOnlyBnd();
            } else {
                if (isGreen) cls = STYLE.greenOnlyBgnd();
                else cls = "";
            }
            if (!cls.isEmpty()) cls = " class='" + cls + "'";
            sb.appendHtmlConstant("<p" + cls + ">");
            sb.appendEscaped(value);
            sb.appendHtmlConstant("</p>");
        }
    }

    private class Dropper extends DropSupport {
        public Dropper() {
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
            Cell.Context origin = payload.iterator().next();
            if (isSameContext(cc, origin)) {
                return cellDropRejected(cc, "Cannot drop on same cell " + cc.getKey());
            } else {
                final String msg = "Transfer from " + origin.getKey() + " to " + cc.getKey();
                return new DropAssessment(msg, new CellTransfer(origin, cc));
            }
        }

        private DropAssessment cellDropRejected(Cell.Context targetOrNull, String msg) {
            CellDropRejected reason = new CellDropRejected(msg, targetOrNull);
            return new DropAssessment(reason);
        }

        @Override
        protected boolean isStillAccurate(DropAssessment assessment) {
            Object desc = assessment.getDescription();
            if (desc instanceof TargetBased) {
                Cell.Context target = ((TargetBased) desc).target;
                return megaCell.isCurrentCell(target);
            }
            // force reassessment
            return false;
        }
    }
}

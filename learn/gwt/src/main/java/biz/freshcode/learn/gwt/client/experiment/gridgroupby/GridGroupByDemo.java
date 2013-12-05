package biz.freshcode.learn.gwt.client.experiment.gridgroupby;

import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.grid.GroupSummaryViewBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.grid.SummaryColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.experiment.grid.reuse.PopOverCell;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.IdentityHashProvider;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.dnd.core.client.DropTarget;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.*;

import java.util.*;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;

public class GridGroupByDemo extends AbstractIsWidget<BorderLayoutContainer> {

    public static final AbstractCell<StringCell> CELL = new AbstractCell<StringCell>() {
        @Override
        public void render(Context context, StringCell value, SafeHtmlBuilder sb) {
            if (value != null) sb.append(value.getDisplay());
        }
    };

    @Override
    protected BorderLayoutContainer createWidget() {
        ListStore<StringRow> store = new ListStore<StringRow>(new IdentityHashProvider<StringRow>());
        int ixA, ixB;

        int cols = 0;
        List<ColumnConfig<StringRow, ?>> configs = Arrays.<ColumnConfig<StringRow, ?>>asList(
                column(ixA = cols++)
                        .header("A")
                        .summaryColumnConfig,
                column(ixB = cols++)
                        .header("B")
                        .summaryColumnConfig,
                new SummaryColumnConfigBuilder<StringRow, StringCell>(new ValueProviderAdapter(cols++))
                        .header("C")
                        .summaryType(new SummaryType.CountSummaryType<StringCell>())
                        .summaryRenderer(new SummaryRenderer<StringRow>() {
                            @Override
                            public SafeHtml render(Number value, Map<ValueProvider<? super StringRow, ?>, Number> data) {
                                return SafeHtmlUtils.fromString(value + " rows...sumhooray!");
                            }
                        })
                        .summaryColumnConfig
        );

        store.addAll(newListFrom(stringRow("Alpha", "Bravo", "Delta"), stringRow("Charlie", "Bravo", "Echo")));

        // special hover widget cell
        Grid<StringRow> grid = new Grid<StringRow>(store, new ColumnModel<StringRow>(configs));
        DropTarget dtGrid = new DropTarget(grid);
        TestCell testCell = new TestCell(dtGrid);
        ColumnConfig<StringRow, StringCell> colA = (ColumnConfig<StringRow, StringCell>) configs.get(ixA);
        colA.setCell(testCell);

        GroupSummaryView<StringRow> v = new GroupSummaryViewBuilder<StringRow>()
                .showGroupedColumn(false)
                .forceFit(true)
                .stripeRows(true)
                .groupSummaryView;
        ColumnConfig<StringRow, StringCell> colB = (ColumnConfig<StringRow, StringCell>) configs.get(ixB);
//        colB.setCell(testCell); Doesn't work when grouped
        v.groupBy(colB);
        grid.setView(v);

        return new BorderLayoutContainerBuilder()
                .centerWidget(grid)
                .borderLayoutContainer;
    }

    private SummaryColumnConfigBuilder<StringRow, StringCell> column(int ixCol) {
        ValueProviderAdapter vp = new ValueProviderAdapter(ixCol);
        // looks like its all or nothing.... getting a class cast exception.
        return new SummaryColumnConfigBuilder<StringRow, StringCell>(vp)
                .comparator(new Comparator<StringCell>() {
                    @Override
                    public int compare(StringCell o1, StringCell o2) {
                        return o1.getStr().compareTo(o2.getStr());
                    }
                })
                .cell(CELL)
                ;
    }

    private StringRow stringRow(String... vals) {
        StringRow r = new StringRow();
        for (String v : vals) r.add(new StringCell(v));
        return r;
    }

    private static class ValueProviderAdapter implements ValueProvider<StringRow, StringCell> {
        private final int colIx;

        private ValueProviderAdapter(int colIx) {
            this.colIx = colIx;
        }

        @Override
        public StringCell getValue(StringRow r) {
            return r.get(colIx);
        }

        @Override
        public void setValue(StringRow r, StringCell value) {
            // nothing
        }

        @Override
        public String getPath() {
            return "" + colIx;
        }
    }

    private static class StringRow extends ArrayList<StringCell> {
    }

    private static class StringCell implements Comparable<StringCell> {
        private String str;
        private SafeHtml display;

        StringCell(String str) {
            this.str = str;
            display = SafeHtmlUtils.fromString(str);
        }

        private SafeHtml getDisplay() {
            return display;
        }

        private String getStr() {
            return str;
        }

        @Override
        public String toString() {
            // NOTE: THis really shouldn't be necessary.  The cell from the column should be used.
            return str;
        }

        @Override
        public int hashCode() {
            return str.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            //noinspection SimplifiableIfStatement
            // NOTE: This really shouldn't be necessary.  The comparator for the column should be used.
            if (obj instanceof StringCell) return compareTo((StringCell) obj) == 0;
            return false;
        }

        @Override
        public int compareTo(StringCell o) {
            // NOTE: This really shouldn't be necessary.  The comparator for the column should be used.
            return str.compareTo(o.str);
        }
    }

    private static class TestCell extends PopOverCell<StringCell, ToolButton> {

        public TestCell(DropTarget dtGrid) {
            super(dtGrid, new ToolButton(ToolButton.GEAR));
        }

        @Override
        public void render(Context context, StringCell value, SafeHtmlBuilder sb) {
            if (value != null) sb.append(value.getDisplay());
        }
    }
}

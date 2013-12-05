package biz.freshcode.learn.gwt.client.experiment.gridgroupby;

import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.grid.GroupSummaryViewBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.grid.SummaryColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.experiment.grid.reuse.PopOverCell;
import biz.freshcode.learn.gwt.client.experiment.gridgroupby.reuse.ElementHover;
import biz.freshcode.learn.gwt.client.experiment.hoverwidget.reuse.HoverWidgetSupport;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.IdentityHashProvider;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.dnd.core.client.DropTarget;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.*;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.*;

import static biz.freshcode.learn.gwt.client.experiment.gridgroupby.GridGroupByDemo.GroupComponent.SUMMARY;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;

public class GridGroupByDemo extends AbstractIsWidget<BorderLayoutContainer> {
    private static final String BRAVO = "Bravo (Hover enabled)";

    public static final AbstractCell<StringCell> CELL = new AbstractCell<StringCell>() {
        @Override
        public void render(Context context, StringCell value, SafeHtmlBuilder sb) {
            if (value != null) sb.append(value.getDisplay());
        }
    };
    private String idPrefix = System.identityHashCode(this) + "-";

    @Override
    protected BorderLayoutContainer createWidget() {
        ListStore<StringRow> store = new ListStore<StringRow>(new IdentityHashProvider<StringRow>());

        int cols = 0;
        final int ixA, ixB;
        List<ColumnConfig<StringRow, ?>> configs = Arrays.<ColumnConfig<StringRow, ?>>asList(
                column(ixA = cols++)
                        .header("A (hover enabled)")
                        .summaryColumnConfig,
                column(ixB = cols++)
                        .header("B")
                        .summaryColumnConfig,
                column(cols++)
                        .header("C")
                        .summaryColumnConfig
        );

        store.addAll(newListFrom(stringRow("Alpha", BRAVO, "Delta"), stringRow("Charlie", BRAVO, "Echo")));

        // special hover widget cell
        Grid<StringRow> grid = new Grid<StringRow>(store, new ColumnModel<StringRow>(configs));
        DropTarget dtGrid = new DropTarget(grid);
        TestCell testCell = new TestCell(dtGrid);
        ColumnConfig<StringRow, StringCell> colA = (ColumnConfig<StringRow, StringCell>) configs.get(ixA);
        colA.setCell(testCell);

        // Grouping by column named 'B' (ixB)
        GroupSummaryView<StringRow> v = new GroupSummaryViewBuilder<StringRow>(new MyGridView(grid))
                .showGroupedColumn(false)
                .forceFit(true)
                .stripeRows(true)
//                .viewConfig() Enables stylesheet customisation to cells and rows
                .groupSummaryView;
        ColumnConfig<StringRow, StringCell> colB = (ColumnConfig<StringRow, StringCell>) configs.get(ixB);
//        colB.setCell(testCell); Doesn't work when grouped
        v.groupBy(colB);
        grid.setView(v);

        // tapping mouse events for a summary cell
        grid.addDomHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                mouseEvent(event, GridGroupByDemo.this.hoverId(ixA, SUMMARY));
            }
        }, MouseOverEvent.getType());
        grid.addDomHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                mouseEvent(event, GridGroupByDemo.this.hoverId(ixA, SUMMARY));
            }
        }, MouseOutEvent.getType());

        return new BorderLayoutContainerBuilder()
                .centerWidget(grid)
                .borderLayoutContainer;
    }

    private void mouseEvent(MouseEvent event, String desiredId) {
        if (event instanceof MouseOutEvent || event instanceof MouseOverEvent) {
            // NOTE: This means that we can hover a widget anywhere we have rendered SafeHtml.
            Element element = event.getNativeEvent().getEventTarget().cast();
            String id = element.getId();
            if (id != null && id.equals(desiredId)) {
                GWT.log(event.getClass().getName() + " " + desiredId + " at " + element.getAbsoluteLeft() + "," + element.getAbsoluteTop());
            }
        }
    }

    private String hoverId(int ixCol, GroupComponent gc) {
        return idPrefix + ixCol + "-" + gc;
    }

    private SummaryColumnConfigBuilder<StringRow, StringCell> column(final int ixCol) {
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
                .summaryType(new SummaryType.CountSummaryType<StringCell>())
                .summaryRenderer(new SummaryRenderer<StringRow>() {
                    @Override
                    public SafeHtml render(Number value, Map<ValueProvider<? super StringRow, ?>, Number> data) {
                        return new SafeHtmlBuilder()
                                .appendHtmlConstant("<p id='" + hoverId(ixCol, SUMMARY) + "'>")
                                .appendEscaped(value + " rows...sumhooray!  See log for hover info.")
                                .appendHtmlConstant("</p>")
                                .toSafeHtml();
                    }
                })
                ;
    }

    private StringRow stringRow(String... vals) {
        StringRow r = new StringRow();
        for (String v : vals) r.add(new StringCell(v));
        return r;
    }

    public static enum GroupComponent {
        HEADER, SUMMARY
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

        private SafeHtml getDisplay() {
            return display;
        }

        private String getStr() {
            return str;
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

    public class MyGridView extends GroupSummaryView<StringRow> implements ElementHover.Callback<StringCell> {
        private final ElementHover<StringCell> eh;
        //        private final MouseOverState mosHover;
        private StringCell hoverValueIfAny;
        private ToolButton hoverWidget = new ToolButton(ToolButton.REFRESH, new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                Info.display("Event", "Group Hover Click: " + hoverValueIfAny);
            }
        });
        private HoverWidgetSupport<ToolButton> hoverer = new HoverWidgetSupport<ToolButton>(hoverWidget);

        public MyGridView(Grid<StringRow> grid) {
            eh = new ElementHover<StringCell>(grid, this);
//            mosHover = new MouseOverState(hoverWidget, new MouseOverState.Callback() {
//                @Override
//                public void stateChange(MouseOverState mos) {
//                    checkHover();
//                }
//            });
        }

        @Override
        protected SafeHtml renderGroupHeader(GroupingData<StringRow> groupInfo) {
            StringCell groupVal = (StringCell) groupInfo.getValue();
            SafeHtml html = super.renderGroupHeader(groupInfo);
            return eh.wrapAndRegister(html, groupVal);
        }

        @Override
        public void stateChange(ElementHover source, Element domElem, StringCell token, boolean mouseIsOver) {
            if (mouseIsOver) {
                Point p = new Point(domElem.getAbsoluteRight() - 20, domElem.getAbsoluteTop());
                hoverer.enablePopup(p);
                hoverValueIfAny = token;
            } else {
                hoverer.disablePopup();
                // NOTE: Don't clear hoverValueIfAny... it might be needed
            }
        }
    }
}

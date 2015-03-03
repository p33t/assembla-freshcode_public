package biz.freshcode.learn.gwt2.mod2.client.spike.checkboxcellicon;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt2.common.client.util.ClientUtil;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.event.CellClickEvent;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.Arrays;

import static biz.freshcode.learn.gwt2.mod2.client.spike.checkboxcellicon.CheckBoxCellIconSpike.RowAccess.ROW_ACCESS;

/**
 * Illustrate how to change order of grid elements using drag/drop
 */
public class CheckBoxCellIconSpike extends Presenter<CheckBoxCellIconSpike.View, CheckBoxCellIconSpike.Proxy> {
    public static final String TOKEN = "checkBoxCellIcon";

    @Inject
    public CheckBoxCellIconSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
        view.getGrid().getStore().addAll(Arrays.asList(
                new Row("a", true),
                new Row("b", false),
                new Row("c", false)
        ));
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<CheckBoxCellIconSpike> {
    }

    public static class View extends ViewImpl {
        private final Grid<Row> grid;

        @Inject
        public View() {
            initWidget(grid = ClientUtil.createGrid(ROW_ACCESS.id(),
                    new ColumnConfigBuilder<>(ROW_ACCESS.value())
                            .header("Boolean")
                            .menuDisabled(true)
                            .sortable(false)
                            .cell(new AbstractCell<Boolean>() {
                                @Override
                                public void render(Context context, Boolean value, SafeHtmlBuilder sb) {
                                    sb.appendEscaped(value == Boolean.TRUE ? "X" : "O");
                                }
                            })
                            .cellClassName(Bundle.STYLE.centerAlign())
                            .columnConfig));
            grid.addCellClickHandler(new CellClickEvent.CellClickHandler() {
                @Override
                public void onCellClick(CellClickEvent event) {
                    Row row = grid.getStore().get(event.getRowIndex());
                    row.setValue(!row.getValue());
                    grid.getStore().update(row);
                }
            });
            grid.getStore().setAutoCommit(true);
        }

        public Grid<Row> getGrid() {
            return grid;
        }
    }

    public static class Row {
        private String id;
        private boolean value;

        public Row(String id, boolean value) {
            this.id = id;
            this.value = value;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean getValue() {
            return value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }
    }

    public interface RowAccess extends PropertyAccess<Row> {
        public static final RowAccess ROW_ACCESS = GWT.create(RowAccess.class);

        ModelKeyProvider<Row> id();

        ValueProvider<Row, Boolean> value();
    }
}

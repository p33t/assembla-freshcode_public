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
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ToStringValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.List;
import java.util.Set;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newSetFrom;

public class GridDemo extends AbstractIsWidget {
    private ListStore<RowEntity> store = new ListStore<RowEntity>(new ModelKeyProvider<RowEntity>() {
        @Override
        public String getKey(RowEntity item) {
            return item.id;
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
                                );
                            }

                            @Override
                            public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> stringValueUpdater) {
                                String msg = event.getType() + " on " + value;
                                GWT.log(msg);
                            }
                        })
                        .columnConfig
        );
        ColumnModel colModel = new ColumnModel(configs);
        store.add(new RowEntity());
        store.add(new RowEntity());
        return new Grid(store, colModel);
    }

    static class RowEntity {
        static int counter = 1;
        final String id = "#" + counter++;

        @Override
        public String toString() {
            return id;
        }
    }
}

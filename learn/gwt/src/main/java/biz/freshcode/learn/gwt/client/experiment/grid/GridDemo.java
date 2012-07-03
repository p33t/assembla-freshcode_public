package biz.freshcode.learn.gwt.client.experiment.grid;

import biz.freshcode.learn.gwt.client.uispike.builder.table.ColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ToStringValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.List;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;

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

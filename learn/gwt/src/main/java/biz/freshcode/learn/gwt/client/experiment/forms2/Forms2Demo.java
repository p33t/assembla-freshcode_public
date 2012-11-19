package biz.freshcode.learn.gwt.client.experiment.forms2;

import biz.freshcode.learn.gwt.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;

public class Forms2Demo extends AbstractIsWidget {

    ListStore<TwoBean> store = new ListStore<TwoBean>(TwoBean.ACCESS.id());

    @Override
    protected Widget createWidget() {
        store.setAutoCommit(true);
        store.add(new TwoBean());
        store.add(new TwoBean());

        ColumnConfig<TwoBean, String> editCol;

        RowNumberer<TwoBean> nums;
        Grid<TwoBean> grid = new Grid<TwoBean>(store, new ColumnModel<TwoBean>(newListFrom(
                nums = new RowNumberer<TwoBean>(new IdentityValueProvider<TwoBean>()),
                new ColumnConfigBuilder<TwoBean, String>(TwoBean.ACCESS.idValue())
                        .header("Id")
                        .columnConfig,
                editCol = new ColumnConfigBuilder<TwoBean, String>(TwoBean.ACCESS.revStr())
                        .header("Rev Str")
                        .columnConfig
                )));

        nums.initPlugin(grid); // needed according to doco

        // editing
        new GridInlineEditing<TwoBean>(grid).addEditor(editCol, new TextField());

        return grid;

//        return null;
    }
}

package biz.freshcode.learn.gwt.client.experiment.forms2;

import biz.freshcode.learn.gwt.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.grid.ColumnModelBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.AppCollectionUtil;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;

public class Forms2Demo extends AbstractIsWidget {

    ListStore<TwoBean> store = new ListStore<TwoBean>(TwoBean.ACCESS.id());
    
    @Override
    protected Widget createWidget() {
        store.setAutoCommit(true);
        store.add(new TwoBean());
        store.add(new TwoBean());

        ColumnConfig<TwoBean, String> c0;

        Grid<TwoBean> grid = new Grid<TwoBean>(store, new ColumnModel<TwoBean>(AppCollectionUtil.<ColumnConfig<TwoBean, ?>>newListFrom(
                c0 = new ColumnConfigBuilder<TwoBean, String>(TwoBean.ACCESS.revStr())
                        .header("Rev Str")
                        .columnConfig
        )));

        // editing
        new GridInlineEditing<TwoBean>(grid).addEditor(c0, new TextField());

        return grid;

//        return null;
    }
}

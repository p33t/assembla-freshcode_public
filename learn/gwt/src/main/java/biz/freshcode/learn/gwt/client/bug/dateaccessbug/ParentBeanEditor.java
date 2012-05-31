package biz.freshcode.learn.gwt.client.bug.dateaccessbug;

import biz.freshcode.learn.gwt.client.uispike.builder.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.FieldLabelBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;

import java.util.Date;

public class ParentBeanEditor extends AbstractIsWidget implements Editor<ParentBean> {
    ListStore<DateBean> dateStore = new ListStore<DateBean>(DateBeanAccess.INSTANCE.id());
    @SuppressWarnings({"UnusedDeclaration"})
    ListStoreEditor<DateBean> dates = new ListStoreEditor<DateBean>(dateStore);

    @Override
    protected Widget createWidget() {
        Grid<DateBean> grid;
        ColumnConfig<DateBean, Date> dateCol;
        FlowLayoutContainer w = new FlowLayoutContainerBuilder()
                .add(new FieldLabelBuilder()
                        .text("Dates")
                        .labelAlign(FormPanel.LabelAlign.TOP)
                        .widget(grid = new Grid<DateBean>(
                                dateStore,
                                columnModel(
                                        dateCol = columnConfig(DateBeanAccess.INSTANCE.dt(), 100, "Date")
                                )
                        ))
                        .fieldLabel)
                .flowLayoutContainer;

        grid.getView().setForceFit(true);

        // Editing in the grid
        dateStore.setAutoCommit(true); // Prevents red tags thus making UI more consistent.
        GridInlineEditing<DateBean> inlineEditor = new GridInlineEditing<DateBean>(grid);
        inlineEditor.addEditor(dateCol, new DateField(new DateTimePropertyEditor()));

        return w;
    }

    // Cut down code noise
    private ColumnModel<DateBean> columnModel(ColumnConfig<DateBean, ?>... cols) {
        return new ColumnModel(Util.createList(cols));
    }

    // Cut down code noise
    private <T> ColumnConfig<DateBean, T> columnConfig(ValueProvider<DateBean, T> provider, int width, String title) {
        return new ColumnConfig<DateBean, T>(provider, width, title);
    }
}

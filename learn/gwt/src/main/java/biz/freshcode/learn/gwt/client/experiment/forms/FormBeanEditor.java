package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.FieldLabelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.NumberFieldBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.TextFieldBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;

import java.util.Date;

public class FormBeanEditor extends AbstractIsWidget implements Editor<FormBean> {
    TextField str;
    NumberField<Integer> num;
    ListStore<FormBeanChild> childStore = new ListStore<FormBeanChild>(ChildAccess.INSTANCE.key());
    // NOTE: This is not used directly.  It needs to have non-private scope and same name as bean property.
    @SuppressWarnings({"UnusedDeclaration"})
    ListStoreEditor<FormBeanChild> children = new ListStoreEditor<FormBeanChild>(childStore);

    @Override
    protected Widget createWidget() {
        Grid<FormBeanChild> grid;
        ColumnConfig<FormBeanChild, String> nameCol;
        ColumnConfig<FormBeanChild, Date> dateCol;
        FlowLayoutContainer w = new FlowLayoutContainerBuilder()
                .add(new FieldLabelBuilder()
                        .text("Str")
                        .widget(str = new TextFieldBuilder()
                                .emptyText("<Enter a string value>")
                                        // NOTE: This does NOT prevent a blank value from being 'flushed' to the object.
                                .allowBlank(false)
                                .textField)
                        .fieldLabel)
                .add(new FieldLabelBuilder()
                        .text("Num")
                        .widget(new NumberFieldBuilder(num = new NumberField<Integer>(new NumberPropertyEditor.IntegerPropertyEditor()))
                                .allowNegative(false)
                                .emptyText("<Enter a non-negative number or leave blank>")
                                .numberField)
                        .fieldLabel)
                .add(new FieldLabelBuilder()
                        .text("Children")
                        .labelAlign(FormPanel.LabelAlign.TOP)
                        .widget(grid = new Grid<FormBeanChild>(
                                childStore,
                                columnModel(
                                        nameCol = columnConfig(ChildAccess.INSTANCE.name(), 200, "Name"),
                                        dateCol = columnConfig(ChildAccess.INSTANCE.dt(), 100, "Date")
                                )
                        ))
                        .fieldLabel)
                .flowLayoutContainer;

        // column sizing
        // TODO: This does not auto resize
        grid.getView().setForceFit(true);

        // Editing in the grid
        childStore.setAutoCommit(true); // Prevents red tags thus making UI more consistent.
        GridInlineEditing<FormBeanChild> inlineEditor = new GridInlineEditing<FormBeanChild>(grid);
        inlineEditor.addEditor(nameCol, new TextFieldBuilder()
                // TODO: This does not appear to be working
                .allowBlank(false)
                .textField);
        inlineEditor.addEditor(dateCol, new DateField(new DateTimePropertyEditor()));

        return w;
    }

    // Cut down code noise
    private ColumnModel<FormBeanChild> columnModel(ColumnConfig<FormBeanChild, ?>... cols) {
        return new ColumnModel(Util.createList(cols));
    }

    // Cut down code noise
    private <T> ColumnConfig<FormBeanChild, T> columnConfig(ValueProvider<FormBeanChild, T> provider, int width, String title) {
        return new ColumnConfig<FormBeanChild, T>(provider, width, title);
    }

}

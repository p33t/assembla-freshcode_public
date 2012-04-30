package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.FieldLabelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.NumberFieldBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.TextFieldBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.table.ColumnConfigBuilder;
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
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;

import java.util.Date;

public class FormBeanEditor extends AbstractIsWidget implements Editor<FormBean> {
    TextField str;
    NumberField<Integer> num;
    ListStore<FormBeanChild> childStore = new ListStore<FormBeanChild>(ChildAccess.INSTANCE.id());
    // NOTE: This is not used directly.  It needs to have non-private scope and same name as bean property.
    @SuppressWarnings({"UnusedDeclaration"})
    ListStoreEditor<FormBeanChild> children = new ListStoreEditor<FormBeanChild>(childStore);

    @Override
    protected Widget createWidget() {
        Grid<FormBeanChild> grid;
        ColumnConfig<FormBeanChild, String> nameCol;
        ColumnConfig<FormBeanChild, Date> dateCol;
        ColumnConfig<FormBeanChild, Long> startCol;
        ColumnConfig<FormBeanChild, Long> durationCol;
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
                                new ColumnModel(Util.createList(
                                        colConfigBuilder(ChildAccess.INSTANCE.key())
                                                .width(50)
                                                .header("ID")
                                                .columnConfig,
                                        nameCol = colConfigBuilder(ChildAccess.INSTANCE.name())
                                                .width(200)
                                                .header("Name")
                                                .columnConfig,
                                        dateCol = colConfigBuilder(ChildAccess.INSTANCE.dt())
                                                .width(100)
                                                .header("Date")
                                                .columnConfig,
                                        startCol = colConfigBuilder(ChildAccess.INSTANCE.start())
                                                .header("Start")
                                                .width(100)
                                                .cell(new HrMinCell())
                                                .columnConfig,
                                        durationCol = colConfigBuilder(ChildAccess.INSTANCE.duration())
                                                .header("Duration")
                                                .width(100)
                                                .cell(new HrMinCell())
                                                .columnConfig,
                                        colConfigBuilder(new FinishProvider<FormBeanChild>())
                                                .header("Finish")
                                                .width(100)
                                                .columnConfig
                                ))
                        ))
                        .fieldLabel)
                .flowLayoutContainer;

        grid.setHeight(200);
        // column sizing
        // TODO: This does not auto resize
        grid.getView().setForceFit(true);

        // Editing in the grid
        childStore.setAutoCommit(true); // Prevents red tags thus making UI more consistent.
        GridEditing<FormBeanChild> editing = new GridRowEditing<FormBeanChild>(grid);
        editing.addEditor(nameCol, new TextFieldBuilder()
                // NOTE: This only works for GridRowEditing (Inline editor doesn't show errors ?!)
                .allowBlank(false)
                .textField);
        editing.addEditor(dateCol, new DateField(new DateTimePropertyEditor()));
        hrMinEditor(editing, startCol);
        hrMinEditor(editing, durationCol);
        return w;
    }

    // Setup grid inline editing for the given HrMin column.
    private void hrMinEditor(GridEditing<FormBeanChild> inlineEditor, ColumnConfig<FormBeanChild, Long> col) {
        TextField tf = new TextFieldBuilder()
                .allowBlank(false)
                .addValidator(HrMinConverter.VALIDATOR)
                .textField;
        inlineEditor.addEditor(col, HrMinConverter.INSTANCE, tf);
    }

    // Cut down code noise
    private <T> ColumnConfigBuilder colConfigBuilder(ValueProvider<FormBeanChild, T> provider) {
        ColumnConfig<FormBeanChild, T> cc = new ColumnConfig<FormBeanChild, T>(provider);
        return new ColumnConfigBuilder(cc);
    }
}

package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.FieldLabelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.NumberFieldBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.TextFieldBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;

import java.util.Date;

public class FormBeanEditor extends AbstractIsWidget implements Editor<FormBean> {
    public static final FormBeanSubProperties subProps = GWT.create(FormBeanSubProperties.class);
    TextField str;
    NumberField<Integer> num;
    ListStore<FormBeanSub> subStore = new ListStore<FormBeanSub>(subProps.key());
    ListStoreEditor<FormBeanSub> subs = new ListStoreEditor<FormBeanSub>(subStore);

    @Override
    protected Widget createWidget() {

        Grid<FormBeanSub> grid;
        ColumnConfig<FormBeanSub, String> nameCol;
        ColumnConfig<FormBeanSub, Date> dateCol;
        FlowLayoutContainer w = new FlowLayoutContainerBuilder()
                .add(new FieldLabelBuilder()
                        .text("Str")
                        .widget(str = new TextFieldBuilder()
                                .emptyText("<Enter a string value>")
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
                        .text("Subs")
                        .labelAlign(FormPanel.LabelAlign.TOP)
                        .widget(//new GridBuilder(
                                grid = new Grid<FormBeanSub>(
                                        subStore,
                                        columnModel(
                                                nameCol = columnConfig(subProps.name(), 200, "Name"),
                                                dateCol = columnConfig(subProps.dt(), 100, "Date")
                                        )
                                )
                        )
//                                .grid)
                        .fieldLabel)
                .flowLayoutContainer;

        // column sizing
        // TODO: This does not auto resize
        grid.getView().setForceFit(true);

        // Editing in the grid
        GridInlineEditing<FormBeanSub> inlineEditor = new GridInlineEditing<FormBeanSub>(grid);
        inlineEditor.addEditor(nameCol, new TextFieldBuilder()
                .allowBlank(false)
                .textField);
        inlineEditor.addEditor(dateCol, new DateField(new DateTimePropertyEditor()));

        return w;
    }

    // Cut down code noise
    private ColumnModel<FormBeanSub> columnModel(ColumnConfig<FormBeanSub, ?>... cols) {
        return new ColumnModel(Util.createList(cols));
    }

    // Cut down code noise
    private <T> ColumnConfig<FormBeanSub, T> columnConfig(ValueProvider<FormBeanSub, T> provider, int width, String title) {
        return new ColumnConfig<FormBeanSub, T>(provider, width, title);
    }

    interface FormBeanSubProperties extends PropertyAccess<FormBeanSub> {
        @Path("name")
        ModelKeyProvider<FormBeanSub> key();

        ValueProvider<FormBeanSub, String> name();

        ValueProvider<FormBeanSub, Date> dt();
    }
}

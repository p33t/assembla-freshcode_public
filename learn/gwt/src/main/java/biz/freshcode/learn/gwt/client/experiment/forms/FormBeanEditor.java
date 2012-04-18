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
    public static final Access subProps = GWT.create(Access.class);
    TextField str;
    NumberField<Integer> num;
    ListStore<FormBeanChild> childStore = new ListStore<FormBeanChild>(subProps.key());
    /*
    TODO: This is having problems.  It seems that if I close the dialog during a table cell edit the processing
    of the changes likely happens in response to a 'lost-focus' event which happens too late for the changes
     to be captured during the BeforeHide event handler.  Further, the changes do eventually make their way to
     the object graph some time later.  Here is the experiment that suggests it:
     - During a dialog show / edit
     - Make sure there are no errors
     - Enter a cell and make a change
     - Hover the mouse over the 'close' icon
     - Hit tab, pause, and then click close
     - Depending on how long the pause was the change may or may not be apparent in the BeforeHide handler
     - The changes are always committed to the object graph eventually
     */
    // NOTE: This is not used directly.  It needs to have non-private scope and same name as bean property.
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
                        .widget(//new GridBuilder(
                                grid = new Grid<FormBeanChild>(
                                        childStore,
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
        // This seems to help a little with commits lagging flush but it doesn't fix it totally.
        childStore.setAutoCommit(true); // Important for changes to propagate properly.
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

    interface Access extends PropertyAccess<FormBeanChild> {
        // Use an immutable key value instead to rule out strange errors        @Path("name")
        ModelKeyProvider<FormBeanChild> key();

        ValueProvider<FormBeanChild, String> name();

        ValueProvider<FormBeanChild, Date> dt();
    }
}

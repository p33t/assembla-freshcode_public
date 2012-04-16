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
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.Date;

public class FormBeanEditor extends AbstractIsWidget implements Editor<FormBean> {
    public static final FormBeanSubProperties subProps = GWT.create(FormBeanSubProperties.class);
    TextField str;
    NumberField<Integer> num;
    ListStore<FormBeanSub> subStore = new ListStore<FormBeanSub>(subProps.key());
    ListStoreEditor<FormBeanSub> subs = new ListStoreEditor<FormBeanSub>(subStore);

    @Override
    protected Widget createWidget() {
        return new FlowLayoutContainerBuilder()
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
                                new Grid<FormBeanSub>(
                                        subStore,
                                        new ColumnModel<FormBeanSub>(
                                                Util.<ColumnConfig<FormBeanSub, ?>>createList(
                                                        new ColumnConfig<FormBeanSub, String>(subProps.name(), 200, "Name"),
                                                        new ColumnConfig<FormBeanSub, Date>(subProps.dt(), 100, "Date")
                                                )
                                        )
                                )
                        )
//                                .grid)
                        .fieldLabel)
                .flowLayoutContainer;
    }

    interface FormBeanSubProperties extends PropertyAccess<FormBeanSub> {
        @Path("name")
        ModelKeyProvider<FormBeanSub> key();

        ValueProvider<FormBeanSub, String> name();

        ValueProvider<FormBeanSub, Date> dt();
    }
}

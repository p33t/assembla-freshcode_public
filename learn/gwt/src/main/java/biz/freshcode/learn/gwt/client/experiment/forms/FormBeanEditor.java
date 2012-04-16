package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.FieldLabelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.NumberFieldBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.TextFieldBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;

public class FormBeanEditor extends AbstractIsWidget implements Editor<FormBean> {
    TextField str;
    NumberField<Integer> num;

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
                .flowLayoutContainer;
    }
}

package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.FieldLabelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.NumberFieldBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;

public class TimeEditor extends AbstractIsWidget implements Editor<TimeBean> {
    NumberField<Long> start;

    @Override
    protected Widget createWidget() {
        return new FlowLayoutContainerBuilder()
                .add(new FieldLabelBuilder()
                        .text("Start")
                        .widget(new NumberFieldBuilder(start = new NumberField<Long>(new NumberPropertyEditor.LongPropertyEditor()))
                                .numberField)
                        .fieldLabel)
                .flowLayoutContainer;
    }
}

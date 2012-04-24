package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.FieldLabelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.HrMinFieldBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.TextFieldBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.form.ConverterEditorAdapter;
import com.sencha.gxt.widget.core.client.form.TextField;

public class PeriodBeanEditor extends AbstractIsWidget implements Editor<PeriodBean> {
    // This doesn't reformat the text on blur
    @Ignore
    TextField hrMinField;
    ConverterEditorAdapter<Long, String, TextField> hrMin;

    // This clears the value if it doesn't parse (?!)
    HrMinField hrMin2;

    @Override
    protected Widget createWidget() {
        FlowLayoutContainer c = new FlowLayoutContainerBuilder()
                .add(new FieldLabelBuilder()
                        .text("Hr Min (Converter)")
                        .widget(hrMinField = new TextFieldBuilder()
                                .allowBlank(false)
                                .addValidator(HrMinConverter.VALIDATOR)
                                .textField)
                        .fieldLabel)
                .add(new FieldLabelBuilder()
                        .text("Hr Min 2 (Field)")
                        .widget(hrMin2 = new HrMinFieldBuilder()
                                .allowBlank(false)
                                .hrMinField)
                        .fieldLabel)
                .flowLayoutContainer;
        hrMin = new ConverterEditorAdapter(hrMinField, HrMinConverter.INSTANCE);
        return c;
    }
}

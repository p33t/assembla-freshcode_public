package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.form.FieldLabelBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Widget;

public class PeriodBeanEditor extends AbstractIsWidget implements Editor<PeriodBean> {
    // This clears the value if it doesn't parse (?!)
    HrMinField hrMin1;

    HrMinField2 hrMin2;

    @Override
    protected Widget createWidget() {
        return new FlowLayoutContainerBuilder()
                .add(new FieldLabelBuilder()
                        .text("Hr Min 2 (Field)")
                        .widget(hrMin1 = new HrMinFieldBuilder()
                                .allowBlank(false)
                                .hrMinField)
                        .fieldLabel)
                .add(new FieldLabelBuilder()
                        .text("Hr Min (Converter)")
                        .widget(hrMin2 = new HrMinField2())
                        .fieldLabel)
                .flowLayoutContainer;
    }
}

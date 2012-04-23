package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.uispike.builder.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.FieldLabelBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.HrMinFieldBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Widget;

public class PeriodBeanEditor extends AbstractIsWidget implements Editor<PeriodBean> {
    HrMinField start;

    @Override
    protected Widget createWidget() {
        return new FlowLayoutContainerBuilder()
                .add(new FieldLabelBuilder()
                        .text("Start")
                        .widget(start = new HrMinFieldBuilder()
                                .allowBlank(false)

                                .hrMinField)
                        .fieldLabel)
                .flowLayoutContainer;
    }
}

package biz.freshcode.learn.gwt.mod1.client.experiment.triggerfield;

import biz.freshcode.learn.gwt.mod1.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.mod1.client.builder.gxt.form.FieldLabelBuilder;
import biz.freshcode.learn.gwt.mod1.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.DateField;

public class TriggerFieldDemo extends AbstractIsWidget {

    @Override
    protected Widget createWidget() {
        return new VerticalLayoutContainerBuilder()
                .add(new FieldLabelBuilder()
                        .text("Date Field")
                        .add(new DateField())
                        .fieldLabel)
                .add(new FieldLabelBuilder()
                        .text("Digit")
                        .add(new MyTriggerField())
                        .fieldLabel)
                .verticalLayoutContainer;
    }
}

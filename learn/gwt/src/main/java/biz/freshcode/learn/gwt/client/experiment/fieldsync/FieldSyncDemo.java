package biz.freshcode.learn.gwt.client.experiment.fieldsync;

import biz.freshcode.learn.gwt.client.builder.gxt.ContentPanelBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.form.FieldLabelBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

import static com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

public class FieldSyncDemo extends AbstractIsWidget<BorderLayoutContainer> {
    @Override
    protected BorderLayoutContainer createWidget() {
        return new BorderLayoutContainerBuilder()
                .centerWidget(new ContentPanelBuilder()
                        .add(new FieldLabelBuilder()
                                .text("Text")
                                .widget(new TextField())
                                .fieldLabel)
                        .contentPanel
                )
                .southWidget(new TextArea(), new BorderLayoutData(100))
                .borderLayoutContainer;
    }
}

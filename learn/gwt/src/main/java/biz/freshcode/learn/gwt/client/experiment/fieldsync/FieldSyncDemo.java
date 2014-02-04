package biz.freshcode.learn.gwt.client.experiment.fieldsync;

import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.form.FieldLabelBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.form.TextAreaBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Command;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.BlurEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.FocusEvent;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

import static com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

public class FieldSyncDemo extends AbstractIsWidget<BorderLayoutContainer> {
    private TextArea fb;

    @Override
    protected BorderLayoutContainer createWidget() {
        TextField text;
        DateField date;
        BorderLayoutContainer blc = new BorderLayoutContainerBuilder()
                .centerWidget(new VerticalLayoutContainerBuilder()
                        .add(new FieldLabelBuilder()
                                .text("Text")
                                .widget(text = new TextField())
                                .fieldLabel)
                        .add(new FieldLabelBuilder()
                                .text("Text")
                                .widget(date = new DateField())
                                .fieldLabel)
                        .verticalLayoutContainer
                )
                .southWidget(fb = new TextAreaBuilder()
                        .textArea, new BorderLayoutData(100))
                .borderLayoutContainer;

        text.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                msg("text value changed to: " + event.getValue());
            }
        });

        date.addCollapseHandler(new CollapseEvent.CollapseHandler() {
            @Override
            public void onCollapse(CollapseEvent event) {
                msg("date collapse");
            }
        });
        date.addBlurHandler(new BlurEvent.BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                msg("date blur");
            }
        });
        date.addFocusHandler(new FocusEvent.FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                msg("date focus");
            }
        });


//      no better than value change
//        text.addChangeHandler(new ChangeHandler() {
//            @Override
//            public void onChange(ChangeEvent event) {
//                msg("text change");
//            }
//        });

        text.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                final TextField source = (TextField) event.getSource();
                msg("text key press #" + event.getUnicodeCharCode() +
                        " resulting in text " + source.getText() +
                        " and value " + source.getValue());
                Scheduler.get().scheduleDeferred(new Command() {
                    @Override
                    public void execute() {
                        msg("text ultimate text was " + source.getText());
                    }
                });
            }
        });

        text.addFocusHandler(new FocusEvent.FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                msg("text focus");
            }
        });

        text.addBlurHandler(new BlurEvent.BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                msg("text blur");
            }
        });

        return blc;
    }

    private void msg(String msg) {
        fb.setValue(msg + "\n" + fb.getValue());
    }
}

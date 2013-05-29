package biz.freshcode.learn.gwtp.client.editform;

import biz.freshcode.learn.gwtp.client.builder.gxt.FramedPanelBuilder;
import biz.freshcode.learn.gwtp.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwtp.client.builder.gxt.form.FieldLabelBuilder;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.widget.core.client.form.SpinnerField;

import static com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;

public class EditFormViewImpl extends ViewImpl implements EditForm.View, Editor<NumBean> {
    // NOTE: non-private and same name as bean property
    // Hmmm.... the view getters are overriding these.  And interfering if they are not private.
    private SpinnerField<Integer> numLeft;
    private SpinnerField<Integer> numRight;

    @Inject
    public EditFormViewImpl() {
        initWidget(new FramedPanelBuilder()
                .headingText("Edit Form")
                .widget(new VerticalLayoutContainerBuilder()
                        .add(new FieldLabelBuilder()
                                .text("Left")
                                .add(numLeft = new SpinnerField<Integer>(new IntegerPropertyEditor()))
                                .fieldLabel)
                        .add(new FieldLabelBuilder()
                                .text("Right")
                                .add(numRight = new SpinnerField<Integer>(new IntegerPropertyEditor()))
                                .fieldLabel)
                        .verticalLayoutContainer)
                .framedPanel);
        numRight.addValueChangeHandler(new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange(ValueChangeEvent<Integer> event) {

            }
        });
    }

    @Override
    public SpinnerField<Integer> numLeft() {
        return numLeft;
    }

    @Override
    public SpinnerField<Integer> numRight() {
        return numRight;
    }
}

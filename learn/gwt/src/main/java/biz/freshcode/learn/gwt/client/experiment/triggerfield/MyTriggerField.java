package biz.freshcode.learn.gwt.client.experiment.triggerfield;

import biz.freshcode.learn.gwt.client.builder.gxt.form.SpinnerFieldBuilder;
import biz.freshcode.learn.gwt.client.experiment.triggerfield.reuse.SpinnerSupport;
import biz.freshcode.learn.gwt.client.uispike.builder.container.PopupPanelBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.SpinnerField;
import com.sencha.gxt.widget.core.client.form.TriggerField;

import static biz.freshcode.learn.gwt.client.experiment.hoverwidget.reuse.Bundle.STYLE;

/**
 * No change events for spinner?
 * http://www.sencha.com/forum/showthread.php?105343-FIXED-170-SpinnerField-change-event-doesn-t-fire
 */
public class MyTriggerField extends TriggerField<Digit> {
    private final SpinnerField<Integer> spin;
    private PopupPanel popup;

    public MyTriggerField() {
        super(new Digit.Editor());
        addTriggerClickHandler(new TriggerClickEvent.TriggerClickHandler() {
            @Override
            public void onTriggerClick(TriggerClickEvent event) {
                triggerClick();
            }
        });
        popup = new PopupPanelBuilder()
                .add(spin = new SpinnerFieldBuilder<Integer>(new NumberPropertyEditor.IntegerPropertyEditor())
                        .minValue(0)
                        .maxValue(9)
                        .spinnerField)
                .modal(false)
                .autoHideEnabled(true)
                .addStyleName(STYLE.hoverWidgetPopup())
                .popupPanel;
        new SpinnerSupport<Integer>(spin, getIntValue(), new SpinnerSupport.Callback<Integer>() {
            @Override
            public void valueChanged(Integer integer) {
                setIntValue(integer);
            }
        });
    }

    private void setIntValue(Integer i) {
        if (i == null) setValue(null);
        else setValue(new Digit((byte) i.intValue()));
    }

    private Integer getIntValue() {
        Digit digit = getValue();
        if (digit == null) return null;
        return (int) digit.getVal();
    }

    private void triggerClick() {
        popup.setPopupPosition(getAbsoluteLeft(), getAbsoluteTop() + getOffsetHeight());
        popup.show();
    }

    @Override
    public Digit.Editor getPropertyEditor() {
        return (Digit.Editor) super.getPropertyEditor();
    }

    @Override
    protected void onCellParseError(ParseErrorEvent event) {
        super.onCellParseError(event);
        //noinspection ThrowableResultOfMethodCallIgnored
        String msg = event.getException().getMessage();
        parseError = msg;
        forceInvalid(msg);
    }
}

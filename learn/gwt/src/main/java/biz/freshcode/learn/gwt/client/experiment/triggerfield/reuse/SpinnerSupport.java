package biz.freshcode.learn.gwt.client.experiment.triggerfield.reuse;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TwinTriggerClickEvent;
import com.sencha.gxt.widget.core.client.form.SpinnerField;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;

/**
 * Works around the spinner field event crappiness (apparent in GXT 3.0.1).
 */
public class SpinnerSupport<N extends Number> {
    private final SpinnerField<N> spinner;
    private final Callback<N> callback;
    List<HandlerRegistration> regos = newList();
    N knownValue;

    public SpinnerSupport(SpinnerField<N> spinner, N initialValue, Callback<N> callback) {
        this.callback = callback;
        spinner.setValue(initialValue);
        knownValue = initialValue;
        this.spinner = spinner;
        Listener listener = new Listener();
        regos.add(spinner.addTriggerClickHandler(listener));
        regos.add(spinner.addTwinTriggerClickHandler(listener)); // for 'down' click
        // NOTE: Value changes are only triggered when losing focus from text field portion.
        regos.add(spinner.addValueChangeHandler(listener));
    }

    private void sync() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                N current = calcValue();
                if (current == null) {
                    if (knownValue == null) return;
                } else if (current.equals(knownValue)) return;
                knownValue = current;
                callback.valueChanged(knownValue);
            }
        });
    }

    private N calcValue() {
        String t = spinner.getText();
        try {
            return spinner.getPropertyEditor().parse(t);
        } catch (ParseException e) {
            return spinner.getValue(); // some default case
        }
    }

    /**
     * Removes handlers associated with events.
     */
    public void removeHandlers() {
        for (Iterator<HandlerRegistration> it = regos.iterator(); it.hasNext(); ) {
            HandlerRegistration next = it.next();
            it.remove();
            next.removeHandler();
        }
    }

    private class Listener implements TriggerClickEvent.TriggerClickHandler, ValueChangeHandler<N>, TwinTriggerClickEvent.TwinTriggerClickHandler {
        @Override
        public void onTriggerClick(TriggerClickEvent event) {
            sync();
        }

        @Override
        public void onValueChange(ValueChangeEvent<N> event) {
            sync();
        }

        @Override
        public void onTwinTriggerClick(TwinTriggerClickEvent event) {
            sync();
        }
    }

    public interface Callback<N extends Number> {
        void valueChanged(N n);
    }
}

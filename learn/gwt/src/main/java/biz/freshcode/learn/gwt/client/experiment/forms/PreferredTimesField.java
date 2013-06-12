package biz.freshcode.learn.gwt.client.experiment.forms;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.form.AdapterField;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;

import java.util.List;
import java.util.Set;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newSet;

public class PreferredTimesField extends AdapterField<Set<AmPm>> implements HasValueChangeHandlers<Set<AmPm>> {
    private final ListStore<AmPmFlag> store;
    private Set<AmPm> publishedValue;

    public PreferredTimesField(GridEditing<AmPmFlag> ge) {
        super(ge.getEditableGrid());
        this.store = ge.getEditableGrid().getStore();
        publishedValue = getValue();

        ge.addCompleteEditHandler(new CompleteEditEvent.CompleteEditHandler<AmPmFlag>() {
            @Override
            public void onCompleteEdit(CompleteEditEvent<AmPmFlag> event) {
                GWT.log("Edit complete");
                setPublishedValue(getValue());
            }
        });
    }

    @Override
    public void setValue(Set<AmPm> value) {
        List<AmPmFlag> flags = newList();
        for (AmPm ap : AmPm.values()) {
            AmPmFlag flag = new AmPmFlag(ap);
            if (value.contains(ap)) flag.setFlag(true);
            flags.add(flag);
        }

        if (flags.equals(store.getAll())) GWT.log("No change");
        else {
            store.replaceAll(flags);
            // NOTE: Ideally use ValueChangeEvent.fireIfNotEquals()
            ValueChangeEvent.fire(this, value);
        }
    }

    @Override
    public Set<AmPm> getValue() {
        Set<AmPm> set = newSet();
        for (AmPmFlag f : store.getAll()) {
            if (f.isFlag()) set.add(f.getAmPm());
        }
        return set;
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Set<AmPm>> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    private void setPublishedValue(Set<AmPm> value) {
        Set<AmPm> old = publishedValue;
        publishedValue = value;
        validate(); // ?!
        ValueChangeEvent.fireIfNotEqual(this, old, value);
    }
}

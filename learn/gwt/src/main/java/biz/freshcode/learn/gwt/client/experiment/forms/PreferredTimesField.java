package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.builder.gxt.grid.ColumnConfigBuilder;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.event.StoreRecordChangeEvent;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.form.AdapterField;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;

import java.util.List;
import java.util.Set;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newSet;

public class PreferredTimesField extends AdapterField<Set<AmPm>> implements HasValueChangeHandlers<Set<AmPm>> {
    private final ListStore<AmPmFlag> store;
    private Set<AmPm> publishedValue;

    private static GridEditing<AmPmFlag> createGridEtc() {
        // no need to populate... set value will populate
        ListStore<AmPmFlag> store = new ListStore<AmPmFlag>(AmPmFlag.ACCESS.id());
        store.setAutoCommit(true);

        ColumnConfig<AmPmFlag, Boolean> colPref;
        @SuppressWarnings("unchecked")
        Grid<AmPmFlag> grid = new Grid(store, new ColumnModel(newListFrom(
                colPref = new ColumnConfigBuilder(AmPmFlag.ACCESS.flag())
                        .header("Preferred")
                        .width(1)
                        .columnConfig,
                new ColumnConfigBuilder(AmPmFlag.ACCESS.amPm())
                        .header("Time")
                        .width(3)
                        .columnConfig
        )));

        GridInlineEditing<AmPmFlag> editing = new GridInlineEditing<AmPmFlag>(grid);
        editing.addEditor(colPref, new CheckBox());

//        Interferes with editing process...
//        CheckBoxCell cbc = new CheckBoxCell();
//        colPref.setCell(cbc);

        grid.getView().setForceFit(true);
        return editing;
    }

    public PreferredTimesField() {
        this(createGridEtc());
    }

    private PreferredTimesField(GridEditing<AmPmFlag> ge) {
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

        store.addStoreRecordChangeHandler(new StoreRecordChangeEvent.StoreRecordChangeHandler<AmPmFlag>() {
            @Override
            public void onRecordChange(StoreRecordChangeEvent<AmPmFlag> event) {
                GWT.log("Record changed: " + event.getRecord().getModel());
            }
        });

        store.addStoreUpdateHandler(new StoreUpdateEvent.StoreUpdateHandler<AmPmFlag>() {
            @Override
            public void onUpdate(StoreUpdateEvent<AmPmFlag> event) {
                GWT.log("Store Update: " + event.getItems());
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

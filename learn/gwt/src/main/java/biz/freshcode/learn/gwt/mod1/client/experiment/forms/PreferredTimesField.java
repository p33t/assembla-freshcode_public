package biz.freshcode.learn.gwt.mod1.client.experiment.forms;

import biz.freshcode.learn.gwt.mod1.client.builder.gxt.grid.ColumnConfigBuilder;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.cell.core.client.form.CheckBoxCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;
import com.sencha.gxt.widget.core.client.form.AdapterField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static biz.freshcode.learn.gwt.mod1.client.util.AppCollectionUtil.*;

public class PreferredTimesField extends AdapterField<Set<AmPm>> implements HasValueChangeHandlers<Set<AmPm>> {
    private static final Set<AmPm> EMPTY_FLAGS = Collections.emptySet();
    private final ListStore<AmPmFlag> store;
    private Set<AmPm> publishedValue;

    private static Grid<AmPmFlag> createGridEtc() {
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

        // NOTE: This will edit the datasource directly without GridInlineEditing.
        // Its behaviour is nicer too.
        CheckBoxCell cbc = new CheckBoxCell();
        colPref.setCell(cbc);

        grid.getView().setForceFit(true);
        return grid;
    }

    public PreferredTimesField() {
        this(createGridEtc());
    }

    private PreferredTimesField(Grid<AmPmFlag> grid) {
        super(grid);
        this.store = grid.getStore();
        publishedValue = getValue();

//  Does nothing.
//        store.addStoreRecordChangeHandler(new StoreRecordChangeEvent.StoreRecordChangeHandler<AmPmFlag>() {
//            @Override
//            public void onRecordChange(StoreRecordChangeEvent<AmPmFlag> event) {
//                GWT.log("Record changed: " + event.getRecord().getModel());
//            }
//        });

        store.addStoreUpdateHandler(new StoreUpdateEvent.StoreUpdateHandler<AmPmFlag>() {
            @Override
            public void onUpdate(StoreUpdateEvent<AmPmFlag> event) {
                GWT.log("Store Update: " + event.getItems());
                setPublishedValue(getValue());
            }
        });
    }

    @Override
    public void setValue(Set<AmPm> value) {
        List<AmPmFlag> flags = asFlags(value);
        store.replaceAll(flags);
        setPublishedValue(value);
    }

    private List<AmPmFlag> asFlags(Set<AmPm> value) {
        Set<AmPm> safe = value == null? EMPTY_FLAGS: value;
        List<AmPmFlag> flags = newList();
        for (AmPm ap : AmPm.values()) {
            AmPmFlag flag = new AmPmFlag(ap);
            if (safe.contains(ap)) flag.setFlag(true);
            flags.add(flag);
        }
        return flags;
    }

    @Override
    public Set<AmPm> getValue() {
        Set<AmPm> set = newSet();
        for (AmPmFlag f : store.getAll()) {
            if (f.isFlag()) set.add(f.getAmPm());
        }
        if (set.isEmpty()) return null;
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

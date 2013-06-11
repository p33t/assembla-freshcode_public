package biz.freshcode.learn.gwt.client.experiment.forms;

import biz.freshcode.learn.gwt.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.form.NumberFieldBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.form.TextFieldBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.FieldLabelBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.CheckBoxCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.*;

public class FormBeanEditor extends AbstractIsWidget implements Editor<FormBean> {
    TextField str;
    NumberField<Integer> num;
    ListStore<FormBeanChild> childStore = new ListStore<FormBeanChild>(ChildAccess.INSTANCE.id());
    // NOTE: This is not used directly.  It needs to have non-private scope and same name as bean property.
    @SuppressWarnings({"UnusedDeclaration"})
    ListStoreEditor<FormBeanChild> children = new ListStoreEditor<FormBeanChild>(childStore);
    PreferredTimesField preferredTimes;

    @SuppressWarnings("unchecked")
    @Override
    protected Widget createWidget() {
        Grid<FormBeanChild> grid;
        ColumnConfig<FormBeanChild, String> nameCol;
        ColumnConfig<FormBeanChild, Date> dateCol;
        ColumnConfig<FormBeanChild, Long> startCol;
        ColumnConfig<FormBeanChild, Long> durationCol;
        VerticalLayoutContainer w = new VerticalLayoutContainerBuilder()
                .add(new FieldLabelBuilder()
                        .text("Str")
                        .widget(str = new TextFieldBuilder()
                                .emptyText("<Enter a string value>")
                                        // NOTE: This does NOT prevent a blank value from being 'flushed' to the object.
                                .allowBlank(false)
                                .textField)
                        .fieldLabel)
                .add(new FieldLabelBuilder()
                        .text("Preferred Times")
                        .widget(preferredTimes = new PreferredTimesField(createPreferredTimes()))
                        .fieldLabel)
                .add(new FieldLabelBuilder()
                        .text("Num")
                        .widget(new NumberFieldBuilder(num = new NumberField<Integer>(new NumberPropertyEditor.IntegerPropertyEditor()))
                                .allowNegative(false)
                                .emptyText("<Enter a non-negative number or leave blank>")
                                .numberField)
                        .fieldLabel)
                .add(new FieldLabelBuilder()
                        .text("Children")
                        .labelAlign(FormPanel.LabelAlign.TOP)
                                // Borders true, width 1* makes the width auto-resize...and maybe Grid.getView().setForceFit()
                        .borders(true)
                        .width("1*")

                        .height(300)
//                       Causes height to gradually increase with window resizing
//                        .height(-100)
//                        .height("1*")
                        .widget(grid = new Grid<FormBeanChild>(
                                childStore,
                                new ColumnModel(Util.createList(
                                        colConfigBuilder(ChildAccess.INSTANCE.key())
                                                .width(50)
                                                .header("ID")
                                                .columnConfig,
                                        nameCol = colConfigBuilder(ChildAccess.INSTANCE.name())
                                                .width(200)
                                                .header("Name")
                                                .columnConfig,
                                        dateCol = colConfigBuilder(ChildAccess.INSTANCE.dt())
                                                .width(100)
                                                .header("Date")
                                                .columnConfig,
                                        startCol = colConfigBuilder(ChildAccess.INSTANCE.start())
                                                .header("Start")
                                                .width(100)
                                                .cell(new HrMinCell())
                                                .columnConfig,
                                        durationCol = colConfigBuilder(ChildAccess.INSTANCE.duration())
                                                .header("Duration")
                                                .width(100)
                                                .cell(new HrMinCell())
                                                .columnConfig,
                                        colConfigBuilder(new FinishProvider<FormBeanChild>())
                                                .header("Finish")
                                                .width(100)
                                                .columnConfig
                                ))
                        ))
                        .fieldLabel)
                .verticalLayoutContainer;

        // column sizing
        // This does auto-resize of width when FieldLabel width is "1*".
        grid.getView().setForceFit(true);

        // Editing in the grid
        childStore.setAutoCommit(true); // Prevents red tags thus making UI more consistent.
        GridEditing<FormBeanChild> editing = new GridRowEditing<FormBeanChild>(grid);
        editing.addEditor(nameCol, new TextFieldBuilder()
                // NOTE: This only works for GridRowEditing (Inline editor doesn't show errors ?!)
                .allowBlank(false)
                .textField);
        editing.addEditor(dateCol, new DateField(new DateTimePropertyEditor()));
        hrMinEditor(editing, startCol);
        hrMinEditor(editing, durationCol);
        return w;
    }

    private Grid<AmPmFlag> createPreferredTimes() {
        // no need to populate... set value will populate
        ListStore<AmPmFlag> store = new ListStore<AmPmFlag>(AmPmFlag.ACCESS.id());
        store.setAutoCommit(true);

        ColumnConfig<AmPmFlag, Boolean> colPref;
        @SuppressWarnings("unchecked")
        Grid<AmPmFlag> grid = new Grid(store, new ColumnModel(newListFrom(
                colPref = new ColumnConfigBuilder(AmPmFlag.ACCESS.flag())
                        .header("Preferred")
                        .width(1)
                        .cell(new CheckBoxCell())
                        .columnConfig,
                new ColumnConfigBuilder(AmPmFlag.ACCESS.amPm())
                        .header("Time")
                        .width(3)
                        .columnConfig
        )));

        GridInlineEditing<AmPmFlag> editing = new GridInlineEditing<AmPmFlag>(grid);
        editing.addEditor(colPref, new CheckBox());
        grid.getView().setForceFit(true);
        return grid;
    }

    public static class PreferredTimesField extends AdapterField<Set<AmPm>> {
        private final Grid<AmPmFlag> grid;

        public PreferredTimesField(Grid<AmPmFlag> grid) {
            super(grid);
            this.grid = grid;
        }

        @Override
        public void setValue(Set<AmPm> value) {
            ListStore<AmPmFlag> store = grid.getStore();

            List<AmPmFlag> flags = newList();
            for (AmPm ap : AmPm.values()) {
                AmPmFlag flag = new AmPmFlag(ap);
                if (value.contains(ap)) flag.setFlag(true);
                flags.add(flag);
            }

            store.replaceAll(flags);
        }

        @Override
        public Set<AmPm> getValue() {
            Set<AmPm> set = newSet();
            for (AmPmFlag f : grid.getStore().getAll()) {
                if (f.isFlag()) set.add(f.getAmPm());
            }
            return set;
        }
    }

    // Setup grid inline editing for the given HrMin column.
    private void hrMinEditor(GridEditing<FormBeanChild> inlineEditor, ColumnConfig<FormBeanChild, Long> col) {
        TextField tf = new TextFieldBuilder()
                .allowBlank(false)
                .addValidator(HrMinConverter.VALIDATOR)
                .textField;
        inlineEditor.addEditor(col, HrMinConverter.INSTANCE, tf);
    }

    // Cut down code noise
    private <T> ColumnConfigBuilder colConfigBuilder(ValueProvider<FormBeanChild, T> provider) {
        ColumnConfig<FormBeanChild, T> cc = new ColumnConfig<FormBeanChild, T>(provider);
        //noinspection unchecked
        return new ColumnConfigBuilder(cc);
    }
}

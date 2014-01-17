package biz.freshcode.learn.gwt.client.experiment.forms2;

import biz.freshcode.learn.gwt.client.builder.Construct;
import biz.freshcode.learn.gwt.client.builder.gxt.form.SimpleComboBoxBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.form.TextFieldBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ToStringValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.info.Info;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;

public class Forms2Demo extends AbstractIsWidget {

    ListStore<TwoBean> store = new ListStore<TwoBean>(TwoBean.ACCESS.id());
    List<String> presets = newListFrom("Zero", "One", "Two", "Three", "Four");

    @Override
    protected Widget createWidget() {
        store.setAutoCommit(true);
        store.add(new TwoBean());
        store.add(new TwoBean());

        ColumnConfig<TwoBean, Object> raw, converted, edited, hacked;
        ColumnConfig<TwoBean, Date> date;
        ColumnConfig<TwoBean, Integer> preset;

        RowNumberer<TwoBean> nums;
        Grid<TwoBean> grid = new Grid<TwoBean>(store, new ColumnModel<TwoBean>(newListFrom(
                nums = new RowNumberer<TwoBean>(new IdentityValueProvider<TwoBean>()),
                new ColumnConfigBuilder<TwoBean, String>(TwoBean.ACCESS.idValue())
                        .header("Id")
                        .columnConfig,
                date = new ColumnConfigBuilder<TwoBean, Date>(TwoBean.ACCESS.date())
                        .header("Date")
                        .columnConfig,
                preset = new ColumnConfigBuilder<TwoBean, Integer>(TwoBean.ACCESS.presetId())
                        .header("Preset")
                        .cell(new AbstractCell<Integer>() {
                            @Override
                            public void render(Context context, Integer value, SafeHtmlBuilder sb) {
                                String s = presetStr(value);
                                sb.appendEscaped(s);
                            }
                        })
                        .columnConfig,
                raw = new ColumnConfigBuilder<TwoBean, Object>(new ToStringValueProvider("lowerStr"))
                        .header("Raw")
                        .columnConfig,
                converted = new ColumnConfigBuilder<TwoBean, Object>(TwoBean.ACCESS.lowerStr())
                        .header("Converted")
                        .columnConfig,
                edited = new ColumnConfigBuilder<TwoBean, Object>(TwoBean.ACCESS.lowerStr())
                        .header("Edited")
                        .columnConfig,
                hacked = new ColumnConfigBuilder<TwoBean, Object>(TwoBean.ACCESS.lowerStr())
                        .header("Hacked")
                        .columnConfig
        )));

        nums.initPlugin(grid); // needed according to doco

        // editing
        GridInlineEditing<TwoBean> edits = new GridInlineEditing<TwoBean>(grid);
//        edits.addEditor(raw, new TextField());
        edits.addEditor(converted, new LowerConverter(), new TextFieldBuilder()
//                .autoValidate(true) // does nothing
                .propertyEditor(LowerPropertyEditor.INSTANCE) // prevents the bad value from sticking
//                Does nothing
//                .errorSupport(new ErrorHandler() {
//                    @Override
//                    public void clearInvalid() {
//                        // nothing
//                    }
//
//                    @Override
//                    public void markInvalid(List<EditorError> errors) {
//                        String msg = "";
//                        for (EditorError error: errors) {
//                            if (!msg.isEmpty()) msg += "\n";
//                            msg += error.getMessage();
//                        }
//                        Info.display("Error", msg);
//                    }
//                })
                .textField);
//        edits.addEditor(edited, new TextFieldBuilder()
//                .clearValueOnParseError(false)
//                .autoValidate(true)
////              Does nothing
////                .construct(new Construct<TextFieldBuilder>() {
////                    @Override
////                    public void run() {
////                        builder.errorSupport(new ToolTipErrorHandler(builder.textField));
////                    }
////                })
////              Does nothing
////                .construct(new Construct<TextFieldBuilder>() {
////                    @Override
////                    public void run() {
////                        builder.errorSupport(new SideErrorHandler(builder.textField));
////                    }
////                })
////                This causes display to briefly show red when 'error' is entered via a different means
////                .addValidator(new LowerValidator())
//                .propertyEditor(new LowerPropertyEditor())
//                .textField);
        edits.addEditor(hacked, new LowerConverterWithFeedback(), new TextFieldBuilder()
                .addValidator(LowerValidator.INSTANCE) // does nothing!!!!
                .textField);
        edits.addEditor(date, new DateField());

        LabelProvider<Integer> labels = new LabelProvider<Integer>() {

            @Override
            public String getLabel(Integer item) {
                return presetStr(item);
            }
        };

        edits.addEditor(preset, new SimpleComboBoxBuilder<Integer>(labels)
//                .forceSelection(true) // this may not do what you think... e'thing is string oriented?
//                .editable(false) // combined with list filter on type to make unusable
                .triggerAction(ComboBoxCell.TriggerAction.ALL) // needed
                .construct(new Construct<SimpleComboBoxBuilder<Integer>>() {
                    @Override
                    public void run() {
                        for (int i = 0; i < presets.size(); i++) {
                            builder.add(i);
                        }
                    }
                })
                // doesn't fix blur-not-saving problem
                .propertyEditor(new PropertyEditor<Integer>() {
                    @Override
                    public Integer parse(CharSequence text) throws ParseException {
                        String s = text.toString().trim();
                        if (s.isEmpty()) return null;
                        int ix = presets.indexOf(s);
                        if (ix < 0) throw new ParseException("Not Found: " + s, 0);
                        return ix;
                    }

                    @Override
                    public String render(Integer ix) {
                        if (ix == null) return "";
                        return presets.get(ix);
                    }
                })
                .simpleComboBox);

        return grid;
    }

    private String presetStr(Integer value) {
        String s;
        if (value == null || value >= presets.size()) s = "?";
        else s = presets.get(value);
        return s;
    }

    private static class LowerConverterWithFeedback extends LowerConverter {
        @Override
        public String convertFieldValue(String fieldVal) {
            if (fieldVal.isEmpty()) return null;
            if ("error".equalsIgnoreCase(fieldVal)) {
                // cook our own feedback (sigh)
                Info.display("Error", "Error Value");
                return null;
            }
            return fieldVal.toLowerCase();
        }
    }
}

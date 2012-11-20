package biz.freshcode.learn.gwt.client.experiment.forms2;

import biz.freshcode.learn.gwt.client.builder.gxt.form.TextFieldBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ToStringValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.Date;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;

public class Forms2Demo extends AbstractIsWidget {

    ListStore<TwoBean> store = new ListStore<TwoBean>(TwoBean.ACCESS.id());

    @Override
    protected Widget createWidget() {
        store.setAutoCommit(true);
        store.add(new TwoBean());
        store.add(new TwoBean());

        ColumnConfig<TwoBean, Object> raw, converted, edited, hacked;
        ColumnConfig<TwoBean, Date> date;

        RowNumberer<TwoBean> nums;
        Grid<TwoBean> grid = new Grid<TwoBean>(store, new ColumnModel<TwoBean>(newListFrom(
                nums = new RowNumberer<TwoBean>(new IdentityValueProvider<TwoBean>()),
                new ColumnConfigBuilder<TwoBean, String>(TwoBean.ACCESS.idValue())
                        .header("Id")
                        .columnConfig,
                date = new ColumnConfigBuilder<TwoBean, Date>(TwoBean.ACCESS.date())
                        .header("Date")
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

        return grid;
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

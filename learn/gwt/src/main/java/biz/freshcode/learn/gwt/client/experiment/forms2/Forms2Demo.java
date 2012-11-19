package biz.freshcode.learn.gwt.client.experiment.forms2;

import biz.freshcode.learn.gwt.client.builder.gxt.form.TextFieldBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.Construct;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.error.ToolTipErrorHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;

import java.text.ParseException;
import java.util.Date;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.firstElem;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;

public class Forms2Demo extends AbstractIsWidget {

    ListStore<TwoBean> store = new ListStore<TwoBean>(TwoBean.ACCESS.id());

    @Override
    protected Widget createWidget() {
        store.setAutoCommit(true);
        store.add(new TwoBean());
        store.add(new TwoBean());

        ColumnConfig<TwoBean, String> raw, converted, edited;
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
                raw = new ColumnConfigBuilder<TwoBean, String>(TwoBean.ACCESS.lowerStr())
                        .header("Raw")
                        .columnConfig,
                converted = new ColumnConfigBuilder<TwoBean, String>(TwoBean.ACCESS.lowerStr())
                        .header("Converted")
                        .columnConfig,
                edited = new ColumnConfigBuilder<TwoBean, String>(TwoBean.ACCESS.lowerStr())
                        .header("Edited")
                        .columnConfig
        )));

        nums.initPlugin(grid); // needed according to doco

        // editing
        GridInlineEditing<TwoBean> edits = new GridInlineEditing<TwoBean>(grid);
        edits.addEditor(raw, new TextField());
        edits.addEditor(converted, new LowerConverter(), new TextField());
        edits.addEditor(edited, new TextFieldBuilder()
                .clearValueOnParseError(false)
                .autoValidate(true)
//              Does nothing
                .construct(new Construct<TextFieldBuilder>() {
                    @Override
                    public void run() {
                        builder.errorSupport(new ToolTipErrorHandler(builder.textField));
                    }
                })
                .propertyEditor(new PropertyEditor<String>() {
                    @Override
                    public String parse(CharSequence text) throws ParseException {
                        String parsed = text.toString().toLowerCase();
                        if (parsed.equals("error")) throw new ParseException("Error value", 0);
                        if (parsed.isEmpty()) return null;
                        return parsed;
                    }

                    @Override
                    public String render(String object) {
                        if (object == null) return "";
                        return object.toUpperCase(); // THIS ISN'T BEING CALLED!
                    }
                })
                .textField);
        edits.addEditor(date, new DateField());

        return grid;
    }
}

package biz.freshcode.learn.gwt.client.bug;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.*;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridEditInlineComboBoxBlurBug implements IsWidget, EntryPoint {
    static final Access ACCESS = GWT.create(Access.class);
    static final List<String> presets = Arrays.asList("one", "two", "three");
    VerticalLayoutContainer vlc = new VerticalLayoutContainer();
    ListStore<MyBean> store = new ListStore<MyBean>(ACCESS.id());
    private Grid<MyBean> grid;

    public GridEditInlineComboBoxBlurBug() {
        store.setAutoCommit(true);
        store.add(new MyBean());
        store.add(new MyBean());

        // construct grid
        List<ColumnConfig<MyBean, ?>> cols = new ArrayList<ColumnConfig<MyBean, ?>>();
        RowNumberer rowNums = new RowNumberer(new IdentityValueProvider());
        cols.add(rowNums);

        ColumnConfig<MyBean, String> strCol = new ColumnConfig<MyBean, String>(ACCESS.str());
        strCol.setHeader("Str");
        cols.add(strCol);

        ColumnConfig<MyBean, Integer> intCol = new ColumnConfig<MyBean, Integer>(ACCESS.integer());
        intCol.setHeader("Integer");
        intCol.setCell(new AbstractCell<Integer>() {
            @Override
            public void render(Context context, Integer value, SafeHtmlBuilder sb) {
                String s = intColRender(value);
                if (s == null) s = "?";
                sb.appendEscaped(s);
            }
        });
        cols.add(intCol);

        ColumnConfig<MyBean, String> dummyCol = new ColumnConfig<MyBean, String>(new DummyValueProvider());
        dummyCol.setHeader("Dummy");
        cols.add(dummyCol);

        grid = new Grid<MyBean>(store, new ColumnModel<MyBean>(cols));
        rowNums.initPlugin(grid);
        setupEditing(strCol, intCol, dummyCol);

        String msg = "To reproduce the error:\n";
        msg += "- Select a value in the 'Integer' column\n";
        msg += "- Click another cell on the same row\n";
        msg += "=> The value does not get saved\n";
        msg += "Contrast this with pressing 'Tab' instead of clicking out of cell\n";

        vlc.add(new HTMLPanel(new SafeHtmlBuilder().appendEscapedLines(msg).toSafeHtml()));
        vlc.add(grid);
    }

    @Override
    public Widget asWidget() {
        return vlc;
    }

    private void setupEditing(ColumnConfig<MyBean, String> strCol, ColumnConfig<MyBean, Integer> intCol, ColumnConfig<MyBean, String> dummyCol) {
        GridInlineEditing<MyBean> edits = new GridInlineEditing<MyBean>(grid);
        SimpleComboBox<String> cboStr = new SimpleComboBox<String>(new StringLabelProvider<String>());
        for (String s : presets) cboStr.add(s);
        cboStr.setTriggerAction(ComboBoxCell.TriggerAction.ALL); // needed or else unusable
        cboStr.setForceSelection(false);
        edits.addEditor(strCol, cboStr);

        LabelProvider<Integer> lblrInt = new LabelProvider<Integer>() {
            @Override
            public String getLabel(Integer item) {
                return intColRender(item);
            }
        };
        SimpleComboBox<Integer> cboInt = new SimpleComboBox<Integer>(lblrInt);
        for (int i = 0; i < presets.size(); i++) cboInt.add(i);
        cboInt.setTriggerAction(ComboBoxCell.TriggerAction.ALL); // needed or else unusable
        cboInt.setForceSelection(false);
        cboInt.setPropertyEditor(new PropertyEditor<Integer>() {
            @Override
            public Integer parse(CharSequence text) throws ParseException {
                int ix = presets.indexOf(text.toString());
                if (ix >= 0) return ix;
                return null;
            }

            @Override
            public String render(Integer object) {
                if (object == null) return "";
                return presets.get(object);
            }
        });
        edits.addEditor(intCol, cboInt);
        edits.addEditor(dummyCol, new TextField());
    }

    private String intColRender(Integer item) {
        if (item == null || item >= presets.size()) return null;
        return presets.get(item);
    }

    @Override
    public void onModuleLoad() {
        Viewport v = new Viewport();
        v.setWidget(this);
        RootLayoutPanel.get().add(v);
    }

    public static class MyBean {
        private final String id = "#" + System.identityHashCode(this);
        private String str;
        private Integer integer;

        public String getId() {
            return id;
        }

        public Integer getInteger() {
            return integer;
        }

        public void setInteger(Integer integer) {
            this.integer = integer;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }

    static interface Access extends PropertyAccess<MyBean> {
        ModelKeyProvider<MyBean> id();

        ValueProvider<MyBean, String> str();

        ValueProvider<MyBean, Integer> integer();
    }

    private static class DummyValueProvider implements ValueProvider<MyBean, String> {
        @Override
        public String getValue(MyBean object) {
            return "";
        }

        @Override
        public void setValue(MyBean object, String value) {
            // nothing
        }

        @Override
        public String getPath() {
            return "";
        }
    }
}

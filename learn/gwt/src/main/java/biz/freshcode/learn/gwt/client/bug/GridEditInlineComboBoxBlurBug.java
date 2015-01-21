package biz.freshcode.learn.gwt.client.bug;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.*;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.*;
import com.sencha.gxt.widget.core.client.button.IconButton;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.*;
import com.sencha.gxt.widget.core.client.grid.Grid;
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
    private GridInlineEditing<MyBean> editing;

    public GridEditInlineComboBoxBlurBug() {
        store.setAutoCommit(true);
        store.add(new MyBean());
        store.add(new MyBean());

        // construct grid
        List<ColumnConfig<MyBean, ?>> cols = new ArrayList<ColumnConfig<MyBean, ?>>();
        RowNumberer<MyBean> rowNums = new RowNumberer<MyBean>(new IdentityValueProvider<MyBean>());
        cols.add(rowNums);
        // NOTE: Premptively 'completing' the edit when mouse-over the button doesn't help.
        rowNums.setWidget(toolButton(ToolButton.PLUS), SafeHtmlUtils.fromString("#"));
        /* No better...
        HTML htmlAdd = new HTML("+");
        htmlAdd.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                addNew();
            }
        });
        rowNums.setWidget(htmlAdd, SafeHtmlUtils.fromString("#"));
        */

        ColumnConfig<MyBean, String> strCol = new ColumnConfig<MyBean, String>(ACCESS.str());
        strCol.setHeader("Str");
        cols.add(strCol);

        ColumnConfig<MyBean, Integer> intCol = new ColumnConfig<MyBean, Integer>(ACCESS.integer());
        intCol.setHeader("Integer");
        // no help... intCol.setWidget(addRowButton("Integer"), SafeHtmlUtils.fromString("Integer"));
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
        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        GridView<MyBean> view = grid.getView();
        view.setColumnLines(true);
        view.setTrackMouseOver(false);
        view.setStripeRows(true);

        setupEditing(strCol, intCol, dummyCol);

        String msg = "To reproduce the error:\n";
        msg += "- Select a value from the combo in the 'Integer' column\n";
        msg += "- Click '+'  to add a new row\n";
        msg += "- Select a value from the combo in the 'Integer' column of the new row\n";
        msg += "=> The combo disappears at first attempt.  It might also still have the old value.\n";
        msg += "Contrast this with clicking 'Add Row'; using a button in a header has strange focus effects\n";
        vlc.add(new HTMLPanel(new SafeHtmlBuilder()
                .appendHtmlConstant("<p>")
                .appendEscapedLines(msg)
                .appendHtmlConstant("</p>")
                .toSafeHtml()
        ));

        vlc.add(addRowButton("Add Row"));
        vlc.add(new HTML("<p>External ToolButton is fine</p>"));
        vlc.add(toolButton(ToolButton.DOWN));
        vlc.add(grid);
    }

    private TextButton addRowButton(String text) {
        return new TextButton(text, new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                addNew();
            }
        });
    }

    private ToolButton toolButton(IconButton.IconConfig icon) {
        return new ToolButton(icon, new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                addNew();
            }
        });
    }

    private void addNew() {
//  Doesn't help... editing has already been cancelled at this point
//        editing.cancelEditing();
        MyBean bean = new MyBean();
        store.add(bean);
        grid.getSelectionModel().select(false, bean);
// Doesn't help but is handy to launch editing...
//        int ix = store.size() - 1;
//        editing.startEditing(new Grid.GridCell(ix, 2));

        /* Doesn't help...
        btnAddRow.focus();
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                MyBean bean = new MyBean();
                store.add(bean);
                grid.getSelectionModel().select(false, bean);
                grid.focus();
            }
        });
        */
    }

    @Override
    public Widget asWidget() {
        return vlc;
    }

    private void setupEditing(ColumnConfig<MyBean, String> strCol, ColumnConfig<MyBean, Integer> intCol, ColumnConfig<MyBean, String> dummyCol) {
        editing = new GridInlineEditing<MyBean>(grid);
        SimpleComboBox<String> cboStr = new SimpleComboBox<String>(new StringLabelProvider<String>());
        for (String s : presets) cboStr.add(s);
        cboStr.setTriggerAction(ComboBoxCell.TriggerAction.ALL); // needed or else unusable
        cboStr.setForceSelection(false);
        editing.addEditor(strCol, cboStr);

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
        editing.addEditor(intCol, cboInt);
        editing.addEditor(dummyCol, new TextField());
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

package biz.freshcode.learn.gwtp.client.bug;

import biz.freshcode.learn.gwtp.client.boot.Root;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;

import java.util.Arrays;
import java.util.List;

import static biz.freshcode.learn.gwtp.client.bug.ComboBoxDebrisBug.MyBeanAccess.MY_ACCESS;

public class ComboBoxDebrisBug extends Presenter<ComboBoxDebrisBug.View, ComboBoxDebrisBug.Proxy> {
    public static final String TOKEN = "comboBoxDebrisBug";
    @Inject
    private PlaceManager placeManager;

    @Inject
    public ComboBoxDebrisBug(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @Override
    protected void onReset() {
        super.onReset();
        // This seems to cause the problem.  It models a form retrieving an updated list of items from the server.
        getView().getIntStore().replaceAll(Arrays.asList(1, 2, 3, 4, 5));
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends ProxyPlace<ComboBoxDebrisBug> {
    }

    public static class View extends ViewImpl {
        private final ColumnConfig<MyBean, String> colId = new ColumnConfig<>(MY_ACCESS.idValue(), 10, "Id");
        private final ColumnConfig<MyBean, Integer> colInt = new ColumnConfig<>(MY_ACCESS.integer(), 10, "Integer");
        private final ColumnConfig<MyBean, String> colStr = new ColumnConfig<>(MY_ACCESS.string(), 10, "String");
        private final ListStore<Integer> intStore = new ListStore<>(new ModelKeyProvider<Integer>() {
            @Override
            public String getKey(Integer item) {
                return item.toString();
            }
        });

        @Inject
        public View() {
            Grid<MyBean> g = createGrid();

            GridInlineEditing<MyBean> edit = new GridInlineEditing<>(g);
            edit.addEditor(colInt, createCombo());
            edit.addEditor(colStr, new TextField());

            g.getStore().add(new MyBean());
            g.getStore().add(new MyBean());
            BorderLayoutContainer blc = new BorderLayoutContainer();
            blc.setCenterWidget(g);
            ContentPanel cp = new ContentPanel();
            cp.setHeadingHtml("An illustration of the ComboBox debris bug");
            cp.setWidget(new ScrollPanel(new HTML(new SafeHtmlBuilder()
                    .appendEscapedLines(
                            "\nSteps:" +
                                    "\n- Click in the 'Integer' column to invoke the combo box" +
                                    "\n- Press 'Tab' to conclude the edit" +
                                    "\n- Click the 'Home' button to navigate away from this page" +
                                    "\n- Click 'Combo Box Debris' to return to this page" +
                                    "\n.... the list portion of the combo is displayed at 0,0" +
                                    "\n\nClues:" +
                                    "\n- Only 'Tab' causes the bug, clicking the adjacent cell will NOT cause the bug" +
                                    "\n- Clicking anywhere on the page only hides the debris temporarily" +
                                    "\n-- Navigating back to this page will cause it to reappear" +
                                    "\n- To remove debris permanently click in the 'Integer' column and then click elsewhere")
                    .toSafeHtml())));
            cp.setResize(true);
            BorderLayoutContainer.BorderLayoutData layout = new BorderLayoutContainer.BorderLayoutData(220);
            layout.setMargins(new Margins(5, 5, 10, 5));
            blc.setNorthWidget(cp, layout);
            initWidget(blc);
        }

        private Grid<MyBean> createGrid() {
            ListStore<MyBean> store = new ListStore<>(MY_ACCESS.id());
            store.setAutoCommit(true);

            List<ColumnConfig<MyBean, ?>> cols = Arrays.<ColumnConfig<MyBean, ?>>asList(colId, colInt, colStr);
            Grid<MyBean> g = new Grid<>(store, new ColumnModel<>(cols));

            GridView<MyBean> v = g.getView();
            v.setForceFit(true);
            return g;
        }

        private ComboBox<Integer> createCombo() {
            ComboBox<Integer> combo = new ComboBox<>(intStore, new LabelProvider<Integer>() {
                @Override
                public String getLabel(Integer item) {
                    return "#" + item;
                }
            });
            combo.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
            return combo;
        }

        public ListStore<Integer> getIntStore() {
            return intStore;
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public static class MyBean {
        private static int nextId = 1;
        String id = "id-" + nextId++;
        Integer integer;

        String string;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getInteger() {
            return integer;
        }

        public void setInteger(Integer integer) {
            this.integer = integer;
        }

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }
    }

    public interface MyBeanAccess extends PropertyAccess<MyBean> {
        public static final MyBeanAccess MY_ACCESS = GWT.create(MyBeanAccess.class);

        ModelKeyProvider<MyBean> id();

        ValueProvider<MyBean, Integer> integer();

        @Editor.Path("id")
        ValueProvider<MyBean, String> idValue();

        ValueProvider<MyBean, String> string();
    }
}

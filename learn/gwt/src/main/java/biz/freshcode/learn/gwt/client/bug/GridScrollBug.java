package biz.freshcode.learn.gwt.client.bug;

import biz.freshcode.learn.gwt.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridScrollBug extends AbstractIsWidget {
    private Grid<String> grid;

    @Override
    protected Widget createWidget() {
        return new HorizontalLayoutContainerBuilder()
                .add(new TextButton("Launch", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        ColumnConfig col = new ColumnConfigBuilder<String, String>(new IdentityValueProvider<String>())
                                .header("Col")
                                .columnConfig;

                        ColumnModel<String> cols = new ColumnModel<String>(Arrays.<ColumnConfig<String, ?>>asList(
                                col
                        ));
                        ListStore<String> store = new ListStore<String>(new ModelKeyProvider<String>() {
                            @Override
                            public String getKey(String item) {
                                return item;
                            }
                        });

                        List<String> l = generateList(0, 100);
                        store.addAll(l);

                        grid = new Grid<String>(store, cols);

                        new DialogBuilder()
                                .widget(grid)
                                .resizable(true)
                                .predefinedButtons()
                                .addButton(new TextButton("Next", new SelectEvent.SelectHandler() {
                                    @Override
                                    public void onSelect(SelectEvent event) {
                                        onNext();
                                    }
                                }))
                                .dialog.show();
                    }
                }))
                .horizontalLayoutContainer;
    }

    private void onNext() {
        Info.display("On", "Next");
    }

    private List<String> generateList(int base, int size) {
        List<String> l = new ArrayList<String>(size);
        for (int i = 0; i < size; i++) {
            l.add("" + (base + i));
        }
        return l;
    }
}

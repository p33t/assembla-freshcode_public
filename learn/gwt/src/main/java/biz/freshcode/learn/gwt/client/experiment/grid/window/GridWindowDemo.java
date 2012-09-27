package biz.freshcode.learn.gwt.client.experiment.grid.window;

import biz.freshcode.learn.gwt.client.builder.gxt.WindowBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.grid.ColumnConfigBuilder;
import com.google.gwt.core.client.Scheduler;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.Arrays;

public class GridWindowDemo {
    public void launch() {
        ListStore<String> store = new ListStore<String>(new ModelKeyProvider<String>() {
            @Override
            public String getKey(String item) {
                return item;
            }
        });
        store.addAll(Arrays.asList("One",
                "Two",
                "Three",
                "Four",
                "Five"
        ));
        final Window window = new WindowBuilder()
                .widget(new BorderLayoutContainerBuilder()
                        .centerWidget(new Grid<String>(store, new ColumnModel<String>(Arrays.<ColumnConfig<String, ?>>asList(
                                new ColumnConfigBuilder<String, String>(new IdentityValueProvider<String>())
                                        .header("Header")
                                        .columnConfig
                        ))))
                        .height(300) // NOTE: Without this the windows needs to be resized before grid is visible.
                        .borderLayoutContainer
                )
                .window;
        window.show();
    }
}

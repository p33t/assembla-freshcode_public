package biz.freshcode.learn.gwt.client.bug;

import biz.freshcode.learn.gwt.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.HorizontalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ToStringValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridScrollBug extends AbstractIsWidget {
    private static final int LIST_SIZE = 100;
    private Dialog dialog;

    @Override
    protected Widget createWidget() {
        return new HorizontalLayoutContainerBuilder()
                .add(new TextButton("Launch (Solved)", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        if (dialog != null) dialog.hide();

                        ColumnConfig col = new ColumnConfigBuilder<Integer, String>(new ToStringValueProvider<Integer>())
                                .header("Col")
                                .columnConfig;

                        ColumnModel<Integer> cols = new ColumnModel<Integer>(Arrays.<ColumnConfig<Integer, ?>>asList(
                                col
                        ));
                        ListStore<Integer> store = new ListStore<Integer>(new ModelKeyProvider<Integer>() {
                            @Override
                            public String getKey(Integer item) {
                                return item.toString();
                            }
                        });

                        List<Integer> l = generateList(1);
                        store.addAll(l);

                        Grid<Integer> grid = new Grid<Integer>(store, cols);

                        dialog = new DialogBuilder()
                                .widget(new BorderLayoutContainerBuilder()
                                        .centerWidget(grid)
                                        .borderLayoutContainer)
                                .resizable(true)
                                .height(300)
                                .predefinedButtons()
                                .addButton(new TextButton("Next", new SelectEvent.SelectHandler() {
                                    @Override
                                    public void onSelect(SelectEvent event) {
                                        onNext();
                                    }
                                }))
                                .dialog;
                        dialog.show();
                    }
                }))
                .horizontalLayoutContainer;
    }

    private void onNext() {
        final Grid<Integer> grid = getGrid();
        ListStore<Integer> store = grid.getStore();
        Integer last = store.get(store.size() - 1);
        List<Integer> newList = generateList(last + 1);

        final int scrollTop = grid.getView().getScroller().getScrollTop();
        store.replaceAll(newList);

        // This scheduler triggers the scroll jitter issue //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< NOTE
        // Also, IE needs to have the 'preventScrollToTopAfterRefresh' flag set for GridView.
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                if (scrollTop != 0) {
                    grid.getView().getScroller().setScrollTop(scrollTop);
                }
            }
        });
    }

    private Grid<Integer> getGrid() {
        BorderLayoutContainer blc = (BorderLayoutContainer) dialog.getWidget();
        return (Grid<Integer>) blc.getCenterWidget();
    }

    private List<Integer> generateList(int base) {
        List<Integer> l = new ArrayList<Integer>(LIST_SIZE);
        for (int i = 0; i < LIST_SIZE; i++) {
            l.add(base + i);
        }
        return l;
    }
}

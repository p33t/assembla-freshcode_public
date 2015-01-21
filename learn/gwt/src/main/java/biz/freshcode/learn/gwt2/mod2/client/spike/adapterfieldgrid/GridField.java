package biz.freshcode.learn.gwt2.mod2.client.spike.adapterfieldgrid;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.grid.ColumnConfigBuilder;
import biz.freshcode.learn.gwt2.common.client.util.data.UnityAccess;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.AdapterField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.List;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt2.common.client.util.data.UnityAccess.unityAccess;

public class GridField extends AdapterField<List<String>> {
    private static final UnityAccess<String> access = unityAccess();
    private final Grid<String> grid;

    @Inject
    public GridField() {
        this(createGrid());
    }

    public GridField(Grid<String> grid) {
        super(grid);
        this.grid = grid;
    }

    private static Grid<String> createGrid() {
        List<ColumnConfig<String, ?>> cols = newList();
        cols.add(new ColumnConfigBuilder<>(access)
                .header("Header")
                .columnConfig);
        Grid<String> g = new Grid<>(new ListStore<>(access), new ColumnModel<>(cols));
        g.setBorders(true);
        g.getView().setForceFit(true);
        g.getStore().add("Bruce");
        return g;
    }

    @Override
    public void setValue(List<String> value) {
        grid.getStore().replaceAll(value);
    }

    @Override
    public List<String> getValue() {
        return grid.getStore().getAll();
    }
}

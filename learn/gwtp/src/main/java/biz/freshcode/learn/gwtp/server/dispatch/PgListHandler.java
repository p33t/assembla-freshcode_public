package biz.freshcode.learn.gwtp.server.dispatch;

import biz.freshcode.learn.gwtp.shared.paginggrid.PgListAction;
import biz.freshcode.learn.gwtp.shared.paginggrid.Tyre;
import biz.freshcode.learn.gwtp.shared.util.gwtp.dispatch.GdPagingLoadResult;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static biz.freshcode.learn.gwtp.shared.util.AppCollectionUtil.*;

public class PgListHandler implements ActionHandler<PgListAction, GdPagingLoadResult<Tyre>> {
    private static int counter = 0;
    private static final Map<String, Tyre> TYRES = newOrderedMap();

    static {
        for (Integer dia : newListFrom(700, 400, 500, 200, 600)) { // note: not sorted
            for (Integer thick : newListFrom(32, 21, 25, 26, 30)) { // note: not sorted
                tyre(dia, thick);
            }
        }
    }

    private static void tyre(int dia, int thick) {
        Tyre t = new Tyre();
        t.setId("id" + ++counter);
        t.setDiameter(dia);
        t.setThickness(thick);
        TYRES.put(t.getId(), t);
    }

    @Override
    public GdPagingLoadResult<Tyre> execute(PgListAction pgListAction, ExecutionContext executionContext) throws ActionException {
        PagingLoadConfig args = pgListAction.getArgs();
        List<? extends SortInfo> sort = args.getSortInfo();
        List<Tyre> list = newListFrom(TYRES.values());
        if (!sort.isEmpty()) {
            // need to sort
            // only sort once
            SortInfo sort0 = sort.get(0);
            Tyre.Comparator comp = new Tyre.Comparator(sort0.getSortField());
            Collections.sort(list, comp);
        }

        // select the desired page
        List<Tyre> page = newList();
        int ixFrom = 0;
        if (args.getOffset() < list.size()) {
            ixFrom = args.getOffset();
            int ixTo = Math.min(list.size(), args.getOffset() + args.getLimit());
            // NOTE: Sublists are not serializable.
            page = newListFrom(list.subList(ixFrom, ixTo));
        }

        GdPagingLoadResult<Tyre> result = new GdPagingLoadResult<Tyre>();
        result.setData(page);
        result.setTotalLength(TYRES.size());
        result.setOffset(ixFrom);
        return result;
    }

    @Override
    public Class<PgListAction> getActionType() {
        return PgListAction.class;
    }

    @Override
    public void undo(PgListAction pgListAction, GdPagingLoadResult<Tyre> tyreGdPagingLoadResult, ExecutionContext executionContext) throws ActionException {
        throw new RuntimeException("undo not implemented");
    }
}

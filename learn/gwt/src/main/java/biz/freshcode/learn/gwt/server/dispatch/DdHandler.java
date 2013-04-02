package biz.freshcode.learn.gwt.server.dispatch;

import biz.freshcode.learn.gwt.shared.dispatch.DdAction;
import biz.freshcode.learn.gwt.shared.dispatch.DdResult;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DdHandler implements ActionHandler<DdAction, DdResult> {
    @Override
    public DdResult execute(DdAction action, ExecutionContext context) throws ActionException {
        return new DdResult(action.getStr().toUpperCase());
    }

    @Override
    public Class<DdAction> getActionType() {
        return DdAction.class;
    }

    @Override
    public void undo(DdAction action, DdResult result, ExecutionContext context) throws ActionException {
        throw new ActionException("Cannot undo");
    }
}

package biz.freshcode.learn.gwt.server.dispatch;

import biz.freshcode.learn.gwt.shared.dispatch.DdAction;
import biz.freshcode.learn.gwt.shared.dispatch.StrResult;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DdHandler implements ActionHandler<DdAction, StrResult> {
    @Override
    public StrResult execute(DdAction action, ExecutionContext context) throws ActionException {
        return new StrResult(action.getStr().toUpperCase());
    }

    @Override
    public Class<DdAction> getActionType() {
        return DdAction.class;
    }

    @Override
    public void undo(DdAction action, StrResult result, ExecutionContext context) throws ActionException {
        throw new ActionException("Cannot undo");
    }
}

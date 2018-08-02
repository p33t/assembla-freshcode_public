package biz.freshcode.learn.gwt.server.dispatch;

import biz.freshcode.learn.gwt2.mod2.shared.StringResult;
import biz.freshcode.learn.gwt2.mod2.shared.spike.rpc.dispatch.DdAction;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DdHandler implements ActionHandler<DdAction, StringResult> {
    @Override
    public StringResult execute(DdAction action, ExecutionContext context) {
        return new StringResult(action.getStr().toUpperCase());
    }

    @Override
    public Class<DdAction> getActionType() {
        return DdAction.class;
    }

    @Override
    public void undo(DdAction action, StringResult result, ExecutionContext context) throws ActionException {
        throw new ActionException("Cannot undo");
    }
}

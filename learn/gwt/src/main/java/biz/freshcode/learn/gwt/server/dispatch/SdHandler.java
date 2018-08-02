package biz.freshcode.learn.gwt.server.dispatch;

import biz.freshcode.learn.gwt2.mod2.shared.StringResult;
import biz.freshcode.learn.gwt2.mod2.shared.spike.rpc.dispatch.SdAction;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Secure command.
 */
public class SdHandler implements ActionHandler<SdAction, StringResult> {
    @Override
    public StringResult execute(SdAction action, ExecutionContext context) {
        return new StringResult("[" + action.getStr() + "]");
    }

    @Override
    public Class<SdAction> getActionType() {
        return SdAction.class;
    }

    @Override
    public void undo(SdAction action, StringResult result, ExecutionContext context) throws ActionException {
        throw new ActionException("Cannot undo");
    }
}

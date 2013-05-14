package biz.freshcode.learn.gwtp.server.dispatch;

import biz.freshcode.learn.gwtp.shared.dispatch.SdAction;
import biz.freshcode.learn.gwtp.shared.dispatch.StrResult;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Secure command.
 */
public class SdHandler implements ActionHandler<SdAction, StrResult> {
    @Override
    public StrResult execute(SdAction action, ExecutionContext context) throws ActionException {
        return new StrResult("[" + action.getStr() + "]");
    }

    @Override
    public Class<SdAction> getActionType() {
        return SdAction.class;
    }

    @Override
    public void undo(SdAction action, StrResult result, ExecutionContext context) throws ActionException {
        throw new ActionException("Cannot undo");
    }
}

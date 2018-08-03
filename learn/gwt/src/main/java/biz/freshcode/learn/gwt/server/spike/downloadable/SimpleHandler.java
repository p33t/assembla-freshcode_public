package biz.freshcode.learn.gwt.server.spike.downloadable;

import biz.freshcode.learn.gwt2.mod2.shared.StringResult;
import biz.freshcode.learn.gwt2.mod2.shared.downloadable.SimpleAction;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.AbstractActionHandler;

import java.util.Date;

public class SimpleHandler extends AbstractActionHandler<SimpleAction, StringResult> {
    public SimpleHandler() {
        super(SimpleAction.class);
    }

    @Override
    public StringResult execute(SimpleAction action, ExecutionContext context) {
        return new StringResult("Yay, " + new Date());
    }

    @Override
    public void undo(SimpleAction action, StringResult result, ExecutionContext context) {
        throw new RuntimeException("undo not implemented");
    }
}

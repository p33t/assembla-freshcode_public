package biz.freshcode.learn.gwt.server.dispatch;

import com.gwtplatform.dispatch.server.AbstractDispatchServiceImpl;
import com.gwtplatform.dispatch.server.Dispatch;
import com.gwtplatform.dispatch.server.RequestProvider;

import java.util.logging.Logger;

public class AppDispatchService extends AbstractDispatchServiceImpl {
    protected AppDispatchService(Logger logger, Dispatch dispatch, RequestProvider requestProvider) {
        super(logger, dispatch, requestProvider);
    }
}

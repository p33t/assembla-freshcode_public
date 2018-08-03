package biz.freshcode.learn.gwt2.mod2.client.spike.downloadable;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.rpc.shared.Result;
import com.sencha.gxt.widget.core.client.info.Info;

public abstract class TestAsyncCallback<R extends Result> implements AsyncCallback<R> {
    @Override
    public void onFailure(Throwable caught) {
        Info.display("Failure", caught.getMessage());
    }
}

package biz.freshcode.learn.gwt2.mod2.client.spike.rpc.dispatch;

import biz.freshcode.learn.gwt2.mod2.shared.StringResult;
import biz.freshcode.learn.gwt2.mod2.shared.spike.rpc.dispatch.SdAction;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class SecureDispatchDemo extends DispatchDemo {
    @Override
    protected void run() {
        dispatch.execute(new SdAction("Lee"), new AsyncCallback<StringResult>() {
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("No can do: " + caught);
            }

            @Override
            public void onSuccess(StringResult result) {
                displayResult("<p>Sent 'Lee'<br/>Result '" + result.getResult() + "'</p>");
            }
        });
    }
}

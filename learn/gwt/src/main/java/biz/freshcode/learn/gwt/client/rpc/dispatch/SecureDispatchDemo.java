package biz.freshcode.learn.gwt.client.rpc.dispatch;

import biz.freshcode.learn.gwt.mod1.shared.dispatch.SdAction;
import biz.freshcode.learn.gwt.mod1.shared.dispatch.StrResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class SecureDispatchDemo extends DispatchDemo {
    @Override
    protected void run() {
        dispatch.execute(new SdAction("Lee"), new AsyncCallback<StrResult>() {
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("No can do: " + caught);
            }

            @Override
            public void onSuccess(StrResult result) {
                displayResult("<p>Sent 'Lee'<br/>Result '" + result.getStr() + "'</p>");
            }
        });
    }
}

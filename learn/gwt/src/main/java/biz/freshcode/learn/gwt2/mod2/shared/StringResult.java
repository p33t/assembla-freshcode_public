package biz.freshcode.learn.gwt2.mod2.shared;

import com.gwtplatform.dispatch.rpc.shared.Result;

public class StringResult implements Result {
    private String result;

    public StringResult(String result) {
        this.result = result;
    }

    protected StringResult() {
    }

    public String getResult() {
        return result;
    }
}

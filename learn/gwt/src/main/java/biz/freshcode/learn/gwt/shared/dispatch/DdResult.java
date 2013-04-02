package biz.freshcode.learn.gwt.shared.dispatch;

import com.gwtplatform.dispatch.shared.Result;

public class DdResult implements Result {
    private String str;

    // for ser'n
    private DdResult() {
        // nothing
    }

    public DdResult(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}

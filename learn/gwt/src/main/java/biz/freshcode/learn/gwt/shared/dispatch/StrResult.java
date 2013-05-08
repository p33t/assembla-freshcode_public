package biz.freshcode.learn.gwt.shared.dispatch;

import com.gwtplatform.dispatch.shared.Result;

public class StrResult implements Result {
    private String str;

    // for ser'n
    @SuppressWarnings("UnusedDeclaration")
    private StrResult() {
        // nothing
    }

    public StrResult(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}

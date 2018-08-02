package biz.freshcode.learn.gwt2.mod2.shared.spike.rpc.dispatch;

import biz.freshcode.learn.gwt2.mod2.shared.StringResult;
import com.gwtplatform.dispatch.rpc.shared.ActionImpl;

public class SdAction extends ActionImpl<StringResult> {
    private String str;

    // for ser'n
    @SuppressWarnings("UnusedDeclaration")
    private SdAction() {
        // nothing
    }

    public SdAction(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}

package biz.freshcode.learn.gwt2.mod2.shared.spike.rpc.dispatch;

import biz.freshcode.learn.gwt2.mod2.shared.StringResult;
import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

public class DdAction extends UnsecuredActionImpl<StringResult> {
    private String str;

    // for ser'n
    @SuppressWarnings("UnusedDeclaration")
    private DdAction() {
        // nothing
    }

    public DdAction(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}

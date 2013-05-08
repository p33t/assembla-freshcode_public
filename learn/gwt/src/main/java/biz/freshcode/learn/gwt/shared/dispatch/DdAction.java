package biz.freshcode.learn.gwt.shared.dispatch;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class DdAction extends UnsecuredActionImpl<StrResult> {
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

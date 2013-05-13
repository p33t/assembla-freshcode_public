package biz.freshcode.learn.gwtp.shared.dispatch;

import com.gwtplatform.dispatch.shared.ActionImpl;

public class SdAction extends ActionImpl<StrResult> {
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

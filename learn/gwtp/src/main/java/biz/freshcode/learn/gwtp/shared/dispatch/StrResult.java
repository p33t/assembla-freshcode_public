package biz.freshcode.learn.gwtp.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.Result;

/**
 * NOTE: It might seem like we need generic wrappers to weave in 'Result'
 * but that would cause pretty bad Serial'n policy bloat.
 * Also, SimpleResult is not useful because it cannot be Serialized and hopefully will be deprecated.
 */
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

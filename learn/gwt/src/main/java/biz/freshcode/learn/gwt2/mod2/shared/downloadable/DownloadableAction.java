package biz.freshcode.learn.gwt2.mod2.shared.downloadable;

import biz.freshcode.learn.gwt2.mod2.shared.StringResult;
import com.gwtplatform.dispatch.rpc.shared.ActionImpl;

public class DownloadableAction extends ActionImpl<StringResult> {
    private boolean downloadFlag;

    // For serialization
    protected DownloadableAction() {
    }

    public DownloadableAction(boolean downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    /**
     * Returns 'true' when a download of the result is desired (not an RPC response).
     */
    public boolean isDownloadFlag() {
        return downloadFlag;
    }
}

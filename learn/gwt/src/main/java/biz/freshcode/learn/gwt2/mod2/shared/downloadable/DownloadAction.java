package biz.freshcode.learn.gwt2.mod2.shared.downloadable;

import com.gwtplatform.dispatch.rpc.shared.Action;
import com.gwtplatform.dispatch.rpc.shared.ActionImpl;

public class DownloadAction extends ActionImpl<DownloadResult> {
    private Action<?> delegate;
    private String lineSep;

    // For serialization
    protected DownloadAction() {

    }

    public DownloadAction(Action<?> delegate, String lineSep) {
        this.delegate = delegate;
        this.lineSep = lineSep;
    }

    public Action<?> getDelegate() {
        return delegate;
    }

    public String getLineSep() {
        return lineSep;
    }
}

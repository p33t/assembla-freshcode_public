package biz.freshcode.learn.gwt2.mod2.shared.downloadable;

import com.gwtplatform.dispatch.shared.ActionException;

public class ResultDownloadedException extends ActionException {
    public ResultDownloadedException() {
        super("Result has been downloaded to user");
    }
}

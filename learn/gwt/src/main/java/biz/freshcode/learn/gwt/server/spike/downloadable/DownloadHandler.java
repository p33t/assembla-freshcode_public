package biz.freshcode.learn.gwt.server.spike.downloadable;

import biz.freshcode.learn.gwt2.mod2.shared.downloadable.DownloadAction;
import biz.freshcode.learn.gwt2.mod2.shared.downloadable.DownloadResult;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.AbstractActionHandler;

import java.util.Iterator;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newListFrom;

public class DownloadHandler extends AbstractActionHandler<DownloadAction, DownloadResult> {
    public DownloadHandler() {
        super(DownloadAction.class);
    }

    @Override
    public DownloadResult execute(DownloadAction action, ExecutionContext context) {
        Iterator<String> lines = newListFrom("hello", "world", "sheesh").iterator();
        return new DownloadResult("hello-world.txt", action.getLineSep(), lines);
    }

    @Override
    public void undo(DownloadAction action, DownloadResult result, ExecutionContext context) {
        throw new RuntimeException("undo not implemented");
    }
}

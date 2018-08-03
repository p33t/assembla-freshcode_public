package biz.freshcode.learn.gwt2.mod2.shared.downloadable;

import com.gwtplatform.dispatch.rpc.shared.Result;

import java.util.Collections;
import java.util.Iterator;

public class DownloadResult implements Result {
    private String fileName;
    private String lineSeparator;
    private transient Iterator<String> lines = Collections.emptyIterator();

    // for serialization
    protected DownloadResult() {
    }

    public DownloadResult(String fileName, String lineSeparator, Iterator<String> lines) {
        this.fileName = fileName;
        this.lineSeparator = lineSeparator;
        this.lines = lines;
    }

    public String getFileName() {
        return fileName;
    }

    public String getLineSeparator() {
        return lineSeparator;
    }

    public Iterator<String> getLines() {
        return lines;
    }
}

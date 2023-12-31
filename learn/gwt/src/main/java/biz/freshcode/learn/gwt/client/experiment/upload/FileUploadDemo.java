package biz.freshcode.learn.gwt.client.experiment.upload;

import biz.freshcode.learn.gwt.client.experiment.upload.reuse.FileReadButton;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.util.IsWidgetImpl;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.widget.core.client.button.TextButton;

import static biz.freshcode.learn.gwt2.common.client.util.ClientUtil.log;

public class FileUploadDemo extends IsWidgetImpl {
    private final HTML html;

    public FileUploadDemo() {
        TextButton btn;
        FileReadButton fileRead;
        initWidget(new FlowLayoutContainerBuilder()
                .add(fileRead = new FileReadButton("Open...", ".txt"))
                .add(html = new HTML())
                .flowLayoutContainer);

        fileRead.setCallback(new FileReadButton.Callback() {
            @Override
            public void fileSelect(String fileName, String contents) {
                log("File name: " + fileName);
                fileContents(contents);
            }
        });
    }

    public void fileContents(String s) {
        html.setHTML(new SafeHtmlBuilder()
                .appendEscapedLines(s)
                .toSafeHtml());
    }
}

package biz.freshcode.learn.gwt.client.experiment.upload;

import biz.freshcode.learn.gwt.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.IsWidgetImpl;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HTML;

public class FileUploadDemo extends IsWidgetImpl {
    private final FileUpload filer;
    private final HTML html;

    public FileUploadDemo() {
        initWidget(new FlowLayoutContainerBuilder()
                .add(filer = new FileUpload())
                .add(html = new HTML())
                .flowLayoutContainer);

        Element e = filer.getElement();
        // NOTE: This only puts an initial filter on the file select dialog.  It can be overridden by user.
        e.setAttribute("accept", ".txt");
        observeImageData(this, e);
    }

    public void fileContents(String s) {
        html.setHTML(new SafeHtmlBuilder()
                .appendEscapedLines(s)
                .toSafeHtml());
    }

    public static native void observeImageData(FileUploadDemo demo, Element e) /*-{
        function handleFileSelect(evt) {
            var files = evt.target.files;
            var output = [];
            for (var i = 0, f; f = files[i]; i++) {
                //var name = f.name;
                //var type = f.type;
                //var size = f.size;
                var reader = new FileReader();
                reader.onload = function (evt) {
                    demo.@biz.freshcode.learn.gwt.client.experiment.upload.FileUploadDemo::fileContents(Ljava/lang/String;)(evt.target.result);
                };
                reader.readAsText(files[i]);
            }
        }

        e.addEventListener('change', handleFileSelect, false);
    }-*/;
}

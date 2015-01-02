package biz.freshcode.learn.gwt.client.experiment.upload;

import biz.freshcode.learn.gwt.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.IsWidgetImpl;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class FileUploadDemo extends IsWidgetImpl {
    private final FileUpload filer;
    private final HTML html;

    public FileUploadDemo() {
        TextButton btn;
        initWidget(new FlowLayoutContainerBuilder()
                // NOTE: IE requires this to be added despite being hidden later.
                .add(filer = new FileUpload())
                .add(btn = new TextButton("Alt Browse..."))
                .add(html = new HTML())
                .flowLayoutContainer);

        Element e = filer.getElement();
        // NOTE: This only puts an initial filter on the file select dialog.  It can be overridden by user.
        e.setAttribute("accept", ".txt");
        observeFileSelect(this, e);

        filer.setVisible(false);
        btn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                filer.click();
            }
        });
    }

    public void fileContents(String s) {
        html.setHTML(new SafeHtmlBuilder()
                .appendEscapedLines(s)
                .toSafeHtml());
    }

    public static native void observeFileSelect(FileUploadDemo demo, Element e) /*-{
        function handleOnLoad(evt) {
            demo.@biz.freshcode.learn.gwt.client.experiment.upload.FileUploadDemo::fileContents(Ljava/lang/String;)(evt.target.result);
        }

        function handleFileSelect(evt) {
            var files = evt.target.files;
            for (var i = 0, f; f = files[i]; i++) {
                var reader = new FileReader();
                reader.onload = handleOnLoad;
                reader.readAsText(files[i]); // NOTE: Encoding can be supplied here.
            }
        }

        e.addEventListener('change', handleFileSelect, false);
    }-*/;
}

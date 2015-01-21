package biz.freshcode.learn.gwt.client.experiment.upload.reuse;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.util.ClientUtil;
import biz.freshcode.learn.gwt2.common.client.util.IsWidgetImpl;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.FileUpload;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import static biz.freshcode.learn.gwt2.common.client.util.ExceptionUtil.illegalState;

/**
 * Loads a file into memory on the client side (NOTE: Requires FileReader >= IE10).
 */
public class FileReadButton extends IsWidgetImpl {
    private Callback callback;
    private FileUpload filer;

    public FileReadButton(String buttonText) {
        this(buttonText, null);
    }

    public FileReadButton(String buttonText, String acceptOrNull) {
        TextButton btn;
        initWidget(new FlowLayoutContainerBuilder()
                // NOTE: IE needs this to be added to container.
                .add(filer = new FileUpload())
                .add(btn = new TextButton(buttonText))
                .flowLayoutContainer);

        Element e = filer.getElement();
        // NOTE: This only puts an initial filter on the file select dialog.  It can be overridden by user.
        if (acceptOrNull != null) e.setAttribute("accept", acceptOrNull);
        observeFileSelect(this, e);

        filer.setVisible(false);
        btn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                ClientUtil.log("Trace btn clicked.");
                filer.click();
            }
        });
    }

    public void fileContents(String contents) {
        if (callback == null) throw illegalState("Callback has not be initialised.");
        ClientUtil.log("Trace fileContents()");
        callback.fileSelect(filer.getFilename(), contents);
    }

    public static native void observeFileSelect(FileReadButton host, Element e) /*-{
        function handleOnLoad(evt) {
            host.@biz.freshcode.learn.gwt.client.experiment.upload.reuse.FileReadButton::fileContents(Ljava/lang/String;)(evt.target.result);
        }

        function handleFileSelect(evt) {
            var files = evt.target.files;
            if (files) {
                var reader = new FileReader();
                reader.onload = handleOnLoad;
                reader.readAsText(files[0], "UTF-8"); // NOTE: Encoding can be supplied here.
            }
        }

        e.addEventListener('change', handleFileSelect, false);
    }-*/;

    public interface Callback {
        void fileSelect(String fileName, String contents);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}

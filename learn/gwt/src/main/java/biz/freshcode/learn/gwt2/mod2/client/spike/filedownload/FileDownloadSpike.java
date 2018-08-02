package biz.freshcode.learn.gwt2.mod2.client.spike.filedownload;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;

/**
 * Trying to simulate a file download in javascript.
 */
public class FileDownloadSpike extends Presenter<FileDownloadSpike.View, FileDownloadSpike.Proxy> {
    public static final String TOKEN = "fileDownload";

    @Inject
    public FileDownloadSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
        view.download.addSelectHandler(evt -> {

            byte[] bytes;
            // this results in a 14 byte text file
            bytes = new byte[]{10,9,8,7,6,5,4,3,2,1,0,99};

            // this results in a 30 byte text file
            // bytes = "Hello World".getBytes(StandardCharsets.UTF_8);
            download(bytes);
        });
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<FileDownloadSpike> {
    }

    public static class View extends ViewImpl {
        private final TextButton download;

        @Inject
        public View() {
            initWidget(new FlowLayoutContainerBuilder()
                    .add(new HtmlLayoutContainer(SafeHtmlUtils.fromString("Click button to perform simulated download")))
                    .add(download = new TextButton("Download"))
                    .flowLayoutContainer);
        }
    }

    @SuppressWarnings({"JSUnusedLocalSymbols", "JSValidateTypes"})
    public static native void download(byte[] bytes) /*-{
        var blob = new Blob(bytes, {type: 'application/octet-stream'});
        var url = window.URL.createObjectURL(blob);

        var a = document.createElement("a");
        a.style = "display: none";
        document.body.appendChild(a);
        a.href = url;
        a.download = 'hello-world-x.bin';

        a.click();

        //release the reference to the file by revoking the Object URL
        window.URL.revokeObjectURL(url);
        document.body.removeChild(a);
    }-*/;
}

package biz.freshcode.learn.gwt2.mod2.client.spike.filedownload;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;

/**
 * Trying to simulate a file download in javascript.
 */
public class FileDownloadSpike extends Presenter<View, FileDownloadSpike.Proxy> {
    public static final String TOKEN = "fileDownload";

    @Inject
    public FileDownloadSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);

        view.download.addSelectHandler(evt -> download());
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

    public static native void download() /*-{
        var blob = new Blob(["Hello World"], {type: 'text/plain'});

        var a = document.createElement("a");
        a.style = "display: none";
        document.body.appendChild(a);
        //Create a DOMString representing the blob
        //and point the link element towards it
        var url = window.URL.createObjectURL(blob);
        a.href = url;
        a.download = 'hello-world.txt';
        //programatically click the link to trigger the download
        a.click();
        //release the reference to the file by revoking the Object URL
        window.URL.revokeObjectURL(url);

    }-*/;
}

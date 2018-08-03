package biz.freshcode.learn.gwt2.mod2.client.spike.downloadable;

import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import biz.freshcode.learn.gwt2.mod2.client.util.ClientUtil;
import biz.freshcode.learn.gwt2.mod2.shared.StringResult;
import biz.freshcode.learn.gwt2.mod2.shared.downloadable.DownloadAction;
import biz.freshcode.learn.gwt2.mod2.shared.downloadable.DownloadResult;
import biz.freshcode.learn.gwt2.mod2.shared.downloadable.SimpleAction;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * Trying to simulate a file download in javascript.
 */
public class DownloadableResultSpike extends Presenter<DownloadableResultSpike.View, DownloadableResultSpike.Proxy> {
    public static final String TOKEN = "downloadableResult";
    @Inject
    private DispatchAsync dispatch;

    @Inject
    public DownloadableResultSpike(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
        view.call.addSelectHandler(evt -> dispatch.execute(new SimpleAction(), new TestAsyncCallback<StringResult>() {
            @Override
            public void onSuccess(StringResult result) {
                Info.display("Success", result.getResult());
            }
        }));

        view.download.addSelectHandler(evt -> {
            DownloadAction action = new DownloadAction(new SimpleAction(), ClientUtil.clientLineSeparator());
            dispatch.execute(action, new TestAsyncCallback<DownloadResult>() {
                @Override
                public void onSuccess(DownloadResult result) {
                    Info.display("Response", "Should not have got a response");
                }
            });
        });
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<DownloadableResultSpike> {
    }

    public static class View extends ViewImpl {
        private final TextButton download;
        private final TextButton call;

        @Inject
        public View() {
            initWidget(new FlowLayoutContainerBuilder()
                    .add(new HtmlLayoutContainer(SafeHtmlUtils.fromString("One will simply call, the other will (fail to) download")))
                    .add(call = new TextButton("Call"))
                    .add(download = new TextButton("Download"))
                    .flowLayoutContainer);
        }
    }
}

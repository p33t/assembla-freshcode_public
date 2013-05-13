package biz.freshcode.learn.gwtp.client.home;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;

public class Home extends Presenter<Home.View, Home.Proxy> {
    public static final String TOKEN = "home";

    @Inject
    public Home(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RevealType.Root);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<Home> {
    }

    public static interface View extends com.gwtplatform.mvp.client.View {
    }
}

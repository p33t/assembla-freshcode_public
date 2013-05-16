package biz.freshcode.learn.gwtp.client.compound;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;

public class Child2 extends Presenter<Child2.View,Child2.Proxy> {
    public static final String TOKEN = "compoundChild2";

    @Inject
    public Child2(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Compound.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<Child2> {
    }

    public interface View extends com.gwtplatform.mvp.client.View {
    }
}

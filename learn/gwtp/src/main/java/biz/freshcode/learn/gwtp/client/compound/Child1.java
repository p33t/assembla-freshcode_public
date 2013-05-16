package biz.freshcode.learn.gwtp.client.compound;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;

public class Child1 extends Presenter<Child1.View,Child1.Proxy> implements Titled {
    public static final String TOKEN = "compoundChild1";

    @Inject
    public Child1(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Compound.SLOT);
    }

    @Override
    public String getTitle() {
        return "Child One";
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<Child1> {
    }

    public interface View extends com.gwtplatform.mvp.client.View {
    }
}

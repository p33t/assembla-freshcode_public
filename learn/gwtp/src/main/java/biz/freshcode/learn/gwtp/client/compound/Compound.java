package biz.freshcode.learn.gwtp.client.compound;

import biz.freshcode.learn.gwtp.client.boot.Root;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;

public class Compound extends Presenter<Compound.View, Compound.Proxy> {
    public static final String TOKEN = "compound";

//    @ContentSlot
//    public static final GwtEvent.Type<RevealContentHandler<?>> SLOT = new GwtEvent.Type<RevealContentHandler<?>>();

    @Inject
    public Compound(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<Compound> {
    }

    public interface View extends com.gwtplatform.mvp.client.View {
    }
}

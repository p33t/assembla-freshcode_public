package biz.freshcode.learn.gwtp.client.parent;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

import static com.google.gwt.event.shared.GwtEvent.Type;

public class Parent extends Presenter<Parent.View, Parent.Proxy> {
    // View needs to override setInSlot() and respond when given this constant as an arg.
    @ContentSlot
    public static final Type<RevealContentHandler<?>> SLOT = new Type<RevealContentHandler<?>>();

    @Inject
    public Parent(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RevealType.Root);
    }

    // NOTE: Not a place!
    @ProxyStandard
    public static interface Proxy extends com.gwtplatform.mvp.client.proxy.Proxy<Parent> {
    }

    public static interface View extends com.gwtplatform.mvp.client.View {
    }
}

package biz.freshcode.learn.gwt.mod1.client.experiment.mvp.gwtp;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

/**
 * Attempted demo of GWTP MVP.  But couldn't get working.  See adjacent project learn-gwtp OR
 * Arcbees archetypes: https://github.com/ArcBees/ArcBees-tools/tree/master/archetypes
 */
public class GwtpMvpDemo extends Presenter<GwtpMvpDemo.View, GwtpMvpDemo.Proxy> {
    @Inject
    public GwtpMvpDemo(EventBus eventBus, View view, Proxy proxy, PlaceManager placeManager) {
        super(eventBus, view, proxy, RevealType.Root); // hopefully root will wipe away any non-gwtp views.
    }

    @ProxyStandard
    @NameToken(GmdModule.GMD)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<GwtpMvpDemo> {
    }

    public interface View extends com.gwtplatform.mvp.client.View {
        void appendHtml(String html);
    }

    @Override
    protected void onBind() {
        super.onBind();
        getView().appendHtml("<p>Bind</p>");
    }

    @Override
    protected void onReveal() {
        super.onReveal();
        getView().appendHtml("<p>Reveal</p>");
    }

    @Override
    protected void onReset() {
        super.onReset();
        getView().appendHtml("<p>Reset</p>");
    }
}

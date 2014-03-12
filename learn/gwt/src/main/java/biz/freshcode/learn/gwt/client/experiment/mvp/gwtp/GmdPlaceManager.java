package biz.freshcode.learn.gwt.client.experiment.mvp.gwtp;

import com.google.gwt.core.shared.GWT;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.shared.proxy.TokenFormatter;

public class GmdPlaceManager extends PlaceManagerImpl {

    @Inject
    public GmdPlaceManager(EventBus eventBus, TokenFormatter tokenFormatter) {
        super(eventBus, tokenFormatter);
    }

    @Override
    public void revealDefaultPlace() {
        // do nothing
        // don't want to interfere
        GWT.log("Reveal default place !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
// Infinite loop error        doRevealPlace(new PlaceRequest(GmdModule.GMD), false);
    }
}

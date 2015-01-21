package biz.freshcode.learn.gwt.client;

import biz.freshcode.learn.gwt.client.experiment.mvp.gwtmvp.GmPlace;
import biz.freshcode.learn.gwt.client.experiment.mvp.gwtmvp.GwtMvp;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * Detects specific Place instances and creates the corresponding Activity.
 */
@Singleton
public class AppActivityMapper implements ActivityMapper {
    public static final Place DEFAULT_PLACE = new Place() {
    };

    @Inject
    GwtMvp.Factory gmFactory;

    @Inject
    Provider<MainActivity> maProvider;

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof GmPlace) {
            return gmFactory.create((GmPlace) place);
//            Non-constructor alternative.  It has less code but is not elegant.
//            return gmProvider.get().startAt((GmPlace) place);
        }
        if (DEFAULT_PLACE.equals(place)) {
            return maProvider.get();
        }
        return null;
    }
}

package biz.freshcode.learn.gwt.client;

import biz.freshcode.learn.gwt.client.experiment.mvp.gwtmvp.GmPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * Declare all the Place tokenizers.
 */
@WithTokenizers(GmPlace.Tokenizer.class)
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}

package biz.freshcode.learn.gwt.client.experiment.mvp.gwtmvp;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class GmPlace extends Place {
    private final int num;

    public GmPlace(int num) {
        this.num = num;
    }


    public int getNum() {
        return num;
    }

    public static class Tokenizer implements PlaceTokenizer<GmPlace> {
        @Override
        public GmPlace getPlace(String token) {
            int num = Integer.parseInt(token);
            return new GmPlace(num);
        }

        @Override
        public String getToken(GmPlace place) {
            return Integer.toString(place.getNum());
        }
    }
}

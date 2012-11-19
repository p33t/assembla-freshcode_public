package biz.freshcode.learn.gwt.client.experiment.forms2;

import com.sencha.gxt.data.shared.Converter;

public class LowerConverter implements Converter<String, String> {
    @Override
    public String convertFieldValue(String fieldVal) {
        if (fieldVal == null) return null;
        return fieldVal.toLowerCase();
    }

    @Override
    public String convertModelValue(String modelVal) {
        if (modelVal == null) return null;
        return modelVal.toUpperCase();
    }
}

package biz.freshcode.learn.gwt.client.experiment.forms2;

import com.sencha.gxt.data.shared.Converter;

public class LowerConverter implements Converter<Object, String> {
    @Override
    public Object convertFieldValue(String fieldVal) {
        if (fieldVal == null) return null;
        return fieldVal.toLowerCase();
    }

    @Override
    public String convertModelValue(Object modelVal) {
        if (modelVal == null) return null;
        return ((String) modelVal).toUpperCase();
    }
}

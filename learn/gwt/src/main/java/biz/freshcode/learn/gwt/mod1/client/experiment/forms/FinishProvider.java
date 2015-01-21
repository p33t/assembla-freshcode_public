package biz.freshcode.learn.gwt.mod1.client.experiment.forms;

import com.sencha.gxt.core.client.ValueProvider;

public class FinishProvider<T extends StartDuration> implements ValueProvider<T, String> {
    @Override
    public String getValue(T sd) {
        if (sd.getStart() != null && sd.getDuration() != null) {
            long finish = sd.getStart() + sd.getDuration();
            return HrMinConverter.INSTANCE.convertModelValue(finish);
        } else return "-";
    }

    @Override
    public void setValue(T object, String value) {
        // nothing
    }

    @Override
    public String getPath() {
        return "";
    }
}

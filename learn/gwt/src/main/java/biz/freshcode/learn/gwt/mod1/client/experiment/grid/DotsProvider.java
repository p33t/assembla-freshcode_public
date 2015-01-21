package biz.freshcode.learn.gwt.mod1.client.experiment.grid;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;

/**
 * Displays a string of '.' the same length as the given RowEntity's id.
 */
class DotsProvider implements ValueProvider<RowEntity, String> {
    @Override
    public String getValue(RowEntity object) {
        String s = "";
        for (int i = 0; i < object.id; i++) s += ".";
        return s;
    }

    @Override
    public void setValue(RowEntity object, String value) {
        GWT.log("Ignoring 'setValue' on custom value provider.");
    }

    @Override
    public String getPath() {
        return null;
    }
}

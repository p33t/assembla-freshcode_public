package biz.freshcode.learn.gwt.mod1.client.experiment.forms2;

import com.sencha.gxt.widget.core.client.form.PropertyEditor;

import java.text.ParseException;

class LowerPropertyEditor extends PropertyEditor<String> {
    public static final LowerPropertyEditor INSTANCE = new LowerPropertyEditor();

    @Override
    public String parse(CharSequence text) throws ParseException {
        String parsed = text.toString().toLowerCase();
        if (parsed.equals("error")) throw new ParseException("Error value", 0);
        if (parsed.isEmpty()) return null;
        return parsed;
    }

    @Override
    public String render(String object) {
        if (object == null) return "";
        return object.toUpperCase(); // THIS ISN'T BEING CALLED!
    }
}

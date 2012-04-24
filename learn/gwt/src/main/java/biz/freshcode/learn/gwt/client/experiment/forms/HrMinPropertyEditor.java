package biz.freshcode.learn.gwt.client.experiment.forms;

import com.sencha.gxt.widget.core.client.form.PropertyEditor;

import java.text.ParseException;

/**
 * Converts string to / from a long.
 * @see HrMinConverter
 */
public class HrMinPropertyEditor extends PropertyEditor<Long> {
    public static final HrMinPropertyEditor INSTANCE = new HrMinPropertyEditor();

    @Override
    public Long parse(CharSequence text) throws ParseException {
        if (text == null) return null;
        String str = text.toString();
        if (str.trim().isEmpty()) return null;
        if (!HrMinConverter.RE.test(str)) throw new ParseException(HrMinConverter.RE_MSG, 0);
        return HrMinConverter.INSTANCE.convertFieldValue(str);
    }

    @Override
    public String render(Long time) {
        return HrMinConverter.INSTANCE.convertModelValue(time);
    }
}

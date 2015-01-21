package biz.freshcode.learn.gwt.client.experiment.forms;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.widget.core.client.form.validator.RegExValidator;

/**
 * Converts between an h:mm string and a long millisecond value.
 * Negatives are permitted.
 * Hour values > 23 are permitted.
 * The ':mm' portion is optional.
 */
public class HrMinConverter implements Converter<Long, String> {
    private static final String RE_STR = "^(-?)(\\d+)(:([0-5]?)(\\d))?$";
    public static final RegExp RE = RegExp.compile(RE_STR);
    public static final String RE_MSG = "Must be in the form '-h:m', '-' and ':m' are optional";
    public static final HrMinConverter INSTANCE = new HrMinConverter();
    public static final RegExValidator VALIDATOR = new RegExValidator(RE_STR, RE_MSG);
    static final int MINUTE = 60 * 1000;
    private static final int HOUR = 60 * MINUTE;

    @Override
    public Long convertFieldValue(String text) {
        if (text == null) return null;
        String s = text.trim();
        if (s.isEmpty()) return null;
        MatchResult mr = RE.exec(s);
        if (mr == null) return null; // no barfing allowed (?)
        int hrs = Integer.parseInt(mr.getGroup(2));
        int min = 0;
        if (!isEmpty(mr.getGroup(3))) min = Integer.parseInt(mr.getGroup(4) + mr.getGroup(5));
        long ms = hrs * HOUR + min * MINUTE;
        if ("-".equals(mr.getGroup(1))) ms = ms * -1;
        return ms;
    }

    @Override
    public String convertModelValue(Long time) {
        if (time == null) return "";
        String s = "";
        Long t = Math.abs(time);
        if (time < 0) s += "-";
        s += t / HOUR;
        s += ":";
        long mins = t % HOUR / MINUTE;
        if (mins < 10) s += "0";
        s += mins;
        return s;    }

    private boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }
}

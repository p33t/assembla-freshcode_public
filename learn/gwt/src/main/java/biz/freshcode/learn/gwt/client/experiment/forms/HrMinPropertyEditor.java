package biz.freshcode.learn.gwt.client.experiment.forms;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;

import java.text.ParseException;

public class HrMinPropertyEditor extends PropertyEditor<Long> {
    private static final int MINUTE = 60 * 1000;
    private static final int HOUR = 60 * MINUTE;
    private static final RegExp RE = RegExp.compile("^(-?)(\\d+)(:([0-5]?)(\\d))?$");

    @Override
    public Long parse(CharSequence text) throws ParseException {
        if (text == null) return null;
        String s = text.toString().trim();
        if (s.isEmpty()) return null;
        MatchResult mr = RE.exec(s);
        if (mr == null) throw new ParseException("Must be in the form '-h:m', '-' and ':m' are optional", 0);
        int hrs = Integer.parseInt(mr.getGroup(2));
        int min = 0;
        if (!isEmpty(mr.getGroup(3))) min = Integer.parseInt(mr.getGroup(4) + mr.getGroup(5));
        long ms = hrs * HOUR + min * MINUTE;
        if ("-".equals(mr.getGroup(1))) ms = ms * -1;
        return ms;
    }

    private boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    @Override
    public String render(Long time) {
        if (time == null) return "";
        String s = "";
        Long t = Math.abs(time);
        if (time < 0) s += "-";
        s += t / HOUR;
        s += ":";
        long mins = t % HOUR / MINUTE;
        if (mins < 10) s += "0";
        s += mins;
        return s;
    }
}

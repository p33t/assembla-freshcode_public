package biz.freshcode.learn.gwt.client.experiment.triggerfield;

import com.sencha.gxt.widget.core.client.form.PropertyEditor;

import java.text.ParseException;

import static biz.freshcode.learn.gwt.client.util.ExceptionUtil.illegalArg;

public class Digit {
    byte val;

    public Digit(byte val) {
        if (val < 0) throw illegalArg("Cannot be -ve: " + val);
        if (val > 9) throw illegalArg("Cannot be > 9: " + val);
        this.val = val;
    }

    public byte getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "d" + val;
    }

    public static class Editor extends PropertyEditor<Digit> {
        @Override
        public Digit parse(CharSequence text) throws ParseException {
            String s = text.toString().trim();
            if (!s.startsWith("d")) throw new ParseException("Needs to start with 'd'.", 0);
            if (s.length() != 2) throw new ParseException("Can only be 2 chars long.", 1);
            try {
                byte b = Byte.parseByte(s.substring(1));
                return new Digit(b);
            } catch (NumberFormatException e) {
                throw new ParseException("Bad number: " + e.getMessage(), 1);
            }
        }

        @Override
        public String render(Digit d) {
            return "d" + d.getVal();
        }
    }
}

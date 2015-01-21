package biz.freshcode.learn.gwt.mod1.client.experiment.forms;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public class AmPmFlag {
    public static final Access ACCESS = GWT.create(Access.class);
    
    private final AmPm amPm;
    private boolean flag;
    
    public AmPmFlag(AmPm amPm) {
        this.amPm = amPm;
    }

    public String getId() {
        return amPm.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmPmFlag amPmFlag = (AmPmFlag) o;

        if (flag != amPmFlag.flag) return false;
        //noinspection RedundantIfStatement
        if (amPm != amPmFlag.amPm) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = amPm.hashCode();
        result = 31 * result + (flag ? 1 : 0);
        return result;
    }

    public interface Access extends PropertyAccess<AmPmFlag> {
        ModelKeyProvider<AmPmFlag> id();
        
        ValueProvider<AmPmFlag, Boolean> flag();

        ValueProvider<AmPmFlag, AmPm> amPm();
    }

    public AmPm getAmPm() {
        return amPm;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

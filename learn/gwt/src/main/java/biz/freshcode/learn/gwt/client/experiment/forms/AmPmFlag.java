package biz.freshcode.learn.gwt.client.experiment.forms;

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

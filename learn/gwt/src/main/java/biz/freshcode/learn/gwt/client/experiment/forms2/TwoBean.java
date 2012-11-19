package biz.freshcode.learn.gwt.client.experiment.forms2;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public class TwoBean {
    public static final Access ACCESS = GWT.create(Access.class);
    
    private String lowerStr;

    public String getId() {
        return "#" + System.identityHashCode(this);
    }
    
    static interface Access extends PropertyAccess<TwoBean> {
        ModelKeyProvider<TwoBean> id();
        ValueProvider<TwoBean, String> lowerStr();
        @Editor.Path("id")
        ValueProvider<TwoBean, String> idValue();
    }
    
    public String getLowerStr() {
        return lowerStr;
    }

    public void setLowerStr(String lowerStr) {
        System.out.println("Changing " + getId() + " from " + this.lowerStr + " to " + lowerStr);
        this.lowerStr = lowerStr;
    }
}

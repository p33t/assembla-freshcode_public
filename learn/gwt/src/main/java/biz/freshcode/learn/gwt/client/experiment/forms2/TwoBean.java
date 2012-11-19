package biz.freshcode.learn.gwt.client.experiment.forms2;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public class TwoBean {
    public static final Access ACCESS = GWT.create(Access.class);
    
    private String revStr;

    public String getId() {
        return "#" + System.identityHashCode(this);
    }
    
    static interface Access extends PropertyAccess<TwoBean> {
        ModelKeyProvider<TwoBean> id();
        ValueProvider<TwoBean, String> revStr();
        @Editor.Path("id")
        ValueProvider<TwoBean, String> idValue();
    }
    
    public String getRevStr() {
        return revStr;
    }

    public void setRevStr(String revStr) {
        System.out.println("Changing " + getId() + " from " + this.revStr + " to " + revStr);
        this.revStr = revStr;
    }
}

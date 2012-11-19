package biz.freshcode.learn.gwt.client.experiment.forms2;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import java.util.Date;

public class TwoBean {
    public static final Access ACCESS = GWT.create(Access.class);
    
    private String lowerStr;
    private Date date;

    public String getId() {
        return "#" + System.identityHashCode(this);
    }

    static interface Access extends PropertyAccess<TwoBean> {
        ModelKeyProvider<TwoBean> id();
        ValueProvider<TwoBean, String> lowerStr();
        @Editor.Path("id")
        ValueProvider<TwoBean, String> idValue();
        ValueProvider<TwoBean, Date> date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getLowerStr() {
        return lowerStr;
    }

    public void setLowerStr(String lowerStr) {
        System.out.println("Changing " + getId() + " from " + this.lowerStr + " to " + lowerStr);
        this.lowerStr = lowerStr;
    }
}

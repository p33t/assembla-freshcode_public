package biz.freshcode.learn.gwt.client.experiment.forms2;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import java.util.Date;

public class TwoBean {
    public static final Access ACCESS = GWT.create(Access.class);
    
    private Object lowerStr;
    private Date date;
    private Integer presetId;

    public String getId() {
        return "#" + System.identityHashCode(this);
    }

    static interface Access extends PropertyAccess<TwoBean> {
        ModelKeyProvider<TwoBean> id();
        ValueProvider<TwoBean, Object> lowerStr();
        @Editor.Path("id")
        ValueProvider<TwoBean, String> idValue();
        ValueProvider<TwoBean, Date> date();
        ValueProvider<TwoBean, Integer> presetId();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Object getLowerStr() {
        return lowerStr;
    }

    public void setLowerStr(Object lowerStr) {
        System.out.println("Changing " + getId() + " from " + this.lowerStr + " to " + lowerStr);
        this.lowerStr = lowerStr;
    }

    public Integer getPresetId() {
        return presetId;
    }

    public void setPresetId(Integer presetId) {
        this.presetId = presetId;
    }
}

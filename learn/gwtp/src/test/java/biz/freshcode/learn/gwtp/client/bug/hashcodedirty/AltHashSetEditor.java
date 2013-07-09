package biz.freshcode.learn.gwtp.client.bug.hashcodedirty;

import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.widget.core.client.form.AdapterField;

import java.util.Set;

import static biz.freshcode.learn.gwtp.shared.util.AppCollectionUtil.newSet;

public class AltHashSetEditor extends AdapterField<Set<AltHash>> {
    private Set<AltHash> value = newSet();

    public AltHashSetEditor() {
        super(new HTML("<p>not used</p>"));
    }

    @Override
    public void setValue(Set<AltHash> value) {
        this.value = value;
    }

    @Override
    public Set<AltHash> getValue() {
        return value;
    }
}

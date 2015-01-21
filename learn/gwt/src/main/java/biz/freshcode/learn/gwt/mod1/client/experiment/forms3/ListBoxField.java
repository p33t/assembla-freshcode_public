package biz.freshcode.learn.gwt.mod1.client.experiment.forms3;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.widget.core.client.form.AdapterField;

import java.util.List;

import static biz.freshcode.learn.gwt.mod1.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt.mod1.client.util.ExceptionUtil.illegalArg;

public class ListBoxField<T> extends AdapterField<T> {
    private final ListBox listBox;
    private final List<T> elems = newList();

    public ListBoxField() {
        this(new ListBox());
    }

    private ListBoxField(final ListBox lb) {
        super(lb);
        this.listBox = lb;
        lb.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                GWT.log("Changed " + lb.getSelectedIndex());
            }
        });
    }

    public void init(List<T> elems, LabelProvider<T> labels) {
        if (elems.isEmpty()) throw illegalArg("Need at least one element");
        this.elems.clear();
        listBox.clear();
        this.elems.addAll(elems);
        for (int i = 0; i < elems.size(); i++) {
            String label = labels.getLabel(elems.get(i));
            listBox.addItem(label);
        }
        listBox.setSelectedIndex(0);
    }

    @Override
    public void setValue(T value) {
        int ix = elems.indexOf(value);
        listBox.setSelectedIndex(ix);
    }

    @Override
    public T getValue() {
        int ix = listBox.getSelectedIndex();
        if (ix < 0) return null;
        return elems.get(ix);
    }
}

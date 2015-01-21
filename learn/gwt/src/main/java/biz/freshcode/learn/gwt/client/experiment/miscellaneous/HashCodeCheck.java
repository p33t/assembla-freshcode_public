package biz.freshcode.learn.gwt.client.experiment.miscellaneous;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextArea;

import java.util.Set;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newSet;

public class HashCodeCheck extends AbstractIsWidget<VerticalLayoutContainer> {
    public static final int ELEM_COUNT = 100000;
    Set<Integer> hashes = newSet();
    private TextArea text;

    @Override
    protected VerticalLayoutContainer createWidget() {
        return new VerticalLayoutContainerBuilder()
                .add(new TextButton("Go", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        go();
                    }
                }))
                .add(new TextButton("Reset", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        hashes.clear();
                        text.setText("");
                        System.gc();
                    }
                }))
                .add(text = new TextArea(), new VerticalLayoutContainer.VerticalLayoutData(1, 200))
                .verticalLayoutContainer;
    }

    private void go() {
        int origHashCount = hashes.size();
        Set<MyClass> objs = newSet();
        for (int i = 0; i < ELEM_COUNT; i++) {
            String s = "#" + i;
            MyClass cls = new MyClass(s);
            objs.add(cls);
            hashes.add(System.identityHashCode(cls));
        }

        String msg = "Match obj  count: " + (objs.size() == ELEM_COUNT);
        msg += "\nMatch hash count: " + (hashes.size() == origHashCount + ELEM_COUNT);
        msg += "\nHash set size: " + hashes.size();
        text.setText(msg);
        System.gc();
    }

    static class MyClass {
        private String str;

        MyClass(String str) {
            this.str = str;
        }

        String getStr() {
            return str;
        }
    }
}

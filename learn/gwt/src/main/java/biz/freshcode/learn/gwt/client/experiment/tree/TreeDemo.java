package biz.freshcode.learn.gwt.client.experiment.tree;

import biz.freshcode.learn.gwt.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.ContentPanelBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.IdentityHashProvider;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.tree.Tree;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;

public class TreeDemo extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        TreeStore<String> store = new TreeStore<String>(new IdentityHashProvider<String>());
        store.add(newListFrom("p1", "p2"));
        store.add("p1", newListFrom("c11", "c12", "c13"));
        store.add("p2", newListFrom("c21", "c22", "c23"));
        IdentityValueProvider<String> stringProvide = new IdentityValueProvider<String>();
        Tree<String, String> tree = new Tree<String, String>(store, stringProvide);

        return new BorderLayoutContainerBuilder()
                .westWidget(new ContentPanelBuilder()
                        .headingText("My Tree")
                        .widget(tree)
                        .contentPanel, new BorderLayoutContainer.BorderLayoutData(1 / 3.0))
                .borderLayoutContainer;
    }
}

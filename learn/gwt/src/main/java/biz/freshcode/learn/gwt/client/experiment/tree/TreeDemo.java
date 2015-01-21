package biz.freshcode.learn.gwt.client.experiment.tree;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt.client.util.IdentityHashProvider;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.ContentPanelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.tree.Tree;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newListFrom;

public class TreeDemo extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        Tree<String, String> tree;
        BorderLayoutContainer blc = new BorderLayoutContainerBuilder()
                .westWidget(new ContentPanelBuilder()
                        .headingText("My Tree")
                        .widget(tree = createTree())
                        .contentPanel, new BorderLayoutContainer.BorderLayoutData(1 / 3.0))
                .borderLayoutContainer;
        tree.getSelectionModel().addSelectionChangedHandler(new SelectionChangedEvent.SelectionChangedHandler<String>() {
            @Override
            public void onSelectionChanged(SelectionChangedEvent<String> event) {
                Info.display("Selection", event.getSelection().toString());
            }
        });
        return blc;

    }

    private Tree<String, String> createTree() {
        TreeStore<String> store = new TreeStore<String>(new IdentityHashProvider<String>());
        store.add(newListFrom("p1", "p2"));
        store.add("p1", newListFrom("c11", "c12", "c13"));
        store.add("p2", newListFrom("c21", "c22", "c23"));
        IdentityValueProvider<String> stringProvide = new IdentityValueProvider<String>();
        return new Tree<String, String>(store, stringProvide);
    }
}

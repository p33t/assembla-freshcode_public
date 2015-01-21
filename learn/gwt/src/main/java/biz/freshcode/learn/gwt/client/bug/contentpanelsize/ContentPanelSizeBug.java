package biz.freshcode.learn.gwt.client.bug.contentpanelsize;

import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.ContentPanelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.BoxLayoutDataBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.HBoxLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.HtmlLayoutContainerBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.util.MarginsBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;

import static com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign.STRETCH;

public class ContentPanelSizeBug extends AbstractIsWidget {
    @Override
    protected Widget createWidget() {
        String ten = "123456789 ";
        for (int i = 0; i < 6; i++) ten += ten;
        int margin;
        return new HBoxLayoutContainerBuilder()
                .hBoxLayoutAlign(STRETCH)
                // NOTE: Removing the contentpanel and using HtmlLayoutContainer directly fixes issue.
                // Maybe ContentPanel is an outer construct only.
                .add(new ContentPanelBuilder()
                        .headingText("Resize bug test")
                        .widget(new HtmlLayoutContainerBuilder(new HtmlLayoutContainer("<p>" + ten + "</p>"))
                                // NOTE: Margin herein is ignored.  Maybe HBoxLayout overrides it ?!
                                .addStyleName(Bundle.INSTANCE.style().debug())
                                .htmlLayoutContainer)
//                        Doesn't help
//                        .bodyStyleName(Bundle.INSTANCE.style().debug())
                        .contentPanel
                        , new BoxLayoutDataBuilder()
                        .flex(1)
                        .margins(new MarginsBuilder()
                                .top(margin = 1)
                                .right(margin)
                                .bottom(margin)
                                .left(margin)
                                .margins)
                        .boxLayoutData)
                .addStyleName(Bundle.INSTANCE.style().thinBorder())
                .hBoxLayoutContainer;
    }

    public interface Bundle extends ClientBundle {
        Bundle INSTANCE = GWT.create(Bundle.class);
        // Needed
        boolean DOM_MUTATED = INSTANCE.style().ensureInjected();

        @Source("style.css")
        Style style();

        interface Style extends CssResource {
            String debug();

            String thinBorder();
        }
    }
}

package biz.freshcode.learn.gwt.client.uibinder.eg;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.TextButton;

/**
 * From http://www.sencha.com/learn/ext-gwt-3-declarative-markup-with-uibinder/
 */
public class Tutorial1 implements IsWidget {
    interface Binder extends UiBinder<Component, Tutorial1> {
    }

    private static Binder binder = GWT.create(Binder.class);

    @UiField
    TextButton exampleButton;

    @Override
    public Widget asWidget() {
        return binder.createAndBindUi(this);
    }
}

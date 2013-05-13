package biz.freshcode.learn.gwtp.client.util;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.View;

/**
 * An alternative default implemenation of {@link com.gwtplatform.mvp.client.View}.
 * This differs from {@link com.gwtplatform.mvp.client.ViewImpl} in that the parent class calls the subclass initializer
 * the first time 'asWidget()' is called.  It is also has an optional type so a field variable is not needed.
 * This also facilitates field based injection.
 * The other methods of {@link com.gwtplatform.mvp.client.View} are also used to make sure widget is initialized.
 * !!!!!!!!!!!!!!!!!!!! Field based injection is not really supported with GWTP.
 */
public abstract class AbstractView<T extends Widget> extends AbstractIsWidget<T> implements View {
    @Override
    public void addToSlot(Object slot, IsWidget content) {
        asWidget();
    }

    @Override
    public void removeFromSlot(Object slot, IsWidget content) {
        asWidget();
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        asWidget();
    }
}

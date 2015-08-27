package biz.freshcode.learn.gwt_bootstrap.client.timed;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import org.gwtbootstrap3.client.ui.html.Div;

public class CompositeReveal extends Div {
    public CompositeReveal() {
        add(new HTML("<object width='100%' type='image/svg+xml' data='" + GWT.getModuleBaseForStaticFiles() + "media/composite.optim.svg'>SVG not supported</object>"));
    }
}

package biz.freshcode.learn.gwt.mod1.client.bug;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.BlurEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.FocusEvent;
import com.sencha.gxt.widget.core.client.form.DateField;

public class DateFieldBlur implements IsWidget {

    @Override
    public Widget asWidget() {
        VerticalLayoutContainer vlc = new VerticalLayoutContainer();
//        vlc.add(new TextButton("Check", new SelectEvent.SelectHandler() {
//            @Override
//            public void onSelect(SelectEvent event) {
//
//            }
//        }));
        DateField date = new DateField();
        vlc.add(date);
        date.addFocusHandler(new FocusEvent.FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                GWT.log("focus");
            }
        });
        date.addBlurHandler(new BlurEvent.BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                GWT.log("blur");
            }
        });
        date.addExpandHandler(new ExpandEvent.ExpandHandler() {
            @Override
            public void onExpand(ExpandEvent event) {
                GWT.log("================");
                GWT.log("expand");
            }
        });
        date.addCollapseHandler(new CollapseEvent.CollapseHandler() {
            @Override
            public void onCollapse(CollapseEvent event) {
                GWT.log("collapse");
            }
        });
        return vlc;
    }
}

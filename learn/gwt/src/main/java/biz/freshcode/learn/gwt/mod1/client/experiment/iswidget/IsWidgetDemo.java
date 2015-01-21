package biz.freshcode.learn.gwt.mod1.client.experiment.iswidget;

import biz.freshcode.learn.gwt.mod1.client.util.AbstractIsWidget;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

import static com.google.gwt.core.client.GWT.log;

public class IsWidgetDemo extends AbstractIsWidget<VerticalLayoutContainer> {
    @Override
    protected VerticalLayoutContainer createWidget() {
        SelectEvent.SelectHandler handler = new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                onB1(event.getSource());
            }
        };

        return new VerticalLayoutContainerBuilder()
                .add(new MyButton("B1", handler))
                .verticalLayoutContainer;
    }

    private void onB1(Object w) {
        log("w " + w);
        if (w instanceof Widget) {
            log("w is a widget with class " + w.getClass());
            onB1(((Widget) w).getParent());
        }
    }

    public static class MyButton extends AbstractIsWidget<TextButton> {
        private final String text;
        private final SelectEvent.SelectHandler handler;

        public MyButton(String text, SelectEvent.SelectHandler handler) {
            this.text = text;
            this.handler = handler;
        }

        @Override
        protected TextButton createWidget() {
            return new TextButton(text, handler);
        }
    }
}

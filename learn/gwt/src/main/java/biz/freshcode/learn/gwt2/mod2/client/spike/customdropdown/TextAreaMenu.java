package biz.freshcode.learn.gwt2.mod2.client.spike.customdropdown;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.menu.Menu;

public class TextAreaMenu extends Menu implements HasValueChangeHandlers<String> {
    protected TextArea area;

    public TextAreaMenu() {
        area = new TextArea();
        area.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                onAreaSelect(event);
            }
        });
        area.setHeight(100);
        add(area);
        getAppearance().applyDateMenu(getElement());
        plain = true;
        showSeparator = false;
        setEnableScrolling(false);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    public void focus() {
        super.focus();
        area.getElement().focus();
    }

    public String getString() {
        return area.getValue();
    }

    public TextArea getTextArea() {
        return area;
    }

    public void setString(String s) {
        area.setValue(s);
    }

    protected void onAreaSelect(ValueChangeEvent<String> event) {
        fireEvent(event);
    }
}

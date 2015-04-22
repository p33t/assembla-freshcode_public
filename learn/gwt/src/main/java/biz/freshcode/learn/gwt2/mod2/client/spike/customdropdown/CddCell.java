package biz.freshcode.learn.gwt2.mod2.client.spike.customdropdown;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.cell.core.client.form.TriggerFieldCell;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.shared.event.GroupingHandlerRegistration;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextArea;

import java.text.ParseException;

public class CddCell extends TriggerFieldCell<String> implements ExpandEvent.HasExpandHandlers, CollapseEvent.HasCollapseHandlers {
    private GroupingHandlerRegistration menuHandler;
    private TextAreaMenu menu;
    private boolean expanded;
    private Context expandContext;
    private XElement expandParent;

    public CddCell() {
        this(GWT.<Appearance>create(Appearance.class));
    }

    public CddCell(Appearance appearance) {
        super(appearance);
        setPropertyEditor(new PropertyEditor<String>() {
            @Override
            public String parse(CharSequence text) throws ParseException {
                if (text == null) return null;
                return text.toString().replaceAll("\\|", "\n");
            }

            @Override
            public String render(String object) {
                if (object == null) return null;
                return object.replaceAll("\\n", "|");
            }
        });
    }

    @Override
    public HandlerRegistration addCollapseHandler(CollapseEvent.CollapseHandler handler) {
        return addHandler(handler, CollapseEvent.getType());
    }

    @Override
    public HandlerRegistration addExpandHandler(ExpandEvent.ExpandHandler handler) {
        return addHandler(handler, ExpandEvent.getType());
    }


    public void collapse(final Context context, final XElement parent) {
        if (!expanded) {
            return;
        }

        expanded = false;
        menu.hide();
        getInputElement(expandParent).focus();

        expandParent = null;
        expandContext = null;

        fireEvent(context, new CollapseEvent(context));
    }

    public void expand(final Context context, final XElement parent, String value, ValueUpdater<String> valueUpdater) {
        if (expanded) {
            return;
        }

        this.expanded = true;

        // expand may be called without the cell being focused
        // saveContext sets focusedCell so we clear if cell
        // not currently focused
        boolean focused = focusedCell != null;
        saveContext(context, parent, null, valueUpdater, value);
        if (!focused) {
            focusedCell = null;
        }

        TextArea picker = getTextArea();

        String s = getText(parent);
        try {
            s = getPropertyEditor().parse(s);
        } catch (ParseException e) {
            s = e.getMessage(); // heh heh
        }
        picker.setValue(s, false);

        expandContext = context;
        expandParent = parent;

        // handle case when down arrow is opening menu
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

            @Override
            public void execute() {
                menu.setOnHideFocusElement(getFocusElement(parent));
                menu.show(parent, new Style.AnchorAlignment(Style.Anchor.TOP_LEFT, Style.Anchor.BOTTOM_LEFT, true));
                menu.getTextArea().focus();

                fireEvent(context, new ExpandEvent(context));
            }
        });
    }

    public TextArea getTextArea() {
        if (menu == null) {
            setMenu(new TextAreaMenu());
        }
        return menu.getTextArea();
    }

    public TextAreaMenu getMenu() {
        return menu;
    }

    public boolean isExpanded() {
        return expanded;
    }

    /**
     * Sets the DateMenu instance to use in this cell when drawing a datepicker
     *
     * @param menu the menu instance to get the datepicker from
     */
    public void setMenu(final TextAreaMenu menu) {
        if (this.menu != null) {
            menuHandler.removeHandler();
            menuHandler = null;
        }
        this.menu = menu;
        if (this.menu != null) {
            menuHandler = new GroupingHandlerRegistration();

            menuHandler.add(menu.getTextArea().addValueChangeHandler(new ValueChangeHandler<String>() {
                @Override
                public void onValueChange(ValueChangeEvent<String> event) {
                    String s = getPropertyEditor().render(event.getValue());
                    MyViewData viewData = ensureViewData(expandContext, expandParent);
                    if (viewData != null) {
                        viewData.setCurrentValue(s);
                    }
                    getInputElement(expandParent).setValue(s);
                    menu.hide();
                }
            }));
            menuHandler.add(menu.addHideHandler(new HideEvent.HideHandler() {
                @Override
                public void onHide(HideEvent event) {
                    collapse(expandContext, expandParent);
                }
            }));
        }
    }

    @Override
    protected MyViewData ensureViewData(Context context, XElement parent) {
        Object key = context.getKey();
        FieldViewData viewData = getViewData(key);
        if (viewData == null || !(viewData instanceof MyViewData)) {
            viewData = new MyViewData(((InputElement) getInputElement(parent).cast()).getValue());
            setViewData(key, viewData);
        }
        return (MyViewData) viewData;
    }

    /**
     * Exposes {@link com.sencha.gxt.cell.core.client.form.FieldCell.FieldViewData#}
     */
    static class MyViewData extends FieldViewData {

        public MyViewData(String value) {
            super(value);
        }

        @Override
        protected void setCurrentValue(String curValue) {
            super.setCurrentValue(curValue);
        }
    }

    @Override
    protected boolean isFocusedWithTarget(Element parent, Element target) {
        return parent.isOrHasChild(target) ||
                (menu != null &&
                        (menu.getElement().isOrHasChild(target) ||
                                menu.getTextArea().getElement().isOrHasChild(target)));
    }

    @Override
    protected void onNavigationKey(Context context, Element parent, String value, NativeEvent event,
                                   ValueUpdater<String> valueUpdater) {
        if (event.getKeyCode() == KeyCodes.KEY_DOWN && !isExpanded()) {
            event.stopPropagation();
            event.preventDefault();
            onTriggerClick(context, parent.<XElement>cast(), event, value, valueUpdater);
        }
    }

    @Override
    protected void onTriggerClick(Context context, XElement parent, NativeEvent event, String value,
                                  ValueUpdater<String> updater) {
        super.onTriggerClick(context, parent, event, value, updater);
        if (!isReadOnly() && !isDisabled()) {
            onFocus(context, parent, value, event, updater);
            expand(context, parent, value, updater);
        }
    }

    @Override
    protected void triggerBlur(Context context, XElement parent, String value, ValueUpdater<String> valueUpdater) {
        super.triggerBlur(context, parent, value, valueUpdater);

        collapse(context, parent);
    }

    public interface Appearance extends TriggerFieldAppearance {
    }
}

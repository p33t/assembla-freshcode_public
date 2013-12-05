package biz.freshcode.learn.gwt.client.experiment.gridgroupby.reuse;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.IsWidget;

import java.util.List;
import java.util.Map;

import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newList;
import static biz.freshcode.learn.gwt.client.util.AppCollectionUtil.newMap;

public class ElementHover<T> implements MouseOverHandler, MouseOutHandler {
    private static final String ID_PREFIX = "uniqueElementHoverId_";
    private static int nextId = 1;
    private final Callback<T> callback;
    private final Map<String, T> tokens = newMap();
    private final List<HandlerRegistration> regos = newList();

    public ElementHover(IsWidget parent, Callback<T> callback) {
        this.callback = callback;
        regos.add(parent.asWidget().addDomHandler(this, MouseOverEvent.getType()));
        regos.add(parent.asWidget().addDomHandler(this, MouseOutEvent.getType()));

    }

    /**
     * Abandons all the element registrations.
     */
    public void reset() {
        tokens.clear();
    }

    public SafeHtml wrapAndRegister(SafeHtml safeHtml, T token) {
        String id = ID_PREFIX + nextId++;
        tokens.put(id, token);
        return new SafeHtmlBuilder().appendHtmlConstant("<div id='" + id + "'>")
                .append(safeHtml)
                .appendHtmlConstant("</div>")
                .toSafeHtml();
    }

    @Override
    public void onMouseOut(MouseOutEvent event) {
        process(event, false);
    }

    @Override
    public void onMouseOver(MouseOverEvent event) {
        process(event, true);
    }

    private void process(MouseEvent evt, boolean isOver) {
        Element element = evt.getNativeEvent().getEventTarget().cast();
        String id = element.getId();
        T token = tokens.get(id);
        if (token != null) {
            callback.stateChange(this, element, token, isOver);
        }
    }

    public interface Callback<T> {
        /**
         * Notification that the mouse is hovering over a dom element of interest.
         *
         * @param source The invoker of the method.
         * @param domElem The dom element that was the subject of the event
         * @param token The token supplied to this utility during registration.
         * @param mouseIsOver
         */
        void stateChange(ElementHover source, Element domElem, T token, boolean mouseIsOver);
    }
}

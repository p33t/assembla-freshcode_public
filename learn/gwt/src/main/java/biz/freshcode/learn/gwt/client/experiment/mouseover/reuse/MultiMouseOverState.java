package biz.freshcode.learn.gwt.client.experiment.mouseover.reuse;

import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 * Initialises multiple MouseOverState instances and funnels callback invocations into a
 * single specialised callback method.
 * This is used for monitoring the mouse over state of multiple widgets at once.
 */
public class MultiMouseOverState {
    private final Callback callback;

    public MultiMouseOverState(List<? extends Widget> widgets, Callback callback) {
        this.callback = callback;
        for (int i = 0; i < widgets.size(); i++) {
            Widget w = widgets.get(i);
            new MouseOverState(w, new Funnel(i));
        }
    }

    /**
     * Call back to notify of mouse over state changes.
     */
    public interface Callback {
        void stateChange(MouseOverState mos, int widgetIndex);
    }

    private class Funnel implements MouseOverState.Callback {
        private final int ix;

        Funnel(int ix) {
            this.ix = ix;
        }

        @Override
        public void stateChange(MouseOverState mos) {
            callback.stateChange(mos, ix);
        }
    }
}

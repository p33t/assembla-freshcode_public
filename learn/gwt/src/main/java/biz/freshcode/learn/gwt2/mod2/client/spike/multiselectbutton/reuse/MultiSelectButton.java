package biz.freshcode.learn.gwt2.mod2.client.spike.multiselectbutton.reuse;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newList;

/**
 *
 */
public class MultiSelectButton extends TextButton {
    private final String prefix;
    private final Menu menu = new Menu();
    private final CheckMenuItem chkAll = new CheckMenuItem("(All)");
    private final List<String> selected = newList();
    private int total = 0;

    public MultiSelectButton(String prefix) {
        this.prefix = prefix;
        setMenu(menu);
        syncChks(false);
        // never closes upon item selection
        menu.addBeforeSelectionHandler(new BeforeSelectionHandler<Item>() {
            @Override
            public void onBeforeSelection(final BeforeSelectionEvent<Item> event) {
                event.cancel();
                Scheduler.get().scheduleDeferred(new Command() {
                    @Override
                    public void execute() {
                        syncChks(event.getItem() == chkAll);
                    }
                });
            }
        });
    }

    public boolean isAll() {
        return selected.size() == total;
    }

    public List<String> getSelected() {
        return Collections.unmodifiableList(selected);
    }

    public void setOptions(Map<String, String> labelsById) {
        menu.clear();
        chkAll.setChecked(true, true);
        if (labelsById.size() > 1) menu.add(chkAll);
        for (Map.Entry<String, String> me : labelsById.entrySet()) {
            CheckMenuItem chk = new CheckMenuItem(me.getValue());
            chk.setItemId(me.getKey());
            menu.add(chk);
        }
        total = labelsById.size();
        syncChks(true);
    }

    private void syncChks(boolean matchAll) {
        selected.clear();
        for (Widget widget : menu) {
            if (widget instanceof CheckMenuItem && widget != chkAll) {
                CheckMenuItem chk = (CheckMenuItem) widget;
                if (matchAll) chk.setChecked(chkAll.isChecked(), true);
                if (chk.isChecked()) selected.add(chk.getItemId());
            }
        }
        setText(prefix + selected.size() + "/" + total);
        if (!matchAll) {
            if (isAll()) chkAll.setChecked(true, true);
            if (selected.size() == 0) chkAll.setChecked(false, true);
        }
    }
}

package biz.freshcode.learn.gwt2.mod2.client.bug;

import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.error.SideErrorHandler;
import com.sencha.gxt.widget.core.client.info.Info;

public class CheckBoxErrorMessageBug extends Presenter<CheckBoxErrorMessageBug.View, CheckBoxErrorMessageBug.Proxy> {
    public static final String TOKEN = "checkBoxErrorMessageBug";

    @Inject
    public CheckBoxErrorMessageBug(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<CheckBoxErrorMessageBug> {
    }

    public static class View extends ViewImpl {
        private boolean chkError = false;

        @Inject
        public View() {
            FlowLayoutContainer flc = new FlowLayoutContainer();
            flc.add(new HTML("<p>Click button to toggle error on check box.  Error message is not shown." +
                    "<br/>NOTE: Found from looking at source code that error feedback is not implemented for check boxes.</p>"));
            CheckBox chk = new CheckBox();
            chk.setErrorSupport(new SideErrorHandler(chk));
//            chk.setAutoValidate(true);
//            chk.addValidator((editor, value) -> {
//                Info.display("Event", "validation");
//                if (Boolean.TRUE.equals(value)) return Collections.singletonList(new DefaultEditorError(editor, "Bad value!", value));
//                return Collections.emptyList();
//            });
            TextButton btn = new TextButton("Toggle Error");
            btn.addSelectHandler(evt -> {
                String msg;
                if (chkError) {
//                    chk.showErrors(Collections.emptyList());
                    chk.clearInvalid();
                    msg = "Error cleared";
                } else {
//                if (chk.getErrorSupport().) {
//                    chk.showErrors(Collections.singletonList(new DefaultEditorError(chk, "Fake Error", Boolean.TRUE)));
                    chk.markInvalid("Fake error");
                    msg = "Error added";
                }
                Info.display("Event", msg);
                chkError = !chkError;
            });
            flc.add(chk);
            flc.add(btn);
            initWidget(flc);
        }
    }
}

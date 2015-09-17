package biz.freshcode.learn.gwt_bootstrap.client.forms;

import biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken;
import biz.freshcode.learn.gwt_bootstrap.client.boot.RootPresenter;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.ColumnBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.InlineHelpBlockBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.RowBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.org.gwtbootstrap3.client.ui.TextBoxBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import org.gwtbootstrap3.client.ui.InlineHelpBlock;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ColumnSize;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.html.Paragraph;

public class Forms extends Presenter<Forms.View, Forms.Proxy> {
    @Inject
    public Forms(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, RootPresenter.SLOT);
        view.getTxtInput().addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                InlineHelpBlock hb = getView().getHelpBlock();
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    hb.setError("Cut it out!");
                    event.preventDefault();
                    GWT.log("Enter pressed");
                } else {
                    hb.clearError();
                    GWT.log("Key Press");
                }
            }
        });
    }

    @ProxyStandard
    @NameToken(PlaceToken.TOK_FORMS)
    public interface Proxy extends ProxyPlace<Forms> {
    }

    public static class View extends ViewImpl {
        private final TextBox txtInput;
        private final InlineHelpBlock helpBlock;

        @Inject
        public View() {
            initWidget(
                    new RowBuilder()
                            .add(new ColumnBuilder(ColumnSize.XS_12)
                                    .add(new Paragraph("Whatever you do, don't press &lt;enter&gt;!"))
                                    .add(helpBlock = new InlineHelpBlockBuilder()
                                            .iconType(IconType.EXCLAMATION_TRIANGLE)
                                            .inlineHelpBlock)
                                    .add(txtInput = new TextBoxBuilder()
                                            .textBox)
                                    .column)
                            .row
            );
//            txtInput.sinkEvents(Event.KEYEVENTS);
        }

        public InlineHelpBlock getHelpBlock() {
            return helpBlock;
        }

        public TextBox getTxtInput() {
            return txtInput;
        }
    }
}

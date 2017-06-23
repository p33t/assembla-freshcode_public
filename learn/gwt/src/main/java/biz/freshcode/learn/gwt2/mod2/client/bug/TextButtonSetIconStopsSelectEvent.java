package biz.freshcode.learn.gwt2.mod2.client.bug;

import biz.freshcode.learn.gwt2.mod2.client.boot.Root;
import biz.freshcode.learn.gwt2.mod2.client.spike.resources.Bundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.Date;

import static com.google.gwt.safehtml.shared.SafeHtmlUtils.fromString;

public class TextButtonSetIconStopsSelectEvent extends Presenter<TextButtonSetIconStopsSelectEvent.View, TextButtonSetIconStopsSelectEvent.Proxy> {
    public static final String TOKEN = "textButtonSetIconBug";

    @Inject
    public TextButtonSetIconStopsSelectEvent(EventBus eventBus, View view, Proxy proxy) {
        super(eventBus, view, proxy, Root.SLOT);
    }

    @ProxyStandard
    @NameToken(TOKEN)
    public interface Proxy extends com.gwtplatform.mvp.client.proxy.ProxyPlace<TextButtonSetIconStopsSelectEvent> {
    }


    public static class View extends ViewImpl {
        private ImageResource imgA = Bundle.INSTANCE.dirtyGif();
        private ImageResource imgB = Bundle.INSTANCE.dirtyGif4();
        private final TextField txt;
        private final TextButton btn;

        @Inject
        public View() {
            FlowLayoutContainer flc = new FlowLayoutContainer();
            flc.add(new HTML("<p>Click btn vs. Edit text and click btn.<br/>" +
                    "This illustrates a SelectEvent being prevented by a change of button icon</p>"));
            flc.add(txt = new TextField());
            flc.add(btn = new TextButton("Button", imgA));
            initWidget(flc);

            btn.addSelectHandler(event -> Info.display(fromString("Select Event"), fromString("At " + new Date())));

            txt.addValueChangeHandler(event -> {
                if (btn.getIcon() == imgA) btn.setIcon(imgB);
                else btn.setIcon(imgA);
            });
        }
    }
}

package biz.freshcode.learn.gwt2.mod2.client.boot;

import biz.freshcode.learn.gwt2.mod2.client.bug.*;
import biz.freshcode.learn.gwt2.mod2.client.home.Home;
import biz.freshcode.learn.gwt2.mod2.client.spike.adapterfieldgrid.AdapterFieldGridSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.checkboxcellicon.CheckBoxCellIconSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.customdropdown.CustomDropDownSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.dragorder.DragOrderSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.gridgraphic.GridGraphicSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.gridheaderhighlight.GridHeaderHighlightSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.gridinteract.GridInteractSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.gridscrollrestore.GridScrollRestoreSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.iframecontents.IFrameContentsSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.lanes.LanesChartSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.multiselectbutton.MultiSelectButtonSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.radiobutton.RadioSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.resize.ResizeSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.resources.ResourcesSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.restcommand.RestCommandSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.tabpanel.TabPanelSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.validatedtextarea.ValidatedTextAreaSpike;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class PresenterModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(Root.class, Root.Proxy.class);
        bindPresenter(Home.class, Home.Proxy.class);

        bindPresenter(ButtonBarFramedPanelBug.class, ButtonBarFramedPanelBug.Proxy.class);
        bindPresenter(CheckBoxErrorMessageBug.class, CheckBoxErrorMessageBug.Proxy.class);
        bindPresenter(ToolTipVsErrorMessageBug.class, ToolTipVsErrorMessageBug.Proxy.class);
        bindPresenter(TextButtonSetIconStopsSelectEvent.class, TextButtonSetIconStopsSelectEvent.Proxy.class);
        bindPresenter(GridEditInlineComboBoxBlurBug2.class, GridEditInlineComboBoxBlurBug2.Prox.class);
        bindPresenter(TextFieldEmptyTextBecomesNullBug.class, TextFieldEmptyTextBecomesNullBug.Proxy.class);

        bindPresenter(ResizeSpike.class, ResizeSpike.Proxy.class);
        bindPresenter(AdapterFieldGridSpike.class, AdapterFieldGridSpike.Proxy.class);
        bindPresenter(DragOrderSpike.class, DragOrderSpike.Proxy.class);
        bindPresenter(GridInteractSpike.class, GridInteractSpike.Proxy.class);
        bindPresenter(CheckBoxCellIconSpike.class, CheckBoxCellIconSpike.Proxy.class);
        bindPresenter(RadioSpike.class, RadioSpike.Proxy.class);
        bindPresenter(GridScrollRestoreSpike.class, GridScrollRestoreSpike.Proxy.class);
        bindPresenter(LanesChartSpike.class, LanesChartSpike.Proxy.class);
        bindPresenter(CustomDropDownSpike.class, CustomDropDownSpike.Proxy.class);
        bindPresenter(MultiSelectButtonSpike.class, MultiSelectButtonSpike.Proxy.class);
        bindPresenter(GridGraphicSpike.class, GridGraphicSpike.Proxy.class);
        bindPresenter(ResourcesSpike.class, ResourcesSpike.Proxy.class);
        bindPresenter(ValidatedTextAreaSpike.class, ValidatedTextAreaSpike.Proxy.class);
        bindPresenter(TabPanelSpike.class, TabPanelSpike.Proxy.class);
        bindPresenter(IFrameContentsSpike.class, IFrameContentsSpike.Proxy.class);
        bindPresenter(GridHeaderHighlightSpike.class, GridHeaderHighlightSpike.Proxy.class);
        bindPresenter(RestCommandSpike.class, RestCommandSpike.Proxy.class);
    }
}

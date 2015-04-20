package biz.freshcode.learn.gwt2.mod2.client.boot;

import biz.freshcode.learn.gwt2.mod2.client.home.Home;
import biz.freshcode.learn.gwt2.mod2.client.spike.adapterfieldgrid.AdapterFieldGridSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.checkboxcellicon.CheckBoxCellIconSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.dragorder.DragOrderSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.gridinteract.GridInteractSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.gridscrollrestore.GridScrollRestoreSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.lanes.LanesChartSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.radiobutton.RadioSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.resize.ResizeSpike;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class PresenterModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(Root.class, Root.Proxy.class);
        bindPresenter(Home.class, Home.Proxy.class);
        bindPresenter(ResizeSpike.class, ResizeSpike.Proxy.class);
        bindPresenter(AdapterFieldGridSpike.class, AdapterFieldGridSpike.Proxy.class);
        bindPresenter(DragOrderSpike.class, DragOrderSpike.Proxy.class);
        bindPresenter(GridInteractSpike.class, GridInteractSpike.Proxy.class);
        bindPresenter(CheckBoxCellIconSpike.class, CheckBoxCellIconSpike.Proxy.class);
        bindPresenter(RadioSpike.class, RadioSpike.Proxy.class);
        bindPresenter(GridScrollRestoreSpike.class, GridScrollRestoreSpike.Proxy.class);
        bindPresenter(LanesChartSpike.class, LanesChartSpike.Proxy.class);
    }
}

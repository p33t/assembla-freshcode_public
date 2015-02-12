package biz.freshcode.learn.gwt2.mod2.client.boot;

import biz.freshcode.learn.gwt2.mod2.client.home.Home;
import biz.freshcode.learn.gwt2.mod2.client.spike.adapterfieldgrid.AdapterFieldGridSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.dragorder.DragOrderSpike;
import biz.freshcode.learn.gwt2.mod2.client.spike.gridinteract.GridInteractSpike;
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
    }
}

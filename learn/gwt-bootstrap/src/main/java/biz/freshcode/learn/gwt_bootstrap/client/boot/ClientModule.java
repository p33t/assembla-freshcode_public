package biz.freshcode.learn.gwt_bootstrap.client.boot;

import biz.freshcode.learn.gwt_bootstrap.client.alt.Alt;
import biz.freshcode.learn.gwt_bootstrap.client.carousels.Carousels;
import biz.freshcode.learn.gwt_bootstrap.client.forms.Forms;
import biz.freshcode.learn.gwt_bootstrap.client.graphic.Graphic;
import biz.freshcode.learn.gwt_bootstrap.client.grids.Grids;
import biz.freshcode.learn.gwt_bootstrap.client.home.Home;
import biz.freshcode.learn.gwt_bootstrap.client.timed.Timed;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

import static biz.freshcode.learn.gwt_bootstrap.client.boot.PlaceToken.TOK_HOME;

public class ClientModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new DefaultModule());

        bindConstant().annotatedWith(DefaultPlace.class).to(TOK_HOME);
        bindConstant().annotatedWith(ErrorPlace.class).to(TOK_HOME);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(TOK_HOME);

        bindPresenter(RootPresenter.class, RootPresenter.Proxy.class);
        bindPresenter(Home.class, Home.Proxy.class);
        bindPresenter(Alt.class, Alt.Proxy.class);
        bindPresenter(Graphic.class, Graphic.Proxy.class);
        bindPresenter(Grids.class, Grids.Proxy.class);
        bindPresenter(Timed.class, Timed.Proxy.class);
        bindPresenter(Carousels.class, Carousels.Proxy.class);
        bindPresenter(Forms.class, Forms.Proxy.class);
    }
}

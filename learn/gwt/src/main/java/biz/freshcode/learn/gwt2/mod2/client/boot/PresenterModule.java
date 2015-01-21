package biz.freshcode.learn.gwt2.mod2.client.boot;

import biz.freshcode.learn.gwt2.mod2.client.home.Home;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class PresenterModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(Home.class, Home.Proxy.class);
    }
}

package biz.freshcode.learn.gwtp.client.special;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class SpecialModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(Special.class, Special.Proxy.class);
    }
}

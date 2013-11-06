package biz.freshcode.learn.gwtp.client.ext;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ExtModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(Extensible.class, Extensible.Proxy.class);
    }
}

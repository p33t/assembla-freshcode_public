package biz.freshcode.learn.gwtp.client.boot;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.dispatch.shared.SecurityCookie;

public class AppModule extends AbstractGinModule {
    public static final String XSRF_COOKIE = "XSRF-SAFETY";

    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bindConstant().annotatedWith(SecurityCookie.class).to(AppModule.XSRF_COOKIE);
        install(new DispatchAsyncModule());
    }
}

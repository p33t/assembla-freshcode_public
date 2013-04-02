package biz.freshcode.learn.gwt.server.dispatch.guice;

import biz.freshcode.learn.gwt.server.dispatch.DdHandler;
import biz.freshcode.learn.gwt.shared.dispatch.DdAction;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.server.guice.HandlerModule;
import com.gwtplatform.dispatch.shared.ActionImpl;

/**
 * Almost entirely duplicated from gwtp basic sample.
 */
public class GuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new MyHandlerModule(), new MyServletModule());
    }

    public static class MyHandlerModule extends HandlerModule {

        @Override
        protected void configureHandlers() {
            bindHandler(DdAction.class, DdHandler.class);
        }
    }

    public static class MyServletModule extends ServletModule {
        @Override
        protected void configureServlets() {
            serve("/" + ActionImpl.DEFAULT_SERVICE_NAME + "*").with(DispatchServiceImpl.class);
        }
    }
}

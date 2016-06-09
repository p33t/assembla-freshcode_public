package biz.freshcode.learn.gwt.server.dispatch.guice;

import biz.freshcode.learn.gwt.client.inject.AppModule;
import biz.freshcode.learn.gwt.server.GreetingServiceImpl;
import biz.freshcode.learn.gwt.server.dispatch.DdHandler;
import biz.freshcode.learn.gwt.server.dispatch.SdHandler;
import biz.freshcode.learn.gwt.shared.dispatch.DdAction;
import biz.freshcode.learn.gwt.shared.dispatch.SdAction;
import com.google.gwt.logging.server.RemoteLoggingServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.gwtplatform.dispatch.rpc.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;
import com.gwtplatform.dispatch.rpc.shared.ActionImpl;
import com.gwtplatform.dispatch.shared.SecurityCookie;

import java.util.Map;

import static biz.freshcode.learn.gwt2.common.client.util.AppCollectionUtil.newMap;

/**
 * Almost entirely duplicated from gwtp basic sample.
 */
public class GuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new MyHandlerModule(), new MyServletModule(), new TweakBindingsModule());
    }

    public static class MyHandlerModule extends HandlerModule {

        @Override
        protected void configureHandlers() {
            bindHandler(DdAction.class, DdHandler.class);
            bindHandler(SdAction.class, SdHandler.class);
        }
    }

    public static class MyServletModule extends ServletModule {

        @Override
        protected void configureServlets() {
            bindConstant().annotatedWith(SecurityCookie.class).to(AppModule.XSRF_COOKIE);
            // populates the SecurityCookie for XSRF protection
            // this required all servlets and filters to be moved to this method
            filter("*.html").through(DummyRandomSecurityCookieFilter.class);
            serve("/" + ActionImpl.DEFAULT_SERVICE_NAME + "*").with(DispatchServiceImpl.class);

            // NOTE: These have been moved here from web.xml so that GuiceFilter can control everything
            //       and XSRF protection can work.  Otherwise cannot auto setup security cookie.
            serve("/Mod1/greet").with(GreetingServiceImpl.class);
            Map<String, String> params = newMap();
            params.put("symbolMapsDirectory", "WEB-INF/classes/symbolMaps/");
            serve("/gwtRequest").with(RequestFactoryServlet.class, params);

            // Not sure why this is enabled but need the servlet.
            serve("/Mod1/remote_logging").with(RemoteLoggingServiceImpl.class);
        }
    }

    public static class TweakBindingsModule extends AbstractModule {

        @Override
        protected void configure() {
            // need to be set to 'singleton' externally.
            bind(GreetingServiceImpl.class).in(Singleton.class);
            bind(RequestFactoryServlet.class).in(Singleton.class);
            bind(RemoteLoggingServiceImpl.class).in(Singleton.class);
        }
    }
}

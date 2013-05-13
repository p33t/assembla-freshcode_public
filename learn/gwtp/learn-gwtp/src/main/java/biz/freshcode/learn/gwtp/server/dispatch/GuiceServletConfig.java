package biz.freshcode.learn.gwtp.server.dispatch;

import biz.freshcode.learn.gwtp.client.boot.AppModule;
import biz.freshcode.learn.gwtp.shared.dispatch.SdAction;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.server.guice.HandlerModule;
import com.gwtplatform.dispatch.server.guice.HttpSessionSecurityCookieFilter;
import com.gwtplatform.dispatch.shared.ActionImpl;
import com.gwtplatform.dispatch.shared.SecurityCookie;

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
//            bindHandler(DdAction.class, DdHandler.class);
            bindHandler(SdAction.class, SdHandler.class);
        }
    }

    public static class MyServletModule extends ServletModule {

        @Override
        protected void configureServlets() {
            bindConstant().annotatedWith(SecurityCookie.class).to(AppModule.XSRF_COOKIE);
            // populates the SecurityCookie for XSRF protection
            // this required all servlets and filters to be moved to this method
            filter("*.html").through(HttpSessionSecurityCookieFilter.class);
            serve("/" + ActionImpl.DEFAULT_SERVICE_NAME + "*").with(DispatchServiceImpl.class);

            // NOTE: These have been moved here from web.xml so that GuiceFilter can control everything
            //       and XSRF protection can work.  Otherwise cannot auto setup security cookie.
//            serve("/Mod1/greet").with(GreetingServiceImpl.class);
//            Map<String, String> params = newMap();
//            params.put("symbolMapsDirectory", "WEB-INF/classes/symbolMaps/");
//            serve("/gwtRequest").with(RequestFactoryServlet.class, params);
        }
    }

    public static class TweakBindingsModule extends AbstractModule {

        @Override
        protected void configure() {
            // need to be set to 'singleton' externally.
//            bind(GreetingServiceImpl.class).in(Singleton.class);
//            bind(RequestFactoryServlet.class).in(Singleton.class);
        }
    }
}
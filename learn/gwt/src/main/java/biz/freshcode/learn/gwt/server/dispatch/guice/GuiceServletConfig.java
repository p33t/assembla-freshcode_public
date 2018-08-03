package biz.freshcode.learn.gwt.server.dispatch.guice;

import biz.freshcode.learn.gwt.server.GreetingServiceImpl;
import biz.freshcode.learn.gwt.server.dispatch.DdHandler;
import biz.freshcode.learn.gwt.server.dispatch.SdHandler;
import biz.freshcode.learn.gwt.server.spike.downloadable.DownloadHandler;
import biz.freshcode.learn.gwt.server.spike.downloadable.SimpleHandler;
import biz.freshcode.learn.gwt2.mod2.shared.SharedUtil;
import biz.freshcode.learn.gwt2.mod2.shared.downloadable.DownloadAction;
import biz.freshcode.learn.gwt2.mod2.shared.downloadable.SimpleAction;
import biz.freshcode.learn.gwt2.mod2.shared.spike.rpc.dispatch.DdAction;
import biz.freshcode.learn.gwt2.mod2.shared.spike.rpc.dispatch.SdAction;
import com.google.gwt.logging.server.RemoteLoggingServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
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
            bindHandler(SimpleAction.class, SimpleHandler.class);
            bindHandler(DownloadAction.class, DownloadHandler.class);
        }
    }

    public static class MyServletModule extends ServletModule {

        @Override
        protected void configureServlets() {
            bindConstant().annotatedWith(SecurityCookie.class).to(SharedUtil.XSRF_COOKIE);
            // populates the SecurityCookie for XSRF protection
            // this required all servlets and filters to be moved to this method
            // NOTE: Need a dummy because XSRF security doesn't work with parallel requests (I think it has a single changing value)
            filter("*.html").through(DummyRandomSecurityCookieFilter.class);
            serve("/" + ActionImpl.DEFAULT_SERVICE_NAME + "*").with(CustomDispatchService.class);

            // NOTE: These have been moved here from web.xml so that GuiceFilter can control everything
            //       and XSRF protection can work.  Otherwise cannot auto setup security cookie.
            serve("/Mod2/greet").with(GreetingServiceImpl.class);
            Map<String, String> params = newMap();
            params.put("symbolMapsDirectory", "WEB-INF/classes/symbolMaps/");
            serve("/gwtRequest").with(RequestFactoryServlet.class, params);

            // Not sure why this is enabled but need the servlet.
            serve("/Mod2/remote_logging").with(RemoteLoggingServiceImpl.class);
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

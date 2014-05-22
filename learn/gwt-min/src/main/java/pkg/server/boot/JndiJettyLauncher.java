package pkg.server.boot;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.dev.shell.jetty.JettyLauncher;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

import static pkg.server.util.AppServerUtil.LOG;

public class JndiJettyLauncher extends JettyLauncher {
    private static final String[] CONFIG_CLASSES = new String[]{
            "org.eclipse.jetty.webapp.WebInfConfiguration",
            "org.eclipse.jetty.webapp.WebXmlConfiguration",
            "org.eclipse.jetty.webapp.MetaInfConfiguration",
            "org.eclipse.jetty.webapp.FragmentConfiguration",
            //    "org.eclipse.jetty.plus.webapp.EnvConfiguration", // add for JNDI
            //    "org.eclipse.jetty.plus.webapp.PlusConfiguration", // add for JNDI
            "org.eclipse.jetty.webapp.JettyWebXmlConfiguration"
            // Not necessary for Jetty 8..."org.eclipse.jetty.webapp.TagLibConfiguration",
    };


    @Override
    protected WebAppContext createWebAppContext(TreeLogger logger, File appRootDir) {
        LOG.info("createWebAppContext() from " + getClass().getSimpleName());
        WebAppContext ctx = super.createWebAppContext(logger, appRootDir);
        ctx.setConfigurationClasses(CONFIG_CLASSES);
        return ctx;
    }
}

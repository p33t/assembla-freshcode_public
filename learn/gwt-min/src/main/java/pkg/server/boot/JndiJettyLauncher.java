package pkg.server.boot;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.dev.shell.jetty.JettyLauncher;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

public class JndiJettyLauncher extends JettyLauncher {
    @Override
    protected WebAppContext createWebAppContext(TreeLogger logger, File appRootDir) {
        return super.createWebAppContext(logger, appRootDir);
    }
}

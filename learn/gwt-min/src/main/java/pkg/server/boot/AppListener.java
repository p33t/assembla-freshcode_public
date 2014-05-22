package pkg.server.boot;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static pkg.server.util.AppServerUtil.LOG;

@WebListener()
public class AppListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.info("Context intialised: " + servletContextEvent.getServletContext().getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.info("Context destroyed: " + servletContextEvent.getServletContext().getServerInfo());
    }
}

package pkg;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;
import java.util.UUID;

/**
 * Manages the timer for the system
 */
public class TimerManager implements ServletContextListener {
    private static final String KEY = "timer";

    @Override
    public void contextInitialized(ServletContextEvent evt) {
        Log.info("Context init");
        UUID session = UUID.randomUUID();
        HeartBeat h = new HeartBeat(session);

        ServletContext ctx = evt.getServletContext();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(h, 1000, 60000);
        ctx.setAttribute(KEY, timer);

    }

    @Override
    public void contextDestroyed(ServletContextEvent evt) {
        Log.info("Context destroy");
        ServletContext ctx = evt.getServletContext();
        Timer timer = (Timer) ctx.getAttribute(KEY);
        if (timer != null) timer.cancel();
        ctx.removeAttribute(KEY);
    }
}

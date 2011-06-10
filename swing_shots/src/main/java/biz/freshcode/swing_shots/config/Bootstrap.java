package biz.freshcode.swing_shots.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static biz.freshcode.swing_shots.util.AppExceptionUtil.illegalState;
import static biz.freshcode.swing_shots.util.AppExceptionUtil.runtime;

public class Bootstrap {
    public static final SysProp APP_HOME = new SysProp("app.home");

    public static AnnotationConfigApplicationContext bootstrap(Package... basePackages) {
        setupAppHome();
        checkAppHome();
        return createContext(basePackages);
    }

    private static AnnotationConfigApplicationContext createContext(Package[] basePackages) {
        String[] names = new String[basePackages.length];
        for (int i = 0; i < basePackages.length; i++) names[i] = basePackages[i].getName();
        return new AnnotationConfigApplicationContext(names);
    }

    private static void checkAppHome() {
        File appHome = new File(APP_HOME.get());
        if (!appHome.exists()) throw illegalState(APP_HOME.name + " does not exist");
        if (!appHome.isDirectory()) throw illegalState(APP_HOME.name + " must be a folder");
    }

    private static void setupAppHome() {
        String val = APP_HOME.get();
        if (val != null) return;
        File defaultHome = calcAppHome();
        APP_HOME.set(defaultHome.getPath());
    }

    private static File calcAppHome() {
        URL u = getLocation();
        String path;
        try {
            path = u.toURI().getPath();
        } catch (URISyntaxException e) {
            throw runtime(e);
        }
        if (path.endsWith(".jar")) return new File(path).getParentFile();
        return new File(".");
    }

    private static URL getLocation() {
        return Bootstrap.class.getProtectionDomain().getCodeSource().getLocation();
    }
}

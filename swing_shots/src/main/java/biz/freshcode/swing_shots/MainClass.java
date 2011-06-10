package biz.freshcode.swing_shots;

import biz.freshcode.swing_shots.config.Bootstrap;
import biz.freshcode.swing_shots.ui.MainFrame;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
    public static final Package PACKAGE = MainClass.class.getPackage();

    public static void main(String[] args) {
        try {
            launch();
        } catch (RuntimeException e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    private static void launch() {
        AnnotationConfigApplicationContext ctx = Bootstrap.bootstrap(PACKAGE);
        MainFrame mf = ctx.getBean(MainFrame.class);
        mf.launch();
    }
}

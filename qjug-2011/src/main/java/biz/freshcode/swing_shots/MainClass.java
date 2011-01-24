package biz.freshcode.swing_shots;

import biz.freshcode.swing_shots.config.Bootstrap;
import biz.freshcode.swing_shots.ui.MainFrame;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
    private static final Package PACKAGE = MainClass.class.getPackage();

    public static void main(String[] args) {
        try {
            launch();
        } catch (RuntimeException e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    private static void launch() {
        Bootstrap.bootstrap();
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PACKAGE.getName());
        MainFrame mf = ctx.getBean(MainFrame.class);
        mf.launch();
    }
}

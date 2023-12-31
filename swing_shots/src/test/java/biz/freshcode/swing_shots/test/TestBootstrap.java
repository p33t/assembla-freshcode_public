package biz.freshcode.swing_shots.test;

import biz.freshcode.swing_shots.MainClass;
import biz.freshcode.swing_shots.config.Bootstrap;
import biz.freshcode.swing_shots.config.Factory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestBootstrap {
    // NOTE: database is not automatically reset between tests... that would take too long.
    public static final AnnotationConfigApplicationContext TEST_CONTEXT = Bootstrap.bootstrap(MainClass.PACKAGE);

    public static Factory factory() {
        return TEST_CONTEXT.getBean(Factory.class);
    }
}

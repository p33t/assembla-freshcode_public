package biz.freshcode.swing_shots.config;

import biz.freshcode.swing_shots.data.H2Database;
import biz.freshcode.swing_shots.ui.MenuBar;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static org.springframework.util.StringUtils.uncapitalize;


/**
 * A surrogate class for object construction.
 * This adds stricter typing to 'prototype' class constructors.
 * <p/>
 * TODO: DisposableBean support for prototype classes using weak-refs.
 */
@Component
public class Factory {
    @Inject private ApplicationContext ctx;

    public <T> T bean(Class<T> cls, Object... args) {
        String name = uncapitalize(cls.getSimpleName());
        return cls.cast(ctx.getBean(name, args));
    }

    public H2Database h2Database(String name) {
        return bean(H2Database.class, name);
    }

    /**
     * An example of statically typing the constructor of a partially injected object.
     */
    public MenuBar menuBar(MenuBar.Host host) {
        return bean(MenuBar.class, host);
    }
}

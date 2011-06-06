package biz.freshcode.swing_shots.config;

import biz.freshcode.swing_shots.ui.MenuBar;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static org.springframework.util.StringUtils.uncapitalize;


@Component
public class Factory {
    @Inject private ApplicationContext ctx;

    /**
     * An example of statically typing the constructor of a partially injected object.
     */
    public MenuBar menuBar(MenuBar.Host host) {
        return bean(MenuBar.class, host);
    }

    public <T> T bean(Class<T> cls, Object... args) {
        String name = uncapitalize(cls.getSimpleName());
        return cls.cast(ctx.getBean(name, args));
    }
}

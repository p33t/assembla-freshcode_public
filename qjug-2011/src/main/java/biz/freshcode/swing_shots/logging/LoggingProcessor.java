package biz.freshcode.swing_shots.logging;

import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.util.ReflectionUtils.doWithFields;

@SuppressWarnings({"UnusedDeclaration"})
@Component
public class LoggingProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(final Object bean, String beanName) {
        doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                if (field.getAnnotation(Logging.class) != null) {
                    ReflectionUtils.makeAccessible(field);
                    Logger log = getLogger(bean.getClass());
                    field.set(bean, log);
                }
            }
        });
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }
}

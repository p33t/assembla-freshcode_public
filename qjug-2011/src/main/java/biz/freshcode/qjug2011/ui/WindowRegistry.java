package biz.freshcode.qjug2011.ui;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

import static biz.freshcode.qjug2011.util.AppCollectionUtils.newList;

// TODO: Use or lose.
@Component
public class WindowRegistry implements BeanPostProcessor {
    private List<WeakReference<Window>> refs = newList();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Window) add(bean);
        return bean;
    }

    public List<Window> listWindows() {
        List<Window>  l = newList();
        for (Iterator<WeakReference<Window>> it = refs.iterator(); it.hasNext();) {
            WeakReference<Window> next = it.next();
            Window window = next.get();
            if (window == null) it.remove();
            else l.add(window);
        }
        return l;
    }

    private void add(Object bean) {
        refs.add(new WeakReference<Window>((Window) bean));
    }
}

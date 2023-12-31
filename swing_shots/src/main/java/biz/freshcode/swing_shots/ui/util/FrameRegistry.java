package biz.freshcode.swing_shots.ui.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

import static biz.freshcode.swing_shots.util.AppCollectionUtils.newList;

@Component
public class FrameRegistry implements BeanPostProcessor {
    private List<WeakReference<JFrame>> refs = newList();

    public List<JFrame> listFrames() {
        List<JFrame> l = newList();
        for (Iterator<WeakReference<JFrame>> it = refs.iterator(); it.hasNext(); ) {
            WeakReference<JFrame> next = it.next();
            JFrame window = next.get();
            if (window == null) it.remove();
            else l.add(window);
        }
        return l;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Window) add(bean);
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private void add(Object bean) {
        refs.add(new WeakReference<JFrame>((JFrame) bean));
    }
}

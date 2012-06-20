package pkg;

import biz.freshcode.b_generation.DefaultBeanBuilderGenerator;
import biz.freshcode.learn.gwt.client.uispike.builder.BeanBuilder;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;

public class BeanBuilderGenerator extends DefaultBeanBuilderGenerator {
    static final Class CLASS = HorizontalLayoutContainer.class; //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    public static void main(String[] args) {
        BeanBuilderGenerator bbg = new BeanBuilderGenerator();
        System.out.println(bbg.generate(CLASS));
    }

    @Override
    protected String getBuilderAnnotation(Class beanClass) {
        return "@" + BeanBuilder.class.getName() + "(" + beanClass.getSimpleName() + ".class)";
    }
}

package pkg;

import biz.freshcode.learn.gwt.client.experiment.forms.HrMinField;
import biz.freshcode.learn.gwt.client.uispike.builder.BeanBuilder;
import pkg.b_generation.DefaultBeanBuilderGenerator;

public class BeanBuilderGenerator extends DefaultBeanBuilderGenerator {
    static final Class CLASS = HrMinField.class;


    public static void main(String[] args) {
        BeanBuilderGenerator bbg = new BeanBuilderGenerator();
        System.out.println(bbg.generate(CLASS));
    }

    @Override
    protected String getBuilderAnnotation(Class beanClass) {
        return "@" + BeanBuilder.class.getName() + "(" + beanClass.getSimpleName() + ".class)";
    }
}

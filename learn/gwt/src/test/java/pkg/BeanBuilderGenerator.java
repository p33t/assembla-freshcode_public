package pkg;

import biz.freshcode.b_generation.BeanBuilderWriter;
import biz.freshcode.b_generation.DefaultBeanBuilderGenerator;
import biz.freshcode.learn.gwt.client.uispike.builder.BeanBuilder;
import com.sencha.gxt.widget.core.client.DatePicker;

public class BeanBuilderGenerator extends DefaultBeanBuilderGenerator {
    static final Class CLASS = DatePicker.class; //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public static final String BASE_PKG = "biz.freshcode.learn.gwt.client.builder";


    public static void main(String[] args) {
        new BeanBuilderWriter()
                .addMapping("com.sencha.gxt.widget.core.client", BASE_PKG + ".gxt")
                .addMapping("com.sencha.gxt.core.client", BASE_PKG + ".gxt")
                .addMapping("com.google.gwt.user.client.ui", BASE_PKG + ".gwt")
                .generator(new BeanBuilderGenerator())
                .write(CLASS);
    }

    @Override
    protected String getBuilderAnnotation(Class beanClass) {
        return "@" + BeanBuilder.class.getName() + "(" + beanClass.getSimpleName() + ".class)";
    }
}

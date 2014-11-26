package pkg;

import biz.freshcode.b_generation.BeanBuilderWriter;
import biz.freshcode.b_generation.BgenUtil;
import biz.freshcode.b_generation.DefaultBeanBuilderGenerator;
import biz.freshcode.b_generation.PackageMapper;
import biz.freshcode.learn.gwtp.client.builder.BeanBuilder;
import biz.freshcode.learn.gwtp.client.builder.Construct;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.SpinnerField;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import java.lang.reflect.Method;

public class BeanBuilderGenerator extends DefaultBeanBuilderGenerator {
    static final Class[] CLASS = {MenuItem.class}; //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public static final String BASE_PKG = "biz.freshcode.learn.gwtp.client.builder";
    public static final PackageMapper MAPPER = new PackageMapper()
            .addMapping("com.sencha.gxt.widget.core.client", BASE_PKG + ".gxt")
            .addMapping("com.sencha.gxt.chart.client", BASE_PKG + ".gxt")
            .addMapping("com.sencha.gxt.core.client", BASE_PKG + ".gxt")
            .addMapping("com.google.gwt.user.client.ui", BASE_PKG + ".gwt");

    public static void main(String[] args) {
        new BeanBuilderWriter()
                .packageMapper(MAPPER)
                .generator(new BeanBuilderGenerator())
                .write(CLASS)
        ;

//        String msg = new MigrationProbe()
//                .packageMapper(MAPPER)
//                .beanClassReader(new MigrationProbe.BeanClassReader() {
//                    @Override
//                    public Class read(Class builderClass) {
//                        BeanBuilder ann = (BeanBuilder) builderClass.getAnnotation(BeanBuilder.class);
//                        if (ann == null) throw new IllegalStateException("No annotation on " + builderClass);
//                        return ann.value();
//                    }
//                })
//                .probe();
//        System.out.println(msg);
    }

    @Override
    protected String getBuilderAnnotation(Class beanClass) {
        return super.getBuilderAnnotation(beanClass) +
                "\n@" + BeanBuilder.class.getName() + "(" + beanClass.getSimpleName() + ".class)";
    }

    /**
     * Add the custom parent class for the builder.
     */
    @Override
    protected String getBuilderParent(Class beanCls, String builderType) {
        String parentName = BgenUtil.className(Construct.Parent.class);
        return parentName + "<" + builderType + ">";
    }

    @Override
    protected String mapType(Class beanCls, Method m, String type, int argIndex, boolean forJavadoc) {
        if (genericTypeMatch(beanCls, type, NumberField.class, "C", "T"))
            return genericTypeResolve(type, "N", forJavadoc, "C", "T");
        if (genericTypeMatch(beanCls, type, SpinnerField.class, "C", "T"))
            return genericTypeResolve(type, "N", forJavadoc, "C", "T");
        return super.mapType(beanCls, m, type, argIndex, forJavadoc);
    }
}

package pkg;

import biz.freshcode.b_generation.BeanBuilderWriter;
import biz.freshcode.b_generation.BgenUtil;
import biz.freshcode.b_generation.DefaultBeanBuilderGenerator;
import biz.freshcode.learn.gwt.client.builder.BeanBuilder;
import biz.freshcode.learn.gwt.client.builder.Construct;
import com.sencha.gxt.widget.core.client.container.NorthSouthContainer;

public class BeanBuilderGenerator extends DefaultBeanBuilderGenerator {
    static final Class CLASS = NorthSouthContainer.class; //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public static final String BASE_PKG = "biz.freshcode.learn.gwt.client.builder";

    public static void main(String[] args) {
//        For migrating to new components.
//        String str = new MigrationProbe()
//                .builderScan(new BuilderScan().targetAnnotation(BeanBuilder.class))
//                .beanClassReader(new MigrationProbe.BeanClassReader() {
//                    @Override
//                    public Class read(Class builderClass) {
//                        BeanBuilder ann = (BeanBuilder) builderClass.getAnnotation(BeanBuilder.class);
//                        if (ann == null)
//                            throw ExceptionUtil.illegalState(builderClass.getName() + " does not have annotation");
//                        return ann.value();
//                    }
//                })
//                .packageMapper(
//                        new PackageMapper()
//                        // grab from below
//                )
//                .probe();
//
//        System.out.println(str);

        new BeanBuilderWriter()
                .addMapping("com.sencha.gxt.widget.core.client", BASE_PKG + ".gxt")
                .addMapping("com.sencha.gxt.chart.client", BASE_PKG + ".gxt")
                .addMapping("com.sencha.gxt.core.client", BASE_PKG + ".gxt")
                .addMapping("com.google.gwt.user.client.ui", BASE_PKG + ".gwt")
                .addMapping("biz.freshcode.learn.gwt.client", BASE_PKG + ".freshcode")
                .generator(new BeanBuilderGenerator())
                .write(CLASS);
    }

    @Override
    protected String getBuilderAnnotation(Class beanClass) {
        return "@" + BeanBuilder.class.getName() + "(" + beanClass.getSimpleName() + ".class)\n" +
                "@" + SuppressWarnings.class.getSimpleName() + "(\"UnusedDeclaration\")";
    }

    /**
     * Add the custom parent class for the builder.
     */
    @Override
    protected String getBuilderParent(Class beanCls, String builderType) {
        String parentName = BgenUtil.className(Construct.Parent.class);
        return parentName + "<" + builderType + ">";
    }
}

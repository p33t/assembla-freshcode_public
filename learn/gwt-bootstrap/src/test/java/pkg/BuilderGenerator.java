package pkg;

import biz.freshcode.b_generation.BeanBuilderWriter;
import biz.freshcode.b_generation.BgenUtil;
import biz.freshcode.b_generation.DefaultBeanBuilderGenerator;
import biz.freshcode.b_generation.PackageMapper;
import biz.freshcode.b_generation.migrate.BuilderScan;
import biz.freshcode.b_generation.migrate.MigrationProbe;
import biz.freshcode.learn.gwt_bootstrap.client.builder.BeanBuilder;
import biz.freshcode.learn.gwt_bootstrap.client.builder.Construct;
import org.gwtbootstrap3.client.ui.Carousel;
import org.gwtbootstrap3.client.ui.InlineHelpBlock;
import org.gwtbootstrap3.client.ui.TextBox;

import java.io.File;
import java.lang.reflect.Method;

import static biz.freshcode.b_generation.BgenStringUtil.join;

public class BuilderGenerator extends DefaultBeanBuilderGenerator {

    private static final PackageMapper MAPPER = new PackageMapper()
            .addMapping("", BeanBuilder.class.getPackage().getName());

    private static final Class[] CLASSES = {InlineHelpBlock.class};  //<<<<<<<<<<<<<<<<<< Bean Class

    public static void main(String[] args) {
        write();
//        probe();
    }

    private static void probe() {
        String msg = new MigrationProbe()
                .packageMapper(MAPPER)
                .builderScan(new BuilderScan().targetAnnotation(BeanBuilder.class))
                .beanClassReader(new MigrationProbe.BeanClassReader() {
                    @Override
                    public Class read(Class builderClass) {
                        BeanBuilder ann = (BeanBuilder) builderClass.getAnnotation(BeanBuilder.class);
                        return ann.value();
                    }
                })
                .probe();
        System.out.println(msg);
    }

    private static void write() {
        new BeanBuilderWriter()
                .generator(new BuilderGenerator())
                .outputFolder(new File("src/main/java")) // The desired output folder
                .packageMapper(MAPPER)           // Map the package of the resulting builder
                .write(CLASSES);                  // The bean class of the desired builder
    }

    /**
     * Add the custom parent class for the builder.
     */
    @Override
    protected String getBuilderParent(Class beanCls, String builderType) {
        String parentName = BgenUtil.className(Construct.Parent.class);
        return parentName + "<" + builderType + ">";
    }

    /**
     * Adds a custom annotation to each builder to facilitate reflective operations
     * like {@link biz.freshcode.b_generation.migrate.MigrationProbe}.
     */
    @Override
    protected String getBuilderAnnotation(@SuppressWarnings("UnusedParameters") Class beanClass) {
        String s = super.getBuilderAnnotation(beanClass);
        s = join(s, "\n", "@" + BeanBuilder.class.getName() + "(" + beanClass.getSimpleName() + ".class)");
        return s;
    }

    @Override
    protected String mapType(Class beanCls, Method m, String type, int argIndex, boolean forJavadoc) {
        if (genericTypeMatch(beanCls, type, TextBox.class, "T"))
            return genericTypeResolve(type, "String", forJavadoc, "T");
        return super.mapType(beanCls, m, type, argIndex, forJavadoc);
    }
}

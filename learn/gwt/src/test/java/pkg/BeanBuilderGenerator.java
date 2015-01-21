package pkg;

import biz.freshcode.b_generation.BeanBuilderWriter;
import biz.freshcode.b_generation.BgenUtil;
import biz.freshcode.b_generation.DefaultBeanBuilderGenerator;
import biz.freshcode.b_generation.PackageMapper;
import biz.freshcode.b_generation.migrate.BuilderScan;
import biz.freshcode.b_generation.migrate.MigrationProbe;
import biz.freshcode.learn.gwt.common.client.builder.BeanBuilder;
import biz.freshcode.learn.gwt.common.client.builder.Construct;
import biz.freshcode.learn.gwt.mod1.client.experiment.forms.HrMinField;
import biz.freshcode.learn.gwt.mod1.client.util.ExceptionUtil;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.*;

import java.lang.reflect.Method;

public class BeanBuilderGenerator extends DefaultBeanBuilderGenerator {
    static final Class CLASS = FileUploadField.class; //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public static final String BASE_PKG = "biz.freshcode.learn.gwt.common.client.builder";

    public static void main(String[] args) {
//        For migrating to new components.
        PackageMapper mapper = new PackageMapper()
                .addMapping("com.sencha.gxt.widget.core.client", BASE_PKG + ".gxt")
                .addMapping("com.sencha.gxt.chart.client", BASE_PKG + ".gxt")
                .addMapping("com.sencha.gxt.core.client", BASE_PKG + ".gxt")
                .addMapping("com.google.gwt.user.client.ui", BASE_PKG + ".gwt")
                // catch all to same package
                .addMapping("", "");
        String str = new MigrationProbe()
                .builderScan(new BuilderScan().targetAnnotation(BeanBuilder.class))
                .beanClassReader(new MigrationProbe.BeanClassReader() {
                    @Override
                    public Class read(Class builderClass) {
                        BeanBuilder ann = (BeanBuilder) builderClass.getAnnotation(BeanBuilder.class);
                        if (ann == null)
                            throw ExceptionUtil.illegalState(builderClass.getName() + " does not have annotation");
                        return ann.value();
                    }
                })
                .packageMapper(mapper)
                .probe();

//        System.out.println(str);

        new BeanBuilderWriter()
                .generator(new BeanBuilderGenerator())
                .packageMapper(mapper)
                .write(CLASS);
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
        if (genericTypeMatch(beanCls, type, SimpleComboBox.class, "C"))
            return genericTypeResolve(type, "T", forJavadoc, "C");
        if (genericTypeMatch(beanCls, type, TextArea.class, "C", "T"))
            return genericTypeResolve(type, "String", forJavadoc, "C", "T");
        if (genericTypeMatch(beanCls, type, HrMinField.class, "C", "T"))
            return genericTypeResolve(type, "Long", forJavadoc, "C", "T");
        if (genericTypeMatch(beanCls, type, TextField.class, "C", "T"))
            return genericTypeResolve(type, "String", forJavadoc, "C", "T");
        if (genericTypeMatch(beanCls, type, NumberField.class, "C", "T"))
            return genericTypeResolve(type, "N", forJavadoc, "C", "T");
        if (genericTypeMatch(beanCls, type, SpinnerField.class, "C", "T"))
            return genericTypeResolve(type, "N", forJavadoc, "C", "T");
        if (genericTypeMatch(beanCls, type, NumericAxis.class, "V"))
            return genericTypeResolve(type, "Number", forJavadoc, "V");
        if (genericTypeMatch(beanCls, type, TextButton.class, "C"))
            return genericTypeResolve(type, "String", forJavadoc, "C");
        return super.mapType(beanCls, m, type, argIndex, forJavadoc);
    }
}

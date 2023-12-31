package pkg;

import biweekly.ICalendar;
import biweekly.property.ICalProperty;
import biz.freshcode.b_generation.BeanBuilderWriter;
import biz.freshcode.b_generation.BgenUtil;
import biz.freshcode.b_generation.DefaultBeanBuilderGenerator;
import biz.freshcode.b_generation.PackageMapper;
import pkg.builder.BeanBuilder;
import pkg.builder.Construct;

import java.lang.reflect.Method;

import static biz.freshcode.b_generation.BgenStringUtil.join;

public class BuilderGen extends DefaultBeanBuilderGenerator {
    public static final Class BEAN_CLS = ICalendar.class;  //<<<<<<<<<<<<<<<<<< The bean class here

    public static void main(String[] args) {
        new BeanBuilderWriter()
                .generator(new BuilderGen())
                .packageMapper(mapper())
                .write(BEAN_CLS);
    }

    private static PackageMapper mapper() {
        return new PackageMapper()
                .addMapping("", "pkg.builder");
    }

    protected static boolean isAddXxx(String name) {
        return name.startsWith("add")
                && (name.length() == 3 || Character.isUpperCase(name.charAt(3)));
    }

    protected static boolean isSetXxx(String name) {
        return name.length() > 3
                && name.startsWith("set")
                && Character.isUpperCase(name.charAt(3));
    }

    protected static boolean isBuilderMethod(String name) {
        return isSetXxx(name) || isAddXxx(name);
    }

    @Override
    protected String getBuilderParent(Class beanCls, String builderType) {
        String parentName = BgenUtil.className(Construct.Parent.class);
        return parentName + "<" + builderType + ">";
    }

    @Override
    protected String getBuilderAnnotation(@SuppressWarnings("UnusedParameters") Class beanClass) {
        String s = super.getBuilderAnnotation(beanClass);
        s = join(s, "\n", "@" + BeanBuilder.class.getName() + "(" + beanClass.getSimpleName() + ".class)");
        return s;
    }

    @Override
    protected boolean isBuilderMethod(Method m) {
        String name = m.getName();
        return super.isBuilderMethod(m) ||
                // Special allowance to propagate convenience builder methods
                (isBuilderMethod(name) && ICalProperty.class.isAssignableFrom(m.getReturnType()));
    }
}

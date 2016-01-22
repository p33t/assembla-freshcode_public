package pkg;

import biweekly.component.VAlarm;
import biz.freshcode.b_generation.BeanBuilderWriter;
import biz.freshcode.b_generation.BgenUtil;
import biz.freshcode.b_generation.DefaultBeanBuilderGenerator;
import pkg.builder.BeanBuilder;
import pkg.builder.Construct;

import static biz.freshcode.b_generation.BgenStringUtil.join;

public class BuilderGen extends DefaultBeanBuilderGenerator {
    public static final Class BEAN_CLS = VAlarm.class;  //<<<<<<<<<<<<<<<<<< The bean class here

    public static void main(String[] args) {
        new BeanBuilderWriter()
                .generator(new BuilderGen())
                .addMapping("", "pkg.builder")
                .write(BEAN_CLS);
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
}

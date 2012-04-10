package pkg;

import biz.freshcode.learn.gwt.client.uispike.builder.BeanBuilder;
import com.google.gwt.user.client.ui.HTMLPanel;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Generates a bean builder so instead of:
 * <pre>
 * Margins m = new Margins();
 * m.setTop(0);
 * m.setRight(5);
 * m.setBottom(0);
 * m.setLeft(5);
 * return m;
 * </pre>
 * we can use
 * <pre>
 * return new MarginsBuilder()
 * .top(0)
 * .right(5)
 * .bottom(0)
 * .left(5)
 * .margins;
 *
 * </pre>
 */
public class BeanBuilderGenerator {
    static final Class CLASS = HTMLPanel.class;


    public static void main(String[] args) {
        System.out.println(generate(CLASS));
    }

    public static String generate(Class cls) {
        String simpleName = cls.getSimpleName();
        String varName = decapitalize(simpleName);
        String builderName = simpleName + "Builder";
        String src = "";

        src += line("import " + cls.getName().replaceAll("\\$", "."));
        src += line("import " + BeanBuilder.class.getName());
        
        src += "\n\n// Generated by " + BeanBuilderGenerator.class.getSimpleName();
        src += "\n@" + BeanBuilder.class.getSimpleName() + "(" + simpleName + ".class)";
        src += "\npublic class " + builderName + " {";
        src += line("  public final " + simpleName + " " + varName);

        // Constructors
        if (hasDefaultConstructor(cls)) {
            src += "\n\n  public " + builderName + "() {";
            src += line("    this(new " + simpleName + "())");
            src += "\n  }";
        }
        src += "\n\n  public " + builderName + "(" + simpleName + " v) {";
        src += line("    " + varName + " = v");
        src += "\n  }";

        // assignment methods
        for (Method m : cls.getMethods()) {
            if (isSetter(m)) {
                src += "\n\n  public " + builderName + " " + shortName(m) + "(" + argType(m) + " v) {";
                src += line("    " + varName + "." + m.getName() + "(v)");
                src += line("    return this");
                src += "\n  }";
            }
        }

        // IDEA: init method to pull all values from another instance?
        
        src += "\n}";
        return src;
    }

    private static boolean hasDefaultConstructor(Class cls) {
        for (Constructor c : cls.getConstructors()) {
            if (c.getParameterTypes().length == 0) return true;
        }
        return false;
    }

    private static String decapitalize(String simpleName) {
        return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
    }

    private static String argType(Method m) {
        return m.getParameterTypes()[0].getCanonicalName();
    }

    private static boolean isSetter(Method m) {
        String name = m.getName();
        return name.length() > 3
                && name.startsWith("set")
                && Character.isUpperCase(name.charAt(3))
                && !Modifier.isPrivate(m.getModifiers())
                && m.getParameterTypes().length == 1;
    }

    /**
     * Will return 'bruce' from method 'setBruce'.
     */
    private static String shortName(Method setter) {
        String setLess = setter.getName().substring(3);
        return decapitalize(setLess);
    }

    private static String line(String contents) {
        return "\n" + contents + ";";
    }
}

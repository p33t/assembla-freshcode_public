package biz.freshcode.learn.gwtp.server.generate;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import java.io.PrintWriter;

/**
 * TODO: Get this working at some point.  Would be nice to have constants to use.
 */
public class MetaGenerator extends Generator {

    @Override
    public String generate(TreeLogger logger,
                           GeneratorContext context,
                           String requestedClass) throws UnableToCompleteException {
        System.out.println("================ Generating meta for " + requestedClass);
        TypeOracle typeOracle = context.getTypeOracle();
        JClassType functionType = typeOracle.findType(requestedClass);
//        assert MetaGen.class.equals(functionType.getClass());

        String pkg = functionType.getPackage().getName();
        String XxxMeta = functionType.getSimpleSourceName() + "Meta";
        ClassSourceFileComposerFactory composerFactory =
                new ClassSourceFileComposerFactory(pkg, XxxMeta);

//        composerFactory.addImport(MetaGen.class.getCanonicalName());
//        composerFactory.addImplementedInterface(MetaGen.class.getName());

        PrintWriter printWriter = context
                .tryCreate(logger, pkg, XxxMeta);
        SourceWriter sourceWriter = composerFactory
                .createSourceWriter(context, printWriter);

        sourceWriter.print("public Object execute() {");
        sourceWriter.print("    return \"Hello\";");
        sourceWriter.print("}");

        sourceWriter.commit(logger);
        return pkg + "." + XxxMeta;
    }
}

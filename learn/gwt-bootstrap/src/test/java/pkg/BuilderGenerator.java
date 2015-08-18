package pkg;

import biz.freshcode.b_generation.BeanBuilderWriter;
import org.gwtbootstrap3.client.ui.Heading;

import java.io.File;

public class BuilderGenerator {
    public static void main(String[] args) {
        new BeanBuilderWriter()
                .outputFolder(new File("src/main/java")) // The desired output folder
                .addMapping("", "biz.freshcode.learn.gwt_bootstrap.client.builder")           // Map the package of the resulting builder
                .write(Heading.class);                  // The bean class of the desired builder
    }
}

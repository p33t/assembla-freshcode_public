package pkg;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.resources.converter.Css2Gss;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CssToGss {
    private static String[] CSSES = {
            "src/main/java/biz/freshcode/learn/gwt2/mod2/client/spike/checkboxcellicon/style.css",
            "src/main/java/biz/freshcode/learn/gwt/client/uibinder/style.css",
            "src/main/java/biz/freshcode/learn/gwt/client/experiment/mouseover/style.css",
            "src/main/java/biz/freshcode/learn/gwt/client/experiment/hoverwidget/reuse/style.css",
            "src/main/java/biz/freshcode/learn/gwt/client/experiment/flextable/style.css",
            "src/main/java/biz/freshcode/learn/gwt/client/experiment/appearance/MyToolBar.css",
            "src/main/java/biz/freshcode/learn/gwt/client/experiment/window/style.css",
            "src/main/java/biz/freshcode/learn/gwt/client/experiment/busy/reuse/style.css",
            "src/main/java/biz/freshcode/learn/gwt/client/experiment/grid/style.css",
            "src/main/java/biz/freshcode/learn/gwt/client/experiment/grid/style2.css",
            "src/main/java/biz/freshcode/learn/gwt/client/experiment/resources/style.css",
            "src/main/java/biz/freshcode/learn/gwt/client/experiment/dnd/style.css",
            "src/main/java/biz/freshcode/learn/gwt/client/experiment/celltable/style.css",
            "src/main/java/biz/freshcode/learn/gwt/client/bug/contentpanelsize/style.css",
            "src/main/webapp/style.css",
    };

    public static void main(String[] args) throws IOException, UnableToCompleteException {
        for (String path: CSSES) {
//            Path p = Paths.get(path);
//            System.out.println(p + " exists " + Files.exists(p));
//            Css2Gss.main(path);
            String alt = new Css2Gss(path).toGss();
            FileUtils.write(new File(path), alt);

        }
    }
}

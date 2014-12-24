package biz.freshcode.learn.gwt.client.experiment.upload;

import biz.freshcode.learn.gwt.client.builder.gxt.container.FlowLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.util.IsWidgetImpl;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import com.sencha.gxt.widget.core.client.info.Info;
import elemental.client.Browser;
import elemental.html.Window;

public class FileUploadDemo extends IsWidgetImpl{
    private final FileUploadField filer;

    public FileUploadDemo() {
        initWidget(new FlowLayoutContainerBuilder()
                .add(filer = new FileUploadField())
//                .add(new TextButton("Choose file...", new SelectEvent.SelectHandler() {
//                    @Override
//                    public void onSelect(SelectEvent event) {
//                        chooseFile();
//                    }
//                }))
                .flowLayoutContainer);
        filer.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                Info.display("Filer changed", "Value is " + filer.getValue());
            }
        });
    }

    private void chooseFile() {
        Window w = Browser.getWindow();
//        w.webkitRequestFileSystem(Window.PERSISTENT, 0, new FileSystemCallback() {
//            @Override
//            public boolean onFileSystemCallback(DOMFileSystem fs) {
////                fs.;
//            }
//        });
    }
}

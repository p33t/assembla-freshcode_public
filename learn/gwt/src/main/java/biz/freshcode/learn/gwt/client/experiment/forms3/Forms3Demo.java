package biz.freshcode.learn.gwt.client.experiment.forms3;

import biz.freshcode.learn.gwt.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.VerticalLayoutContainerBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.field.FieldLabelBuilder;
import biz.freshcode.learn.gwt.client.util.AbstractIsWidget;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class Forms3Demo extends AbstractIsWidget {
    private final Person person = new Person();
    private final PersonFormFields fields = new PersonFormFields();
    private final Driver driver = GWT.create(Driver.class);
    private Dialog dlg = new DialogBuilder()
            .headingText("Forms 3 Demo")
            .width(500)
            .add(new VerticalLayoutContainerBuilder()
                    .add(new FieldLabelBuilder()
                            .text("New Password")
                            .widget(fields.newPassword)
                            .fieldLabel)
                    .verticalLayoutContainer)
            .predefinedButtons()
            .addButton(new TextButton("Done", new SelectEvent.SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    done();
                }
            }))
            .modal(true)
            .dialog;
    private HTML op;

    @Override
    protected Widget createWidget() {

        // initialize
        driver.initialize(fields);

        return new VerticalLayoutContainerBuilder()
                .add(new TextButton("Go", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        go();
                    }
                }))
                .add(op = new HTML("<p>Ready</p>"))
                .verticalLayoutContainer;
    }

    private void go() {
        driver.edit(person);
        dlg.show();
    }

    private void done() {
        dlg.hide();
        driver.flush();
        String result = "Result is: " + person;
        result += "\n Errors: " + driver.getErrors();
        op.setHTML(op.getHTML() + "<p>" + result + "</p>");
    }

    public interface Driver extends SimpleBeanEditorDriver<Person, PersonFormFields> {
    }
}

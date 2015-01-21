package biz.freshcode.learn.gwt.mod1.client.experiment.celltable;

import biz.freshcode.learn.gwt.common.client.builder.gxt.container.BorderLayoutContainerBuilder;
import biz.freshcode.learn.gwt.mod1.client.util.AbstractIsWidget;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class CellTableDemo extends AbstractIsWidget {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    protected Widget createWidget() {
        String highlight = Bundle.INSTANCE.style().highlight();
        return new BorderLayoutContainerBuilder()
                .stylePrimaryName(highlight)
                .northWidget(new HTMLPanel("Phew, resizing and layout are OK."))
                .centerWidget(createTable())
                .borderLayoutContainer;
    }

    /*
    NOTE:
        Copied largely from http://developers.google.com/web-toolkit/doc/latest/DevGuideUiCellWidgets#celltable
     */
    // A simple data type that represents a contact.
    private static class Contact {
        private final String address;
        private final String name;

        public Contact(String name, String address) {
            this.name = name;
            this.address = address;
        }
    }

    // The list of data to display.
    private static List<Contact> CONTACTS = Arrays.asList(
            new Contact("John", "123 Fourth Road"),
            new Contact("Mary", "222 Lancer Lane"));

    private CellTable<Contact> createTable() {

        // Create a CellTable.
        CellTable<Contact> table = new CellTable<Contact>();

        // Create name column.
        TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
            @Override
            public String getValue(Contact contact) {
                return contact.name;
            }
        };

        // Create address column.
        TextColumn<Contact> addressColumn = new TextColumn<Contact>() {
            @Override
            public String getValue(Contact contact) {
                return contact.address;
            }
        };

        // Add the columns.
        table.addColumn(nameColumn, "Name");
        table.addColumn(addressColumn, "Address");

        // Set the total row count. This isn't strictly necessary, but it affects
        // paging calculations, so its good habit to keep the row count up to date.
        table.setRowCount(CONTACTS.size(), true);

        // Push the data into the widget.
        table.setRowData(0, CONTACTS);

        return table;
    }

}

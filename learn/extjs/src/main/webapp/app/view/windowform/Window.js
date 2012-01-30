Ext.define('LE.view.windowform.Window', {
    extend: 'Ext.window.Window',
    requires: ['Ext.ux.form.ItemSelector'],
    alias: 'widget.wfWindow',
    // NOTE: 'hide' was just an experiment.  It retains the prev position and size of window but is not necessary.
    closeAction: 'hide',
    title: 'Hello',
    height: 300,
    width: 400,
    layout: 'fit',
    items: {
        xtype: 'form',
        items: {
            xtype: 'itemselector',
            name: 'itemselector',
            id: 'itemselector-field',
            anchor: '100% 100%',
            labelStyle: {display: 'none'},
            store: Ext.create('Ext.data.ArrayStore', {
                data: [
                    [0, 'No data supplied']
                ],
                fields: ['value','text'],
                sortInfo: {
                    field: 'value',
                    direction: 'ASC'
                }
            }),
            displayField: 'text',
            valueField: 'value',
            allowBlank: false,
            msgTarget: 'side'
        },
        buttons: [
            {
                text: 'Reset',
                handler: function() {
                    this.up('form').getForm().reset();
                }
            },
            {
                text: 'Submit',
                formBind: true, //only enabled once the form is valid
                disabled: true,
                handler: function() {
                    var basicForm = this.up('form');
                    var form = basicForm.getForm();
                    if (form.isValid()) {
                        var values = form.getFieldValues();
                        var win = basicForm.up('wfWindow');
                        win.close();
                        var callback = win.getCallback();
                        callback(values);
                    }
                }
            }
        ]
    },
    config: {
        /**
         * The callback that will be called when 'submit' is pressed.
         * @param result The json object pulled from the form.
         */
        callback: function(result) {
            log('Result from Window Form: ', result);
        },
        /**
         * A 2D array of key/value pairs to place in the item selector.
         */
        data: undefined
    },
    // Will enable the read/write vars
    constructor: function(config) {
        this.callParent(arguments);
        this.initConfig(config);
    },
    initComponent: function() {
        this.callParent(arguments);
        var data = this.getData();
        var store = this.down('itemselector').store;
        if (data !== undefined) {
            store.removeAll();
            var elems = Ext.Array.map(data, function(elem) {
                return {value: elem[0], text: elem[1]};
            });
            store.add(elems);
        }
        else log('No data supplied.');
    },
    // NOTE: This is a little clunky.  Just use 'config' facility.
    launch: function(current) {
        var is = this.down('itemselector');
        is.setValue(current.itemselector);
        this.show();
    }
});
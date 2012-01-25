Ext.define('LE.view.windowform.Window', {
    extend: 'Ext.window.Window',
    requires: ['Ext.ux.form.ItemSelector'],
    alias: 'widget.wfWindow',
    closeAction: 'hide',
    title: 'Hello',
    height: 200,
    width: 400,
    layout: 'fit',
    items: {
        xtype: 'form',
        items: {
            xtype: 'itemselector',
            name: 'itemselector',
            id: 'itemselector-field',
            anchor: '100%',
            fieldLabel: 'Selection',
            store: Ext.create('Ext.data.ArrayStore', {
                data: [
                    [123,'One Hundred Twenty Three'],
                    [1, 'One'],
                    [2, 'Two'],
                    [3, 'Three'],
                    [4, 'Four'],
                    [5, 'Five'],
                    [6, 'Six'],
                    [7, 'Seven'],
                    [8, 'Eight'],
                    [9, 'Nine']
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
                        win.hide();
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
            Ext.iterate(data, function(elem, ix, arr) {
                store.add({value: elem[0], text: elem[1]});
            });
        }
        else log('No data supplied.');
    },
    launch: function(current) {
        var is = this.down('itemselector');
        is.setValue(current.itemselector);
        this.show();
    }
});
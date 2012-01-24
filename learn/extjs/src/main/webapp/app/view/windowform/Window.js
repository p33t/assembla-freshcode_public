Ext.define('LE.view.windowform.Window', {
    extend: 'Ext.window.Window',
    requires: ['Ext.ux.form.ItemSelector'],
    alias: 'widget.wfWindow',
    closeAction: 'hide',
    title: 'Hello',
    height: 200,
    width: 400,
    layout: 'fit',
    onRender: function() {
        this.callParent(arguments);
        log('Rendered wfWindow:', this);
        log('Callback is', this.getCallback());
    },
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
            value: ['3', '4', '6'],
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
                        log("Values: ", values);
                        var win = basicForm.up('wfWindow');
                        log("wfWindow", win);
                        win.hide();
                        var callback = win.getCallback();
                        log("Callback: ", callback);
                        // TODO: Figure out this.
//                        callback(values);
                    }
                }
            }
        ]
    },
    config: {
        callback: function(result) {
            log('Result from Window Form: ', result);
        }
    }
});
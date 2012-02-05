Ext.define('FieldSetDemo', {
    extend: 'Ext.form.FieldSet',
    alias: 'widget.fieldsetdemo',
    title: 'Fieldset Title',
    items: {
        xtype: 'field',
        name: 'myfieldset',
        fieldLabel: 'My FieldSet',
        value: 2
//        allowBlank: false // This value is for TextField (not Base)
    }
});
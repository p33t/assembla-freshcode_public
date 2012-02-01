/**
 * A field representing a dual value.
 */
Ext.define('CoordField', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.coordfield',
    mixins: ['Ext.form.field.Field'],
    items: [
        {
            xtype: 'textfield',
            value: 'abc',
            // need to factor this out
            getModelData: function() {return null;},
            getSubmitData: function() {return null;}
        }
    ]
});
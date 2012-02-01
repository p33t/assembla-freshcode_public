/**
 * A field representing a dual value.
 */
Ext.define('CoordField', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.coordfield',
    mixins: ['Ext.form.field.Field'],
    requires: ['SuppressField'],
    initComponent: function() {
        this.callParent(arguments);
        this.add(Ext.widget('field', Ext.apply({
//            mixins: ['SuppressField'], // causes error
            value: 'abc',
            name: 'neverSubmitted',
            hideLabel: true
        }, SuppressFieldConfig)));
    }
});
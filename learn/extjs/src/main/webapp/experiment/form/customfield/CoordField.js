/**
 * A field representing a dual value.
 */
Ext.define('CoordField', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.coordfield',
    mixins: ['Ext.form.field.Field'],
    layout: 'hbox',
    defaults: {
        // prevent participation if form values / submission.
        getModelData: function() {return null;},
        getSubmitData: function() {return null;},
        hideLabel: true
    },
    items: [
        {
            xtype: 'numberfield',
            dimension: 'x',
            value: 0
        },
        {
            xtype: 'numberfield',
            dimension: 'y',
            value: 0
        }
    ]
});
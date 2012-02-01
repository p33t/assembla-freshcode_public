/**
 * A field representing a dual value.
 */
Ext.define('CoordField', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.coordfield',
    mixins: ['Ext.form.field.Field'],
    initComponent: function() {
        this.callParent(arguments);
        this.add(Ext.widget('field', {
            value: 'abc',
            name: 'neverSubmitted',
            hideLabel: true,

            // Only relevant to Basic.getValues() which is not typed.
//            submitValue: false,
//            getSubmitData: function() {return null;},
//            getSubmitValue: function() {return null;}

            // prevent input from participating
            // the typed data accessor, used during Basic.getFieldValues().
            getModelData: function() {return null;}

        }));
    }
});
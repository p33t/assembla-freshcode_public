/**
 * A field representing a dual value.
 */
Ext.define('CoordField', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.coordfield',
    mixins: {field: 'Ext.form.field.Field'},
    layout: 'hbox',
    defaults: {
        // prevent participation if form values / submission.
        getModelData: function() {return null;},
        getSubmitData: function() {return null;},
        hideLabel: true,
        xtype: 'numberfield',
        maxValue: 10,
        minValue: -10,
        allowBlank: false
    },
    items: [
        {
            value: 0
        },
        {
            value: 0 //'xxx'
        }
    ],
    // TODO: Need to use this in an event handler or something.
    readValue: function() {
        var nums = this.query('numberfield');
        return [nums[0].getValue(), nums[1].getValue()];
    },
    writeValue: function(arr) {
        var nums = this.query('numberfield');

        function process(ix) {
            var dimen = arr[ix];
            if (!Ext.isNumber(dimen)) dimen = null;
            nums[ix].setValue(dimen);
        }

        process(0);
        process(1);
    },
    initComponent: function() {
        this.callParent(arguments);
        this.initField();
    },
    setValue: function(v) {
        log('this', this);
        var result = this.mixins.field.setValue.call(this, v);
        this.writeValue(v);
        return result;
    }
});
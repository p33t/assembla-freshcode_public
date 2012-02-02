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
        allowBlank: false,
        onChange: function(newValue, oldValue) {
            var coord = this.up('coordfield');
            coord.setValue(coord.readValue());
        }
    },
    items: [
        {
            value: 0
        },
        {
            value: 0
        }
    ],
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
        var nums = this.query('numberfield');
        nums[0].resetOriginalValue();
        nums[1].resetOriginalValue();
    },
    setValue: function(v) {
        var result = this.mixins.field.setValue.call(this, v);
        this.writeValue(v);
        return result;
    }
});
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
        msgTarget: 'side',
        onChange: function(newValue, oldValue) {
            var coord = this.up('coordfield');
            var arr = coord.readValue();
            coord.setValue(arr);
        }
    },
    items: [
        {
            value: 0
        },
        {
            value: 0,
            validator: function(v) {
                // NOTE: Designate a convenient control that will do the check and display cross validation messages.
                var numX = this.up('coordfield').down('numberfield');
                if (this.rawToValue(v) === numX.getValue()) return 'Cannot have same x,y coord values.';
                return true;
            }
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
    },
    getErrors: function(v) {
        // NOTE: Don't know how to display these errors onscreen.
        // Cross validation should happen at each or one of the components.

        var errs = this.mixins.field.getErrors.call(this, v);

        // visit each of the components... this also fixes an issue with error propagation and submit button state
        var nums = this.query('numberfield');
        Ext.Array.each(nums, function(num, ix) {
            if (!num.isValid()) errs.push('Error in elem #' + (ix + 1));
        });
        // TODO: Put this aggregate check somewhere
//        if (Ext.isEmpty(errs) && v[0] === v[1]) errs.push('Cannot have same x,y coord values.');
        return errs;
    }
});
/**
 * A field representing a dual value.
 */
Ext.define('CoordField', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.coordfield',
    mixins: {field: 'Ext.form.field.Field'},
    // Errors show when hover over any control
    combineErrors: true,
    layout: 'hbox',
    defaults: {
        // prevent participation in form values / submission.
        getModelData: function() {return null;},
        getSubmitData: function() {return null;},
        hideLabel: true,
        xtype: 'numberfield',
        maxValue: 10,
        minValue: -10,
        allowBlank: false,
        onChange: function(newValue, oldValue) {
            // keep the container value synchronised
            var coord = this.up('coordfield');
            var arr = coord.readValue();
            coord.setValue(arr);
        }
// fields get too long... flex: 1
    },
    items: [
        {
            value: 0
        },
        {
            value: 0
        },
        {
            // NOTE: Nasty hack because FieldContainer can't itself show error messages... don't know why.
            xtype: 'field',
            name: 'errors',
            msgTarget: 'side',
            value: 'x',
            // suppresses display of a control
            inputType: 'hidden',
            // wide enough for error icon
            width: 20
//            flex: 1 // puts error icon on edge
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

        // TODO: This error is not displaying on screen but it is influencing the 'submit' button.
        if (nums[0].getValue() === nums[1].getValue()) errs.push('Cannot have same x,y coord values.');

        var errCtl = this.down('[name=errors]');
        if (Ext.isEmpty(errs)) errCtl.clearInvalid();
        else errCtl.markInvalid(errs);

        return errs;
    }
//    markInvalid : function(errors) {
//        Save the message and fire the 'invalid' event
//        var oldMsg = this.getActiveError();
//        var newMsg = Ext.Array.from(errors);
//        if (oldMsg !== this.getActiveError()) {
//            this.setActiveErrors(newMsg);
//            this.doComponentLayout();
//        }
//    }
});
/**
 * A field representing a simple number that can be editted in multiple ways.
 */
Ext.define('NumSlideField', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.numslidefield',
    mixins: {field: 'Ext.form.field.Field'},
    // Errors show when hover over any control
    combineErrors: true,
    layout: 'hbox',
    defaults: {
        // prevent participation in form values / submission.
        getModelData: function() {return null;},
        getSubmitData: function() {return null;},
        hideLabel: true,
        allowBlank: false,
        onChange: function() {
            // keep the container value synchronised
            var ns = this.up('numslidefield');
            var arr = ns.readValue();
            ns.setValue(arr);
        }
    },
    items: [
        {
            xtype: 'numberfield',
            value: 0,
            maxValue: 10,
            width: 60
        },
        {
            xtype: 'slider',
            value: 0,
            flex: 1,
            listeners:{
//                scope : this, // this breaks it ?!
                change: function(sldr, newVal) {
                    var num = this.up('fieldcontainer').down('numberfield');
                    num.setValue(newVal);
                }
            }
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
        var num = this.down('numberfield');
        return num.getValue();
    },
    writeValue: function(v) {
        var num = this.down('numberfield');
        if (!Ext.isNumber(v)) v = null;
        num.setValue(v);
    },
    initComponent: function() {
        this.callParent(arguments);
        this.initField();
        var num = this.down('numberfield');
        num.resetOriginalValue();
    },
    setValue: function(v) {
        var result = this.mixins.field.setValue.call(this, v);
        this.writeValue(v);
        return result;
    },
    getErrors: function(v) {
        // NOTE: Don't know how to display these errors onscreen.
        var errs = this.mixins.field.getErrors.call(this, v);

        // visit each of the components... this also fixes an issue with error propagation and submit button state
        var num = this.down('numberfield');
        if (!num.isValid()) errs.push('Error');

        // Having the check here influences the 'submit' button.
//        if (nums[0].getValue() === nums[1].getValue()) errs.push('Cannot have same x,y coord values.');

        // Hack: Put errors in the special 'errors' control because FieldContainer is not displaying errors.
        var errCtl = this.down('[name=errors]');
        if (Ext.isEmpty(errs)) errCtl.clearInvalid();
        else errCtl.markInvalid(errs);

        return errs;
    }
});
/**
 * A number field with a slider underneath for small adjustments.
 * NOTE: vTypes don't seem to have a string<->altValue conversion like valueToRaw() and rawToValue() so can't use.
 */
Ext.define('PercentField', {
    extend: 'Ext.form.field.Text',
    alias: 'widget.percentfield',
    regex: Percent.REG_EXP,
    regexText: 'Must be between 0% and 100% inclusive, no decimals, "%" optional.',
    rawToValue: Percent.fromText,
    valueToRaw: Percent.toText,
    // NOTE: processRawValue() doesn't seem to be a replacement for this.
    onBlur: function() {
        // trigger reformat... is there a nicer way?
        if (this.isValid()) this.setValue(this.getValue());
    },
    // needed to enable 'this.callParent(..)' in children.
    validator: function(v) {return true;}
});
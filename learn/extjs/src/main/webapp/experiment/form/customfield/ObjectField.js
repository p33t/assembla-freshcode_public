/**
 * A field that has an 'object' as its value.  It is merely json serialized as a string.
 */
Ext.define('ObjectField', {
    extend: 'Ext.form.field.Base',
    alias: 'widget.objectfield',
    valueToRaw: function(v) {
        log('Encoding', v);
        return Ext.encode(v);
    },
    rawToValue: function(raw) {
        return Ext.decode(raw, true);
    },
    getErrors: function(raw) {
        var arr = this.callParent(arguments);
        if (Ext.isEmpty(arr)) {
            // no errors so far
            if (this.rawToValue(raw) === null) arr.push('Invalid JSON string.');
        }
        return arr;
    }
});
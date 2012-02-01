/**
 * A field representing a dual value.
 */
Ext.define('CoordField', {
    extend: 'Ext.form.field.Base',
    alias: 'widget.coordfield',
    valueToRaw: function(v) {
        log('Encoding', v);
        return Ext.encode(v);
    },
    rawToValue: function(raw) {
        log('Decoding', raw);
        var v = Ext.decode(raw, true);
        log('Got', v);
        return v;
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
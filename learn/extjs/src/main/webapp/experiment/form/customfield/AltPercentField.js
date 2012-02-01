Ext.define('AltPercentField', {
    extend: 'PercentField',
    alias: 'widget.altpercentfield',
    getErrors: function(raw) {
        log('In get error with', raw);
        var arr = this.callParent(arguments);
        log('parent.getErrors() result', arr);
        if (Ext.isEmpty(arr)) {
            // no errors so far
            log('performing own verification');
            // This looks like a bug.
            if (this.rawToValue(raw) === 1) arr.push('Custom check: "100%" is not allowed.');
        }
        return arr;
    }
});
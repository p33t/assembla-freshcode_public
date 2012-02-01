Ext.define('AltPercentField', {
    extend: 'PercentField',
    alias: 'widget.altpercentfield',
    // NOTE: validator is not a template method.  getErrors() is and might be a better alternative.
    // It seems this subclass needs to know the parent class is a 'Text' field.
    validator: function(raw) {
        log('In get error with', raw);
        var result = this.callParent(arguments);
        log('parent.validator() result', result);
        if (result !== true) return result;
        log('performing own verification');
        if (this.rawToValue(raw) === 1) return 'Custom check: "100%" is not allowed.';
        return true;
    }
    /* Alternative 'getErrors()' way.
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
        */
});
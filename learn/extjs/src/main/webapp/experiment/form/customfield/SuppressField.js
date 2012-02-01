/**
 * Mix in to a field to suppress participation in form.getValues() and form.getFieldValues().
 */
var SuppressFieldConfig = {
    getModelData: function() {return null;},
    getSubmitData: function() {return null;}
};
// This mixin isn't working (?!)
Ext.define('SuppressField', SuppressFieldConfig);

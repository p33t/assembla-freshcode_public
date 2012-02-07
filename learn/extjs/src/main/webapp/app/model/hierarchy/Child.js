Ext.define('LE.model.hierarchy.Child', {
    extend: 'Ext.data.Model',
    idgen: 'sequential',
    fields: ['name', 'parent_id']
//    belongsTo: 'hierarchy.Parent'
});

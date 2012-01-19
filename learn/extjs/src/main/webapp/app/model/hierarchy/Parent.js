Ext.define('LE.model.hierarchy.Parent', {
    extend: 'Ext.data.Model',
    idgen: 'sequential',
    fields: ['id', 'name']
//    ,
//    hasMany: {model: 'LE.model.hierarchy.Child', name: 'children'}
});

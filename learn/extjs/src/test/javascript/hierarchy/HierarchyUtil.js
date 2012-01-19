var Hierarchy = {
    boot: function() {
        testApp();
        Ext.define('Child', {
            extend: 'Ext.data.Model',
            idgen: 'sequential',
            fields: ['name', 'parent_id']
//    belongsTo: 'hierarchy.Parent'
        });

        Ext.define('Parent', {
            extend: 'Ext.data.Model',
            idgen: 'sequential',
            fields: ['name']
            // enabling this line makes everything barf
            //hasMany: {model: 'Child', name: 'children'}
        });
    },
    parent: function () {
        return Ext.create('Parent');
    }
};

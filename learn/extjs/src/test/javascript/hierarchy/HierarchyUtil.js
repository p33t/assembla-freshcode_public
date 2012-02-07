var Hierarchy = {
    boot: function() {
        testApp();
//        Ext.syncRequire(['LE.model.hierarchy.Parent', 'LE.model.hierarchy.Child']);
    },
    parent: function () {
        return Ext.create('LE.model.hierarchy.Parent');
    }
};

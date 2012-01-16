Ext.define('EI.model.NumStr', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'num', type: 'int'},
        {name: 'str', type: 'string'}
    ],
    proxy: {
        type: 'jsonp',
        api: {
            create: CONFIG.rootPath.ajax + "/extjsinterop/c",
            read: CONFIG.rootPath.ajax + "/extjsinterop/r",
            update: CONFIG.rootPath.ajax + "/extjsinterop/u",
            destroy: CONFIG.rootPath.ajax + "/extjsinterop/d"
        },
        reader: {
            type: 'json',
            root: 'data'
        }
//        api: {
//            create: PATH_TO_ROOT + "/crud/c",
//            read: PATH_TO_ROOT + "/crud/r",
//            update: PATH_TO_ROOT + "/crud/u",
//            destroy: PATH_TO_ROOT + "/crud/d"
//        }
    }
});

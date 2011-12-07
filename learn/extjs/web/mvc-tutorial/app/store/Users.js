Ext.define('MVC.store.Users', {
    extend: 'Ext.data.Store',
    model: 'MVC.model.User',
    autoLoad: true,

    proxy: {
        type: 'ajax', // there are other types of store proxies
//        Use this instead of 'api' for not writing changes back to server
//        url: 'data/users.json',
        api: {
            read: 'data/users.json',
            update: 'data/updateUsers.json' // .sync() posts changes to here and looks for the success response
        },
        reader: {
            type: 'json', // how to decode
            root: 'users',
            successProperty: 'success'
        }
    }});
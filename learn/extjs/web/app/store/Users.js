Ext.define('LE.store.Users', {
    extend: 'Ext.data.Store',
    model: 'LE.model.User',
    autoLoad: true,

    proxy: {
        type: 'ajax', // there are other types of store proxies
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
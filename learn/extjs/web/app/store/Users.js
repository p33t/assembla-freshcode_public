Ext.define('LE.store.Users', {
    extend: 'Ext.data.Store',
    model: 'LE.model.User',
    autoLoad: true,

    proxy: {
        type: 'ajax', // there are other types of store proxies
        url: 'data/users.json',
        reader: {
            type: 'json', // how to decode
            root: 'users',
            successProperty: 'success'
        }
    }});
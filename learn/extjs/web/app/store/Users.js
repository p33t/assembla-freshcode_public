Ext.define('LE.store.Users', {
    extend: 'Ext.data.Store',
    model: 'LE.model.User',
    data: [
        {name: 'Ed',    email: 'ed@sencha.com'},
        {name: 'Tommy', email: 'tommy@sencha.com'}
    ]
});
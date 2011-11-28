Ext.define('LE.controller.Users', {
    extend: 'Ext.app.Controller',

    // these will be loaded up front
    views: [ 'user.List', 'user.Edit' ],
    stores: ['Users'],
    models: ['User'],

    init: function() {
        this.control({
            userlist: { // v simple ComponentQuery
                itemdblclick: this.editUser
            }
        });
    },

    editUser: function(grid, record) {
        var view = Ext.widget('useredit'); // same as Ext.create('widget.useredit')

        // every component has a 'down' that evaluates a ComponentQuery to identify a child.
        view.down('form').loadRecord(record);
    }
});

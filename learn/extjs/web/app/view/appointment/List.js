Ext.define('LE.view.appointment.List' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.appointmentlist',

    title : 'All Appointments',

    initComponent: function() {
        this.store = 'Diary';

        this.columns = [
            {header: 'Description',  dataIndex: 'description',  flex: 1},
            {header: 'Starting', dataIndex: 'start', flex: 1},
            {header: 'Duration (min)', dataIndex: 'duration', flex: 1}
        ];

        this.callParent(arguments);
    }
});
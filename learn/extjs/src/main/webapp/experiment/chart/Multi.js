Ext.define('Multi', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.chtMulti',
    requires: ['Ext.chart.*'],
    layout: 'fit',
    config: {
        data: [
            [1, 1, 6],
            [2, 4, 5],
            [3, 3, 1]
        ]
//        seriesConfigs: [
//            {
//                name: 's1',
//                color: '#226622',
//                type: 'bar'
//            },
//            {
//                name: 's2',
//                color: '#220022',
//                type: 'bar'
//            }
//        ]
    },
    constructor: function(config) {
        this.callParent(arguments);
        this.initConfig(config);

    },
    initConfig: function(config) {
        this.callParent(arguments);
        var me = this;

        // Construct fields for the data
        var fields = [
            {name: 'x', type: 'float'}
        ];
        var names = ['s1', 's2']; //Ext.Array.pluck(this.getSeriesConfigs(), 'name');
        Ext.iterate(names, function(name) {
            fields.push({name: name, type: 'float'})
        });

        this.add(Ext.widget('chart', {
            legend: true,
            store: Ext.create('Ext.data.ArrayStore', {
                idIndex: 0,
                fields: fields,
                data: me.getData()
            }),
            axes: [
                {
                    type: 'Category',
                    position: 'bottom',
                    fields: ['x'],
                    label: {
                        renderer: Ext.util.Format.numberRenderer('0,0')
                    },
                    title: 'X Axis',
                    grid: true
                },
                {
                    type: 'Numeric',
                    position: 'left',
                    fields: names,
                    title: 'Y Axis',
                    minimum: 0
                }
            ],
            series: [
                {
                    type: 'column',
                    axis: 'left',
                    xField: 'x',
                    yField: names,
                    stacked: true
                }
            ]
        }));
    }
});
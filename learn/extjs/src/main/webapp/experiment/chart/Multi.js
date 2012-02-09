var ix10 = [];
for (var ix = 0; ix <= 10; ix ++) {
    ix10.push(ix);
}
function genData(ixData) {
    var divisor = ixData + 1;
    return Ext.Array.map(ix10, function(ix) {
        if (ix % divisor === 0) return 0.5;
        return ix;
    });
}


Ext.define('Multi', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.chtMulti',
    requires: ['Ext.chart.*'],
    layout: 'fit',
    config: {
        xSeries: {
            // Minutes 0 thru 10
            data: Ext.Array.map(ix10, function(ix) {
                return ix * Period.MIN;
            }),
            title: 'Time'
        },
        stackSeries: [
            {
                name: 's1',
                color: '#226699',
                data: genData(1)
            },
            {
                name: 's2',
                color: '#BB00BB',
                data: genData(2)
            },
            {
                name: 's2',
                color: '#339933',
                data: genData(3)
            }
        ],
        lineSeries: {
            name: 'load',
            color: '#000000',
            data: genData(5)
        }
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
            {name: 'x_axis', type: 'float'}
        ];
        var stackSeries = this.getStackSeries();
        log('stackSeries', stackSeries);
        var stackNames = Ext.Array.pluck(stackSeries, 'name');
        Ext.iterate(stackNames, function(name) {
            fields.push({name: name, type: 'float'})
        });
        var lineSeries = this.getLineSeries();
        log('lineSeries', lineSeries);
        fields.push({name: lineSeries.name, type: 'float'});

        // NOTE: This assume each 'data' field has the correct number of elements (dictated by xSeries)
        var xSeries = this.getXSeries();
        log('xSeries', xSeries);
        var data = Ext.Array.map(xSeries.data, function(xVal, ix) {
            var row = [xVal];
            Ext.iterate(stackSeries, function(ss) {
                row.push(ss.data[ix]);
            });
            row.push(lineSeries.data[ix]);
            return row;
        });
        log('data', data);

        var colors = Ext.Array.pluck(stackSeries, 'color');
        colors.push('#000000'); // for the 'line'
        Ext.chart.theme.chtMulti = Ext.extend(Ext.chart.theme.Base, {
            constructor: function(config) {
                this.callParent([Ext.apply({
                    colors: colors
                }, config)]);
            }
        });

        this.add(Ext.widget('chart', {
            theme: 'chtMulti',
            shadow: false,
            legend: true,
            store: Ext.create('Ext.data.ArrayStore', {
//                idIndex: 0,
                fields: fields,
                data: data
            }),
            axes: [
                {
                    // NOTE: Ideally we'd use a date but Date axis not working so well.
                    type: 'Numeric',
                    position: 'bottom',
                    fields: ['x_axis'],
                    title: 'X Axis',
                    label: {
                        renderer: function(ms) {
                            // uterly useless... cannot escape the timezone
//                            return Ext.Date.format(new Date(ms), '\\WW D H:i Z');
                            return Period.toWeekDayTimeString(ms);
                        }
                    }
                },
                {
                    type: 'Numeric',
                    position: 'left',
                    fields: stackNames,
                    title: 'Y Axis',
                    minimum: 0
                }
            ],
            series: [
                {
                    type: 'column',
                    axis: 'left',
                    yField: stackNames,
                    stacked: true
                }
            ]
        }));
    }
});
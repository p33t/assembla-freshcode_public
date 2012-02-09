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
                color: 'green',
                data: genData(1)
            },
            {
                name: 's2',
                color: 'red',
                data: genData(2)
            },
            {
                name: 's2',
                color: 'blue',
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
    getDataFields: function() {
        // Construct fields for the data
        var fields = [
            {name: 'x_axis', type: 'float'}
        ];
        var stackSeries = this.getStackSeries();
        Ext.iterate(stackSeries, function(ss) {
            fields.push({name: ss.name, type: 'float'})
        });
        var lineSeries = this.getLineSeries();
        fields.push({name: lineSeries.name, type: 'float'});
        return fields;
    },
    getData: function() {
        // NOTE: This assume each 'data' field has the correct number of elements (dictated by xSeries)
        var stackSeries = this.getStackSeries();
        var lineSeries = this.getLineSeries();
        return Ext.Array.map(this.getXSeries().data, function(xVal, ix) {
            var row = [xVal];
            Ext.iterate(stackSeries, function(ss) {
                row.push(ss.data[ix]);
            });
            row.push(lineSeries.data[ix]);
            return row;
        });
    },
    createColorTheme: function(themeName) {
        var colors = Ext.Array.pluck(this.getStackSeries(), 'color');
        Ext.chart.theme[themeName] = Ext.extend(Ext.chart.theme.Base, {
            constructor: function(config) {
                this.callParent([Ext.apply({
                    colors: colors
                }, config)]);
            }
        });
    },
    initConfig: function(config) {
        this.callParent(arguments);
        var me = this;
        var stackNames = Ext.Array.pluck(this.getStackSeries(), 'name');
        var lineSeries = this.getLineSeries();
        this.createColorTheme('chtMulti');

        this.add(Ext.widget('chart', {
            theme: 'chtMulti',
            shadow: false,
            legend: true,
            store: Ext.create('Ext.data.ArrayStore', {
                fields: me.getDataFields(),
                data: this.getData()
            }),
            axes: [
                {
                    // NOTE: Ideally we'd use a date but Date axis not working so well.
                    // Also, Mixed series only works with 'Category'.
                    type: 'Category',
                    position: 'bottom',
                    fields: ['x_axis'],
                    title: 'X Axis',
                    label: {
                        renderer: function(ms) {
                            // utterly useless... cannot escape the timezone
//                            return Ext.Date.format(new Date(ms), '\\WW D H:i Z');
                            return Period.toWeekDayTimeString(ms);
                        }
                    }
                },
                {
                    type: 'Numeric',
                    position: 'left',
                    fields: stackNames.concat([lineSeries.name]),
                    title: 'Y Axis',
                    minimum: 0
                }
            ],
            series: [
                {
                    type: 'column',
                    axis: 'left',
                    yField: stackNames,
                    stacked: true,
                    xField: 'x_axis'
                },
                {
                    type: 'line',
                    showMarkers: false,
                    style: {
                        stroke: 'black',
                        "stroke-width": 2
                    },
                    axis: 'left',
                    yField: lineSeries.name,
                    xField: 'x_axis'
                }
            ]
        }));
    }
});
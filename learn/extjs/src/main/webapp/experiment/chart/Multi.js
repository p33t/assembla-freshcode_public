var ix10 = [];
for (var ix = 0; ix < 10; ix ++) {
    ix10.push(ix);
}
function genData(ixData) {
    var divisor = ixData + 1;
    return Ext.Array.map(ix10, function(ix) {
        if (ix % divisor === 0) return 0;
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
            data: Ext.Array.map(ix10, function(ix) {
                return ix * Period.MIN
            }),
            title: 'Time',
            timeFormat: '\\WW H:i'
        },
        stackSeries: [
            {
                name: 's1',
                color: '#226622',
                data: genData(1)
            },
            {
                name: 's2',
                color: '#220022',
                data: genData(2)
            },
            {
                name: 's2',
                color: '#002200',
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
            {name: 'x-axis', type: 'date', dateFormat: 'time'} // should this be 'timestamp'?  UNIX vs Javascript
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
        var data = Ext.Array.map(xSeries.data, function(xVal, ix){
            var row = [xVal];
            Ext.iterate(stackSeries, function(ss) {
                row.push(ss.data[ix]);
            });
            row.push(lineSeries.data[ix]);
            return row;
        });
        log('data', data);
        this.add(Ext.widget('chart', {
            shadow: false,
            legend: true,
            store: Ext.create('Ext.data.ArrayStore', {
                idIndex: 0,
                fields: fields,
                data: data
            }),
            axes: [
                // TODO: Sort out time.
                {
                    type: 'Category',
                    position: 'bottom',
                    fields: ['x-axis'],
                    title: 'X Axis',
                    grid: true
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
                    xField: 'x',
                    yField: stackNames,
                    stacked: true
                }
            ]
        }));
    }
});
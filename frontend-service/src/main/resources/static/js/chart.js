
Highcharts.StockChart('chart1', {
    data: {
        table: 'datatable'
    },

    plotOptions: {
        series: {
            showInNavigator: true,
            dataGrouping: {
                forced: true,
                units: [
                    ['minute', [1]]
                ]
            }
        }
    },

    rangeSelector: {
        allButtonsEnabled: false,
        buttons: [{
            type: 'hour',
            count: 1,
            text: '1h'
        }, {
            type: 'day',
            count: 1,
            text: '1d'
        }, {
            type: 'week',
            count: 1,
            text: '1w'
        }, {
            type: 'year',
            count: 1,
            text: '1y'
        }, {
            type: 'all',
            text: 'All'
        }],
        buttonTheme: {
            width: 60
        },
        selected: 2
    },


    legend: {
        enabled: true
    },
    chart: {
        type: 'spline', zoomType: 'x', panning: true, pinchType: 'x'
    },
    title: {
        text: ''
    },
    yAxis: {
        allowDecimals: false,
        opposite: false,
        title: {
            text: 'Temperature'
        }
    },
    tooltip: {
        "shared": true
    }
});

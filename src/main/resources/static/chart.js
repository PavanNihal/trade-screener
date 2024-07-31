(function (ctx) {

    let chart;

    function initChart() {                
        getData();
    }

    function addDataToChart(data) {
        let options = getChartOptions();

        options.series = [];

        let series = {};

        series.data = [];
        
        for(const candle of data) {
            const open = candle.open;
            const high = candle.high;
            const low = candle.low;
            const close = candle.close;
            const timestamp = candle.time.split("T")[0];

            series.data.push({x: timestamp, y: [open, high, low, close]});
        }

        options.series.push(series);

        var chart = new ApexCharts(document.querySelector("#chart"), options);

        chart.render();
        
    }

    function getChartOptions() {
        var options = {
            chart: {
                type: 'candlestick'
            }
        }

        return options;
    }

    function getData() {
        fetch('/data/historic')
        .then(response => response.json()) // Parse the response as JSON
        .then(data => {
            addDataToChart(data);
            console.log(data); // Do something with the data
        })
        .catch(error => {
            console.error('Error:', error); // Handle errors
        });
    }

    initChart();

})(this);
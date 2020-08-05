
function dateFomate(time, format){
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : '') + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    });
}

function columnChartAllLinePcb(data){
    var column=Highcharts.chart('container-linePcbYeild',{
        chart: {
            //type: 'area'
        },
        title: {
            text: 'yeid(良率趋势)%'
        },
        subtitle: {
            text: ''
        },
        xAxis: data.xaxis,
        yAxis: {
            title: {
                text: 'yeild',
                min:0,
                max:100
            },
            labels: {
                /* formatter: function () {
                    return this.value / 1000 + 'k';
                } */
            },
            opposite: true
        },
        tooltip: {
            pointFormat: '{series.name} ： {point.y:,.0f}%'
        },
        plotOptions: {
            column: {
                grouping: true,
                shadow: true,
                borderWidth: 0,
                stacking:'normal',
                dataLabels:{enabled:true}
            }
        },
        series: [{
            type:'column',
            name: '直通率%',
            data: [20,
                58, 88, 55, 33, 22, 11, 33 ],
            stack:'0',
            events:{
                click:function(e){
                    alert(e.point.category);
                }

            }
        },{
            type:'column',
            name: '不良率%',
            data: [30,
                28, 21, 17, 14, 13, 12, 12 ],
            stack:'0',
            events:{
                click:function(e){
                    alert(e.point.category);
                }
            }
        },{
            type:'column',
            name: '误报率%',
            data: [50,
                28, 21, 17, 47, 30, 25, 11, ],
            stack:'0',
            events:{
                click:function(e){
                    alert(e.point.category);
                }
            }
        },]

    })


}
function columnChartPcbCount(){
    var column=Highcharts.chart('container-linePcbYeild',{
        chart: {
            //type: 'area'
        },
        title: {
            text: 'yeid(良率趋势)%'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            allowDecimals: false,
            min:0,
            max:7,
            categories:['ALL-Line','SPI-01', 'SPI-02', 'SPI-03', 'SPI-04', 'SPI-05','SPI-06', 'SPI-07']
        },
        yAxis: {
            title: {
                text: 'yeild',
                min:0,
                max:100
            },
            labels: {
                /* formatter: function () {
                    return this.value / 1000 + 'k';
                } */
            },
            opposite: true
        },
        tooltip: {
            pointFormat: '{series.name} ： {point.y:,.0f}%'
        },
        plotOptions: {
            column: {
                grouping: true,
                shadow: true,
                borderWidth: 0,
                stacking:'normal',
                dataLabels:{enabled:true}
            }
        },
        series: [{
            type:'column',
            name: '直通率%',
            data: [20,
                58, 88, 55, 33, 22, 11, 33 ],
            stack:'0',
            events:{
                click:function(e){
                    alert(e.point.category);
                }

            }
        },{
            type:'column',
            name: '不良率%',
            data: [30,
                28, 21, 17, 14, 13, 12, 12 ],
            stack:'0',
            events:{
                click:function(e){
                    alert(e.point.category);
                }
            }
        },{
            type:'column',
            name: '误报率%',
            data: [50,
                28, 21, 17, 47, 30, 25, 11, ],
            stack:'0',
            events:{
                click:function(e){
                    alert(e.point.category);
                }
            }
        },]

    })

}

function  addFunctionAltyPcbLineData(value, row, index){
    return ['<button type="button" id="TablePcbDataDetails"   class="btn btn-primary btn-xs">详情</button>'].join("");
}

function containerlineFn(){
    var containerlineFn = Highcharts.chart('container-lineFn',{
        chart: {
            type: 'area'
        },
        title: {
            text: 'line-Info(缺陷趋势)'
        },
        subtitle: {
            text: ''

        },
        xAxis: {
            allowDecimals: false,
            min:0,
            max:23,
            categories:['1h', '2h', '3h', '4h', '5h','6h', '7h', '8h', '9h', '10h','11h', '12h', '13h', '14h', '15h','16h', '17h', '18h', '19h','20h', '21h', '22h', '23h', '24h',]
        },
        yAxis: {
            title: {
                text: 'count',
                min:0,
                max:1000
            },
            labels: {
                /* formatter: function () {
                    return this.value / 1000 + 'k';
                } */
            }
        },
        tooltip: {
            pointFormat: '{series.name} ： {point.y:,.0f}'
        },
        plotOptions: {
            area: {
                pointStart: 0,
                marker: {
                    enabled: true,
                    symbol: 'circle',
                    radius: 3,
                    states: {
                        hover: {
                            enabled: true
                        }
                    }
                },
                pointPadding: 0.3,
                borderWidth: 0,
                dataLabels:{enabled:true}
            }
        },
        series: [{
            name: 'PCB(个数)',
            data: [
                280, 210, 172, 147, 130, 125, 121, 263,457,52,
                108, 108, 105, 105, 104, 104, 103 , 147, 130, 125, 121 ,35,75,1],
            events:{
                click:function(e){
                    alert(e.point.category);
                }
            }
        },{
            name: '缺陷(个数)',
            data: [
                2800, 2100, 1720, 1470, 1300, 1250, 1210, 2630,4570,520,
                1080, 1080, 1050, 1050, 1040, 1040, 1030 , 1470, 1300, 1250, 1210 ,350,750,10],
            events:{
                click:function(e){
                    alert(e.point.category);
                }
            }
        }]
    });
}



dateFomate =function (time, format){
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
columnChartAllLinePcb =function (data){
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
columnChartPcbCount =function (){
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

addFunctionAltyPcbLineData=function (value, row, index){
    return ['<button type="button" id="TablePcbDataDetails"   class="btn btn-primary btn-xs">详情</button>'].join("");
}



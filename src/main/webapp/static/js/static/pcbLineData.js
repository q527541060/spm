
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



/*function InitPcbLineDataMainTable(basePath){
    var startTime = $("#startTime").val().toString();
    var endTime = $("#endTime").val().toString();
    $('#pcbline_table').bootstrapTable({
        url: basePath+"/sLine/pcbTableLine?inspectStarttime="+startTime+"&inspectEndtime="+endTime,                      //请求后台的URL（*）
        dataType:"json",
        method: 'GET',                      //请求方式（*）
        //data:{inspectStarttime:startTime,inspectEndtime:endTime},
        toolbar: '#lineToolbar',              //工具按钮用哪个容器
        toolbarAlign:'right',
        striped: true,                      //是否显示行间隔色
        cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,
        sortName:'Date',//是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 5,                     //每页的记录行数（*）
        pageList: [15, 20, 50, 100,'ALL'],        //可供选择的每页的行数（*）
        search: true,                      //是否显示表格搜索
        strictSearch: false,
        showColumns: true,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        //showColumnsToggleAll:true,
        // minimumCountColumns: 0,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        height: 400,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        classes:'table table-striped table-hover',
        exportDataType:'all',
        showExport: true,  //是否显示导出按钮
        buttonsAlign:"right",  //按钮位置
        //exportTypes: [ 'csv', 'txt', 'xml', 'excel'],
        Icons:'glyphicon-export icon-share',
        exportOptions:{
            //ignoreColumn: [0],  //忽略某一列的索引
            fileName: "spi_"+$("#startTime").val()+ "_"+$("#endTime").val()+ "_line.csv",  //文件名称设置
            worksheetName: 'sheet1',  //表格工作区名称
            tableName: '缺陷',
            excelstyles: ['background-color', 'color', 'font-size', 'font-weight'],
            //onMsoNumberFormat: DoOnMsoNumberFormat
        },
        rowStyle: function(row, index) {
            if (index % 2 == 0 ) {
                return {
                    classes: 'success',
                }
            }else{
                return {
                    classes: 'info ',
                }
            }
        },
        /!*cellStyle: function (value, row, index){
            return {css:{'font-size':'9px','padding':'0px'}}
        },*!/
        //得到查询的参数
        queryParams : function (params) {
            //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
            return {
                pageSize: params.limit,               //页面大小
                pageNumber: params.offset/params.limit+1,  //页码
                sort: params.sort,      //排序列名
                sortOrder: params.order, //排位命令（desc，asc）
                inspectStarttime:startTime,inspectEndtime:endTime
            };
        },
        columns: [
            /!*{
        checkbox: true,
        align:'center',
        visible: true
        //是否显示复选框
    }, *!/
            {
                field: 'lineNo',
                title: 'lineNo',
                width:50,
                align:'center',
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{'font-size':'9px','padding':'0px'}}
                },
            }, {
                field: 'goodPcbYeild',
                title: 'PassPcbYeild',
                align:'center',
                width:50,
                formatter:function(value,row,index){
                    var goodCount =row.goodarrayCount;
                    var Count=parseInt(row.passarrayCount)+parseInt(row.goodarrayCount)+parseInt(row.ngarrayCount);
                    if(ichoicePcb==1){
                        return '<span>'+row.goodPcbYeild+'</span>';
                    }else if(ichoicePcb==2){
                        goodCount =row.goodarrayCount;
                        Count=parseInt(row.passarrayCount)+parseInt(row.goodarrayCount)+parseInt(row.ngarrayCount);
                        return '<span>'+(goodCount/Count*100).toFixed(2)+'</span>';
                    }else{
                        goodCount =row.goodpadCount;
                        Count=row.totalpadCount;
                        return '<span>'+(goodCount/Count*100).toFixed(2)+'</span>';
                    }

                },cellStyle: function (value, row, index){
                    return {css:{'font-size':'9px','padding':'0px'}}
                },
                sortable: true
            },{
                field: 'passPcbYeild',
                title: 'RePassPcbYeild',
                align:'center',
                sortable:true,
                width:50,
                formatter:function(value,row,index){
                    var passCount =row.passarrayCount;
                    var Count=parseInt(row.passarrayCount)+parseInt(row.goodarrayCount)+parseInt(row.ngarrayCount);
                    //var ngarrayCount=row.ngarrayCount;
                    if(ichoicePcb==1){
                        return '<span>'+row.passPcbYeild+'</span>';
                    }else if(ichoicePcb==2){
                        passCount =row.passarrayCount;
                        Count=parseInt(row.passarrayCount)+parseInt(row.goodarrayCount)+parseInt(row.ngarrayCount);
                        return '<span>'+(passCount/Count*100).toFixed(2)+'</span>';
                    }else{
                        passCount =row.passpadCount;
                        Count=row.totalpadCount;
                        return '<span>'+(passCount/Count*100).toFixed(2)+'</span>';
                    }
                },
                cellStyle: function (value, row, index){
                    return {css:{'font-size':'9px','padding':'0px'}}
                },
                //formatter: linkFormatter
            }, {
                field: 'ngPcbYeild',
                title:  'ngPcbYeild',
                align:'center',
                sortable: true,
                width:50,
                formatter:function(value,row,index){
                    var ngCount =row.ngarrayCount;
                    var Count=parseInt(row.passarrayCount)+parseInt(row.goodarrayCount)+parseInt(row.ngarrayCount);
                    //var ngarrayCount=row.ngarrayCount;
                    if(ichoicePcb==1){
                        return '<span>'+row.ngPcbYeild+'</span>';
                    }else if(ichoicePcb==2){
                        ngCount =row.ngarrayCount;
                        Count=parseInt(row.passarrayCount)+parseInt(row.goodarrayCount)+parseInt(row.ngarrayCount);
                        return '<span>'+(ngCount/Count*100).toFixed(2)+'</span>';
                    }else{
                        ngCount =row.ngpadCount;
                        Count=row.totalpadCount;
                        return '<span>'+(ngCount/Count*100).toFixed(2)+'</span>';
                    }
                },
                cellStyle: function (value, row, index){
                    return {css:{'font-size':'9px','padding':'0px'}}
                },
                //events:operateEvents,
            }, {
                field: 'total',
                title:  'pcbTotal',
                align:'center',
                sortable: true,
                width:50,
                formatter:function(value,row,index){
                    if(ichoicePcb==1){
                        return '<span>'+row.total+'</span>';
                    }else if(ichoicePcb==2){
                        return '<span>'+(parseInt(row.passarrayCount)+parseInt(row.goodarrayCount)+parseInt(row.ngarrayCount))+'</span>';
                    }else{
                        return '<span>'+row.total+'</span>';
                    }

                },
                cellStyle: function (value, row, index){
                    return {css:{'font-size':'9px','padding':'0px'}}
                },
            }, {
                field: 'ngPcbCount',
                title:  'ngPcbCount',
                align:'center',
                sortable: true,
                width:50,
                formatter:function(value,row,index){
                    if(ichoicePcb==1){
                        return '<span>'+row.ngPcbCount+'</span>';
                    }else if(ichoicePcb==2){
                        return '<span>'+row.ngarrayCount+'</span>';
                    }else{
                        return '<span>'+row.ngPcbCount+'</span>';
                    }

                },
                cellStyle: function (value, row, index){
                    return {css:{'font-size':'9px','padding':'0px'}}
                },
            }, {
                field: 'passPcbCount',
                title:  'RePassPcbCount',
                align:'center',
                sortable: true,
                width:50,
                formatter:function(value,row,index){
                    if(ichoicePcb==1){
                        return '<span>'+row.passPcbCount+'</span>';
                    }else if(ichoicePcb==2){
                        return '<span>'+row.passarrayCount+'</span>';
                    }else{
                        return '<span>'+row.passPcbCount+'</span>';
                    }

                },
                cellStyle: function (value, row, index){
                    return {css:{'font-size':'9px','padding':'0px'}}
                },
            }, {
                field:  'ngpadCount',
                title:  'ngpadCount',
                align:  'center',
                sortable: true,
                width:50,
                formatter:function(value,row,index){
                    if(ichoicePcb==1){
                        return '<span>'+row.ngpadCount+'</span>';
                    }else if(ichoicePcb==2){
                        return '<span>'+row.ngarrayCount+'</span>';
                    }else{
                        return '<span>'+row.ngpadCount+'</span>';
                    }

                },
                cellStyle: function (value, row, index){
                    return {css:{'font-size':'9px','padding':'0px'}}
                },
            }, {
                field: 'Date',
                title:  'Date',
                align:'center',
                //sortable: true,
                width:200,
                formatter:function(value,row,index){
                    var html='<span>'+row.inspectStarttime+ '-' +row.inspectEndtime +'</span>';
                    return html;
                },
                cellStyle: function (value, row, index){
                    return {css:{'font-size':'9px','padding':'0px'}}
                },
            },{
                field: 'operation',
                title:  'operation',
                align:'center',
                //sortable: true,
                width:50,
                events: operateEventsPcbLineData,//给按钮注册事件
                formatter: addFunctionAltyPcbLineData,//表格中增加按钮
                cellStyle: function (value, row, index){
                    return {css:{'font-size':'9px','padding':'0px'}}
                },

            }],
        onLoadSuccess: function (sta) {
        },
        onLoadError: function (status,res) {
            console.log(res);
            console.log(status);
        },
        onDblClickRow: function (row, $element) {

        },
        onClickRow:function(row,$element){
            $('.danger').removeClass('danger');
            $($element).addClass('danger');
        }
    });
}
function areaYeildChartPcbCount(basePath){
    // alert($("#startTime").val());
    var startTime = $("#startTime").val()+' 00:00:00';
    var endTime = $("#endTime").val()+' 00:00:00';
    //alert(startTime+"__" +endTime);
    var jsonYeildHour={};
    var jsonContainerline={};
    $.ajax({
        url:basePath+"/sLine/jsonPcbLine",
        dataType:"json",
        async:true,
        data:{inspectStarttime:startTime,inspectEndtime:endTime,mode:iGroupMode,pcbType:ichoicePcb},
        scriptCharset: 'utf-8',
        type:"GET",

        beforeSend:function(){

        },
        success:function(req){
            jsonYeildHour.chart = {
            };
            jsonYeildHour.title ={
                text: 'line-Info(良率分析)'
            };
            jsonYeildHour.subtitle = '';
            jsonYeildHour.tooltip ={formatter: function () {
                    return '<b>' + this.x + '</b><br/>' +
                        this.series.name + ': ' + this.y ;
                }};
            jsonYeildHour.credits={enabled: false };
            jsonYeildHour.xAxis = req.data.xaxis;
            jsonYeildHour.yAxis = {
                title: {
                    text: 'yeild%',
                },min:0,
                max:100,
                labels: {
                },
                //type: 'datetime',
                gridLineWidth:0,
                minorGridLineWidth:0,
                opposite: false,
                //type:'logarithmic'

            };
            jsonYeildHour.exporting={
                enabled:false
            };
            jsonYeildHour.series=req.data.series;
            jsonYeildHour.plotOptions = {
                areaspline: {
                    pointStart: 0,
                    shadow: false,
                    marker: {
                        enabled: true,
                        symbol: 'triangle-down',
                        radius: 1,
                        states: {
                            hover: {
                                enabled: true
                            }
                        }
                    },
                    pointPadding: 0.3,
                    borderWidth: 0,
                    dataLabels:{enabled:true,useHTML: true,
                        formatter: function() {
                            return this.series.name+':'+this.y+"%";
                        },}
                },
                column: {
                    cursor:'pointer',
                    grouping: true,
                    shadow: false,
                    borderWidth: 0,
                    pointPadding: 0,
                    stacking:'normal',
                    dataLabels:{enabled:true,useHTML: true,
                        formatter: function() {
                            return this.series.name+':'+this.y+"%";
                        },},
                    events: {
                        click:function(e){
                            var startTime = $("#startTime").val();
                            var endTime = $("#endTime").val();
                            window.open(basePath+"/sLine/pcbLineDetails?lineNo="+this.xAxis.categories[e.point.x]+"&inspectStarttime="+startTime+ "&inspectEndtime="+ endTime +"&pcbType="+ichoicePcb);
                        }

                    }
                },
                spline:{
                    dataLabels:{enabled:true,useHTML: true,
                        formatter: function() {
                            return this.series.name+':'+this.y+"%";
                        },}
                }
            };
            //alert(json);
            jsonYeildHour.credits={enabled: false };
            jsonYeildHour.legend = {
                enabled:false,
            };
            $('#container-linePcbYeild').highcharts(jsonYeildHour);


            jsonContainerline.chart = {
            };
            jsonContainerline.title ={
                text: 'line-Info(缺陷趋势)'
            };
            jsonContainerline.subtitle = '';
            jsonContainerline.tooltip ={formatter: function () {
                    return '<b>' + this.x + '</b><br/>' +
                        this.series.name + ': ' + this.y ;
                }};
            jsonContainerline.credits={enabled: false };

            jsonContainerline.xAxis = req.data.xaxis;
            var vYaxisType ='logarithmic';
            if(req.data.xaxis.categories.length==1){
                vYaxisType = 'linear';
            }
            jsonContainerline.yAxis =
                {
                    title: {
                        text: 'count',
                    },
                    labels: {
                    },
                    type:vYaxisType,
                    gridLineWidth:0,
                    minorGridLineWidth:0

                };
            jsonContainerline.exporting={
                enabled:false
            };
            jsonContainerline.series=req.rows.series;
            jsonContainerline.plotOptions = {
                areaspline: {
                    pointStart: 0,
                    shadow: false,
                    marker: {
                        enabled: true,
                        symbol: 'triangle-down',
                        radius: 1,
                        states: {
                            hover: {
                                enabled: true
                            }
                        }
                    },
                    pointPadding: 0.3,
                    borderWidth: 0,
                    dataLabels:{enabled:true,useHTML: true,
                        formatter: function() {
                            return (this.series.name)+ ':' +(this.y);
                        },}
                },
                column: {
                    grouping: true,
                    shadow: false,
                    cursor:'pointer',
                    pointPadding: 0,
                    borderWidth: 0,
                    dataLabels:{
                        useHTML: true,
                        enabled:true,
                        formatter: function() {
                            return (this.series.name)+ ':' +(this.y);
                        },
                        style:{
                            fontSize:'12px',
                            fontWeight:'bold',
                            color:'#141328'
                        },
                    },//,color:'#ff0816'
                    events: {
                        click:function(e){
                            var startTime = $("#startTime").val();
                            var endTime = $("#endTime").val();
                            window.open(basePath+"/sLine/pcbLineDetails?lineNo="+this.xAxis.categories[e.point.x]+"&inspectStarttime="+startTime+ "&inspectEndtime="+ endTime +"&pcbType="+ichoicePcb);
                        }

                    }
                },
                spline:{
                    dataLabels:{enabled:true,useHTML: true,
                        formatter: function() {
                            return (this.y)+'pcs';
                        },}
                }
            };
            jsonContainerline.credits={enabled: false };
            jsonContainerline.legend = {
                enabled:false,
            };
            //alert(json);
            $('#container-lineFn').highcharts(jsonContainerline);
        },
        complete:function(){
        },
        error:function(data){
            //alert(data);
            //console.log(data);
        }
    });
    refreshTable(startTime,endTime);
}*/

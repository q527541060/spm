<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<title>Board-Machine-View</title>
		<meta name="viewport"  />
		<%--content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"--%>
		<style>
			body{
				margin: 0px;
				padding: 0px;
				background: url("${staticPath}/static/img/home6.jpg")  ;
				background-size:cover;
			}
			/*h4{
				color: #4cae4c;
				text-align: center;
			}
			.table{
				background-color: #ECF0F5;
			}*/
            .row{
                text-align: center;
                margin: 0px;
                padding: 0px;
            }
		</style>
    </head>
	<body>
		<nav>
            <%@include file="../header.jsp"%>
           <%-- <jsp:include page="header.jsp"></jsp:include>--%>
            <%--<%@include file="header.jsp" %>--%>
			<div class="row" style=" text-align: left;">
				<div class="col-md-14">
					<ol class="breadcrumb">
						<li><a href="${basePath}/Home/pcbHome">Home</a></li>
						<li class="active" ><a  data-toggle="tooltip" data-placement="bottom" title="点击切换至aoi" href="${basePath}/Status/aoi/pcbMonitorview">spi</a></li>
						<li class="active">Board-Machine-RealLineView</li>
					</ol>
				</div>
			</div>
			<div class="row">
			    <div class="col-md-11" >

                    <!-- fpy product -->
                    <div class="row" >
                        <div class="col-md-14">
                           <%-- <div class="right-wap" >--%>  <%--style="height: 350px;"--%>
                                <!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
                                <div id="container-FPY" style="max-height: 800px;height: 700px">
                                </div>
                          <%-- </div>--%>
                               <input type="hidden" id="boardMachineRefreshTime" value="${boardMachineRefreshTime}"/>
                               <input type="hidden" id="Frequency-start" value="${Frequency_start}"/>
                        </div>
                    </div>
                    <!-- top5 -->
                    <div class="row" >
                        <div class="col-md-14">
                          <%--  <div class="right-wap" >--%> <%--style="height: 350px;"--%>
                                <!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
                                <div id="container-defaultTop" style="height: 500px;overflow:auto;"></div>
                           <%-- </div>--%>
                        </div>
                    </div>
                    <!-- cpk -->
                    <div class="row" >
                        <div class="col-md-14" >

                            <%-- <div class="right-wap" > --%> <%--style="height: 200px;">--%>
                            <!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
                            <div id="container-CPK" style="max-height: 200px;height: 200px">

                            </div>
                            <%-- </div>--%>
                            <h4><div class="btn-group-sm" role="group" aria-label="...">
                                <%--<span class="glyphicon glyphicon-flag" aria-hidden="true">&nbsp;</span><i>CPK</i>&nbsp;&nbsp;&nbsp;&nbsp;--%>
                                <button type="button" class="btn btn-sm" onclick="FPYRealTime(this.value)" VALUE="0">area</button>
                                <button type="button" class="btn btn-sm" onclick="FPYRealTime(this.value)" VALUE="1">height</button>
                                <button type="button" class="btn btn-sm" onclick="FPYRealTime(this.value)" VALUE="2">vol</button>
                                <button type="button" class="btn btn-sm" onclick="FPYRealTime(this.value)" VALUE="3">shiftX</button>
                                <button type="button" class="btn btn-sm" onclick="FPYRealTime(this.value)" VALUE="4">shiftY</button>
                            </div></h4>

                        </div>
                    </div>
			   </div>
				<div class="col-md-1">
					<%--<div id="left-wap" style="height: 500px;overflow:auto;">
						<div class="panel panel-info" >--%>
							<table  class="table" id="machineStatus">
							</table>
					<%--	</div>
					</div>--%>
				</div>
			</div>
            <%--<button class="btn btn-default" id="generate-excel" type="submit">导出</button>
            <table class="table table-bordered table-striped" id="test_table">
                <thead>
                <tr>
                    <td>设备编号</td>
                    <td>直通率</td>
                    <td>Top5</td>
                    <td>CPK</td>
                    <td>设备状态</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <span>SPI20</span>
                    </td>
                    <td>
                        <span>99.8% </span>
                    </td>
                    <td>
                        <span>25</span>
                    </td>
                    <td>
                        <span>1.6</span>
                    </td>
                    <td>
                        <span>STOP</span>
                    </td>

                </tr>
                <tr>
                    <td>
                        <span>SPI25</span>
                    </td>
                    <td>
                        <span>99.8% </span>
                    </td>
                    <td>
                        <span>20</span>
                    </td>
                    <td>
                        <span>0.9</span>
                    </td>
                    <td>
                        <span>STOP</span>
                    </td>

                </tr>

                </tbody>
            </table>--%>
		</nav>

        <script type="text/javascript" >  /*src="{staticPath}/js/pcbMonotorview.js" >*/

        window.operateEventsRealLineView={
            "click #MachineListNo" :function(e,value, row, index){
                var Frequency_start = $('#Frequency-start').val();
                var nowDate = new Date();
                var startTime = dateFomate(nowDate.setDate(nowDate.getDate()+0),'yyyy-MM-dd') +" "+Frequency_start+":00:00";
                var endTime =  dateFomate(new Date(),'yyyy-MM-dd HH:mm:ss');
                window.location.href="${basePath}/sLine/pcbLineDetails?lineNo="+row.lineNo+"&inspectStarttime="+startTime + "&inspectEndtime="+ endTime;
            }
        }
        var StatusQueryUrl = '${basePath}/Status/pcbMonitorJson';
        var vValue=1;
        var refreshSecon = $('#boardMachineRefreshTime').val();
        if(refreshSecon==0){
            refreshSecon=10;
        }
        InitMainTable();
        FPYRealTime(vValue);
        //ProductRealTime();
        //defaultTopRealTime();
        var  pcbTotal =0;
        function FPYRealTime(value){
            vValue = value;
            //alert(vValue);
            var json = {};
            $.ajax({
                url: "${basePath}/Status/pcbMonitorview_realLineViewJson?aValue="+value,
                dataType:"json",   //返回格式为json
                async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                data:'',    //参数值
                type:"GET",   //请求方式
                beforeSend:function(){
                    //请求前的处理
                },
                success:function(req){

                    pcbTotal = req.rows;
                    pcbTotal = pcbTotal+pcbTotal/4;
                    //请求成功时处理
                    //json.chart = req.data.chart;
                    json.chart ={
                        zoomType: 'xy'
                    };
                    json.title = {text:'FPY、Product',style: {
                            fontWeight: 'bold',
                            fontSize:"22px",
                            color: '#5cccff'// (Highcharts.theme &&
                            // Highcharts.theme.textColor) ||
                        }};
                    //json.subtitle = {text:'FPY、Product'};
                    json.plotOptions={
                        spline:{
                            dataLabels:{enabled:true,useHTML: true,} //,color:'#0f100b'
                        },
                        column:{
                            pointPadding: 0,
                            borderWidth: 0,
                            dataLabels:{
                                useHTML: true,
                                enabled:true,
                                formatter: function() {
                                    return (this.series.name)+ ':' +(this.y);
                                },
                                style:{
                                    fontSize:'5px',
                                    fontWeight:'bold',
                                    //color:'#141328'
                                },
                            }//,color:'#ff0816'
                        },

                    };
                    json.credits={enabled: false };
                    json.xAxis = req.data[0].xaxis;
                    /*json.xAxis =  [{
                        categories: ['SPI20', 'SPI25', 'SPI30'],
                        //crosshair: true
                    }];*/
                    //json.yAxis = req.data.yaxis;
                    json.yAxis =
                    [
                        { // Secondary yAxis
                            title: {
                                text: '',
                            },
                            labels: {
                                format: '{value}pcs',
                                /*style: {
                                    color: Highcharts.getOptions().colors[1]
                                }*/
                            },
                            opposite: true,
                            minorGridLineWidth:0,
                            min:0,
                            max:pcbTotal,
                            //type:'category'
                            //type: 'logarithmic'

                        },
                        {
                            labels: {
                                format: '{value}%',
                            },
                            title: {
                               text: '',
                                /*style: {
                                    color: Highcharts.getOptions().colors[0]
                                }*/
                            },
                            minorGridLineWidth:0,
                            max:100,
                            min:0,
                            tickInterval:20,
                            type:'category'
                            //opposite: false
                        },
                    ];
                    json.tooltip = {
                        formatter: function () {
                            return '<b>' + this.x + '</b><br/>' +
                                this.series.name + ': ' + this.y ;//+ '<br/>' +
                            //'value: ' + this.point.stackTotal;
                        }
                        //shared: true
                    };
                    json.series = req.data[0].series;
					json.exporting={
						enabled:false
					};

					//cpk
                    var jsonCPK={};
                    jsonCPK.title={
                        text: 'CPK',
                        style: {
                            fontWeight: 'bold',
                            fontSize:"22px",
                            color: '#5cccff'// (Highcharts.theme &&
                            // Highcharts.theme.textColor) ||
                        }};
                    jsonCPK.series=req.data[1].series;
                    jsonCPK.legend={
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'middle',
                    };
                    jsonCPK.tooltip={formatter: function () {
                            return '<b>' + this.x + '</b><br/>' +
                                this.series.name + ': ' + this.y ;
                        }};
                    jsonCPK.xAxis=req.data[0].xaxis;
                    /*jsonCPK.xAxis={
                        categories: ['SPI20', 'SPI25', 'SPI30']
                    };*/
                    //jsonCPK.yAxis=req.rows.yaxis;
                    jsonCPK.yAxis={
                        title:'',
                        minorGridLineWidth:0,
                        gridLineWidth:'0px',
                        width:2,
                        //min:0.1,
                        //type: 'logarithmic',

                    };
                    //dataLabels:{enabled:true},

                     jsonCPK.plotOptions = {
                        series: {
                            lineWidth:1,
                            //allowPointSelect :true,
                            //animation:true,
                            //lineColor:'blue',
                            //enableMouseTracking:true,//显示提示框
                            dataLabels:{
                                useHTML: true,
                                enabled:true,

                            },//,colo
                            marker: {
                                enabled:true,
                                radius: 6,//点的大小
                                //fillColor: '#DD7272',
                                //lineColor: '#DEECF9',
                                //lineWidth:5,
                                shadow:false,
                                states:{
                                    hover:{
                                        radius: 10,
                                        enabled:true,
                                    }
                                }
                            }
                        }
                    };
                    jsonCPK.exporting={
                        enabled:false
                    };
                    jsonCPK.credits={enabled: false };

                    //top5
                    var jsonDefault = {};
                    //jsonDefault.chart = req.data.chart;
                    jsonDefault.chart = {

                    };
                    jsonDefault.title = {
                        text:'TOP5',
                        style: {
                            fontWeight: 'bold',
                            fontSize:"22px",
                            color: '#5cccff'// (Highcharts.theme &&
                            // Highcharts.theme.textColor) ||
                        }
                    };
                    jsonDefault.subtitle = '';
                    jsonDefault.tooltip = {
                        formatter: function () {
                            return '<b>' + this.x + '</b><br/>' +
                                this.series.name + ': ' + this.y;//+ '<br/>' +
                            //'value: ' + this.point.stackTotal;
                        }
                    };
                    jsonDefault.legend = {
                        enabled:false,
                    };
                    jsonDefault.xAxis = req.data[0].xaxis;
                    //jsonDefault.yAxis = req.data.yaxis;
                    jsonDefault.yAxis =
                        {
                            min:0,
                            title:'',
                            minorGridLineWidth:0,
                            stackLabels:
                                {
                                    enabled: true,
                                    allowOverlap: true,
                                    style:
                                        {
                                            fontWeight: 'bold',
                                            fontSize:"22px",
                                            color: '#5cccff'// (Highcharts.theme &&
                                            // Highcharts.theme.textColor) ||
                                        },
                                },
                            //type: 'logarithmic',
                            //tickPixelInterval:5
                            //tickLength: 20
                            //min:0,
                        };
                    jsonDefault.series = req.data[2].series;

                    jsonDefault.exporting={
                        enabled:false
                    };
                    jsonDefault.credits = {enabled: false};
                    jsonDefault.plotOptions={
                        column:{
                            borderWidth: 0,
                            dataLabels:{
                                useHTML: true,
                                enabled:true,
                                formatter: function() {
                                    return (this.series.name)+ ':' +(this.y);
                                },
                                style:{
                                    fontSize:'5px',
                                    fontWeight:'bold',
                                    color:'#141328'
                                },
                            },//,color:'#ff0816'
                        },
                    };
                    //console.log(JSON.stringify(json));
                    $("#container-defaultTop").highcharts(jsonDefault);
                    //alert('come');
                    $('#container-CPK').highcharts(jsonCPK);
                    //alert('come');
                    $('#container-FPY').highcharts(json);
                },
                complete:function(){
                    //请求完成的处理
                },
                error:function(){
                    //请求出错处理
                }
            });
        }
        function CPKRealTime(value) {
            $.ajax({
                url: "${basePath}/Pcb/FPY",
                dataType:"json",   //返回格式为json
                async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                data:{aValue:value},  //参数值
                type:"GET",   //请求方式
                success:function(req){
                    var jsonCPK={};
                    jsonCPK.title={
                        text: 'CPK',
                        style: {
                            fontWeight: 'bold',
                            fontSize:"22px",
                            color: '#5cccff'// (Highcharts.theme &&
                            // Highcharts.theme.textColor) ||
                        }};
                    //jsonCPK.series=req.rows.series;
                    jsonCPK.series=[{
                        name: 'CPK',
                        type: 'line',
                        data: [{y:1.6,color:'#25dd19'},{y:0.6,color:'#dd1127'},{y:0.8,color:'#dd1127'}],
                        lineWidth:0,
                        connectEnds:false
                    },{
                        name: 'StandCPK',
                        type: 'line',
                        data: [1, 1, 1],

                    },];
                    jsonCPK.legend={
                        layout: 'vertical',
                            align: 'right',
                            verticalAlign: 'middle',
                    };
                    jsonCPK.tooltip={formatter: function () {
                            return '<b>' + this.x + '</b><br/>' +
                                this.series.name + ': ' + this.y ;
                        }};
                    //jsonCPK.xAxis=req.rows.xaxis;
                    jsonCPK.xAxis={
                        categories: ['SPI20', 'SPI25', 'SPI30']
                    };
                    //jsonCPK.yAxis=req.rows.yaxis;
                    jsonCPK.yAxis={
                        title:'0.0f',
                        minorGridLineWidth:0,
                        gridLineWidth:'0px',
                        width:0
                    };
                    jsonCPK.plotOptions={line: {
                            pointPadding: 0,
                            borderWidth: 0,
                            dataLabels:{enabled:true}
                        },column: {
                            pointPadding: 0,
                            borderWidth: 0,
                            dataLabels:{enabled:true,useHTML: true,}
                        }
                    };
					jsonCPK.exporting={
						enabled:false
					};
                    jsonCPK.credits={enabled: false };
                    //alert('come');
                    $('#container-CPK').highcharts(jsonCPK);
                    //alert('come');
                }
            });


        }
        <!-- defaultTop -->
        function defaultTopRealTime(){
            $.ajax({
                url: "${basePath}/Pcb/FPY",
                dataType:"json",   //返回格式为json
                async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                data:"",  //参数值
                type:"GET",   //请求方式
                beforeSend:function(){
                    //请求前的处理
                },
                success:function(req){
                    //请求成功时处理
                    if(req.success == true) {
                        var jsonDefault = {};
                        //jsonDefault.chart = req.data.chart;
                        jsonDefault.chart = {

                        };
                        jsonDefault.title = {
                            text:'TOP5',
                            style: {
                                fontWeight: 'bold',
                                fontSize:"22px",
                                color: '#5cccff'// (Highcharts.theme &&
                                // Highcharts.theme.textColor) ||
                            }
                        };
                        jsonDefault.subtitle = '';
                        jsonDefault.tooltip = {
                            formatter: function () {
                                return '<b>' + this.x + '</b><br/>' +
                                    this.series.name + ': ' + this.y;//+ '<br/>' +
                                //'value: ' + this.point.stackTotal;
                            }
                        };
                        jsonDefault.legend = {
                            enabled:false,
                        };
                        //jsonDefault.xAxis = req.data.xaxis;
                        jsonDefault.xAxis = {
                            categories: ['SPI20', 'SPI25', 'SPI30']
                        }
                        //jsonDefault.yAxis = req.data.yaxis;
                        jsonDefault.yAxis =
                        {
                            min:0.1,
                            title:'',
                            minorGridLineWidth:0,
                            stackLabels:
                            {
                                enabled: true,
                                allowOverlap: true,
                                style:
                                {
                                fontWeight: 'bold',
                                fontSize:"22px",
                                color: '#5cccff'// (Highcharts.theme &&
                                // Highcharts.theme.textColor) ||
                                },
                            },
                            type: 'logarithmic',
                            //tickLength: 20
                            //min:0,
                        };
                        //jsonDefault.series = req.data.series;
                        jsonDefault.series = [
                            {
                                type: 'column',
                                name: 'Missing',
                                data: [300, 0, 0],
                                stacking:'normal'
                            }, {
                                type: 'column',
                                name: 'Insufficient',
                                data: [200, 0, 0],
                                stacking:'normal'
                            }, {
                                type: 'column',
                                name: 'Excess',
                                data: [4,0,5],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Overheight',
                                data: [4, 0, 2],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Lowheight',
                                data: [4, 0, 3],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Overarea',
                                data: [0, 23, 1],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Lowarea',
                                data: [0, 3, 3],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Shiftx',
                                data: [0, 3, 0],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Shifty',
                                data: [0, 2, 0],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Bridge',
                                data: [0, 1, 0],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Shapeerror',
                                data: [0, null, 0],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Smeared',
                                data: [0, null, 0],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Coplanarity',
                                data: [0, null, 0],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Prebridge',
                                data: [0, null, 0],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Padareapercent',
                                data: [0, null, 0],
                                stacking:'normal'
                            },
                        ];

                        jsonDefault.exporting={
							enabled:false
						};
                        jsonDefault.credits = {enabled: false};
                        jsonDefault.plotOptions={
                            column:{

                                borderWidth: 0,
                                dataLabels:{
                                    useHTML: true,
                                    enabled:true,
                                    formatter: function() {
                                        return (this.series.name)+ ':' +(this.y);
                                    },
                                    style:{
                                        fontSize:'5px',
                                        fontWeight:'bold',
                                        color:'#141328'
                                    },

                                },//,color:'#ff0816'
                            },


                        };
                        //console.log(JSON.stringify(json));
                        $("#container-defaultTop").highcharts(jsonDefault);

                    }else{
                        alert(req.message);
                    }
                },
                complete:function(){
                    //请求完成的处理
                },
                error:function(message){
                    console.log(message);
                }
            });
        }

        function  addFunctionAltyRealLineView(value, row, index) {
            if(row.error==1 ){
                return ['<span class="span-machineno">'+row.lineNo+'</span><br>'].join("")+['<image style="cursor:pointer;width:70px;height:60px" id="MachineListNo"  src="${staticPath}/img/spi2_red.jpg">'].join("");
               // return ['<span id="TableNGImage"  style="cursor:pointer"  class="glyphicon glyphicon-picture">'+row.lineNo+"-红灯"+'</span>'].join("");
            }else  if(row.stop==1){
                return ['<span class="span-machineno">'+row.lineNo+'</span><br>'].join("")+['<image id="MachineListNo"  style="cursor:pointer;width:70px;height:60px" src="${staticPath}/img/spi2_yellow.jpg">'].join("");
            }else{
                return ['<span class="span-machineno">'+row.lineNo+'</span><br>'].join("")+['<image id="MachineListNo"  style="cursor:pointer;width:70px;height:60px" src="${staticPath}/img/spi2_green.jpg">'].join("");
            }

        }
        <!--   设备状态js代码  -->
        var $table;
        function InitMainTable () {
        	//alert('come in..');
            //记录页面bootstrap-table全局变量$table，方便应用
            $table = $('#machineStatus').bootstrapTable({
                url: StatusQueryUrl,                      //请求后台的URL（*）
                dataType:"json",
                method: 'GET',                      //请求方式（*）
                //toolbar: '#toolbar',              //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: false,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                //sortOrder: "desc",                   //排序方式
                //sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                //pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
                //pageSize: 5,                     //每页的记录行数（*）
                //pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                search: false,                      //是否显示表格搜索
                strictSearch: false,
                showColumns: false,                  //是否显示所有的列（选择显示的列）
                showRefresh: false,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                  //是否显示父子表
                classes:'table table-striped table-hover',
                rowStyle: function(row, index) {
                    var classes = [
                        'bg-blue',
                        'bg-green',
                        'bg-red'
                    ]
                    if (index % 2 === 0 ) {
                        return {
                            classes: 'success'
                        }
                    }else{
                        return {
                            classes: 'info '
                        }
                    }
                },
                //得到查询的参数
                queryParams : function (params) {
                    //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
                    return {
                        pageSize: params.limit,               //页面大小
                        pageNumber: params.offset/params.limit+1,  //页码
                        sort: params.sort,      //排序列名
                        sortOrder: params.order //排位命令（desc，asc）
                    };
                },
                columns: [
				 {
                    field: 'lineNo',
                    title: 'Machine-List',
                    align:'center',
                    width:30,
                     sortable: true,
                     events: operateEventsRealLineView,
                     formatter: addFunctionAltyRealLineView,
                    cellStyle: function (value, row, index){
                        if(row.status == 1) { return {css:{"background-color":"D9534F"}}  }
                    },
                    cellStyle:function(value,row,index){
                        if(row.error==1) {
                            return {css: {"color": "#FF0000"}}
                        }else{
                            return {css: {"color": "#333333"}}
                        }
                    }

                }],
                onLoadSuccess: function (sta) {
                    //console.log("in onLoadSuccess");
                    //console.log(sta);
                },
                onLoadError: function (status,res) {
                    //showTips("数据加载失败！");
                    //alert("onLoadError");
                    console.log(res);
                    console.log(status);
                },
                onDblClickRow: function (row, $element) {
                    var line = row.lineNo;
                    vlineNo = line;
                    //EditViewById(id, 'view');
                    //alert(line);
                    //defaultTopRealTime(line);
                    //ProductRealTime(line);
                },
                onClickRow: function (row, $element) {
                    var line = row.lineNo;
                    vlineNo = line;
                    //EditViewById(id, 'view');
                    //alert(line);
                    //defaultTopRealTime(line);
                    //ProductRealTime(line);
                },
            });

        };

        function refreshTable(){
			var opt = {
				url: StatusQueryUrl,
				silent: true,
				query:{
					type:1,
					level:2
				}
			};
			$("#machineStatus").bootstrapTable('refresh', opt);

		}
		function refreshFPYRealTime(){
            FPYRealTime(vValue);
        }
		setInterval(refreshTable,refreshSecon*1000);//,FPYRealTime,CPKRealTime,defaultTopRealTime,
        setInterval(refreshFPYRealTime,refreshSecon*1000*2);
        //setInterval(CPKRealTime,10000);
        //setInterval(defaultTopRealTime,10000);
		//window.setInterval(timeShow,1000);
        $(function () {
            $('[data-toggle="tooltip"]').tooltip();
        })
      /* excel = new ExcelGen({
            "src_id": "test_table",
            "show_header": true
        });
        $("#generate-excel").click(function () {
            excel.generate();
        });*/
        </script>
        <style type="text/css">
				/*body{
					margin: 0px;
					padding: 0px;
					background-color: #ECF0F5;
				}*/
				h4{
					color: #4cae4c;
					text-align: center;
				}
				.table{
					background-color: #ECF0F5;
				}
                /*#TableNGImage{
                    cursor: pointer;
                }*/
           /* .span-machineno{
                font-weight:bold;
                fontSize:12px;
                color: rgba(0, 0, 0, 0.84);
            }*/

			</style>
	</body>

			
</html>

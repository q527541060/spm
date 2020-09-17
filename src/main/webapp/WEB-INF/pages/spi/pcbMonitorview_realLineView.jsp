<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>Board-Machine-View</title>
		<meta name="viewport"  />
		<%--content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"--%>
		<style>
			body{
				margin: 0px;
				padding: 0px;
				/*background: url("$staticPath}/static/img/white-pictue.png");*/
				/*background-size:cover;*/
                /*background-color: #FFFFFF;*/
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
            #machineStatusLeft{
                float: right;
                /*table-layout: fixed;
                word-break:break-all;*/
            }
            #machineStatusRight{
                float: right;
               /* table-layout: fixed;
                word-break:break-all;*/
            }
		</style>
    </head>
	<body>
		<nav>
            <%@include file="../header.jsp"%>
           <%-- <jsp:include page="header.jsp"></jsp:include>--%>
            <%--<%@include file="header.jsp" %>--%>

			<div class="row" style="text-align: left;margin-top: -5px">
				<div class="col-md-14" >
                        <ol class="breadcrumb" >
                            <li><a href="${basePath}/Home/pcbHome">Home</a></li>
                            <li class="active" ><a  data-toggle="tooltip" data-placement="bottom" title="点击切换至aoi" href="${basePath}/Status/aoi/pcbMonitorview">spi</a></li>
                            <li class="active">Board-Machine-RealLineView</li>
                            <li>
                                <div  class="btn-group" role="group" aria-label="..." >
                                    <button type="button" class="btn btn-primary btn-xs"data-toggle="tooltip" data-placement="bottom" title="良率按大板分析" onclick="changeChartView(1)">pcb</button>
                                    <button type="button" class="btn btn-primary btn-xs"data-toggle="tooltip" data-placement="bottom" title="良率按小拼板分析" onclick="changeChartView(2)">array</button>
                                    <button type="button" class="btn btn-primary btn-xs"data-toggle="tooltip" data-placement="bottom" title="良率按焊盘点位分析" onclick="changeChartView(3)">position</button>
                                </div>
                            </li>
                        </ol>

				</div>
			</div>

			<div class="row" style="margin-top: -25px">
			    <div class="col-md-11" style="padding: 0;margin: 0px">
                 <%--   <marquee  behavior="right">SMT-01直通率70%</marquee>--%>
                    <!-- fpy product -->
                    <div class="row" >
                        <div class="col-md-14">
                           <%-- <div class="right-wap" >--%>  <%--style="height: 350px;"--%>
                                <!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
                                <div id="container-FPY" style="max-height: 50vh;height: 50vh;overflow:auto;">
                                </div>
                          <%-- </div>--%>

                        </div>
                    </div>
                    <!-- top5 -->
                    <div class="row" >
                        <div class="col-md-14">
                          <%--  <div class="right-wap" >--%> <%--style="height: 350px;"--%>
                                <!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
                                <div id="container-defaultTop" style="max-height: 35vh;height: 35vh;overflow:auto;"></div>
                           <%-- </div>--%>
                        </div>
                    </div>
                    <!-- cpk -->
                    <div class="row" >
                        <div class="col-md-14" >
                            <%-- <div class="right-wap" > --%> <%--style="height: 200px;">--%>
                            <!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
                            <div id="container-CPK" style="max-height: 18vh;height: 18vh;overflow:auto;">

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
				<div class="col-md-1" style="padding: 0;margin: 0">
					<%--<div id="left-wap" style="height: 500px;overflow:auto;">
						<div class="panel panel-info" >--%>
                    <div style="float: right;">
                        <table  class="table" id="machineStatusLeft" cellpadding="0px" cellspacing="0px" >
                        </table>
                   </div>
                    <div style="float: right;margin-right: 2px">
                        <table  class="table" id="machineStatusRight" cellpadding="0px" cellspacing="0px">
                        </table>
                    </div>

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
            <input type="hidden" id="boardMachineRefreshTime" value="${boardMachineRefreshTime}"/>
            <input type="hidden" id="Frequency-start" value="${Frequency_start}"/>
            <input type="hidden" id="passPcbYeild" value="${passPcbYeild}">
            <input type="hidden" id="boardView-chartMove" value="${boardView_chartMove}">
		</nav>

        <script type="text/javascript" >  /*src="{staticPath}/js/pcbMonotorview.js" >*/

        var mode = 1;
        window.operateEventsRealLineView={
            "click #MachineListNo" :function(e,value, row, index){
                var Frequency_start = $('#Frequency-start').val();

                var nowDate = new Date();
                var startTime = dateFomate(nowDate.setDate(nowDate.getDate()+0),'yyyy-MM-dd') +" "+Frequency_start+":00:00";
                var endTime =  dateFomate(new Date(),'yyyy-MM-dd HH:mm:ss');
                window.location.href="${basePath}/sLine/pcbLineDetails?lineNo="+row.lineNo+"&inspectStarttime="+startTime + "&inspectEndtime="+ endTime;
            }
        };
        var StatusQueryUrl = '${basePath}/Status/pcbMonitorJson';
        var vValue=1;
        var refreshSecon = $('#boardMachineRefreshTime').val();
        var passPcbYeild = $('#passPcbYeild').val();
        if(passPcbYeild==null||passPcbYeild==""){
            passPcbYeild=85;
        }
        var bChartMove = $('#boardView-chartMove').val();
        if(bChartMove==null||bChartMove==""){
            bChartMove = true;
        }else if(bChartMove=='0'){
            bChartMove =false;
        }else{
            bChartMove = true;
        }
        if(refreshSecon==0){
            refreshSecon=10;
        }
        InitMainTableLeft();
        InitMainTableRight();
        FPYRealTime(vValue,mode);
        //ProductRealTime();
        //defaultTopRealTime();
        var  pcbTotal =0;
        function changeChartView(x){
            mode = x;
            FPYRealTime(vValue,mode);
        }
        function FPYRealTime(value,x){
            vValue = value;
           // alert(value+mode);
            var json = {};
            $.ajax({
                url: "${basePath}/Status/pcbMonitorview_realLineViewJson?aValue="+value+"&mode="+mode,
                dataType:"json",   //返回格式为json
                async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                data:'',    //参数值
                type:"GET",   //请求方式
                beforeSend:function(){
                    //请求前的处理
                },
                success:function(req){

                    //var xAxisjsonData = req.data[0].xaxis;
                    pcbTotal = Number(req.rows.iTotal);
                    pcbTotal = pcbTotal+pcbTotal/4;
                    //请求成功时处理
                    //json.chart = req.data.chart;
                    json.chart ={
                        zoomType: 'xy',
                        //animation:false
                    };
                    json.title = {
                        text:'FPY、Product',
                        style: {
                            fontWeight: 'bold',
                            fontSize:"22px",
                            color: '#5cccff',
                           // backgroundColor: 'green',
                           // border:'2px'
                            // (Highcharts.theme &&
                            // Highcharts.theme.textColor) ||
                        }};
                    //json.subtitle = {text:'FPY、Product'};
                    json.plotOptions={
                        /*bar:{
                            borderColor:'red',
                            borderWidth:2,
                        },*/
                        series:{
                            groupPadding : 0.1,
                            pointPadding : 0,
                            animation: bChartMove,
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
                            //fontSize:"22px",
                        },
                        spline:{
                            dataLabels:{enabled:true,useHTML: true,
                                formatter: function() {
                                    return (this.y)+'%';
                                },} //,color:'#0f100b'
                        },
                        column:{
                            //borderColor:'#ff2514',
                            //borderWidth:10,
                            //selected:true,
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
                                    color:'#141328'
                                },
                            }//,color:'#ff0816'
                        },


                    };
                    json.credits={enabled: false };
                    json.legend = {
                        enabled:false,
                    };
                    json.xAxis = req.data[0].xaxis;

                    json.xAxis.labels={
                        format: '{value}',
                        style: {
                            //color: Highcharts.getOptions().colors[1],
                            fontSize:'14px',
                            fontWeight:'bold'
                        }
                    }
                    /*json.xAxis.plotBands= [{ // mark the weekend
                        color: '#FCFFC5',
                        from: -0.5,
                        to: 0.5,
                    }]*/
                    json.yAxis =
                    [
                        { // Secondary yAxis
                            title: {
                                text: '',
                            },
                            labels: {
                                format: '{value}p',
                                style: {
                                    //color: Highcharts.getOptions().colors[1],
                                    //fontSize:'16px'
                                }
                            },
                            opposite: true,
                            gridLineWidth:0,
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
                            gridLineWidth:0,
                            minorGridLineWidth:0,
                            max:100,
                            min:0,
                            tickInterval:20,
                            type:'category',
                            plotLines:[{
                                color:'#d7d906',           //线的颜色，定义为红色
                                dashStyle:'Dash',     //默认值，这里定义为实线
                                value:passPcbYeild,               //定义在那个值上显示标示线，这里是在x轴上刻度为3的值处垂直化一条线
                                //width:1                //标示线的宽度，2px
                            }]
                            //opposite: false
                        },
                    ];
                    json.tooltip = {
                        //animation:true,
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
                        enabled:false,
                    };
                    jsonCPK.tooltip={formatter: function () {
                            return '<b>' + this.x + '</b><br/>' +
                                this.series.name + ': ' + this.y ;
                        }};
                    jsonCPK.xAxis=req.data[1].xaxis;
                    jsonCPK.xAxis.labels={
                        format: '{value}',
                        style: {
                            //color: Highcharts.getOptions().colors[1],
                            fontSize:'14px',
                            fontWeight:'bold'
                        }
                    }
                    jsonCPK.yAxis={
                        title:'',
                        minorGridLineWidth:0,
                        //gridLineWidth:'0px',
                        //width:2,
                        gridLineWidth:0,
                        max:req.rows.maxCpk,
                        min:0,
                        //type:'category',
                        //type: 'logarithmic',
                        plotLines:[{
                            color:'#d9c01d',           //线的颜色，定义为红色
                            dashStyle:'Dash',     //默认值，这里定义为实线
                            value:req.rows.standCPK,//Number(req.rows.standCPK),               //定义在那个值上显示标示线，这里是在x轴上刻度为3的值处垂直化一条线
                           // width:3                //标示线的宽度，2px
                        }]
                    };
                    //dataLabels:{enabled:true},
                     jsonCPK.plotOptions = {
                        series: {
                            lineWidth:1,
                            //allowPointSelect :true,
                            animation:bChartMove,  //动画
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
                    jsonDefault.xAxis = req.data[1].xaxis;
                    jsonDefault.xAxis.labels={
                        format: '{value}',
                        style: {
                            //color: Highcharts.getOptions().colors[1],
                            fontSize:'14px',
                            fontWeight:'bold'
                        }
                    }
                    //jsonDefault.yAxis = req.data.yaxis;
                    jsonDefault.yAxis =
                        {
                            min:0,
                            title:'',
                            minorGridLineWidth:0,
                            gridLineWidth:0,
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
                        series:{
                            animation: bChartMove,
                            groupPadding : 0.1,
                            pointPadding : 0
                        },
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

        function  addFunctionAltyRealLineView(value, row, index) {
                if(row.error==1 ){
                    //(row.errContent==''?'':' ['+row.errContent+']')+
                    return ['<i data-toggle="tooltip" data-placement="bottom" title="'+row.errContent+'" id="MachineListNo" style="cursor:pointer;">'+row.lineNo+'</i ><br>']+['<image data-toggle="tooltip" data-placement="bottom" title="'+row.errContent+'" style="cursor:pointer;width:27px;height:20px" id="MachineListNo"  src="${staticPath}/img/spi2_red.jpg">'].join("");
                   // return ['<span id="TableNGImage"  style="cursor:pointer"  class="glyphicon glyphicon-picture">'+row.lineNo+"-红灯"+'</span>'].join("");
                }else  if(row.stop==1){
                    //(row.errContent==''?'':' ['+row.errContent+']')+
                    return ['<i  id="MachineListNo" style="cursor:pointer;">' +row.lineNo+'</i><br>']+['<image id="MachineListNo"  style="cursor:pointer;width:27px;height:20px" src="${staticPath}/img/spi2_yellow.jpg">'].join("");
                }else{
                    //(row.errContent==''?'':' ['+row.errContent+']')+
                    return ['<i  id="MachineListNo" style="cursor:pointer;">'+row.lineNo+'</i><br>']+['<image id="MachineListNo"  style="cursor:pointer;width:27px;height:20px" src="${staticPath}/img/spi2_green.jpg">'].join("");
                }
        }
        <!--   设备状态js代码  -->
        var $table;
        function InitMainTableLeft () {
        	//alert('come in..');
            //记录页面bootstrap-table全局变量$table，方便应用
            $table = $('#machineStatusLeft').bootstrapTable({
                url: StatusQueryUrl+"?lane="+0,                      //请求后台的URL（*）
                dataType:"json",
                method: 'GET',                      //请求方式（*）
                //data:{lane:0},
                //toolbar: '#toolbar',              //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: false,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                //width:3,
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
                    if (index % 2 == 0 ) {
                        return {
                            classes: 'success',
                        }
                    }else{
                        return {
                            classes: 'info',
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
                columns:
                    [
                     {
                        field: 'lineNo',
                        title: 'L1',
                        align:'center',
                        width:3,
                         sortable: true,
                         events: operateEventsRealLineView,
                         formatter: addFunctionAltyRealLineView,
                        cellStyle: function (value, row, index){
                            if(row.status == 1) { return {css:{"background-color":"D9534F"}}  }
                        },
                        cellStyle:function(value,row,index){
                            if(row.error==1) {
                                return {css: {"color": "#FF0000","padding":0}}
                            }else{
                                return {css: {"color": "#333333","padding":0}}
                            }
                        },
                    },
                    ],
                onLoadSuccess: function (sta) {
                    $("#machineStatusLeft").css("width","5vh");
                },
                formatNoMatches:function(){
                    return "无";
                }
            });
        };
        function InitMainTableRight(){
             $('#machineStatusRight').bootstrapTable({
                url: StatusQueryUrl+"?lane="+1,                        //请求后台的URL（*）
                dataType:"json",
                method: 'GET',                      //请求方式（*）
                //data:{lane:1},
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
                // width:3,
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
                    if (index % 2 == 0 ) {
                        return {
                            classes: 'info'
                        }
                    }else{
                        return {
                            classes: 'success'
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
                columns:
                    [
                        {
                            field: 'lineNo',
                            title: 'L2',
                            align:'center',
                            width:3,
                            sortable: true,
                            events: operateEventsRealLineView,
                            formatter: addFunctionAltyRealLineView,
                            cellStyle: function (value, row, index){
                                if(row.status == 1) { return {css:{"background-color":"D9534F"}}  }
                            },
                            cellStyle:function(value,row,index){
                                if(row.error==1) {
                                    return {css: {"color": "#FF0000","padding":0}}
                                }else{
                                    return {css: {"color": "#333333","padding":0}}
                                }
                            },
                        },
                    ],
                onLoadSuccess: function (sta) {
                    $("#machineStatusRight").css("width","5vh");
                },formatNoMatches:function(){
                return "无";
            }
            });
        }
        function refreshTableLeft(){
			var opt = {
				url: StatusQueryUrl+"?lane="+0,
				silent: true,
				query:{
					type:1,
					level:2
				}
			};
			$("#machineStatusLeft").bootstrapTable('refresh', opt);

		}
        function refreshTableRight(){
            var opt = {
                url: StatusQueryUrl+"?lane="+1,
                silent: true,
                query:{
                    type:1,
                    level:2
                }
            };
            $("#machineStatusRight").bootstrapTable('refresh', opt);

        }
		function refreshFPYRealTime(){
            FPYRealTime(vValue,mode);
        }
        setInterval(refreshTableLeft,refreshSecon*1000);
		setInterval(refreshTableRight,refreshSecon*1000);//,FPYRealTime,CPKRealTime,defaultTopRealTime,
        setInterval(refreshFPYRealTime,refreshSecon*1000*2);
        //setInterval(CPKRealTime,10000);
        //setInterval(defaultTopRealTime,10000);
		//window.setInterval(timeShow,1000);

      /* excel = new ExcelGen({
            "src_id": "test_table",
            "show_header": true
        });
        $("#generate-excel").click(function () {
            excel.generate();
        });*/
       /*document.body.onclick = function(){
            this.webkitRequestFullscreen();
        }*/
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
                i{
                    font-size: 5px;
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

<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%--<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
	String staticPath = basePath + "/static";
	request.setAttribute("basePath", basePath);
	request.setAttribute("staticPath", staticPath);
%>--%>
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
			    <div class="col-md-10">
                    <!-- fpy product -->
                    <div class="row">
                        <div class="col-md-14">
                            <div class="right-wap" style="height: 250px;">
                                <!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
                                <div id="container-FPY" style="min-width: 100%; height: 100%; margin: 0 auto">
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- top5 -->
                    <div class="row">
                        <div class="col-md-14">
                            <div class="right-wap" style="height: 350px;">
                                <!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
                                <div id="container-defaultTop" style="max-width:100%;height:100%"></div>
                            </div>
                        </div>
                    </div>
                    <!-- cpk -->
                    <div class="row">
                        <div class="col-md-14">

                            <div class="right-wap" style="height: 250px;">
                                <!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
                                <div id="container-CPK" style="min-width:310px;height:100%;margin: 0 auto">
                                </div>
                            </div>
                            <h4><div class="btn-group-sm" role="group" aria-label="...">
                                <%--<span class="glyphicon glyphicon-flag" aria-hidden="true">&nbsp;</span><i>CPK</i>&nbsp;&nbsp;&nbsp;&nbsp;--%>
                                <button type="button" class="btn btn-sm" onclick="CPKRealTime(this.value)" VALUE="0">area</button>
                                <button type="button" class="btn btn-sm" onclick="CPKRealTime(this.value)" VALUE="1">height</button>
                                <button type="button" class="btn btn-sm" onclick="CPKRealTime(this.value)" VALUE="2">vol</button>
                                <button type="button" class="btn btn-sm" onclick="CPKRealTime(this.value)" VALUE="3">shiftX</button>
                                <button type="button" class="btn btn-sm" onclick="CPKRealTime(this.value)" VALUE="4">shiftY</button>
                            </div></h4>
                        </div>
                    </div>
			   </div>
				<div class="col-md-1 col-md-offset-1">
					<div id="left-wap" style="height: 500px;overflow:auto;">
						<div class="panel panel-info" >
							<table  class="table" id="machineStatus">
							</table>
						</div>
					</div>
				</div>
			</div>
			<%--<div class="row">
			  <div class="col-md-8">
				  <h4 ><span class="glyphicon glyphicon-leaf" aria-hidden="true">&nbsp;</span><i>FPY</i></h4>
				  <div class="right-wap" style="height: 250px;">
				  	<!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
				  	<div id="container-FPY" style="min-width: 310px; height: 100%; margin: 0 auto">
					</div>
				  </div>
			  </div>
			  <div class="col-md-4">
				  <h4><span class="glyphicon glyphicon-equalizer" aria-hidden="true">&nbsp;</span><i>Default Top5</i></h4>
				  <div class="right-wap" style="height: 250px;">
				  	<!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
				  	<div id="container-defaultTop" style="max-width:800px;height:100%"></div>
					</div>
				  </div>
			  </div>
			</div>
			<div class="row">
			  <div class="col-md-6">
					<h4><span class="glyphicon glyphicon-object-align-bottom" aria-hidden="true">&nbsp;</span><i>Product</i></h4>
					<div class="right-wap" style="height: 250px;">
						<!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
						<div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div>
					</div>
			  </div>		
			  <div class="col-md-6">
			  				  <h4><div class="btn-group-sm" role="group" aria-label="...">
								  <span class="glyphicon glyphicon-flag" aria-hidden="true">&nbsp;</span><i>CPK</i>&nbsp;&nbsp;&nbsp;&nbsp;
								  <button type="button" class="btn btn-sm" onclick="CPKRealTime(this.value)" VALUE="0">area</button>
								  <button type="button" class="btn btn-sm" onclick="CPKRealTime(this.value)" VALUE="1">height</button>
								  <button type="button" class="btn btn-sm" onclick="CPKRealTime(this.value)" VALUE="2">vol</button>
								  <button type="button" class="btn btn-sm" onclick="CPKRealTime(this.value)" VALUE="3">shiftX</button>
								  <button type="button" class="btn btn-sm" onclick="CPKRealTime(this.value)" VALUE="4">shiftY</button>
							  </div></h4>
			  				  <div class="right-wap" style="height: 250px;">
			  				  	<!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
									<div id="container-CPK" style="min-width:310px;height:100%;margin: 0 auto">
									</div>
			  					</div>
			  				  </div>
			  </div>
			</div>--%>
		</nav>

        <script type="text/javascript" >  /*src="{staticPath}/js/pcbMonotorview.js" >*/

        window.operateEventsRealLineView={
            "click #TableNGImage" :function(e,value, row, index){
                 window.location.href="${basePath}/sLine/pcbLineDetails?lineNo="+row.lineNo;
            }
        }
        var StatusQueryUrl = '${basePath}/Status/pcbMonitorJson';
        var iTop1count=0;
        var iTop2count=0;
        var iTop3count=0;
        var iTop4count=0;
        var iTop5count=0;
        var fristLineNo ="";
        var vlineNo="";
        InitMainTable();
        FPYRealTime();
        //ProductRealTime();
        CPKRealTime();
        defaultTopRealTime();
        function FPYRealTime(){
            var json = {};
            $.ajax({
                url: "${basePath}/Pcb/FPY",
                dataType:"json",   //返回格式为json
                async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                data:"",    //参数值
                type:"GET",   //请求方式
                beforeSend:function(){
                    //请求前的处理
                },
                success:function(req){
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
                            dataLabels:{enabled:true} //,color:'#0f100b'
                        },
                        column:{
                            pointPadding: 0.2,
                            borderWidth: 0,
                            dataLabels:{enabled:true}//,color:'#ff0816'
                        },

                    };
                    json.credits={enabled: false };
                    //json.xAxis = req.data.xaxis;
                    json.xAxis =  [{
                        categories: ['SPI20', 'SPI25', 'SPI30'],
                        //crosshair: true
                    }];
                    //json.yAxis = req.data.yaxis;
                    json.yAxis =  [{ // Secondary yAxis
                        title: {
                            text: '',
                        },
                        labels: {
                            format: '{value} pcs',
                            /*style: {
                                color: Highcharts.getOptions().colors[1]
                            }*/
                        },
                        opposite: true,
                        minorGridLineWidth:0,
                        min:0,
                        max:400
                    },{ // Primary yAxis
                        labels: {
                            format: '{value}%',
                        },
                        title: {
                           text: '',
                            /*style: {
                                color: Highcharts.getOptions().colors[0]
                            }*/
                        },minorGridLineWidth:0,
                        max:100,
                        min:0,
                    }];
                    json.tooltip = {
                        formatter: function () {
                            return '<b>' + this.x + '</b><br/>' +
                                this.series.name + ': ' + this.y ;//+ '<br/>' +
                            //'value: ' + this.point.stackTotal;
                        }
                        //shared: true
                    };
                    //json.series = req.data.series;
                    json.series= [{
                        name: 'PCB->PASS',
                        type: 'column',
                        data: [266, 200, 202],
                        tooltip: {
                            valueSuffix: 'pcs'
                        },
                        stacking:'normal',
                        color:'#13dd15'
                    },{
                        name: 'PCB->REPASS',
                        type: 'column',
                        data: [0, 20, 0],
                        tooltip: {
                            valueSuffix: 'pcs'
                        },
                        stacking:'normal',
                        color:'#4449dd'
                    },{
                        name: 'PCB->NG',
                        type: 'column',
                        data: [0, 1, 50],
                        tooltip: {
                            valueSuffix: 'pcs'
                        },
                        stacking:'normal',
                        color:'#dd0f31'
                    },{
                        name: '直通率',
                        type: 'spline',
                        yAxis: 1,
                        data: [93.9, 99.5, 80.9],
                        tooltip: {
                            valueSuffix: '%'
                        },
                        color:'#7bdd18'
                    }];
					json.exporting={
						enabled:false
					};
                    /*iTop1count = req.rows.iTop1Count;
                    iTop2count =req.rows.iTop2Count;
                    iTop3count =req.rows.iTop3Count;
                    iTop4count =req.rows.iTop4Count;
                    iTop5count =req.rows.iTop5Count;
                    fristLineNo = req.rows.fristLineNo;*/
                    $('#container-FPY').highcharts(json);
                    vlineNo = fristLineNo;
                    //defaultTopRealTime(fristLineNo);
                   // ProductRealTime(fristLineNo);
                },
                complete:function(){
                    //请求完成的处理
                },
                error:function(){
                    //请求出错处理
                }
            });
        }

        function CPKRealTime() {
            $.ajax({
                url: "${basePath}/Pcb/FPY",
                dataType:"json",   //返回格式为json
                async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                data:{},  //参数值
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
                        data: [1.6, 0.3, 2.2],
                        lineWidth:0,
                        connectEnds:false
                    },{
                        name: 'UCL',
                        type: 'line',
                        data: [2, 2, 2],
                        lineWidth:0,
                        connectEnds:false
                    },{
                        name: 'LCL',
                        type: 'line',
                        data: [1, 1, 1],
                        lineWidth:0,
                        connectEnds:false
                    }];
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
                            pointPadding: 0.2,
                            borderWidth: 1,
                            dataLabels:{enabled:true}
                        },column: {
                            pointPadding: 0.2,
                            borderWidth: 1,
                            dataLabels:{enabled:true}
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
                data:{},  //参数值
                type:"GET",   //请求方式
                beforeSend:function(){
                    //请求前的处理
                },
                success:function(req){
                    //请求成功时处理
                    if(req.success == true) {
                        var jsonDefault = {};
                        //jsonDefault.chart = req.data.chart;
                        /*jsonDefault.chart = {
                        };*/
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
                        jsonDefault.plotOptions={
                            spline:{
                                dataLabels:{enabled:true} //,color:'#0f100b'
                            },
                            column:{
                                pointPadding: 0.2,
                                borderWidth: 0,
                                dataLabels:{enabled:true}//,color:'#ff0816'
                            },

                        };
                        //jsonDefault.xAxis = req.data.xaxis;
                        jsonDefault.xAxis = {
                            categories: ['SPI20', 'SPI25', 'SPI30']
                        }
                        //jsonDefault.yAxis = req.data.yaxis;
                        jsonDefault.yAxis = {
                            title:'count',
                            minorGridLineWidth:0,
                            stackLabels: {
                                enabled: true,
                                allowOverlap: true,
                                style: {
                                    fontWeight: 'bold',
                                    fontSize:"22px",
                                    color: '#5cccff'// (Highcharts.theme &&
                                    // Highcharts.theme.textColor) ||
                                },
                                formatter: function(){
                                    if(this.total==0){
                                        return "";
                                    }else{
                                        return this.total;
                                    }
                                }
                            }
                        };
                        //jsonDefault.series = req.data.series;
                        jsonDefault.series = [
                            {
                                type: 'column',
                                name: 'missing',
                                data: [3, 0, 0],
                                stacking:'normal'
                            }, {
                                type: 'column',
                                name: 'Insufficient',
                                data: [2, 0, 0],
                                stacking:'normal'
                            }, {
                                type: 'column',
                                name: 'Excess',
                                data: [4, 0, 3],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Overheight',
                                data: [4, 0, 3],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Lowheight',
                                data: [4, 0, 3],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Overarea',
                                data: [0, 3, 3],
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
                                data: [0, 11, 0],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Bridge',
                                data: [0, 12, 0],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Shapeerror',
                                data: [0, 0, 0],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Smeared',
                                data: [0, 0, 0],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Coplanarity',
                                data: [0, 0, 0],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Prebridge',
                                data: [0, 0, 0],
                                stacking:'normal'
                            },{
                                type: 'column',
                                name: 'Padareapercent',
                                data: [0, 0, 0],
                                stacking:'normal'
                            },];
						jsonDefault.exporting={
							enabled:false
						};
                        jsonDefault.credits = {enabled: false};
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
                return ['<span>'+row.lineNo+'</span>'].join("")+['<image style="cursor:pointer;width:80px;height:60px;" src="${staticPath}/img/spi2_red.jpg">'].join("");
               // return ['<span id="TableNGImage"  style="cursor:pointer"  class="glyphicon glyphicon-picture">'+row.lineNo+"-红灯"+'</span>'].join("");
            }else  if(row.stop==1){
                return ['<span>'+row.lineNo+'</span>'].join("")+['<image style="cursor:pointer;width:80px;height:60px;" src="${staticPath}/img/spi2_yellow.jpg">'].join("");
            }else{
                return ['<span>'+row.lineNo+'</span>'].join("")+['<image style="cursor:pointer;width:80px;height:60px;" src="${staticPath}/img/spi2_green.jpg">'].join("");
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
                    /*formatter: function (value, row, index){
                        //row.status ==0?"停止":row.status==1?"故障":"运行";
						if(row.error==1){
							return row.lineNo+"-error";
						}else  if(row.run==1 && row.start==1){
							return "run";
						}else if(row.idle==1){
							return  row.lineNo+"-idel";
						}else{
							return  row.lineNo+"-stop";
						}
                        /!*switch (row.status) {
                            case 0:
                                return "run";
                            case  3:
                                return  "stop";
                            case 2:
                                return  "idel";
                            case  1:
                                return "error";
                            default :
                                return "run";
                        }*!/
                        return ;
                    },*/
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

        /*function timeShow() {

            var  startTime_str = ("2018-11-09 20:19:00").replace(/-/g,"/");;
            var startTime= new Date(startTime_str);
            var date = new Date(+new Date()+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'')
            document.getElementById("timeShow").innerText=date;
        }*/
        //$("#status-refresh").click(refreshTable);

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
		setInterval(refreshTable,5000);//,FPYRealTime,CPKRealTime,defaultTopRealTime,
        setInterval(FPYRealTime,10000);
        setInterval(CPKRealTime,10000);
        setInterval(defaultTopRealTime,10000);
		//window.setInterval(timeShow,1000);
        $(function () {
            $('[data-toggle="tooltip"]').tooltip();
        })
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

			</style>
	</body>

			
</html>

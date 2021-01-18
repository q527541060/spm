<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8" >
		<%--<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">--%>
		<title>pcbLineData</title>
		<link rel="stylesheet icon" href="${staticPath}/static/img/logo.jpg" type="image/x-icon" media="screen" />
		<style>
			body{
				margin: 0px;
				padding: 0px;
				/*background-color: #ECF0F5;*/
               /* background: url("$staticPath}/static/img/home6.jpg")  ;*/
             /*   background-size:cover;*/
			} 
			.row{
				text-align: center;
				margin: 0px;
			}
			.row-border{
				/*background-color: #F5F5F5;color: #F5F5F5;height: 25px*/
                margin-top: 15px;
			}
		</style>
	</head>
	<body>
    <nav>
		<%@include file="../header.jsp"  %>
		<div class="row" style=" text-align: left;">
			<div class="col-md-4" style="padding: 0px">
				<ol class="breadcrumb"  style="float: left;margin: 0px">
					<li><a href="${basePath}/Home/pcbHome">Home</a></li>
					<li class="active"><c:if test="${aoiType==1}">pre-aoi</c:if><c:if test="${aoiType==2}">post-aoi</c:if><%--<a  data-toggle="tooltip" data-placement="bottom" title="点击切换至spi" href="${basePath}/Home/pcbHome">pre-aoi</a>--%></li>
					<%--<li class="active">line</li>--%>
					<li class="active">dataInfo</li>
					<li>
						<div class="btn-group" role="group" aria-label="...">
							<button type="button" class="btn btn-primary btn-xs"data-toggle="tooltip" data-placement="bottom" title="良率按小时排序"  onclick="choiceChart_PreAoi(0)">Hour</button>
							<button type="button" class="btn btn-primary btn-xs" data-toggle="tooltip" data-placement="bottom" title="良率按线体排序" onclick="choiceChart_PreAoi(1)">Lines</button>
							<button type="button" class="btn btn-primary btn-xs"data-toggle="tooltip" data-placement="bottom" title="良率按大板分析"  onclick="choicePcb_PreAoi(1)">pcb</button>
							<button type="button" class="btn btn-primary btn-xs" data-toggle="tooltip" data-placement="bottom" title="良率按小拼板分析" onclick="choicePcb_PreAoi(2)">array</button>
							<button type="button" class="btn btn-primary btn-xs" data-toggle="tooltip" data-placement="bottom" title="良率按元器件分析" onclick="choicePcb_PreAoi(3)">position</button>
						</div>
					</li>
				</ol>
				<%--<div style="float: left;margin-left: 16%;padding: 5px" id="sandBox-container">
					<!-- glyphicon glyphicon-time   col-lg-offset-4-->
					&lt;%&ndash;<span class="glyphicon glyphicon-time" aria-hidden="true"></span>&ndash;%&gt;
					<span  style="margin-left: -19px;" class="glyphicon glyphicon-calendar"></span>
					<input size="12" type="text" value="" readonly class="form-date" id="startTime" /> -
					<input  size="12"  type="text"  value="" readonly class="form-date" id="endTime"/>
					<!-- glyphicon glyphicon-search-->
					<button type="button" class="btn  btn-info btn-xs"  onclick="areaYeildChartPcbCount_PreAoi()">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 搜索
					</button>
				</div>--%>
			</div>
			<div class="col-md-4">
				<div style="text-align: center;padding: 5px" id="sandBox-container">
					<%--<span class="glyphicon glyphicon-time" aria-hidden="true"></span>--%>
					<span  style="margin-left: -19px;" class="glyphicon glyphicon-calendar"></span>
					<input size="16" type="text"  readonly  id="startTime" /> -
					<input  size="16"  type="text"   readonly  id="endTime"/>
					<!-- glyphicon glyphicon-search-->
					<button type="button" class="btn  btn-info btn-xs"  onclick="areaYeildChartPcbCount_PreAoi()">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 搜索
					</button>
				</div>
			</div>
		</div>
        <div class="row row-border" style="padding: 0px;margin: 0px">
            <div class="col-md-14">
                    <div id="container-linePcbYeild" style="min-width: 35vh; height: 35vh; margin: 0 auto"></div>
            </div>
        </div>
		<%--<div class="row row-border" >
		</div>--%>
		<div class="row row-border" style="padding: 0px;margin: 0px">
			<div class="col-md-14">
					<div id="container-lineFn" style="min-width: 32vh; height: 32vh; margin: 0 auto"></div>
			</div>
		</div>
		<%--<div class="row row-border" >
		</div>--%>
		<div class="row row-border" style="padding: 0px;margin: 0px">
			<table  class="table" id="pcbline_table">
			</table>
		</div>
		<div id="lineToolbar">
			<button type="button" class="btn btn-default" >EXP</button>
		</div>
		<!-- aoiType input -->
		<input type="hidden" id="aoiType" value="${aoiType}" />
	</nav>
	<script type="text/javascript" >

		window.operateEventsPcbLineData={
			"click #TablePcbDataDetails" :function(e,value, row, index){
				//window.location.href="${basePath}/sLine/pcbLineDetails?lineno="+row.lineNo+"&inspectStarttime="+row.inspectStarttime + "&inspectEndtime="+ row.inspectEndtime;
				window.open("${basePath}/aLine/pcbLineDetails?lineNo="+row.lineNo+"&inspectStarttime="+row.inspectStarttime + "&inspectEndtime="+ row.inspectEndtime +"&pcbType="+ichoicePcb+"&aoiType="+aoiType);
			}
		};
		var aoiType = $('#aoiType').val();
		//alert(aoiType);
		if(aoiType==''||aoiType==null){
			aoiType=1;
		}
		var iGroupMode = 1;
		var ichoicePcb =1;
		var nowDate = new Date();
		var dStart = dateFomate( nowDate.setDate(nowDate.getDate()-1),'yyyy-MM-dd HH:mm:ss' );
		var dEnd = dateFomate( nowDate.setDate(nowDate.getDate()+1),'yyyy-MM-dd HH:mm:ss' );
		$("#startTime").datetimepicker({
			//minView:"month",
			format: 'yyyy-mm-dd hh:ii:ss',
			language:"zh-CN",
			todayHighlight:true,
			showMeridian: true,
			autoclose: true,
			todayBtn: true,
			//pickerPosition: "bottom-left"
			//minuteStep: 1
		});
		$("#endTime").datetimepicker({
			//minView:"month",
			format: 'yyyy-mm-dd hh:ii:ss',
			language:"zh-CN",
			todayHighlight:true,
			showMeridian: true,
			autoclose: true,
			todayBtn: true,
			//pickerPosition: "bottom-left",
			//minuteStep: 1
		});
		$("#startTime").val(dStart) ;
		$("#endTime").val(dEnd) ;
		InitPcbLineDataMainTable_PreAoi();
		areaYeildChartPcbCount_PreAoi();

		<!--LINE TABLE -->
		function choicePcb_PreAoi(x) {
			ichoicePcb = x;
			areaYeildChartPcbCount_PreAoi();
		}
		function choiceChart_PreAoi(x){
			iGroupMode = x;
			areaYeildChartPcbCount_PreAoi();
		}
		function InitPcbLineDataMainTable_PreAoi(){
			var startTime = $("#startTime").val().toString();
			var endTime = $("#endTime").val().toString();
			$('#pcbline_table').bootstrapTable({
				url: "${basePath}/aLine/pcbTableLine?inspectStarttime="+startTime+"&inspectEndtime="+endTime+"&aoiType="+aoiType,                      //请求后台的URL（*）
				dataType:"json",
				method: 'GET',                      //请求方式（*）
				//data:{inspectStarttime:startTime,inspectEndtime:endTime,aoiType:aoiType},
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
					fileName: ($('#aoiType').val()==1?'pre-aoi_':'post-aoi_')+$("#startTime").val()+ "_"+$("#endTime").val()+ "_line.csv",  //文件名称设置
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
				/*cellStyle: function (value, row, index){
					return {css:{'font-size':'9px','padding':'0px'}}
				},*/
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
					/*{
                checkbox: true,
                align:'center',
                visible: true
                //是否显示复选框
            }, */
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
						field: 'goodPcbYeildAoi',
						title: 'PassPcbYeildAoi',
						align:'center',
						width:50,
						formatter:function(value,row,index){
							var goodCount =row.goodarrayCount;
							var Count=parseInt(row.passarrayCount)+parseInt(row.goodarrayCount)+parseInt(row.ngarrayCount);
							if(ichoicePcb==1){
								return '<span>'+row.goodPcbYeildAoi+'</span>';
							}else if(ichoicePcb==2){
								goodCount =row.goodarrayCount;
								Count=parseInt(row.passarrayCount)+parseInt(row.goodarrayCount)+parseInt(row.ngarrayCount);
								return '<span>'+(goodCount/Count*100).toFixed(2)+'</span>';
							}else{
								goodCount =row.goodComponentCount;
								Count=row.totalComponentCount;
								return '<span>'+(goodCount/Count*100).toFixed(2)+'</span>';
							}

						},cellStyle: function (value, row, index){
							return {css:{'font-size':'9px','padding':'0px'}}
						},
						sortable: true
					},{
						field: 'passPcbYeildAoi',
						title: 'RePassPcbYeildAoi',
						align:'center',
						sortable:true,
						width:50,
						formatter:function(value,row,index){
							var passCount =row.passarrayCount;
							var Count=parseInt(row.passarrayCount)+parseInt(row.goodarrayCount)+parseInt(row.ngarrayCount);
							//var ngarrayCount=row.ngarrayCount;
							if(ichoicePcb==1){
								return '<span>'+row.passPcbYeildAoi+'</span>';
							}else if(ichoicePcb==2){
								passCount =row.passarrayCount;
								Count=parseInt(row.passarrayCount)+parseInt(row.goodarrayCount)+parseInt(row.ngarrayCount);
								return '<span>'+(passCount/Count*100).toFixed(2)+'</span>';
							}else{
								passCount =row.passComponentCount;
								Count=row.totalComponentCount;
								return '<span>'+(passCount/Count*100).toFixed(2)+'</span>';
							}
						},
						cellStyle: function (value, row, index){
							return {css:{'font-size':'9px','padding':'0px'}}
						},
						//formatter: linkFormatter
					}, {
						field: 'ngPcbYeildAoi',
						title:  'ngPcbYeildAoi',
						align:'center',
						sortable: true,
						width:50,
						formatter:function(value,row,index){
							var ngCount =row.ngarrayCount;
							var Count=parseInt(row.passarrayCount)+parseInt(row.goodarrayCount)+parseInt(row.ngarrayCount);
							//var ngarrayCount=row.ngarrayCount;
							if(ichoicePcb==1){
								return '<span>'+row.ngPcbYeildAoi+'</span>';
							}else if(ichoicePcb==2){
								ngCount =row.ngarrayCount;
								Count=parseInt(row.passarrayCount)+parseInt(row.goodarrayCount)+parseInt(row.ngarrayCount);
								return '<span>'+(ngCount/Count*100).toFixed(2)+'</span>';
							}else{
								ngCount =row.ngComponentCount;
								Count=row.totalComponentCount;
								return '<span>'+(ngCount/Count*100).toFixed(2)+'</span>';
							}
						},
						cellStyle: function (value, row, index){
							return {css:{'font-size':'9px','padding':'0px'}}
						},
						//events:operateEvents,
					}, {
						field: 'totalAoi',
						title:  'pcbTotalAoi',
						align:'center',
						sortable: true,
						width:50,
						formatter:function(value,row,index){
							if(ichoicePcb==1){
								return '<span>'+row.totalAoi+'</span>';
							}else if(ichoicePcb==2){
								return '<span>'+(parseInt(row.passarrayCount)+parseInt(row.goodarrayCount)+parseInt(row.ngarrayCount))+'</span>';
							}else{
								return '<span>'+row.totalAoi+'</span>';
							}

						},
						cellStyle: function (value, row, index){
							return {css:{'font-size':'9px','padding':'0px'}}
						},
					}, {
						field: 'ngPcbCountAoi',
						title:  'ngPcbCountAoi',
						align:'center',
						sortable: true,
						width:50,
						formatter:function(value,row,index){
							if(ichoicePcb==1){
								return '<span>'+row.ngPcbCountAoi+'</span>';
							}else if(ichoicePcb==2){
								return '<span>'+row.ngarrayCount+'</span>';
							}else{
								return '<span>'+row.ngPcbCountAoi+'</span>';
							}

						},
						cellStyle: function (value, row, index){
							return {css:{'font-size':'9px','padding':'0px'}}
						},
					}, {
						field: 'passPcbCountAoi',
						title:  'RePassPcbCountAoi',
						align:'center',
						sortable: true,
						width:50,
						formatter:function(value,row,index){
							if(ichoicePcb==1){
								return '<span>'+row.passPcbCountAoi+'</span>';
							}else if(ichoicePcb==2){
								return '<span>'+row.passarrayCountAoi+'</span>';
							}else{
								return '<span>'+row.passPcbCountAoi+'</span>';
							}

						},
						cellStyle: function (value, row, index){
							return {css:{'font-size':'9px','padding':'0px'}}
						},
					}, {
						field:  'ngComponentCount',
						title:  'ngComponentCount',
						align:  'center',
						sortable: true,
						width:50,
						formatter:function(value,row,index){
							if(ichoicePcb==1){
								return '<span>'+row.ngComponentCount+'</span>';
							}else if(ichoicePcb==2){
								return '<span>'+row.ngComponentCount+'</span>';
							}else{
								return '<span>'+row.ngComponentCount+'</span>';
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
		function areaYeildChartPcbCount_PreAoi(){
			// alert($("#startTime").val());
			var startTime = $("#startTime").val()+' 00:00:00';
			var endTime = $("#endTime").val()+' 00:00:00';
			//alert(startTime+"__" +endTime);
			var jsonYeildHour={};
			var jsonContainerline={};
			$.ajax({
				url:"${basePath}/aLine/jsonPcbLine",
				dataType:"json",
				async:true,
				data:{inspectStarttime:startTime,inspectEndtime:endTime,mode:iGroupMode,pcbType:ichoicePcb,aoiType:aoiType},
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
						max:100,labels: {
						},
						opposite: false,
                        gridLineWidth:0,
						minorGridLineWidth:0


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
							stacking:'normal',
							dataLabels:{enabled:true,useHTML: true,
								formatter: function() {
                                    return this.series.name+':'+this.y+"%";
								},},
							events:{
								click:function (e) {
									var startTime = $("#startTime").val();
									var endTime = $("#endTime").val();
									window.open("${basePath}/aLine/pcbLineDetails?lineNo="+this.xAxis.categories[e.point.x]+"&inspectStarttime="+startTime+ "&inspectEndtime="+ endTime +"&pcbType="+ichoicePcb+"&aoiType="+aoiType);

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
                    jsonYeildHour.credits={enabled: false };
                    jsonYeildHour.legend = {
                        enabled:false,
                    };
					//alert(json);
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
							cursor:'pointer',
							grouping: true,
							shadow: false,
							borderWidth: 0,
							stacking:'normal',
							dataLabels:{enabled:true,useHTML: true,
								formatter: function() {
                                    return (this.series.name)+ ':' +(this.y);
								},},
							events:{
								click:function (e) {
									var startTime = $("#startTime").val();
									var endTime = $("#endTime").val();
									window.open("${basePath}/aLine/pcbLineDetails?lineNo="+this.xAxis.categories[e.point.x]+"&inspectStarttime="+startTime+ "&inspectEndtime="+ endTime +"&pcbType="+ichoicePcb+"&aoiType="+aoiType);

								}
							}
						},
						spline:{
							dataLabels:{enabled:true,useHTML: true,
								formatter: function() {
                                    return (this.series.name)+ ':' +(this.y);
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
			refreshTable_PreAoi(startTime,endTime);
		}
		function refreshTable_PreAoi(startTime,endTime){
			var opt = {
				url: "${basePath}/aLine/pcbTableLine?inspectStarttime="+startTime+"&inspectEndtime="+endTime+"&aoiType="+aoiType,
				silent: true,
				query:{
					type:1,
					level:2
				}
			};
			$("#pcbline_table").bootstrapTable('refresh', opt);
		}
		$("#lineToolbar").click(function () {
			excel = new ExcelGen({
				"src_id": "pcbline_table",
				"show_header": true,
				"format": "csv",
				"type": "table",
				"exclude_selector": null,
				"file_name":$("#startTime").val()+ "_"+$("#endTime").val()+ "_line.csv",//"output.xlsx",
			});
			excel.generate();
		});
	</script>
	</body>
</html>

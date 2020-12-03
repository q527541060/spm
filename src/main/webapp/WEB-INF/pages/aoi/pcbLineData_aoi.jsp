<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8" >
		<%--<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">--%>
		<title>pcbLineData</title>
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
		<div class="row" style=" text-align: left;margin-top: -5px">
			<div class="col-md-14">
				<ol class="breadcrumb">
					<li><a href="${basePath}/Home/pcbHome">Home</a></li>
					<li class="active"><a  data-toggle="tooltip" data-placement="bottom" title="点击切换至spi" href="${basePath}/sLine/pcbLine">aoi</a></li>
					<li class="active">line</li>
				</ol>
			</div>
		</div>
		<div class="row" style="margin-top: -6px;">
           <%-- <div class="col-md-1 col-md-offset-0" style="text-align: left;margin-top: 5px"><i>良率分组:</i></div>--%>
            <div class="col-md-2" style="text-align: left;">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-primary btn-xs"data-toggle="tooltip" data-placement="bottom" title="良率按小时排序"  onclick="choiceChart(0)">Hour</button>
                    <button type="button" class="btn btn-primary btn-xs" data-toggle="tooltip" data-placement="bottom" title="良率按线体排序" onclick="choiceChart(1)">Lines</button>
                    <button type="button" class="btn btn-primary btn-xs"data-toggle="tooltip" data-placement="bottom" title="良率按大板分析"  onclick="choicePcb(1)">pcb</button>
                    <button type="button" class="btn btn-primary btn-xs" data-toggle="tooltip" data-placement="bottom" title="良率按小拼板分析" onclick="choicePcb(2)">array</button>
                    <button type="button" class="btn btn-primary btn-xs" data-toggle="tooltip" data-placement="bottom" title="良率按元器件分析" onclick="choicePcb(3)">position</button>
                </div>
            </div>
			<div class="col-md-4 col-md-offset-2"  id="sandBox-container">
				<!-- glyphicon glyphicon-time   col-lg-offset-4-->
				<%--<span class="glyphicon glyphicon-time" aria-hidden="true"></span>--%>
                <span  style="margin-left: -19px;" class="glyphicon glyphicon-calendar"></span>
                <input size="12" type="text" value="" readonly class="form-date" id="startTime" /> -
				<input  size="12"  type="text"  value="" readonly class="form-date" id="endTime"/>
				 <!-- glyphicon glyphicon-search-->
				<button type="button" class="btn  btn-info btn-xs"> <%--  onclick="areaYeildChartPcbCount()">--%>
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 搜索
				</button>
			</div>
		</div>
		<%--<hr style= "border:1px dotted  #ffffff"  />--%>
		<%--<div class="row row-border"  >
		</div>--%>
        <div class="row row-border" >
            <div class="col-md-14">
                <div class="right-wap" style="height: 38vh;">
                    <!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
                    <div id="container-linePcbYeild" style="min-width: 310px; height: 100%; margin: 0 auto"></div>
                </div>
            </div>
        </div>
		<%--<div class="row row-border" >
		</div>--%>
		<div class="row row-border" >
			<div class="col-md-14">
				<div class="right-wap" style="height: 38vh;">
					<!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
					<div id="container-lineFn" style="min-width: 310px; height: 100%; margin: 0 auto"></div>
				</div>
			</div>
		</div>
		<%--<div class="row row-border" >
		</div>--%>
		<div class="row row-border" >
			<table  class="table" id="pcbline_table">
			</table>
		</div>
		<div id="lineToolbar">
			<button type="button" class="btn btn-default" >导出</button>
		</div>
	</nav>
	<script type="text/javascript">
		window.operateEventsPcbLineData={
			"click #TablePcbDataDetails" :function(e,value, row, index){
				//window.location.href="${basePath}/sLine/pcbLineDetails?lineno="+row.lineNo+"&inspectStarttime="+row.inspectStarttime + "&inspectEndtime="+ row.inspectEndtime;
				window.open("${basePath}/sLine/pcbLineDetails?lineNo="+row.lineNo+"&inspectStarttime="+row.inspectStarttime + "&inspectEndtime="+ row.inspectEndtime +"&pcbType="+ichoicePcb);
			}
		};
		var iGroupMode = 0;
		var ichoicePcb =1;
		var nowDate = new Date();
		var dStart = dateFomate(nowDate.setDate(nowDate.getDate()+0),'yyyy-MM-dd');
		var dEnd = dateFomate(nowDate.setDate(nowDate.getDate()+1),'yyyy-MM-dd');
		//var timestamp=d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
		$("#startTime").datetimepicker({
			minView:"month",
			format: 'yyyy-mm-dd',
			language:"zh-CN",
			todayHighlight:true,
			showMeridian: true,
			autoclose: true,
			todayBtn: true,
			//pickerPosition: "bottom-left"
			//minuteStep: 1

		});
		$("#endTime").datetimepicker({
			minView:"month",
			format: 'yyyy-mm-dd',
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

		areaYeildChartPcbCount();
		InitPcbLineDataMainTable();
		<!--LINE TABLE -->

		function choicePcb(x) {
			ichoicePcb = x;
			areaYeildChartPcbCount();
		}
		function choiceChart(x){
			iGroupMode = x;
			areaYeildChartPcbCount();
		}
		function InitPcbLineDataMainTable(){
			var startTime = $("#startTime").val().toString();
			var endTime = $("#endTime").val().toString();
			$('#pcbline_table').bootstrapTable({
				url: "${basePath}/sLine/pcbTableLine",//?inspectStarttime="+startTime+"&inspectEndtime="+endTime,                      //请求后台的URL（*）
				dataType:"json",
				method: 'GET',                      //请求方式（*）
				data:{inspectStarttime:startTime,inspectEndtime:endTime},
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
				pageSize: 10,                     //每页的记录行数（*）
				pageList: [15, 20, 50, 100,'ALL'],        //可供选择的每页的行数（*）
				search: true,                      //是否显示表格搜索
				strictSearch: false,
				showColumns: true,                  //是否显示所有的列（选择显示的列）
                showRefresh: true,                  //是否显示刷新按钮
                //showColumnsToggleAll:true,
               // minimumCountColumns: 0,             //最少允许的列数
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
					if (index % 2 == 0 ) {
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
					sortable: true
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
				}, {
					field: 'Date',
					title:  'Date',
					align:'center',
					//sortable: true,
					width:200,
					formatter:function(value,row,index){
						var html='<span>'+row.inspectStarttime+ '-' +row.inspectEndtime +'</span>';
						return html;
					}
				},{
					field: 'operation',
					title:  'operation',
					align:'center',
					//sortable: true,
					width:50,
					events: operateEventsPcbLineData,//给按钮注册事件
					formatter: addFunctionAltyPcbLineData//表格中增加按钮

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
		function areaYeildChartPcbCount(){
			// alert($("#startTime").val());
			var startTime = $("#startTime").val()+' 00:00:00';
			var endTime = $("#endTime").val()+' 00:00:00';
			//alert(startTime+"__" +endTime);
			var jsonYeildHour={};
			var jsonContainerline={};
			$.ajax({
				url:"${basePath}/sLine/jsonPcbLine",
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
						max:100,labels: {
						},
						opposite: false,
						minorGridLineWidth:0


					};
					jsonYeildHour.exporting={
						enabled:false
					};
					jsonYeildHour.series=req.data.series;
					jsonYeildHour.plotOptions = {
						areaspline: {
							pointStart: 0,
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
							dataLabels:{enabled:true}
						},
						column: {
							grouping: true,
							shadow: true,
							borderWidth: 0,
							stacking:'normal',
							dataLabels:{enabled:true}
						}
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
					jsonContainerline.yAxis =
							{
								title: {
									text: 'count',
								},
								labels: {
								},
								minorGridLineWidth:0

							};
					jsonContainerline.exporting={
						enabled:false
					};
					jsonContainerline.series=req.rows.series;
					jsonContainerline.plotOptions = jsonYeildHour.plotOptions;
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
		}
		function refreshTable(startTime,endTime){
			var opt = {
				url: "${basePath}/sLine/pcbTableLine?inspectStarttime="+startTime+"&inspectEndtime="+endTime,
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

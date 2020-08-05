<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>pcbLineData</title>
		<style>
			body{
				margin: 0px;
				padding: 0px;
				/*background-color: #ECF0F5;*/
                background: url("${staticPath}/static/img/home6.jpg")  ;
                background-size:cover;
			} 
			.row{
				text-align: center;
				margin: 0px;
			}
		</style>
	</head>
	<body>
		<%@include file="../header.jsp"  %>
		<div class="row" style=" text-align: left;">
			<div class="col-md-14">
				<ol class="breadcrumb">
					<li><a href="${basePath}/Home/pcbHome">Home</a></li>
					<%--<li><a href="#">spi</a></li>--%>
					<li class="active">line</li>
				</ol>
			</div>
		</div>
		<div class="row" style="margin-top: -6px;">
           <%-- <div class="col-md-1 col-md-offset-0" style="text-align: left;margin-top: 5px"><i>良率分组:</i></div>--%>
            <div class="col-md-2" style="text-align: left;">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-primary" onclick="choiceChart(0)">Hour</button>
                    <button type="button" class="btn btn-primary" onclick="choiceChart(1)">Lines</button>
                </div>
            </div>
			<div class="col-md-4 col-md-offset-2"  id="sandBox-container">
				<!-- glyphicon glyphicon-time   col-lg-offset-4-->
				<%--<span class="glyphicon glyphicon-time" aria-hidden="true"></span>--%>
                <span  style="margin-left: -19px;" class="glyphicon glyphicon-calendar"></span>
                <input size="12" type="text" value="" readonly class="form-date" id="startTime" /> -
				<input  size="12"  type="text"  value="" readonly class="form-date" id="endTime"/>
				 <!-- glyphicon glyphicon-search-->
				<button type="button" class="btn  btn-info btn-xs"  onclick="areaYeildChartPcbCount()">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 搜索
				</button>
			</div>
		</div>

        <div class="row">
            <div class="col-md-14">
                <div class="right-wap" style="height: 350px;">
                    <!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
                    <div id="container-linePcbYeild" style="min-width: 310px; height: 100%; margin: 0 auto"></div>
                </div>
            </div>
        </div>
		<div class="row">
			<div class="col-md-14">
				<div class="right-wap" style="height: 350px;">
					<!-- <div id="container-product" style="min-width: 310px; height: 100%; margin: 0 auto"></div> -->
					<div id="container-lineFn" style="min-width: 310px; height: 100%; margin: 0 auto"></div>

				</div>
			</div>
		</div>
		<div class="row">

			<table  class="table" id="pcbline_table">
			</table>
		</div>
	</nav>

    <script >
			var iGroupMode = 0;
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
			window.operateEventsPcbLineData={
				"click #TablePcbDataDetails" :function(e,value, row, index){
					//window.location.href="${basePath}/sLine/pcbLineDetails?lineno="+row.lineNo+"&inspectStarttime="+row.inspectStarttime + "&inspectEndtime="+ row.inspectEndtime;
					window.open("${basePath}/sLine/pcbLineDetails?lineNo="+row.lineNo+"&inspectStarttime="+row.inspectStarttime + "&inspectEndtime="+ row.inspectEndtime);
				}
			}
            areaYeildChartPcbCount();
            //containerlineFn();


            InitMainTable();
            <!--LINE TABLE -->
			var tableURL="${basePath}/sLine/pcbTableLine";
            function InitMainTable () {
                //记录页面bootstrap-table全局变量$table，方便应用
				var startTime = $("#startTime").val().toString();
				var endTime = $("#endTime").val().toString();
                var  $table = $('#pcbline_table').bootstrapTable({
                    url: "${basePath}/sLine/pcbTableLine",//?inspectStarttime="+startTime+"&inspectEndtime="+endTime,                      //请求后台的URL（*）
                    dataType:"json",
                    method: 'GET',                      //请求方式（*）
					data:{inspectStarttime:startTime,inspectEndtime:endTime},
					//toolbar: '#toolbar',              //工具按钮用哪个容器
                    striped: true,                      //是否显示行间隔色
                    cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                    pagination: true,                   //是否显示分页（*）
                    sortable: true,
					sortName:'Date',//是否启用排序
                    sortOrder: "asc",                   //排序方式
                    sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
                    pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
                    pageSize: 5,                     //每页的记录行数（*）
                    pageList: [15, 20, 50, 100],        //可供选择的每页的行数（*）
                    search: true,                      //是否显示表格搜索
                    strictSearch: true,
                    showColumns: true,                  //是否显示所有的列（选择显示的列）
                    showRefresh: true,                  //是否显示刷新按钮
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
                            sortOrder: params.order, //排位命令（desc，asc）
                            inspectStarttime:startTime,inspectEndtime:endTime
                        };
                    },
                    columns: [{
                        checkbox: false,
                        align:'center',
                        visible: false
                        //是否显示复选框
                    }, {
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
                        sortable: true
                    },{
                        field: 'passPcbYeild',
                        title: 'RePassPcbYeild',
                        align:'center',
                        sortable:true,
                        width:50
                        //formatter: linkFormatter
                    }, {
                        field: 'ngPcbYeild',
                        title:  'ngPcbYeild',
                        align:'center',
                        sortable: true,
                        width:50
                        //events:operateEvents,
                    }, {
                        field: 'total',
                        title:  'pcb个数',
                        align:'center',
                        sortable: true,
                        width:50
                    }, {
						field: 'ngPcbCount',
						title:  'ngPcbCount',
						align:'center',
						sortable: true,
						width:50
					}, {
						field: 'passPcbCount',
						title:  'RePassPcbCount',
						align:'center',
						sortable: true,
						width:50
					}, {
						field:  'ngpadCount',
						title:  'ngpadCount',
						align:  'center',
						sortable: true,
						width:50
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
                        //var line = row.lineNo;
                        //vlineNo = line;
                        //EditViewById(id, 'view');
                        //alert(line);
                        //defaultTopRealTime(line);
                        //ProductRealTime(line);
                    },
                });
            };

            $('.toolId').click(function () {
                alert('点击');
            });


            function choiceChart(x){
				iGroupMode = x;
				areaYeildChartPcbCount();
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
					data:{inspectStarttime:startTime,inspectEndtime:endTime,mode:iGroupMode},
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
						console.log(data);
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
	</script>
	</body>
</html>

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
		<meta name="viewport" /><%--content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />--%>

		<style>
			body{
				margin: 0px;
				padding: 0px;
				/*background: url("$staticPath}/static/img/home6.jpg")  ;*/
				/*background-size:cover;*/
			}

		</style>
    </head>
	<body>
		<nav>
            <%@include file="../header.jsp" %>
           <%-- <jsp:include page="header.jsp"></jsp:include>--%>
            <%--<%@include file="header.jsp" %>--%>
			<div class="row" style=" text-align: left;">
				<div class="col-md-14">
					<ol class="breadcrumb">
						<li><a href="${basePath}/Home/pcbHome">Home</a></li>
						<li class="active" ><a data-toggle="tooltip" data-placement="bottom" title="点击切换至spi" href="${basePath}/sStatus/pcbMonitorview_realLineView">aoi</a></li>
						<li class="active">Board-Machine</li>
					</ol>
				</div>
			</div>
			<div class="row">
			    <div class="col-md-14">
                    <h4><div id="timeShow" style="float: left"></div><span class="glyphicon glyphicon-tree-deciduous" aria-hidden="true">&nbsp;</span><i>Equipment Status</i>
						  <button type="button" style="width: 30px;height:25px;float: right" id= "status-refresh" class="btn btn-primary  btn-xs">
							  <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span></button>
					  </h4>
					  <div id="left-wap" style="height: 500px;overflow:auto;">
						 <div class="panel panel-info" >
							 <table  class="table" id="machineStatus">
							 </table>
						 </div>
					  </div>
			   </div>
			</div>
			<div class="row">


			</div>
			<div class="row">

			</div>
		</nav>

        <script type="text/javascript" >  /*src="{staticPath}/js/pcbMonotorview.js" >*/


        var StatusQueryUrl = '${basePath}/Status/aoi/pcbMonitorJson';
        var iTop1count=0;
        var iTop2count=0;
        var iTop3count=0;
        var iTop4count=0;
        var iTop5count=0;
        var fristLineNo ="";
        var vlineNo="";
        InitMainTable();
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
                        sortOrder: params.order //排位命令（desc，asc）
                    };
                },
                columns: [{
                    checkbox: true,
                    align:'center',
                    visible: true,

                    //是否显示复选框
                }, {
                    field: 'equipmentNo',
                    title: 'Machine',
                    width:50,
                    align:'center',
                    sortable: true
                }, {
                    field: 'lineNo',
                    title: 'LaneNo',
                    align:'center',
                    width:100,

                    sortable: true
                }, {
                    field: 'status',
                    title: 'Status',
                    align:'center',
                    width:100,
                    sortable: true,
                    cellStyle: function (value, row, index){
                        if(row.status == 1) { return {css:{"background-color":"D9534F"}}  }
                    },
                    formatter: function (value, row, index){
                        //row.status ==0?"停止":row.status==1?"故障":"运行";
						if(row.error==1){
							return "error";
						}else  if(row.run==1 && row.start==1){
							return "run";
						}else if(row.idle==1){
							return  "idel";
						}else{
							return  "stop";
						}
                        /*switch (row.status) {
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
                        }*/
                        return
                    },
                    cellStyle:function(value,row,index){
                        if(row.error==1) {
                            return {css: {"color": "#FF0000"}}
                        }else{
                            return {css: {"color": "#333333"}}
                        }
                    }

                },{
                    field: 'factory',
                    title: 'Model',
                    align:'center',
                    sortable:true,
                    width:100
                    //formatter: linkFormatter
                }, {
                    field: 'errContent',
                    title:  'Alarm',
                    align:'center',
                    //sortable: true,
                    width:250
                    //events:operateEvents,

                },{
					field: 'updateTime',
					title:  'dateTime',
					align:'center',
					sortable: true,
					width:100
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
                onClickRow: function (row, $element) {
                    //var line = row.lineNo;
                   // vlineNo = line;
                    //EditViewById(id, 'view');
                    //alert(line);
                    //defaultTopRealTime(line);
                    //ProductRealTime(line);
                },
            });

        };

        function timeShow() {

            var  startTime_str = ("2018-11-09 20:19:00").replace(/-/g,"/");;
            var startTime= new Date(startTime_str);
            var date = new Date(+new Date()+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'')
            document.getElementById("timeShow").innerText=date;
        }
        $("#status-refresh").click(refreshTable);

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
		window.setInterval(refreshTable,5000);
		window.setInterval(timeShow,1000);
        //setInterval(2,InitMainTable);
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

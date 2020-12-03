<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8" >
		<title>pcbfovImageViewAoi</title>
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
			/*.row-border{
				!*background-color: #F5F5F5;color: #F5F5F5;height: 25px*!
                margin-top: 15px;
			}*/
			.progress{
				padding: 0px;
			}

		</style>
	</head>
	<body>
    <nav>
		<%@include file="../header.jsp"  %>
		<div class="row" style=" text-align: left;">
			<div class="col-md-14" >
				<ol class="breadcrumb"  style="float: left;margin: 0px;">
					<li><a href="${basePath}/Home/pcbHome">Home</a></li>
					<li><a href="active">pre-aoi</a></li>
					<li>pcbfovImageView</li>
				</ol>
				<div style="float: left;margin-left: 20%;padding: 5px" id="sandBox-container">
					<!-- glyphicon glyphicon-time   col-lg-offset-4-->
					<%--<span class="glyphicon glyphicon-time" aria-hidden="true"></span>--%>
					<span  style="margin-left: -19px;" class="glyphicon glyphicon-calendar"></span>
					<input size="12" type="text" value="" readonly class="form-date" id="startTime" /> -
					<input  size="12"  type="text"  value="" readonly class="form-date" id="endTime"/>
					<!-- glyphicon glyphicon-search-->
					<button type="button" class="btn  btn-info btn-xs"  onclick="">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 搜索
					</button>
				</div>
			</div>

		</div>
		<div class="row" >
			<!-- 侧导航栏 线体  -->
			<div class="col-md-1" style="padding: 0px">
				<div class="panel panel-primary" style="margin: 0px">
					<div class="panel-heading">Line-List-Info</div>
				</div>
				<!-- line job Table -->
				<div id="lineTable" ></div>
			</div>
			<div class="col-md-11" style="padding: 0px">
				<div class="row">
					<div class="col-md-5" style="padding: 2px">
						<!--  pcb图 -->
						<div class="progress">
							<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
								<span >PCB-Picture &nbsp;&nbsp;&nbsp;  Barcode:BV982HIJ87 &nbsp;&nbsp;  Result:<label style="color: red;margin: 0px;padding: 0px">NG</label></span>
							</div>
						</div>
						<div id="pcbImage" style="margin-top: -20px"></div>
						<div id="pcbTable" style="height: 40vh"></div>
					</div>
                    <div class="col-md-4" style="padding: 2px">
                        <!-- fov -->
                        <div class="progress">
                            <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                                <span >FOV-Picture &nbsp;&nbsp;&nbsp;  Barcode:BV982HIJ87 &nbsp;&nbsp;  Result:<label style="color: red;margin: 0px;padding: 0px">NG</label> &nbsp;&nbsp; FovIndex:1 </span>
                            </div>
                        </div>
                        <div id="fovImage" style="margin-top: -20px"></div>
						<div id="fovTable"style="height: 45vh"></div>
                    </div>
                    <div class="col-md-3" style="padding: 2px">
                        <div class="row">
                            <div class="progress">
                                <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                                    <span >Component-Picture &nbsp;&nbsp; Name:F3-21 &nbsp;&nbsp;  Result:<label style="color: red;margin: 0px;padding: 0px">NG</label> &nbsp;&nbsp; PartNo:DF2 </span>
                                </div>
                            </div>
                            <div id="2D-Component" style="margin-top: -20px"></div>
                        </div>
                        <div class="row">
                            <div class="progress" style="margin: 0px">
                                <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                                    <span >3DComponent-Picture &nbsp;&nbsp;&nbsp;</span>
                                </div>
                            </div>
                            <div id="3D-Component" style="width: 100%;height:35vh;"></div>
						</div>
						<div class="row">
							<div id="componentTable"style="height: 24vh"></div>
						</div>

                    </div>
				</div>

			</div>
		</div>
	</nav>

	<script type="text/javascript">
		var nowDate = new Date();
		var dStart = dateFomate(nowDate.setDate(nowDate.getDate()+0),'yyyy-MM-dd');
		var dEnd = dateFomate(nowDate.setDate(nowDate.getDate()+1),'yyyy-MM-dd');
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
        showPcbImage("${basePath}");
        showFovImage("${basePath}");
        showComponentImage("${basePath}","${staticPath}");
		//InitComponentTable("${basePath}");
		InitLineDataMainTable("${basePath}");
	</script>
	<style>
		/*.bootstrap-table .fixed-table-toolbar .columns-left {
			margin: 0px;
			margin-right: 5px;
		}
		.bootstrap-table .fixed-table-toolbar .bs-bars, .bootstrap-table .fixed-table-toolbar .columns, .bootstrap-table .fixed-table-toolbar .search {
			position: relative;
			margin-top: 10px;
			margin-bottom: 10px;
			margin: 0px;
		}
		.bootstrap-table .fixed-table-pagination>.pagination, .bootstrap-table .fixed-table-pagination>.pagination-detail {
			margin-top: 10px;
			margin-bottom: 10px;
			font-size: 5px;
		}*/
	</style>
	</body>
</html>

<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
	String staticPath = basePath + "/static";
	request.setAttribute("basePath", basePath);
	request.setAttribute("staticPath", staticPath);
%>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>


		<%--<link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.css">--%>
		<link rel="stylesheet" href="${staticPath}/bootstrap/css/bootstrap-table.min.css">
		<link rel="stylesheet"  href="${staticPath}/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${staticPath}/bootstrap/css/bootstrap-datetimepicker.min.css" />
		<link rel="stylesheet" href="${staticPath}/bootstrap/css/bootstrap-select.min.css" />
		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script type="text/javascript" src="${staticPath}/jquery/jQuery-2.2.0.min.js"></script>
		<script type="text/javascript" src="${staticPath}/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${staticPath}/bootstrap/js/bootstrap-select.min.js"></script>

		<!-- Latest compiled and minified JavaScript -->
		<script src="${staticPath}/bootstrap/js/bootstrap-table.min.js"></script>
		<script src="${staticPath}/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
		<%--<script src="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.js"></script>
		<script src="https://unpkg.com/bootstrap-table@1.15.3/dist/locale/bootstrap-table-zh-CN.min.js"></script>--%>
		<script src="${staticPath}/hchart/highcharts.js"></script>
		<!-- <script src="./hchart/highcharts-3d.js"></script>
		<script src="./hchart/modules/cylinder.js"></script> -->
		<script src="${staticPath}/hchart/modules/exporting.js"></script>
		<script src="${staticPath}/hchart/modules/export-data.js"></script>
		<!-- <script src="./hchart/modules/accessibility.js"></script> -->
		<!-- <script src="./hchart/themes/dark-unica.js"></script> -->
		<%--<script src="${staticPath}/hchart/themes/grid-light.js"></script> --%><!--grid-light -->

		<script src="${staticPath}/bootstrap/js/bootstrap-datetimepicker.js"></script>
		<script src="${staticPath}/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
	   <!-- export  bootstrap-table-export.js-->
		<script  src="${staticPath}/bootstrap/js/tableExport.js"></script>
		<script src="${staticPath}/bootstrap/js/bootstrap-table-export.js"></script>

		<script src="${staticPath}/js/static/pcbLineData.js"></script>
		<script src="${staticPath}/js/static/publicJs.js"></script>

		<script src="${staticPath}/exportjs/jszip.min.js"></script>
		<script src="${staticPath}/exportjs/FileSaver.js"></script>
		<script src="${staticPath}/exportjs/excel-gen.js"></script>
		<script src="${staticPath}/exportjs/demo.page.js"></script>

		<style type="text/css">
			ul{
				padding: 0px;
				margin: 0px;
				text-align: left;
				height: 40px;
				background-color: #409EFE;
			}
			ul>li{
				width: 150px;
				padding: 0px;
				margin: 0px;
			}
			ul>li>a{
				color: #FFFFFF;
				font-weight: 600
			}
			ul>li>a>img{
				height:30px;
			}
			.bootstrap-table .fixed-table-container .table thead th .both{
				background-image:url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAABgFBMVEWKv+aIvuZrruBur+Btr+B6tuMsi9OOweeMwOeMwOaLwOaLwOaMwOaKv+aKv+aKv+aKv+aKv+aKv+aJvuZ1s+JrruCKv+aKv+Zsr+Btr+CKv+Ztr+CKv+Ztr+Btr+CKv+aKv+Ztr+Btr+CKv+aKv+Ztr+Btr+CKv+Ztr+Btr+CJv+aKv+Ztr+Btr+CJv+aKv+Ztr+Btr+CJvuaKv+Ztr+Btr+CIvuaKv+Ztr+Btr+B8uOOJvuZtr+Btr+B8t+N0s+Ftr+Btr+BrruBtr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+CKv+aJvuZ0s+J0s+Ftr+C21++HveVxseGhzOvz+f2Cu+RusOCPwufZ6vf///94teK42PD9/v+z1e+dyeru9vvq9PuFvOVsruDC3fL+//9rruCJv+abyOqy1e+w1O6jzeyfy+ugy+uDu+Vsr+CHvuWAueRysuFqrt+IvuaGveV3tOJwseEAAAA24TjAAAAAU3RSTlMAAAAAAAAAAA8sRlRGGnnC5fP3w3kaGLq5GHBvCrkKJuDfJUDw8EBS91JX+PhXTvb2Tjjs7Dga09MaAZaVAS/W1S8rjMzp9fn06MuLKxQyS1hKMcNktbAAAAABYktHRH9Iv3HlAAAACXBIWXMAACcQAAAnEAGUaVEZAAAAB3RJTUUH5AcYBBA25P1/QAAAAOtJREFUGNNjYGBgYOTg5OLm5uHkYGJmYQHyefn4BQSFhAQFhEVEQQJi4sFQEBIqIQkUkAqGC4SFS7MwyEAUREQEB0dGhcvKMcgrgOSiY2KiQ2LjwhWVGJRVgALxCYmJCfFJ4eGqagzqQkAFySmJiSmpceHhGpoMWtrBIWnpiUCQkZkVrqPLoKcfHJKdmAMEiam54QaGDEbGwXn5BamFRcUlpWXhJqYMZuZAQ8srKnOrqrLCwy0sGaysq2sqaoHmgYGNLQOrnX1dOAw4OLIwsLE7Obu4urm7e3h6efsAPccCBL5+/gG6gUG+IDYAkflCDXYUg6QAAAAldEVYdGRhdGU6Y3JlYXRlADIwMjAtMDctMTlUMDM6Mzk6MjArMDA6MDCGZw5cAAAAJXRFWHRkYXRlOm1vZGlmeQAyMDE5LTA4LTEwVDE0OjIxOjIxKzAwOjAwJQImowAAACB0RVh0c29mdHdhcmUAaHR0cHM6Ly9pbWFnZW1hZ2ljay5vcme8zx2dAAAAGHRFWHRUaHVtYjo6RG9jdW1lbnQ6OlBhZ2VzADGn/7svAAAAGHRFWHRUaHVtYjo6SW1hZ2U6OkhlaWdodAA1NTbsjFKcAAAAF3RFWHRUaHVtYjo6SW1hZ2U6OldpZHRoADU1Nn99AsEAAAAZdEVYdFRodW1iOjpNaW1ldHlwZQBpbWFnZS9wbmc/slZOAAAAF3RFWHRUaHVtYjo6TVRpbWUAMTU2NTQ0Njg4MVViNaoAAAASdEVYdFRodW1iOjpTaXplADE5NTMzQt+GreAAAABadEVYdFRodW1iOjpVUkkAZmlsZTovLy9kYXRhL3d3d3Jvb3Qvd3d3LmVhc3lpY29uLm5ldC9jZG4taW1nLmVhc3lpY29uLmNuL2ZpbGVzLzEyMy8xMjM2NDc0LnBuZ7aoUuwAAAAASUVORK5CYII=")}
			.bootstrap-table .fixed-table-container .table thead th .desc{
				background-image:url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAABgFBMVEWKv+aIvuZrruBur+Btr+B6tuMsi9OOweeMwOeMwOaLwOaLwOaMwOaKv+aKv+aKv+aKv+aKv+aKv+aJvuZ1s+JrruCKv+aKv+Zsr+Btr+CKv+Ztr+CKv+Ztr+Btr+CKv+aKv+Ztr+Btr+CKv+aKv+Ztr+Btr+CKv+Ztr+Btr+CJv+aKv+Ztr+Btr+CJv+aKv+Ztr+Btr+CJvuaKv+Ztr+Btr+CIvuaKv+Ztr+Btr+B8uOOJvuZtr+Btr+B8t+N0s+Ftr+Btr+BrruBtr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+CKv+aJvuZ0s+J0s+Ftr+C21++HveVxseGhzOvz+f2Cu+RusOCPwufZ6vf///94teK42PD9/v+z1e+dyeru9vvq9PuFvOVsruDC3fL+//9rruCJv+abyOqy1e+w1O6jzeyfy+ugy+uDu+Vsr+CHvuWAueRysuFqrt+IvuaGveV3tOJwseEAAAA24TjAAAAAU3RSTlMAAAAAAAAAAA8sRlRGGnnC5fP3w3kaGLq5GHBvCrkKJuDfJUDw8EBS91JX+PhXTvb2Tjjs7Dga09MaAZaVAS/W1S8rjMzp9fn06MuLKxQyS1hKMcNktbAAAAABYktHRH9Iv3HlAAAACXBIWXMAACcQAAAnEAGUaVEZAAAAB3RJTUUH5AcYBBA25P1/QAAAAOtJREFUGNNjYGBgYOTg5OLm5uHkYGJmYQHyefn4BQSFhAQFhEVEQQJi4sFQEBIqIQkUkAqGC4SFS7MwyEAUREQEB0dGhcvKMcgrgOSiY2KiQ2LjwhWVGJRVgALxCYmJCfFJ4eGqagzqQkAFySmJiSmpceHhGpoMWtrBIWnpiUCQkZkVrqPLoKcfHJKdmAMEiam54QaGDEbGwXn5BamFRcUlpWXhJqYMZuZAQ8srKnOrqrLCwy0sGaysq2sqaoHmgYGNLQOrnX1dOAw4OLIwsLE7Obu4urm7e3h6efsAPccCBL5+/gG6gUG+IDYAkflCDXYUg6QAAAAldEVYdGRhdGU6Y3JlYXRlADIwMjAtMDctMTlUMDM6Mzk6MjArMDA6MDCGZw5cAAAAJXRFWHRkYXRlOm1vZGlmeQAyMDE5LTA4LTEwVDE0OjIxOjIxKzAwOjAwJQImowAAACB0RVh0c29mdHdhcmUAaHR0cHM6Ly9pbWFnZW1hZ2ljay5vcme8zx2dAAAAGHRFWHRUaHVtYjo6RG9jdW1lbnQ6OlBhZ2VzADGn/7svAAAAGHRFWHRUaHVtYjo6SW1hZ2U6OkhlaWdodAA1NTbsjFKcAAAAF3RFWHRUaHVtYjo6SW1hZ2U6OldpZHRoADU1Nn99AsEAAAAZdEVYdFRodW1iOjpNaW1ldHlwZQBpbWFnZS9wbmc/slZOAAAAF3RFWHRUaHVtYjo6TVRpbWUAMTU2NTQ0Njg4MVViNaoAAAASdEVYdFRodW1iOjpTaXplADE5NTMzQt+GreAAAABadEVYdFRodW1iOjpVUkkAZmlsZTovLy9kYXRhL3d3d3Jvb3Qvd3d3LmVhc3lpY29uLm5ldC9jZG4taW1nLmVhc3lpY29uLmNuL2ZpbGVzLzEyMy8xMjM2NDc0LnBuZ7aoUuwAAAAASUVORK5CYII=")}
			.bootstrap-table .fixed-table-container .table thead th .asc
			{background-image:url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAABfVBMVEWKv+aIvuZrruBur+Btr+B6tuMsi9OOweeMwOeMwOaLwOaLwOaMwOaKv+aKv+aKv+aKv+aKv+aKv+aJvuZ1s+JrruCKv+aKv+Zsr+Btr+CKv+Ztr+CKv+Ztr+Btr+CKv+aKv+Ztr+Btr+CKv+aKv+Ztr+Btr+CKv+Ztr+Btr+CJv+aKv+Ztr+Btr+CJv+aKv+Ztr+Btr+CJvuaKv+Ztr+Btr+CIvuaKv+Ztr+Btr+B8uOOJvuZtr+Btr+B8t+N0s+Ftr+Btr+BrruBtr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+Btr+CKv+aJvuZ0s+J0s+Ftr+CIvuaGveVxseGJv+abyOqy1e6y1e+TxOhur+DC3vL////+/v+01u9rruCdyurv9vzr9PuFvOVsruC52fD9/v/8/v6oz+yPwufa6/fQ5fVzsuFsr+CizOv0+f3x9/yBuuSp0O2lzeyAueR3tOJusOBwseEAAACemKNgAAAAU3RSTlMAAAAAAAAAAA8sRlRGGnnC5fP3w3kaGLq5GHBvCrkKJuDfJUDw8EBS91JX+PhXTvb2Tjjs7Dga09MaAZaVAS/W1S8rjMzp9fn06MuLKxQyS1hKMcNktbAAAAABYktHRH4/uEFzAAAACXBIWXMAACcQAAAnEAGUaVEZAAAAB3RJTUUH5AcYBBIwP6i49wAAAOdJREFUGNNjYGBgYOTg5OLm5uHkYGJmYQHyefn4BQSFhAQFhEVEQQJi4sFQEBIqIQkUkAqGC4SFS7MwyAAVhESAQWRUuKwcg7xCcHRMLAjExSeEKyoxKKsEhyQmJQNBUkpquKoag7pQcEhaehIQZGRmhWtoMmhpA83IzklKys3LCg/X0WXQ0wean1+QlFRYVBwebmDIYGQMsrCktCw4NTw83MSUwcwcKBBdXlEZDgIWlgxW1hGRVdU1xWB+uI0tA6udfW04DDg4sjCwsTs5u7i6ubt7eHp5+wA9xwIEvn7+AbqBQb4gNgCWskGGOzhvAQAAACV0RVh0ZGF0ZTpjcmVhdGUAMjAyMC0wNy0xOVQwMzozOToyMCswMDowMIZnDlwAAAAldEVYdGRhdGU6bW9kaWZ5ADIwMTktMDgtMTBUMTQ6MjE6MTcrMDA6MDDIXRR6AAAAIHRFWHRzb2Z0d2FyZQBodHRwczovL2ltYWdlbWFnaWNrLm9yZ7zPHZ0AAAAYdEVYdFRodW1iOjpEb2N1bWVudDo6UGFnZXMAMaf/uy8AAAAYdEVYdFRodW1iOjpJbWFnZTo6SGVpZ2h0ADU1NuyMUpwAAAAXdEVYdFRodW1iOjpJbWFnZTo6V2lkdGgANTU2f30CwQAAABl0RVh0VGh1bWI6Ok1pbWV0eXBlAGltYWdlL3BuZz+yVk4AAAAXdEVYdFRodW1iOjpNVGltZQAxNTY1NDQ2ODc3O5mMUAAAABJ0RVh0VGh1bWI6OlNpemUAMTg5MzVC/mqcbgAAAFp0RVh0VGh1bWI6OlVSSQBmaWxlOi8vL2RhdGEvd3d3cm9vdC93d3cuZWFzeWljb24ubmV0L2Nkbi1pbWcuZWFzeWljb24uY24vZmlsZXMvMTIzLzEyMzY0NzIucG5nOeinTAAAAABJRU5ErkJggg==")}
			.bootstrap-table .fixed-table-toolbar .pull-right .dropdown-menu {
				height: max-content;
			}
		</style>

	</head>
	<body>

			<!--头部信息-->
			<div class="row">
				 <div class="col-md-14">
					<ul class="nav nav-pills" >
					  <li role="presentation" style="text-align: left;">
						  <button type="button" class="btn btn-primary" onclick="showHome()" aria-label="Left Align"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></button>
					  </li>
					  <li role="presentation"><a href="${basePath}/Home/pcbHome"><img  src="${staticPath}/img/zz.png">HomePage</a></li>
					  <li role="presentation"><a href="${basePath}/sLine/pcbLine"><img  src="${staticPath}/img/VD_001.png">DataInfo</a></li>
					  <li role="presentation"><a href="${basePath}/Status/pcbMonitorview_realLineView"><img  src="${staticPath}/img/zz04.png">Monitoring</a></li><li role="presentation"><a href="${basePath}/sDefaultsetting/setting"><img  src="${staticPath}/img/DL.png">Setting</a></li>
					</ul>
					 <input  type="hidden" id="chartColor" value="${hChartColor}"/>
                     <input type="hidden" id="backgroundColor" value="${backgroundColor}">
                 </div>
			</div>

			<script type="text/javascript">
				function showHome(){
					window.location.href="${basePath}/Home/pcbHome";
				}
				//$(document).ready(function () {
				//});
				//$(".nav a").click().addClass("active");
				//$(".nav").children().removeClass("active");
				var vCharColor = $('#chartColor').val();
				if(vCharColor==null || vCharColor==''){
					vCharColor=0;
				}
				var vBackground = $('#backgroundColor').val();
                if(vBackground==null || vBackground==''){
                    vBackground=0;
                }

				<!-- 换图表背景 -->
				switch (vCharColor) {
					case '0':
						$.getScript("${staticPath}/hchart/themes/grid-light.js");
                        break;
					case '1':
						$.getScript("${staticPath}/hchart/themes/dark-blue.js");
                        break;
					case '2':
						$.getScript("${staticPath}/hchart/themes/dark-green.js");
						break;
					case '3':
						$.getScript("${staticPath}/hchart/themes/dark-unica.js");
						break;
					case '4':
						$.getScript("${staticPath}/hchart/themes/gray.js");
						break;
					case '5':
						$.getScript("${staticPath}/hchart/themes/grid.js");
						break;
					case '6':
						$.getScript("${staticPath}/hchart/themes/grid-light.js");
						break;
					case '7':
						$.getScript("${staticPath}/hchart/themes/high-contrast-dark.js");
						break;
					case '8':
						$.getScript("${staticPath}/hchart/themes/high-contrast-light.js");
						break;
					case '9':
						$.getScript("${staticPath}/hchart/themes/sand-signika.js");
						break;
					case '10':
						$.getScript("${staticPath}/hchart/themes/skies.js");
						break;
					case '11':
						$.getScript("${staticPath}/hchart/themes/sunset.js");
						break;
					default :{
						$.getScript("${staticPath}/hchart/themes/sunset.js");
						break;
					}
				}
			    <!-- 换后台背景-->
                switch (vBackground) {
                    case '0':{
                        $("body").css(
                            {
                                'background-image':'url("${staticPath}/img/home6.png")',
								'background-size':'cover',
								//'opacity':1,
								//'filter':'alpha(opacity=40)',
                            });
                        break;
                    }
                    case '1':
                        //white-pictue
                        $("body").css({'background-image':'url("${staticPath}/img/white-pictue.png")'});//white-pictue
                        break;
                    case '2':
						$("body").css({
							'background-image':'url("${staticPath}/img/zzbg.png")',
							'background-size':'cover',
						});//white-pictue
						break;
                    case '3':
						$("body").css({'background-image':'url("${staticPath}/img/1.jpg")'});
						break;
                    case '4':
						$("body").css({'background-image':'url("${staticPath}/img/2.jpg")','background-size':'cover',});
						break;
                    case '5':
						$("body").css({'background-image':'url("${staticPath}/img/gray.jpg")',});
						break;
                    case '6':
						$("body").css({'background-image':'url("${staticPath}/img/4.jpg")',});
						break;
                    case '7':
						$("body").css({'background-image':'url("${staticPath}/img/dls.jpg")',});
						break;
                    case '8':
                    case '9':
                    case '10':
                    case '11':
                    default :{
                        $("body").css({'background-image':'url("${staticPath}/img/home6.png")','background-size':'cover'});
                        break;
                    }
                }

				$(function () {
					$('[data-toggle="tooltip"]').tooltip();
				})
            </script>
	</body>
</html>

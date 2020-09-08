<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>Home</title>
		<%--<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet"  href="./bootstrap/css/bootstrap.min.css">
		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script type="text/javascript" src="./jquery/jQuery-2.2.0.min.js"></script>
		<script type="text/javascript" src="./bootstrap/js/bootstrap.min.js"></script>--%>
		<style type="text/css">

			body{ 
				margin: 0px;
				padding: 0px;
				/*background-color: #FFFFFF;*/
              /*  background: url("$staticPath}/static/img/home6.png")  ;*/
              /*  background-size:cover;*/
			} 
			.col-md-3{
				padding: 0px;
			}
			.col-md-12>img{
				width: 8%;
			}
			.row{
				text-align: center;
				margin-bottom: 100px;
			}
			.col-md-4{
				text-align: center;
				background-color: #c1c4c8;
				margin: 0px;
				padding: 0px;
			}
			h3{
				color: #757877;
				text-align: center;
			}
			#line-win {
				width: 100%;
				background-color: #F2F2F2;
				padding: 20px;
				transition: all 0.5s;
			}
			#line-win:hover{
				cursor:pointer;
				background-color: rgba(122, 163, 53, 0.2);
				transform: scale(1.2);
				/*width: 120%;
				transition: all 2s ease-in 1s;*/
			}
			#line-win span{
				color: #7BA4D9;
				font-size: 30px;
			}
			#line-win .line-count{
				color: #E67964;
				font-size: 30px;
			}
            /*#nav_div{
                background: url("${staticPath}/static/img/home.jpg") ;

			}*/
			/*#line-win div:hover {
				transition: all 2s ease-in 1s;width: 120%;
			}*/
		</style>
		<script type="text/javascript">
			function showModel(x){

				switch (x) {
					case 1:
                        alert('待开放...');
						break;
					case 2:
                        window.location.href="${basePath}/sLine/pcbLine";
						break;
					case 3:
						window.location.href="${basePath}/Status/pcbMonitorview_realLineView";
						break;
					case 7:
						window.location.href="${basePath}/Status/aoi/pcbMonitorview";
						break;
					default :
                        alert('待开放...');
						break;
				}


			}
		</script>
	</head>
	<body>

		<nav id="nav_div">
            <%@include file="header.jsp" %>
			<%--<div class="row">
				<div class="col-md-12" >
					<!-- <img src="img/AOI-01.jpg" /><img src="img/spi-01.png" /><img src="img/spi-02.jpg" /><img src="img/3dSPI-shape.jpg" /><img src="img/lixianji.jpg" /> -->
					&lt;%&ndash;<img src="${staticPath}/img/spi1.jpg" />
					<img src="${staticPath}/img/spi2.jpg" />
					<img src="${staticPath}/img/spi3.jpg" />
					<img src="${staticPath}/img/3daoi.jpg" />
					<img src="${staticPath}/img/lixianji.jpg" />&ndash;%&gt;
				</div>
			</div>--%>

			<div class="row" style="margin-top: -100px">
				<h3>  SPI  </h3>
					<div class="col-md-2 col-md-offset-3">
						<div id="line-win"  onclick="showModel(2)">
							<span  class="glyphicon glyphicon-star-empty"></span>
							<p> 过程分析大数据</p>
							<i class="line-count"> ${spi_dataCount}</i>
						</div>
					</div>
					<%--<div class="col-md-2">
						<div id="line-win"  onclick="showModel(1)">
								<span  class="glyphicon glyphicon-folder-open"></span>
								<p> 活动线体/总线体</p>
								<i class="line-count">12/15</i>
						</div>
					</div>--%>
					<div class="col-md-2">
						<div id="line-win"  onclick="showModel(3)">
								<span  class="glyphicon glyphicon-bell"></span>
								<p>活动总线实时监控</p>
								<%--<p> 活动线体/总线体</p>--%>
								<i class="line-count">${spi_lineCount}</i>
						</div>
					</div>
					<div class="col-md-2">
						<div id="line-win"  onclick="showModel(4)">
								<span  class="glyphicon glyphicon-exclamation-sign"></span>
								<p> 条码追溯</p>
								<i class="line-count">${spi_barcodeCount}</i>
						</div>
					</div>
                   <%-- <div class="col-md-1">
                        <div id="line-win"  onclick="showModel(4)">
                            <span  class="glyphicon glyphicon-exclamation-sign"></span>
                            <p> 预留</p>
                            <i class="line-count">10201</i>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div id="line-win"  onclick="showModel(4)">
                            <span  class="glyphicon glyphicon-exclamation-sign"></span>
                            <p> 预留</p>
                            <i class="line-count">10201</i>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div id="line-win"  onclick="showModel(4)">
                            <span  class="glyphicon glyphicon-exclamation-sign"></span>
                            <p> 预留</p>
                            <i class="line-count">10201</i>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div id="line-win"  onclick="showModel(4)">
                            <span  class="glyphicon glyphicon-exclamation-sign"></span>
                            <p> 预留</p>
                            <i class="line-count">10201</i>
                        </div>
                    </div>--%>
			</div>
			<!-- fov pcb图 -->
			<div class="row" style="margin-top:-70px ">
				<div class="col-md-2 col-md-offset-3">
					<div id="line-win"  onclick="showModel(4)">
						<span  class="glyphicon glyphicon-modal-window"></span>
						<p> FOV/PCB分析</p>
						<i class="line-count"> ${spi_fovCount}/${spi_pcbCount}</i>
					</div>
				</div>
				<div class="col-md-2">
					<div id="line-win"  onclick="showModel(5)">
						<span  class="glyphicon glyphicon-copyright-mark"></span>
						<p>component分析</p>
						<i class="line-count">${spi_componentCount}</i>
					</div>
				</div>
				<div class="col-md-2">
					<div id="line-win"  onclick="showModel(6)">
						<span  class="glyphicon glyphicon-exclamation-sign"></span>
						<p> 预留</p>
						<i class="line-count">2333</i>
					</div>
				</div>
				<%-- <div class="col-md-1">
                     <div id="line-win"  onclick="showModel(4)">
                         <span  class="glyphicon glyphicon-exclamation-sign"></span>
                         <p> 预留</p>
                         <i class="line-count">10201</i>
                     </div>
                 </div>
                 <div class="col-md-1">
                     <div id="line-win"  onclick="showModel(4)">
                         <span  class="glyphicon glyphicon-exclamation-sign"></span>
                         <p> 预留</p>
                         <i class="line-count">10201</i>
                     </div>
                 </div>
                 <div class="col-md-1">
                     <div id="line-win"  onclick="showModel(4)">
                         <span  class="glyphicon glyphicon-exclamation-sign"></span>
                         <p> 预留</p>
                         <i class="line-count">10201</i>
                     </div>
                 </div>
                 <div class="col-md-1">
                     <div id="line-win"  onclick="showModel(4)">
                         <span  class="glyphicon glyphicon-exclamation-sign"></span>
                         <p> 预留</p>
                         <i class="line-count">10201</i>
                     </div>
                 </div>--%>
			</div>


			<div class="row">
				<h3> AOI</h3>
				<div class="col-md-2  col-md-offset-3">
					<div id="line-win"  onclick="showModel(5)">
							<span  class="glyphicon glyphicon-star-empty"></span>
							<p> 活动线体/总线体</p>
							<i class="line-count">12/15</i>
					</div>
				</div>
				<%--<div class="col-md-2">
					<div id="line-win"  onclick="showModel(6)">
							<span  class="glyphicon glyphicon-folder-open"></span>
							<p>载入程式数</p>
							<i class="line-count">12/15</i>
					</div>
				</div>--%>
				<div class="col-md-2">
					<div id="line-win"  onclick="showModel(7)">
							<span  class="glyphicon glyphicon-bell"></span>
							<p> 当前故障报警数</p>
							<i class="line-count">0</i>
					</div>
				</div>
				<div class="col-md-2">
					<div id="line-win"  onclick="showModel(8)">
							<span  class="glyphicon glyphicon-exclamation-sign"></span>
							<p> 其它</p>
							<i class="line-count">0</i>
					</div>
				</div>
		</div>
			<div class="row" style="margin-top:-70px ">
				<div class="col-md-2 col-md-offset-3">
					<div id="line-win"  onclick="showModel(4)">
						<span  class="glyphicon glyphicon-modal-window"></span>
						<p> FOV/PCB分析</p>
						<i class="line-count"> 236/50</i>
					</div>
				</div>
				<div class="col-md-2">
					<div id="line-win"  onclick="showModel(5)">
						<span  class="glyphicon glyphicon-copyright-mark"></span>
						<p>component分析</p>
						<%--<p> 活动线体/总线体</p>--%>
						<i class="line-count">50</i>
					</div>
				</div>
				<div class="col-md-2">
					<div id="line-win"  onclick="showModel(6)">
						<span  class="glyphicon glyphicon-exclamation-sign"></span>
						<p> 预留</p>
						<i class="line-count">2333</i>
					</div>
				</div>
				<%-- <div class="col-md-1">
                     <div id="line-win"  onclick="showModel(4)">
                         <span  class="glyphicon glyphicon-exclamation-sign"></span>
                         <p> 预留</p>
                         <i class="line-count">10201</i>
                     </div>
                 </div>
                 <div class="col-md-1">
                     <div id="line-win"  onclick="showModel(4)">
                         <span  class="glyphicon glyphicon-exclamation-sign"></span>
                         <p> 预留</p>
                         <i class="line-count">10201</i>
                     </div>
                 </div>
                 <div class="col-md-1">
                     <div id="line-win"  onclick="showModel(4)">
                         <span  class="glyphicon glyphicon-exclamation-sign"></span>
                         <p> 预留</p>
                         <i class="line-count">10201</i>
                     </div>
                 </div>
                 <div class="col-md-1">
                     <div id="line-win"  onclick="showModel(4)">
                         <span  class="glyphicon glyphicon-exclamation-sign"></span>
                         <p> 预留</p>
                         <i class="line-count">10201</i>
                     </div>
                 </div>--%>
			</div>

		</nav>
	</body>
</html>

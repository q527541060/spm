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
                text-align: center;
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
				margin-bottom: 50px;
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
				background-color: #D2DCDD;/* #F2F2F2;*/
				padding: 20px;
				transition: all 0.5s;
               /* margin-left: 280px;*/
                margin: 0 auto;
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
				font-size: 20px;
			}
		</style>
		<script type="text/javascript">
			function showModel(x){

				switch (x) {
					case 2:
                        window.location.href="${basePath}/sLine/pcbLine";
						break;
					case 3:
						window.location.href="${basePath}/sStatus/pcbMonitorview_realLineView";
						break;
					case 5:
						window.location.href="${basePath}/aLine/pcbLine";
						break;
					case 7:
						window.location.href="${basePath}/aStatus/pcbMonitorview_realLineView";
						break;
                    case 10:
                        window.location.href="${basePath}/aLine/pre/pcbfovView";
                        break;
                    case 11:
                        window.location.href="${basePath}/threePointClose/showThreePointClose";
                        break;
					default :
                        alert('待开放！ 尽请期待哦亲！...');
						break;
				}


			}
		</script>
	</head>
	<body>

		<nav id="nav_div">
            <%@include file="header.jsp" %>
			<!--  SPI -->
			<div class="row" >
				    <h3>  SPI  </h3>
                    <div class="col-md-10 col-md-offset-1">
                        <div class="row">
                            <div class="col-md-2">
                                <div id="line-win"  onclick="showModel(2)">
                                    <span  class="glyphicon glyphicon-star-empty"></span>
                                    <p> 过程分析大数据</p>
                                    <i class="line-count"> ${spi_dataCount}</i>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div id="line-win"  class="line-win-offset-right" onclick="showModel(3)">
                                    <span  class="glyphicon glyphicon-bell"></span>
                                    <p>活动总线实时监控</p>
                                    <%--<p> 活动线体/总线体</p>--%>
                                    <i class="line-count">${spi_lineCount}</i>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div id="line-win"  class="line-win-offset-right" onclick="showModel(4)">
                                    <span  class="glyphicon glyphicon-exclamation-sign"></span>
                                    <p> 条码追溯</p>
                                    <i class="line-count">${spi_barcodeCount}</i>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div id="line-win"  class="line-win-offset-left" onclick="showModel(4)">
                                    <span  class="glyphicon glyphicon-modal-window"></span>
                                    <p> FOV/PCB分析</p>
                                    <i class="line-count"> ${spi_fovCount}/${spi_pcbCount}</i>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div id="line-win"  class="line-win-offset-left" onclick="showModel(11)">
                                    <span  class="glyphicon glyphicon-copyright-mark"></span>
                                    <p>component分析</p>
                                    <i class="line-count">${spi_componentCount}</i>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div id="line-win"  class="line-win-offset-left" onclick="showModel(6)">
                                    <span  class="glyphicon glyphicon-exclamation-sign"></span>
                                    <p> 预留</p>
                                    <i class="line-count">2333</i>
                                </div>
                            </div>
                        </div>
                    </div>

			</div>

			<!-- Pre-AOI -->
			<div class="row">
				<h3> PRE-AOI</h3>
                <div class="col-md-10 col-md-offset-1">
                    <div class="row">
                        <div class="col-md-2">
                            <div id="line-win"  onclick="showModel(5)">
                                <span  class="glyphicon glyphicon-star-empty"></span>
                                <p> 活动线体/总线体</p>
                                <i class="line-count">0/0</i>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div id="line-win"  onclick="showModel(7)">
                                <span  class="glyphicon glyphicon-bell"></span>
                                <p> 故障报警数</p>
                                <i class="line-count">0</i>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div id="line-win"  onclick="showModel(8)">
                                <span  class="glyphicon glyphicon-exclamation-sign"></span>
                                <p> 炉前质量分析</p>
                                <i class="line-count">0</i>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div id="line-win"  onclick="showModel(10)">
                                <span  class="glyphicon glyphicon-modal-window"></span>
                                <p> FOV/PCB分析</p>
                                <i class="line-count"> 0/0</i>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div id="line-win"  onclick="showModel(11)">
                                <span  class="glyphicon glyphicon-copyright-mark"></span>
                                <p>component分析</p>
                                <%--<p> 活动线体/总线体</p>--%>
                                <i class="line-count">0</i>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div id="line-win"  onclick="showModel(6)">
                                <span  class="glyphicon glyphicon-exclamation-sign"></span>
                                <p> 预留</p>
                                <i class="line-count">0</i>
                            </div>
                        </div>
                    </div>
                </div>

		    </div>

			<!-- Post-Aoi -->
			<div class="row">
				<h3> POST-AOI</h3>
                <div class="col-md-10 col-md-offset-1">
                    <div class="row">
                        <div class="col-md-2">
                            <div id="line-win"  onclick="showModel(5)">
                                <span  class="glyphicon glyphicon-star-empty"></span>
                                <p> 活动线体/总线体</p>
                                <i class="line-count">0/0</i>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div id="line-win"  onclick="showModel(7)">
                                <span  class="glyphicon glyphicon-bell"></span>
                                <p> 统计</p>
                                <i class="line-count">0</i>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div id="line-win"  onclick="showModel(8)">
                                <span  class="glyphicon glyphicon-exclamation-sign"></span>
                                <p> 炉后缺陷分析</p>
                                <i class="line-count">0</i>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div id="line-win"  onclick="showModel(4)">
                                <span  class="glyphicon glyphicon-modal-window"></span>
                                <p> FOV/PCB分析</p>
                                <i class="line-count"> 0/0</i>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div id="line-win"  onclick="showModel(11)">
                                <span  class="glyphicon glyphicon-copyright-mark"></span>
                                <p>component分析</p>
                                <%--<p> 活动线体/总线体</p>--%>
                                <i class="line-count">0</i>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div id="line-win"  onclick="showModel(6)">
                                <span  class="glyphicon glyphicon-exclamation-sign"></span>
                                <p> 预留</p>
                                <i class="line-count">0</i>
                            </div>
                        </div>
                    </div>
                </div>

			</div>

		</nav>

        <style>

        </style>
	</body>
</html>

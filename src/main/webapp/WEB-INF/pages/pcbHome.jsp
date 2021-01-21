<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>Home</title>
        <link rel="stylesheet icon" href="${staticPath}/static/img/logo.jpg" type="image/x-icon" media="screen" />
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
				width:100px;
                height: 100px;
				background-color: #87b8cd;/* #F2F2F2; 337AB7  87b8cd*/
				padding: 1px;
				transition: all 0.5s;
               /* margin-left: 280px;*/
                margin: 0 auto;
              /* // margin: 20px;*/
                border-radius:100%;
			}

			#line-win:hover{
				cursor:pointer;
				background-color: rgba(122, 163, 53, 0.2);
				transform: scale(1.15);
				/*width: 120%;
				transition: all 2s ease-in 1s;*/
			}
			#line-win-logo{
				color: #3f3f57;
				font-size: 25px;
			}
            #line-win-span-badge{
               /* color: #7BA4D9;
                font-size: 8px;*/
            }

			#line-win .line-count{
				color: #E67964;
				font-size: 15px;
			}
            td{
              width: 11%;
            }
            th{
                width: 11%;
            }
            @keyframes changeFrames
            {
                0%   {background: #87b8cd; left:0px; top:0px;}
                10%  {background: #e7ff41; left:0px; top:0px;}
                20%  {background: #87b8cd; left:0px; top:0px;}
                30%  {background: #e7ff41; left:0px; top:0px;}
                40%  {background: #87b8cd; left:0px; top:0px;}
                50%  {background: #e7ff41; left:0px; top:0px;}
                60%  {background: #87b8cd; left:0px; top:0px;}
                70%  {background: #e7ff41; left:0px; top:0px;}
                80%  {background: #87b8cd; left:0px; top:0px;}
                90%  {background: #e7ff41; left:0px; top:0px;}
                100% {background: #87b8cd; left:0px; top:0px;}
            }
            @keyframes iniFrames
            {
                100% {background: #87b8cd; left:0px; top:0px;}
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
					case 5:   //炉前
						window.location.href="${basePath}/aLine/pcbLine?aoiType=1";
						break;
					case 7: //炉前
						window.location.href="${basePath}/aStatus/pcbMonitorview_realLineView?aoiType=1";
						break;
                    case 10://炉前
                        window.location.href="${basePath}/aLine/pre/pcbfovView?aoiType=1";
                        break;
                    case 11://炉前
                        window.location.href="${basePath}/threePointClose/showThreePointClose";
                        break;

                    case 115:   //炉后
                        window.location.href="${basePath}/aLine/pcbLine?aoiType=2";
                        break;
                    case 117: //炉后
                        window.location.href="${basePath}/aStatus/pcbMonitorview_realLineView?aoiType=2";
                        break;
                    case 110://炉后
                        window.location.href="${basePath}/aLine/pre/pcbfovView?aoiType=2";
                        break;
                    case 111://炉后
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
            <!-- 设备看板轮询时间 -->
            <input type="hidden" id="boardMachineRefreshTime" value="${boardMachineRefreshTime}"/>
            <div class="row" style="margin-top: -50px;padding:5px;background-color: #C2CAE1">
                <h3 style="margin-top: 0px">Statistical Data Control System<span class="label label-default">SINICTEK</span></h3>
                <div class="col-md-8 col-xs-offset-2" >
                    <image  src="${staticPath}/img/smt_best.jpg" style="width:70%;height:20vh;"></image>
                </div>
            </div>
            <div class="row" style="margin-top: -40px;">
                <div class="col-md-12 table-responsive" >
                    <table class="table text-nowrap" data-toggle="table" data-classes="table  table-hover"
                           data-row-style="dataRow">
                        <thead>
                        <tr>
                            <th>Station</th>
                            <th>DataInfo</th>
                            <th>Monitoring</th>
                            <th>Barcodes</th>
                            <th>Fov/Pcb</th>
                            <th>ThreePointClose</th>
                            <th>MES</th>
                            <th>Detail</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>SPI</td>
                            <td>
                                <div id="line-win"  onclick="showModel(2)">
                                    <span id="line-win-logo" class="glyphicon glyphicon-stats"></span>
                                    <p> 过程分析</p>
                                    <i class="line-count"> <c:if test="${ empty spi_dataCount}">0/0</c:if> <c:if test="${spi_dataCount!=''}">${spi_dataCount}</c:if> </i>
                                </div>
                            </td>
                            <td>
                                <div id="line-win"  class="line-win-offset-right" onclick="showModel(3)">
                                    <span  id="line-win-logo" class="glyphicon glyphicon-bell spi-line-win-logo"></span>
                                 <%--   <p>SPI看板监控</p>--%>
                                    <p style="margin: 8px">良率报警:<span class="badge" id="spi-yeild-count">0</span></p>
                                    <p  style="margin: 4px">故障报警:<span class="badge" id="spi-status-count">0</span></p>
                                    <%--<p> 活动线体/总线体</p>--%>
                                   <%-- <i class="line-count">${spi_lineCount}</i>--%>
                                </div>
                            </td>
                            <td>
                                <div id="line-win"  class="line-win-offset-right" onclick="showModel(4)">
                                    <span id="line-win-logo" class="glyphicon glyphicon-qrcode"></span>
                                    <p>条码追溯</p>
                                    <i class="line-count">${spi_barcodeCount}</i>
                                </div>
                            </td>
                            <td>
                                <div id="line-win"  class="line-win-offset-left" onclick="showModel(10)">
                                    <span id="line-win-logo" class="glyphicon glyphicon-folder-close"></span>
                                    <p> FOV/PCB</p>
                                    <i class="line-count"> ${spi_fovCount}/${spi_pcbCount}</i>
                                </div>
                            </td>
                            <td>
                                <div id="line-win"  class="line-win-offset-left" onclick="showModel(11)">
                                    <span id="line-win-logo" class="glyphicon glyphicon-tasks"></span>
                                    <p>component</p>
                                    <i class="line-count">${spi_componentCount}</i>
                                </div>
                            </td>
                            <td>
                                <div id="line-win"  class="line-win-offset-left" onclick="showModel(6)">
                                    <span id="line-win-logo" class="glyphicon glyphicon-sound-5-1"></span>
                                    <p> spi-demo</p>
                                    <i class="line-count">2333</i>
                                </div>
                            </td>
                            <td>
                                <div id="line-win"  class="line-win-offset-left" onclick="showModel(666)">
                                    <span id="line-win-logo" class="glyphicon glyphicon-flag"></span>
                                    <p>spidetail</p>
                                    <i class="line-count">0</i>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>PRE-AOI</td>
                            <td>
                                <div id="line-win"  onclick="showModel(5)">
                                    <span id="line-win-logo" class="glyphicon glyphicon-equalizer"></span>
                                    <p> 活动线体/总线体</p>
                                    <i class="line-count">${preAoi_lineCount}/${preAoi_lineCount}</i>
                                </div>
                            </td>
                            <td><div id="line-win"  onclick="showModel(7)">
                                <span id="line-win-logo" class="glyphicon glyphicon-bell AoiPre-line-win-logo"></span>
                                <%--<p> 炉前看板查询</p>
                                <i class="line-count">0</i>--%>
                                <p style="margin: 8px" >良率报警:<span class="badge" id="preaoi-yeild-count">0</span></p>
                                <p style="margin: 4px">故障报警:<span class="badge" id="preaoi-status-count">0</span></p>
                            </div></td>
                            <td><div id="line-win"  onclick="showModel(8)">
                                <span id="line-win-logo" class="glyphicon glyphicon-list-alt"></span>
                                <p> 炉前分析</p>
                                <i class="line-count">0</i>
                            </div></td>
                            <td><div id="line-win"  onclick="showModel(10)">
                                <span id="line-win-logo" class="glyphicon glyphicon-level-up"></span>
                                <p> FOV/PCB</p>
                                <i class="line-count"> 0/0</i>
                            </div></td>
                            <td><div id="line-win"  onclick="showModel(11)">
                                <span id="line-win-logo" class="glyphicon glyphicon-tasks"></span>
                                <p>component</p>
                                <%--<p> 活动线体/总线体</p>--%>
                                <i class="line-count">0</i>
                            </div></td>
                            <td><div id="line-win"  onclick="showModel(6)">
                                <span id="line-win-logo" class="glyphicon glyphicon-subtitles"></span>
                                <p> pre-demo</p>
                                <i class="line-count">0</i>
                            </div></td>
                            <td>
                                <div id="line-win"  class="line-win-offset-left" onclick="showModel(666)">
                                    <span id="line-win-logo" class="glyphicon glyphicon-flag"></span>
                                    <p>preAoidetail</p>
                                    <i class="line-count">0</i>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>POST-AOI</td>
                            <td><div id="line-win"  onclick="showModel(115)">
                                <span id="line-win-logo" class="glyphicon glyphicon-signal"></span>
                                <p> 活动线体/总线体</p>
                                <i class="line-count">${postAoi_lineCount}/${postAoi_lineCount}</i>
                            </div></td>
                            <td><div id="line-win"  onclick="showModel(117)">
                                <span id="line-win-logo" class="glyphicon glyphicon-bell AoiPost-line-win-logo"></span>
                                <%--<p>炉后看板查询</p>
                                <i class="line-count">0</i>--%>
                                <p style="margin: 8px">良率报警:<span class="badge" id="postaoi-yeild-count">0</span></p>
                                <p style="margin: 4px">故障报警:<span class="badge" id="postaoi-status-count">0</span></p>
                            </div></td>
                            <td><div id="line-win"  onclick="showModel(118)">
                                <span id="line-win-logo" class="glyphicon glyphicon-list-alt"></span>
                                <p> 炉后分析</p>
                                <i class="line-count">0</i>
                            </div></td>
                            <td><div id="line-win"  onclick="showModel(110)">
                                <span id="line-win-logo" class="glyphicon glyphicon-folder-open"></span>
                                <p> FOV/PCB</p>
                                <i class="line-count"> 0/0</i>
                            </div></td>
                            <td> <div id="line-win"  onclick="showModel(111)">
                                <span id="line-win-logo" class="glyphicon glyphicon-tasks"></span>
                                <p>component</p>
                                <%--<p> 活动线体/总线体</p>--%>
                                <i class="line-count">0</i>
                            </div></td>
                            <td><div id="line-win"  onclick="showModel(116)">
                                <span id="line-win-logo" class="glyphicon glyphicon-sound-6-1"></span>
                                <p> post-demo</p>
                                <i class="line-count">0</i>
                            </div></td>
                            <td><div id="line-win"  class="line-win-offset-left" onclick="showModel(666)">
                                <span id="line-win-logo" class="glyphicon glyphicon-flag"></span>
                                <p>postAoidetail</p>
                                <i class="line-count">0</i>
                            </div></td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>

            <div class="row" style="text-align: center">
                <div class="col-md-12">
                    <span>©2021 Sinic-Tek intelligent Technology Co.,Ltd 版权所有</span>
                </div>

            </div>

		</nav>


    <script>

        function dataRow(row, index){
            if (index % 2 == 0 ) {
                return {
                    classes: 'info',
                }
            }else{
                return {
                    classes: 'info ',
                }
            }
        }
        //看板轮询时间
        var boardMachineRefreshTime = $("#boardMachineRefreshTime").val();
        if(boardMachineRefreshTime==null || boardMachineRefreshTime=='' ||boardMachineRefreshTime==0){
            boardMachineRefreshTime = 10;
        }
        homeTimeInterver('${basePath}');
        //轮询monitor
        setInterval(function (){homeTimeInterver('${basePath}')},boardMachineRefreshTime*1000,);
    </script>
	</body>
</html>

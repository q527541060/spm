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
				width: 68%;
				background-color: #87b8cd;/* #F2F2F2; 337AB7  87b8cd*/
				padding: 2px;
				transition: all 0.5s;
               /* margin-left: 280px;*/
                margin: 0 auto;
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
				font-size: 20px;
			}
            td{
                width: 10%;
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
                    case 17: //炉后
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
            <div class="row" style="margin-top: -40px;">
                <h3 style="margin-top: 0px">Statistical Data Control System<span class="label label-default">SINICTEK</span></h3>
                <div class="col-md-12">
                    <image  src="${staticPath}/img/spi-aoi.png" style="width:90%;height:30vh;"></image>
                </div>
            </div>
            <div class="row" style="margin-top: -40px;">
                <div class="col-md-12">
                    <table data-toggle="table" data-classes="table  table-hover"
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
                                    <p >良率报警:<span class="badge" id="spi-yeild-count">0</span></p>
                                    <p >故障报警:<span class="badge" id="spi-status-count">0</span></p>
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
                                <div id="line-win"  class="line-win-offset-left" onclick="showModel(4)">
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
                                <p >良率报警:<span class="badge" id="preaoi-yeild-count">0</span></p>
                                <p >故障报警:<span class="badge" id="preaoi-status-count">0</span></p>
                            </div></td>
                            <td><div id="line-win"  onclick="showModel(8)">
                                <span id="line-win-logo" class="glyphicon glyphicon-list-alt"></span>
                                <p> 炉前质量分析</p>
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
                                <p >良率报警:<span class="badge" id="postaoi-yeild-count">0</span></p>
                                <p >故障报警:<span class="badge" id="postaoi-status-count">0</span></p>
                            </div></td>
                            <td><div id="line-win"  onclick="showModel(118)">
                                <span id="line-win-logo" class="glyphicon glyphicon-list-alt"></span>
                                <p> 炉后缺陷分析</p>
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

            <!--  SPI -->
			<%--<div class="row" >
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
                                    &lt;%&ndash;<p> 活动线体/总线体</p>&ndash;%&gt;
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
--%>
			<!-- Pre-AOI -->
			<%--<div class="row">
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
                                &lt;%&ndash;<p> 活动线体/总线体</p>&ndash;%&gt;
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

		    </div>--%>

			<!-- Post-Aoi -->
			<%--<div class="row">
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
                                &lt;%&ndash;<p> 活动线体/总线体</p>&ndash;%&gt;
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

			</div>--%>

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
        homeTimeInterver();
        //刷新Monitoring
        function homeTimeInterver(){
            $.ajax({
                url: "${basePath}/Home/homeTimeInterver",
                dataType:"json",   //返回格式为json
                async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                data:'',    //参数值
                type:"GET",   //请求方式
                beforeSend:function(){
                    //请求前的处理
                },
                success:function(req) {
                    //刷新spi aoi monitoring
                    //var spi_yeild_count = req.data.iSpiYeildCount;
                   /* $('#spi-status-count').empty();
                    $('#preaoi-status-count').empty();
                    $('#postaoi-status-count').empty();
                    $('#spi-yeild-count').empty();
                    $('#preaoi-yeild-count').empty();
                    $('#postaoi-yeild-count').empty();*/

                    $('#spi-status-count')[0].innerText=req.data.iSpiLineErrorCount;
                    $('#preaoi-status-count')[0].innerText=req.data.iAoiPreLineErrorCount;
                    $('#postaoi-status-count')[0].innerText=req.data.iAoiPostLineErrorCount;

                    $('#spi-yeild-count')[0].innerText = req.data.iSpiYeildCount;
                    $('#preaoi-yeild-count')[0].innerText = req.data.iPreAoiYeildCount;
                    $('#postaoi-yeild-count')[0].innerText = req.data.iPostAoiYeildCount;
                    //spi-line-win-logo
                    if(req.data.iSpiLineErrorCount>0 || req.data.iSpiYeildCount>0) {
                        $('.spi-line-win-logo').css({'color': '#ff1908','animation':'changeFrames 5s infinite alternate'});
                    }else{
                        $('.spi-line-win-logo').css({'color': '#3f3f57','animation':'iniFrames   5s infinite alternate'});
                    }
                    if(req.data.iAoiPreLineErrorCount>0 || req.data.iPreAoiYeildCount>0) {
                        $('.AoiPre-line-win-logo').css({'color': '#ff1908','animation':'changeFrames 5s infinite alternate'});
                    }else{
                        $('.AoiPre-line-win-logo').css({'color': '#3f3f57','animation':'iniFrames   5s infinite alternate'});
                    }
                    if(req.data.iAoiPostLineErrorCount>0 || req.data.iPostAoiYeildCount>0) {
                        $('.AoiPost-line-win-logo').css({'color': '#ff1908','animation':'changeFrames 5s infinite alternate'});
                    }else{
                        $('.AoiPost-line-win-logo').css({'color': '#3f3f57','animation':'iniFrames   5s infinite alternate'});
                    }
                },error:function(data){
                }
            });
        }
        //轮询monitor
        setInterval(homeTimeInterver,boardMachineRefreshTime*1000);
    </script>
	</body>
</html>

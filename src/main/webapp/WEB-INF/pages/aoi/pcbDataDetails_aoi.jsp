<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="zh-CN">
<head>
    <title>dataDetails</title>
    <link rel="stylesheet icon" href="${staticPath}/static/img/logo.jpg" type="image/x-icon" media="screen" />
     <style>
         body{
             margin: 0px;
             padding: 0px;
             /*background-color: #ECF0F5;*/
            /* background: url("$staticPath}/static/img/home6.jpg")  ;*/
           /*  background-size:cover;*/
         }
         .row{
             text-align: center;
             margin: 5px;
             align-content: center;
         }
        .col-md-14{
            margin: 0px;padding: 0px;
        }
     </style>
</head>
<body>
    <nav>
        <%@include file="../header.jsp" %>
        <div class="row" style="margin-top: -10px; text-align: left;">
            <div class="col-md-14">
                <ol class="breadcrumb">
                    <li><a href="${basePath}/Home/pcbHome">Home</a></li>
                   <%-- <li><a href="#">spi</a></li>--%>
                    <li class="avtive"><c:if test="${aoiType !=1}">post-aoi</c:if><c:if test="${aoiType ==1}">pre-aoi</c:if></li>
                    <li class="active">dataInfo</li>
                    <li class="active">dataInfoDetail</li>
                </ol>
            </div>
        </div>
        <!-- s饼图详情-->
        <div class="row" style="margin-top: -20px;; padding: 0px;">
            <div class="col-md-12">
                <h4 style="margin: 0px"> ${lineNo}线质量统计分析 </h4>
                <input id="lineNo" type="hidden" value="${lineNo}">
                <input id="inspectStarttime" type="hidden" value="${inspectStarttime}">
                <input id="inspectEndtime" type="hidden" value="${inspectEndtime}">
                <input id="pcbType" type="hidden" value="${pcbType}">

            </div>
        </div>
        <!--  饼图缺陷  -->
        <div class="row" style="margin: 0px">
            <div class="col-md-6">
                <div id="container_pcb" style="min-height:43vh;height:43vh"></div>
            </div>
            <div class="col-md-6">
                <div id="container_pad" style="min-height:43vh;height:43vh"></div>

            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <table id="table_pcbList" ></table>
            </div>
            <div class="col-md-6">
                <table id="table_padList"></table>
            </div>
            <div id="pcbToolbar">
                <button  type="button" class="btn btn-default">EXP</button>
            </div>
            <div id="padToolbar">
                <button  type="button" class="btn btn-default ">EXP</button>
            </div>
        </div>

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Spotted Image</h4>
                    </div>
                    <div class="modal-body">
                        <div id="pad2dimage" style="text-align: center;">
                            <%--<img src="">--%>
                        </div>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
            </div>
        </div>

        <div id="svgImage">

        </div>
        <!-- aoiType input -->
        <input type="hidden" id="aoiType" value="${aoiType}" />
       <%-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5>pad image</h5>
                    </div>
                    <div class="modal-body">
                        <div id="pad2dimage" style="text-align: center;">
                            &lt;%&ndash;<img src="">&ndash;%&gt;
                        </div>
                    </div>
                </div>
            </div>
        </div>--%>
    </nav>

    <script type="text/javascript">

        var barcode='';
        var firstPcbId =1;
        var firstpcbIdLine ='';
        var lineNo =$('#lineNo').val();
        var inspectStarttime = $("#inspectStarttime").val();
        var inspectEndtime = $("#inspectEndtime").val();
        var pcbType = $('#pcbType').val();
        var padArrBarcode = null;
        var padArrResult='';
        var aoiType = $('#aoiType').val();
        //alert(aoiType);
        if(aoiType==''||aoiType==null){
            aoiType=1;
        }
        //alert(inspectStarttime);
        //alert(inspectEndtime);
        <!--  缺陷类型图  -->
        function padCpountpie(pcbId){
            var padCountpieJson={};
            $.ajax({
                url:"${basePath}/aComponent/componentDefaultTypeInfo",
                dataType:"json",
                async:true,
                data:{id:pcbId},
                type:"GET",
                success:function (req) {
                    padCountpieJson.title={
                        text:'缺陷分布分析'
                    };
                    padCountpieJson.xAxis= [
                        {
                            type: "category",
                            index: 0,
                            isX: true
                        }
                    ];

                    padCountpieJson.chart= {
                        type: "pie",
                            style: {
                            fontFamily: "\"微软雅黑\", Arial, Helvetica, sans-serif",
                                color: "#333",
                                fontSize: "12px",
                                fontWeight: "normal",
                                fontStyle: "normal"
                        }
                    };
                    padCountpieJson.exporting={
                        enabled:false
                    };
                    padCountpieJson.plotOptions= {
                        pie: {
                            allowPointSelect: true,
                                cursor: 'pointer',
                                dataLabels: {
                                enabled: true,
                                    //distance: -30,
                                    format:'<b>{point.name}:({point.y}PCS)</b>',


                            },
                            showInLegend: true,
                            events:{
                                click:function (e) {
                                    var padDefultType = '';
                                    var tmp = e.point.name;
                                    switch (tmp) {
                                        case 'Missing':
                                            padDefultType = '0';
                                            break;
                                        case  'Custom':
                                            padDefultType = '1';
                                            break;
                                        case  'Default':
                                            padDefultType = '2';
                                            break;
                                        case  'ShiftX':
                                            padDefultType = '3';
                                            break;
                                        case  'ShiftY':
                                            padDefultType = '4';
                                            break;
                                        case  'Rotation':
                                            padDefultType = '5';
                                            break;
                                        case  'Bridge':
                                            padDefultType = '6';
                                            break;
                                        case  'Void':
                                            padDefultType = '7';
                                            break;
                                        case  'TombStone':
                                            padDefultType = '8';
                                            break;
                                        case  'PinLift':
                                            padDefultType = '9';
                                            break;
                                        case  'SolderBead':
                                            padDefultType = '10';
                                            break;
                                        case  'Smear':
                                            padDefultType = '11';
                                            break;
                                        case  'Polarity':
                                            padDefultType = '12';
                                            break;
                                        case  'Reverse':
                                            padDefultType = '13';
                                            break;
                                        case  'WrongPart':
                                            padDefultType = '14';
                                            break;
                                        case  'NoSolder':
                                            padDefultType = '15';
                                            break;
                                        case  'CopperExposure':
                                            padDefultType = '16';
                                            break;
                                        case  'ExcessParts':
                                            padDefultType = '17';
                                            break;
                                        case  'ExcessParts':
                                            padDefultType = '18';
                                            break;

                                        default :
                                            padDefultType = '0';
                                            break;
                                    }
                                    refreshPcbDataDetailsPadTable(firstpcbIdLine,padDefultType);
                                }
                            }
                        }
                    };
                    padCountpieJson.credits= {
                        enabled: false
                    };
                    padCountpieJson.series=[
                        {
                            type: "pie",
                            name: "barcode",
                            data:JSON.parse(req.data)
                        }
                    ];
                    padCountpieJson.credits={enabled: false };
                    padCountpieJson.legend = {
                        enabled:false,
                    };
                    $('#container_pad').highcharts(padCountpieJson);
                }
            });

        }
        <!-- tablelist -->
        var  $Pcbtable;
        InitPcbTable('0,1,2,4');
        <!--  缺陷pcb图  -->
        pcbCountpie('0,1,2,4');

        function clickPiePcb(PcbResult){
            refreshPcbDataDetailsPCBTable(PcbResult);
        }

        function pcbCountpie(pcbResult){
            var pcbCountpieJson={};
            $.ajax({
                url:"${basePath}/aLine/lineDetailsLeftChart",
                dataType:"json",
                async:true,
                data:{lineNo:lineNo,inspectStarttime:inspectStarttime,inspectEndtime:inspectEndtime,pcbResult:pcbResult,pcbType:pcbType,aoiType:aoiType},
                type:"GET",
                success:function (req) {
                    //success
                    var pieColors=['#007AAE','#B84A5B','#22B14C'];
                    pcbCountpieJson.title={
                        text:'PCB分析'
                    };
                    pcbCountpieJson.xaxis=[
                        {
                            type: "category",
                            index: 0,
                            isX: true
                        }
                    ];
                    pcbCountpieJson.plotOptions={
                        pie: {
                            allowPointSelect: true,
                            colors:pieColors,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                //distance: -30,
                                format:'<b>{point.name}:({point.y}PCS)</b>',


                            },
                            showInLegend: true,
                            events:{
                                click:function (e) {
                                    var pcbresult = '';
                                    var tmp = e.point.name;
                                    if(pcbType==2){
                                        switch (tmp) {
                                            case 'NG':
                                                pcbresult = '1';
                                                break;
                                            default :
                                                pcbresult = '0,1,2,4';
                                                break;
                                        }
                                    }else {
                                        switch (tmp) {
                                            case 'NG':
                                                pcbresult = '1';
                                                break;
                                            case  'REPASS':
                                                pcbresult = '2';
                                                break;
                                            case  'PASS':
                                                pcbresult = '0';
                                                break;
                                            default :
                                                pcbresult = '0,1,2,4';
                                                break;
                                        }
                                    }
                                    refreshPcbDataDetailsPCBTable(pcbresult);
                                }
                            }
                        }
                    };
                    pcbCountpieJson.chart={
                        type: "pie",
                        style: {
                            fontFamily: "\"微软雅黑\", Arial, Helvetica, sans-serif",
                            color: "#333",
                            fontSize: "12px",
                            fontWeight: "normal",
                            fontStyle: "normal"
                        }
                    };
                    pcbCountpieJson.credits={
                        enabled: false
                    };
                    pcbCountpieJson.series = [
                        {
                            type: "pie",
                            name: lineNo,
                            data:  JSON.parse(req.data)

                        }
                    ];
                    pcbCountpieJson.exporting={
                        enabled:false
                    };
                    pcbCountpieJson.credits={enabled: false };
                    pcbCountpieJson.legend = {
                        enabled:false,
                    };
                    $('#container_pcb').highcharts(pcbCountpieJson);

                },
                error:function(){
                }

            });
        }

        window.operateEventslineDetailsLeftChart={
            "click #TableNGImage" :function(e,value, row, index){
                showPadImageModal(row.comImageBase64);
                // window.location.href="${basePath}/sLine/pcbLineDetails?lineNo="+row.lineNo;
                //alert(row.pad2dImage);
                //byte[] out =
                /*var pcbidLine = row.pcbIdLine;
                pcbidLine.replace("#","=====");
                var padid = row.id;
                $.ajax({
                    url:"${basePath}/aComponent/padImage",
                    dataType:"json",
                    async:true,
                    data:{pcbidLine:pcbidLine,padId:padid,aoiType:aoiType},
                    type:"POST",
                    success:function (req) {
                        //alert(req.data);
                        showPadImageModal(req.data);
                    },
                    error:function (data) {
                        //alert('error:'+data);
                    }
                });*/

            }
        }
        function showPadImageModal(image){
            $('#pad2dimage').empty();
            $("<img  style='width: 450px;height:400px; object-fit: cover;' src='data:image/jpeg;base64,"+ image+"  '>").appendTo("#pad2dimage");
           // html.modal('show');
            $('#myModal').modal('show');
        }

        function DoOnMsoNumberFormat(cell, row, col) {
            var result = "";
            if (row > 0 && col == 0)
                result = "\\@";
            return result;
        }
        function InitPcbTable (pcbResult) {
            //alert(lineNo);
            //记录页面bootstrap-table全局变量$table，方便应用
            $Pcbtable = $('#table_pcbList').bootstrapTable({
                url:"${basePath}/aLine/lineDetailsLeftChart?lineNo="+lineNo+
                    "&inspectStarttime="+inspectStarttime+
                    "&inspectEndtime="+inspectEndtime+
                    "&pcbResult="+pcbResult+                      //请求后台的URL（*）
                    "&pcbType="+pcbType+
                    "&aoiType="+aoiType,
                dataType:"json",
                method: 'GET',                      //请求方式（*）
                //data:{lineNo:lineNo,inspectStarttime:inspectStarttime,inspectEndtime:inspectEndtime,pcbResult:pcbResult},
                toolbar: '#pcbToolbar',              //工具按钮用哪个容器
                toolbarAlign:'right',
                striped: true,                      //是否显示行间隔色
                cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                sortName:'inspectStarttime',
                sortOrder: "desc",                   //排序方式
                sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
                pageSize: 12,                     //每页的记录行数（*）
                pageList: [15, 20, 50, 100,'ALL'],        //可供选择的每页的行数（*）
                search: true,                      //是否显示表格搜索
                //data-search=true,
                strictSearch: false,
                showColumns: true,                  //是否显示所有的列（选择显示的列）
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                height:380,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                //uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                  //是否显示父子表
                classes:'table table-striped table-hover',
                exportDataType:'all',
                showExport: false,  //是否显示导出按钮
                showLoading:true,
                exportTypes: [ 'csv', 'txt', 'xml', 'excel'],
                Icons:'glyphicon-export',
                exportOptions:{
                    ignoreColumn: [0],  //忽略某一列的索引
                    fileName: '线体',  //文件名称设置
                    worksheetName: 'sheet1',  //表格工作区名称
                    tableName: '线体',
                    excelstyles: ['background-color', 'color', 'font-size', 'font-weight'],
                    onMsoNumberFormat: DoOnMsoNumberFormat
                },
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
                columns: [
                    /*{
                    checkbox: true,
                    align:'center',
                    visible: true,
                    //field: 'id',
                    title: 'ser'
                    //是否显示复选框
                }, */
                    {
                        field: 'jobName',
                        title: 'jobName',
                        // width:50,
                        align:'center',
                        sortable: true,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                    }, {
                        field: 'lineNo',
                        title: 'lineNo',
                        align:'center',
                        width:10,
                        sortable: true,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                    },
                    {
                        field: 'boardBarcode',
                        title:  'boardBarcode',
                        align:'center',
                        sortable: true,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                        //  width:50
                        //events:operateEvents,
                    },  {
                        field: 'inspectResult',
                        title:  'pcbResult',
                        align:'center',
                        width:5,
                        sortable: true,
                        formatter:function (value,row,index) {
                            switch (  row.inspectResult) {
                                case 0:
                                case '0':
                                    return 'PASS';
                                case 1:
                                case '1':
                                    return 'NG';
                                case 2:
                                case '2':
                                    return 'REPASS';
                                default :
                                    return  'SKIP';

                            }
                        },
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                    }, {
                        field: 'arrayInfo',
                        title:  'arrayInfo',
                        align:'center',
                        sortable: true,
                        formatter:function (value,row,index) {

                            var arrBarcode = '';//row.arrayBarcode.split(';');
                            var arrResult = '';//row.arrayinspectResult.split(';');
                            var resultHtml='<span>';
                            if(row.arrayInfo!=null) {
                                var arrinfo = JSON.parse(row.arrayInfo);
                                if(arrinfo.length>1){
                                    for (var i=0;i<arrinfo.length;i++){
                                        resultHtml +=arrinfo[i].arraryIndex +';'+ arrinfo[i].arrayBarcode+';'+
                                            arrpcbResultToStandResult_WITH_AOI(arrinfo[i].result)+'<br>';
                                    }
                                }else{
                                    resultHtml= arrBarcode + ';' +arrResult;
                                }
                            }
                            resultHtml +='</span>';
                            return  resultHtml;

                        },
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                        //  width:50
                    },{
                        field: 'ngComponentCount',
                        title:  'ngComponentCount',
                        align:'center',
                        sortable: true,
                        width:10,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                    },{
                        field: 'cpk',
                        title:  'cpk',
                        align:'center',
                        sortable: true,
                        formatter:function (value,row,index) {

                            return '<span>'+ 'area:'+ row.acpk.toFixed(3) +
                                ';hight:'+ row.hcpk.toFixed(3)+
                                ';vol:'+ row.vcpk.toFixed(3)+
                                ';shithxCpk:'+row.shithxCpk.toFixed(3)+
                                ';shithyCpk:'+row.shithyCpk.toFixed(3) +'</span>';
                        },
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                    },
                    /* {
                     field: 'arrayinspectResult',
                     title:  'arrayinspectResult',
                     align:'center',
                     sortable: true,
                   //  width:50
                 },*/
                    {
                        field: 'laneNo',
                        title:  'laneNo',
                        align:'center',
                        sortable: true,
                        width:2,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                    },{
                        field: 'inspectStarttime',
                        title:  'inspectStarttime',
                        align:'center',
                        sortable: true,
                        width:10,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                        //events: operateEventslineDetailsLeftChart,//给按钮注册事件
                        //formatter: addFunctionAltylineDetailsLeftChart//表格中增加按钮
                    },
                    {
                        field: 'inspectEndtime',
                        title:  'inspectEndtime',
                        align:'center',
                        sortable: true,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                        //   width:50
                    },
                    /*{
                        field: 'svgInfo',
                        title:  'svgInfo',
                        formatter:function (value,row,index) {
                            var chart = $('#container_pcb').highcharts().getSVG();
                            return '<img style="width:100px;height:100px;object-fit: cover;" src="data:image/svg+xml;base64,'+window.btoa(unescape(encodeURIComponent(chart)))+'" >';
                            //return 'sds';
                        }
                    }*/
                ],
                formatLoadingMessage:function(){
                    return "请稍等,正在加载中.........";
                },
                onLoadSuccess: function (data,$element) {

                    if(data.rows[0].arrayInfo!=null){
                        padArrBarcode = JSON.parse(data.rows[0].arrayInfo);
                    }
                    /*if(padArrBarcode!=null&&padArrBarcode.length>0){
                        padArrBarcode = padArrBarcode[0].arrayBarcode;
                    }*/
                    //padArrResult = data.rows.arrayinspectResult.split(';');
                    barcode = data.rows==null?'':data.rows[0].boardBarcode;

                    padCpountpie(data.rows[0].id);

                    //firstPcbId = data.rows[0].id;
                    firstpcbIdLine = data.rows[0].pcbIdLine;
                    firstpcbIdLine = firstpcbIdLine.replace('#','=====');
                    InitPadTable(firstpcbIdLine,'');

                },
                onLoadError: function (status,res) {
                    //showTips("数据加载失败！");
                    //alert("onLoadError"+res.data);
                    console.log(res);
                    console.log(status);
                },
                onDblClickRow: function (row, $element) {
                    var line = row.lineNo; //线体
                    var pcbidLine = row.pcbIdLine;  //pcbIdLine
                    barcode = row.boardBarcode;
                    if(row.arrayInfo!=null){
                        padArrBarcode = JSON.parse(row.arrayInfo);
                    }
                    //var tableName = row.
                    var id = row.id;
                    pcbidLine = pcbidLine.replace('#','=====');
                    firstpcbIdLine = pcbidLine;
                    padCpountpie(id);
                    //EditViewById(id, 'view');
                    //alert(line);
                    //defaultTopRealTime(line);
                    //ProductRealTime(line);
                    //refreshPcbDataDetailsPadTable(pcbidLine);
                    refreshPcbDataDetailsPadTable(pcbidLine,'');
                },
                onClickRow: function (row, $element) {
                    $('.danger').removeClass('danger');
                    $($element).addClass('danger');
                    var line = row.lineNo; //线体
                    var pcbidLine = row.pcbIdLine;  //pcbIdLine
                    barcode = row.boardBarcode;
                   // padArrBarcode = row.arrayBarcode.split(';');
                    if(row.arrayInfo!=null){
                        padArrBarcode = JSON.parse(row.arrayInfo);
                    }
                    //padArrResult = row.arrayinspectResult.split(';');
                    //var tableName = row.
                    var id = row.id;
                    pcbidLine = pcbidLine.replace('#','=====');
                    firstpcbIdLine = pcbidLine;
                    padCpountpie(id);
                    //EditViewById(id, 'view');
                    //alert(line);
                    //defaultTopRealTime(line);
                    //ProductRealTime(line);
                    //refreshPcbDataDetailsPadTable(pcbidLine);
                    refreshPcbDataDetailsPadTable(pcbidLine,'');
                },
            });
        };

        function refreshPcbDataDetailsPadTable(ApcbIdLine,padDefultType){
            var opt = {
                url: "${basePath}/aComponent/padList?pcbIdLine="+ApcbIdLine+"&defectTypeCode="+padDefultType+"&aoiType="+aoiType,
                silent: true,
                //contentType: "application/x-www-form-urlencoded",//请求方式（*）
                query:{
                    type:1,
                    level:2
                }
            };
            $("#table_padList").bootstrapTable('refresh',opt
                );
        }

        function refreshPcbDataDetailsPCBTable(pcbResult){
            var opt = {
                url:"${basePath}/aLine/lineDetailsLeftChart?lineNo="+lineNo+
                    "&inspectStarttime="+inspectStarttime+
                    "&inspectEndtime="+inspectEndtime+
                    "&pcbResult="+pcbResult+
                    "&pcbType="+pcbType+
                    "&aoiType="+aoiType,
                silent: true,
                //contentType: "application/x-www-form-urlencoded",//请求方式（*）
                query:{
                    type:1,
                    level:2
                }
            };
            $("#table_pcbList").bootstrapTable('refresh',opt
            );
        }

        function InitPadTable (ApcbIdLine,defectTypeCode) {
            //记录页面bootstrap-table全局变量$table，方便应用
            //alert(barcode);
            var $table = $('#table_padList').bootstrapTable({
                url: "${basePath}/aComponent/padList?pcbIdLine="+ApcbIdLine+
                    "&defectTypeCode="+defectTypeCode                      //请求后台的URL（*）
                    +"&aoiType="+aoiType,
                dataType:"json",
                method: 'GET',
                //contentType: "application/x-www-form-urlencoded",//请求方式（*）
                //data:{pcbIdLine:ApcbIdLine},
                toolbar: '#padToolbar',              //工具按钮用哪个容器
                toolbarAlign:'right',
                striped: true,                      //是否显示行间隔色
                cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                sortName:'result',
                sortOrder: 'desc',               //排序方式

                sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
                pageSize: 12,                     //每页的记录行数（*）
                pageList: [15, 20, 50, 100,'ALL'],        //可供选择的每页的行数（*）
                search: true,                      //是否显示表格搜索
                strictSearch: false,
                showColumns: true,                  //是否显示所有的列（选择显示的列）
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                settimeout:1,
                height: 380,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                //uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                exportDataType:'all',
                showExport: true,  //是否显示导出按钮
                buttonsAlign:"right",  //按钮位置
                //exportTypes: [ 'csv', 'txt', 'xml', 'excel'],
                Icons:'glyphicon-export icon-share',
                exportOptions:{
                    //ignoreColumn: [0],  //忽略某一列的索引
                    fileName: ($('#aoiType').val()==1?'pre-aoi_':'post-aoi_')+lineNo+"_"+$("#inspectStarttime").val()+"-"+$("#inspectEndtime").val(),  //文件名称设置
                    worksheetName: 'sheet1',  //表格工作区名称
                    tableName: '缺陷',
                    excelstyles: ['background-color', 'color', 'font-size', 'font-weight'],
                    onMsoNumberFormat: DoOnMsoNumberFormat
                },
                showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
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
                        //pcbIdLine:ApcbIdLine
                    };
                },
                columns: [
                   /* {
                    checkbox: true,
                    align:'center',
                    visible: true
                    //是否显示复选框
                }, */
                    {
                    field: 'barcode',
                    title: 'barcode',
                    // width:50,
                    align:'center',
                    sortable: true,
                    formatter:function () {
                        return barcode;
                    },
                    cellStyle: function (value, row, index){
                        return {css:{'font-size':'9px','padding':'0px'}}
                    }
                },
                    {
                        field: 'comImageBase64',
                        title: 'picture',
                        // width:50,
                        align:'center',
                        sortable: true,
                        events: operateEventslineDetailsLeftChart,
                        formatter:function(value,row,index){
                          return '<image id="TableNGImage" width="30px" height="30px" style="cursor: pointer" src='+'data:image/jpeg;base64,'+ row.comImageBase64+'  >';
                        },
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                    },
                    {
                    field: 'padBarcode',
                    title: 'padBarcode',
                    // width:50,
                    align:'center',
                    sortable: true,
                    formatter:function (value,row,index) {
                        if(padArrBarcode!=null&&padArrBarcode.length>0)
                        {
                            for (var i = 0; i < padArrBarcode.length; i++) {
                                if(row.arrayIndex == (i+1)+''){
                                    return padArrBarcode[i].arrayBarcode;
                                }
                            }
                        }
                       return barcode;
                    },
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                },
                    {
                    field: 'id',
                    title: 'id',
                    // width:50,
                    align:'center',
                    sortable: true,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                },
                    /*{
                        field: 'comImageBase64',
                        title:  'image',
                        align:'center',
                        sortable: true,
                        //  width:100,
                        events: operateEventslineDetailsLeftChart,
                        formatter: addFunctionAltylineDetailsLeftChart,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                },*/
                    {
                        field: 'position',
                        title:  'position',
                        align:'center',
                        sortable: true,
                        formatter:function (value,row,index) {
                            return row.arrayId+ '_'+row.componentId;
                        },
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                    },{
                        field: 'Result',
                        title:  'result',
                        align:'center',
                        sortable: true,
                        formatter:function (value,row,index) {
                            switch (row.result) {
                                case '0':
                                case 0:
                                    return 'PASS';
                                case '1':
                                case 1:
                                    return 'NG';
                                case '2':
                                case 2:
                                    return 'REPASS';
                                default :
                                    return  'SKIP';
                            }
                           //return ;
                        },
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                    },{
                        field: 'defectType',
                        title:  'errorCode',
                        align:'center',
                        sortable: true,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                        //    width:100
                    },{
                    field: 'height',
                    title: 'height',
                    align:'center',
                    //  width:100,
                    sortable: true,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                },{
                    field: 'area',
                    title: 'area',
                    align:'center',
                    sortable:true,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                    //  width:100
                    //formatter: linkFormatter
                }, {
                    field: 'volume',
                    title:  'volume',
                    align:'center',
                    sortable: true,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                    //  width:50
                    //events:operateEvents,
                }, {
                    field: 'offsetx',
                    title:  'offX',
                    align:'center',
                    sortable: true,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                    //  width:50
                    //events:operateEvents,
                },{
                    field: 'offsety',
                    title:  'offY',
                    align:'center',
                    sortable: true,
                        cellStyle: function (value, row, index){
                            return {css:{'font-size':'9px','padding':'0px'}}
                        }
                    //  width:50
                    //events:operateEvents,
                },

                ],
                onLoadSuccess: function (sta) {
                    //console.log("in onLoadSuccess");
                    //console.log(sta);
                    //alert(sta.rows.pcbIdLine);

                },
                onLoadError: function (status,res) {
                    //showTips("数据加载失败！");
                    //alert("onLoadError");
                    console.log(res);
                    console.log(status);
                },
                onClickRow: function (row, $element) {
                    $('.danger').removeClass('danger');
                    $($element).addClass('danger');
                },
            });
        };

        function  addFunctionAltylineDetailsLeftChart(value, row, index) {
            return ['<span id="TableNGImage"  style="cursor:pointer"  class="glyphicon glyphicon-picture"></span>'].join("");
        }


        $("#padToolbar").click(function () {
            var toolbarBarcode = (barcode == ''|| barcode==null)?'NOREAD':barcode;
            var toolbarInspectStarttime = inspectStarttime.myReplace('-','').myReplace(':','').myReplace(' ','');
            //toolbarInspectStarttime = toolbarInspectStarttime.replace("-",'');
            //toolbarInspectStarttime = toolbarInspectStarttime.replace("_",'');
            var toolbarfirstpcbIdLine = firstpcbIdLine.replace('=====','_');
            excel = new ExcelGen({
                "src_id": "table_padList",
                "show_header": true,
                //"src": "D:\\360MoveData\\Users\\sinictek\\Desktop\\web-dataexport",
                "format": "csv",
                "type": "table",
                // "auto_format": false,
                //"header_row": null,
                //"body_rows": null,
                "exclude_selector": null,
                "file_name":toolbarfirstpcbIdLine+"_"+toolbarBarcode+"_"+toolbarInspectStarttime+ "_pad.csv",//"output.xlsx",
                //"column_formats": []
            });
            excel.generate();
        });
        $("#pcbToolbar").click(function () {
            excel = new ExcelGen({
                "src_id": "table_pcbList",
                "show_header": true,
                "format": "csv",
                "type": "table",
                "exclude_selector": null,
                "file_name":lineNo+"_"+$("#inspectStarttime").val()+"-"+$("#inspectEndtime").val()+ "_pcb.csv",//"output.xlsx",
            });
            excel.generate();
        });
       /* $('#pcbToolbar').click(function() {
            var chart = $('#container_pcb').highcharts().getSVG();
            $('<img style="width:500px;height:500px;object-fit: cover;" src="data:image/svg+xml;base64,'+window.btoa(unescape(encodeURIComponent(chart)))+'" >').appendTo("#svgImage");

        });*/
        /*document.body.onclick = function(){
             this.webkitRequestFullscreen();
         }*/
    </script>
</body>
</html>

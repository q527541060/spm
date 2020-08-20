<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>dataDetails</title>
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
        <div class="row" style="margin-top: -6px; text-align: left;">
            <div class="col-md-14">
                <ol class="breadcrumb">
                    <li><a href="${basePath}/Home/pcbHome">Home</a></li>
                   <%-- <li><a href="#">spi</a></li>--%>
                    <li><a href="${basePath}/sLine/pcbLine">line</a></li>
                    <li class="active">lineDetail</li>
                </ol>
            </div>
        </div>
        <!-- s饼图详情-->
        <div class="row" style="margin-top: -40px; ">
            <div class="col-md-12">
                <h2> ${lineNo}线质量统计分析 </h2>
                <input id="lineNo" type="hidden" value="${lineNo}">
                <input id="inspectStarttime" type="hidden" value="${inspectStarttime}">
                <input id="inspectEndtime" type="hidden" value="${inspectEndtime}">
            </div>
        </div>
        <!--  饼图缺陷  -->
        <div class="row">
            <div class="col-md-6">
                <div id="container_pcb" style="min-width:40%;height:350px"></div>
            </div>
            <div class="col-md-6">
                <div id="container_pad" style="min-width:40%;height:350px"></div>

            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <table id="table_pcbList"></table>
            </div>
            <div class="col-md-6">
                <table id="table_padList"></table>
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
        //alert(inspectStarttime);
        //alert(inspectEndtime);
        <!--  缺陷类型图  -->
        function padCpountpie(pcbId){
            var padCountpieJson={};
            $.ajax({
                url:"${basePath}/sPad/padDefaultTypeInfo",
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
                                        case  'Insufficient':
                                            padDefultType = '1';
                                            break;
                                        case  'Excess':
                                            padDefultType = '2';
                                            break;
                                        case  'OverHeight':
                                            padDefultType = '3';
                                            break;
                                        case  'LowHeight':
                                            padDefultType = '4';
                                            break;
                                        case  'OverArea':
                                            padDefultType = '5';
                                            break;
                                        case  'LowArea':
                                            padDefultType = '6';
                                            break;
                                        case  'ShiftX':
                                            padDefultType = '7';
                                            break;
                                        case  'ShiftY':
                                            padDefultType = '8';
                                            break;
                                        case  'Bridge':
                                            padDefultType = '9';
                                            break;
                                        case  'ShapeError':
                                            padDefultType = '10';
                                            break;
                                        case  'Smeared':
                                            padDefultType = '11';
                                            break;
                                        case  'Coplanarity':
                                            padDefultType = '12';
                                            break;
                                        case  'PreBridge':
                                            padDefultType = '13';
                                            break;
                                        case  'PadAreaError':
                                            padDefultType = '14';
                                            break;
                                        case  'WarpError':
                                            padDefultType = '15';
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

                    $('#container_pad').highcharts(padCountpieJson);
                }
            });

        }
        <!--  缺陷pcb图  -->
        pcbCountpie('0,1,2,4');

        function clickPiePcb(PcbResult){
            refreshPcbDataDetailsPCBTable(PcbResult);
        }

        function pcbCountpie(pcbResult){
            var pcbCountpieJson={};
            $.ajax({
                url:"${basePath}/sLine/lineDetailsLeftChart",
                dataType:"json",
                async:true,
                data:{lineNo:lineNo,inspectStarttime:inspectStarttime,inspectEndtime:inspectEndtime,pcbResult:pcbResult},
                type:"GET",
                success:function (req) {
                    //success
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
                                            pcbresult ='0,1,2,4';
                                            break;

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
                    $('#container_pcb').highcharts(pcbCountpieJson);

                },
                error:function(){
                }

            });
        }
        <!-- tablelist -->
        var  $Pcbtable;
        window.operateEventslineDetailsLeftChart={
            "click #TableNGImage" :function(e,value, row, index){
                // window.location.href="${basePath}/sLine/pcbLineDetails?lineNo="+row.lineNo;
                //alert(row.pad2dImage);
                //byte[] out =
                var pcbidLine = row.pcbidLine;
                pcbidLine.replace("#","=====");
                var padid = row.padId;
                $.ajax({
                    url:"${basePath}/sPad/padImage",
                    dataType:"json",
                    async:true,
                    data:{pcbidLine:pcbidLine,padId:padid},
                    type:"POST",
                    success:function (req) {
                        //alert(req.data);
                        showPadImageModal(req.data);
                    },
                    error:function (data) {
                        //alert('error:'+data);
                    }
                });



            }
        }
        function showPadImageModal(image){
            $('#pad2dimage').empty();
            $("<img  style='width: 450px;height:400px; object-fit: cover;' src='data:image/jpeg;base64,"+ image+"  '>").appendTo("#pad2dimage");
           // html.modal('show');
            $('#myModal').modal('show');
        }
        InitPcbTable('0,1,2,4');
        function InitPcbTable (pcbResult) {
            //alert(lineNo);
            //记录页面bootstrap-table全局变量$table，方便应用
            $Pcbtable = $('#table_pcbList').bootstrapTable({
                url:"${basePath}/sLine/lineDetailsLeftChart?lineNo="+lineNo+
                    "&inspectStarttime="+inspectStarttime+
                    "&inspectEndtime="+inspectEndtime+
                    "&pcbResult="+pcbResult,                      //请求后台的URL（*）
                dataType:"json",
                method: 'GET',                      //请求方式（*）
                //data:{lineNo:lineNo,inspectStarttime:inspectStarttime,inspectEndtime:inspectEndtime,pcbResult:pcbResult},
                //toolbar: '#toolbar',              //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                sortName:'inspectStarttime',
                sortOrder: "desc",                   //排序方式
                sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
                pageSize: 10,                     //每页的记录行数（*）
                pageList: [15, 20, 50, 100,'ALL'],        //可供选择的每页的行数（*）
                search: true,                      //是否显示表格搜索
                strictSearch: true,
                showColumns: true,                  //是否显示所有的列（选择显示的列）
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                  //是否显示父子表
                classes:'table table-striped table-hover',
                showLoading:true,
                showExport:true,
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
                        sortOrder: params.order //排位命令（desc，asc）
                    };
                },
                columns: [{
                    checkbox: true,
                    align:'center',
                    visible: true
                    //是否显示复选框
                }, {
                    field: 'jobName',
                    title: 'jobName',
                   // width:50,
                    align:'center',
                    sortable: true
                }, {
                    field: 'lineNo',
                    title: 'lineNo',
                    align:'center',
                  //  width:100,pcbIdLine
                    sortable: true
                },
                    /*{
                    field: 'pcbIdLine',
                    title: 'pcbIdLine',
                    align:'center',
                },*/
                    {
                    field: 'boardBarcode',
                    title:  'boardBarcode',
                    align:'center',
                    sortable: true,
                  //  width:50
                    //events:operateEvents,
                },  {
                    field: 'inspectResult',
                    title:  'inspectResult',
                    align:'center',
                    sortable: true,
                    formatter:function (value,row,index) {
                        switch (row.inspectResult) {
                            case '0':
                                return 'PASS';
                            case '1':
                                return 'NG';
                            case '2':
                                return 'REPASS';
                            default :
                                return  'SKIP';

                        }
                    }
                }, {
                        field: 'cpk',
                        title:  'cpk',
                        align:'center',
                        sortable: true,
                        formatter:function (value,row,index) {

                           return '<span>'+ 'area:'+ row.aCpk.toFixed(3) +
                                ';hight:'+ row.hCpk.toFixed(3)+
                                ';vol:'+ row.vcpk.toFixed(3)+
                                ';shithxCpk:'+row.shithxCpk.toFixed(3)+
                                ';shithyCpk:'+row.shithyCpk.toFixed(3) +'</span>';
                        }
                    },{
                    field: 'ngpadCount',
                    title:  '不良点数',
                    align:'center',
                    sortable: true,
                    width:10
                },{
                    field: 'arrayBarcode',
                    title:  'arrayBarcode',
                    align:'center',
                    sortable: true,
                    //  width:50
                },{
                    field: 'arrayinspectResult',
                    title:  'arrayinspectResult',
                    align:'center',
                    sortable: true,
                  //  width:50
                },{
                    field: 'laneNo',
                    title:  'laneNo',
                    align:'center',
                    sortable: true,
                    width:2
                },{
                    field: 'inspectStarttime',
                    title:  'inspectStarttime',
                    align:'center',
                    sortable: true,
                     width:10,
                    //events: operateEventslineDetailsLeftChart,//给按钮注册事件
                    //formatter: addFunctionAltylineDetailsLeftChart//表格中增加按钮
                },
                {
                    field: 'inspectEndtime',
                    title:  'inspectEndtime',
                    align:'center',
                    sortable: true,
                 //   width:50
                }],
                formatLoadingMessage:function(){
                  return "请稍等,正在加载中.........";
                },
                onLoadSuccess: function (data,$element) {
                    barcode = data.rows[0].boardBarcode;
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
                    var line = row.lineNo; //线体
                    var pcbidLine = row.pcbIdLine;  //pcbIdLine
                    barcode = row.boardBarcode;

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
                url: "${basePath}/sPad/padList?pcbIdLine="+ApcbIdLine+"&defectTypeCode="+padDefultType,
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
                url:"${basePath}/sLine/lineDetailsLeftChart?lineNo="+lineNo+
                    "&inspectStarttime="+inspectStarttime+
                    "&inspectEndtime="+inspectEndtime+
                    "&pcbResult="+pcbResult,
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
                url: "${basePath}/sPad/padList?pcbIdLine="+ApcbIdLine+
                    "&defectTypeCode="+defectTypeCode,                      //请求后台的URL（*）
                dataType:"json",
                method: 'GET',
                //contentType: "application/x-www-form-urlencoded",//请求方式（*）
                //data:{pcbIdLine:ApcbIdLine},
                //toolbar: '#toolbar',              //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                sortName:'padInspectResult',
                sortOrder: 'desc',               //排序方式

                sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
                pageSize: 12,                     //每页的记录行数（*）
                pageList: [15, 20, 50, 100,'ALL'],        //可供选择的每页的行数（*）
                search: true,                      //是否显示表格搜索
                strictSearch: true,
                showColumns: true,                  //是否显示所有的列（选择显示的列）
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                //uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
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
                        //pcbIdLine:ApcbIdLine
                    };
                },
                columns: [{
                    checkbox: true,
                    align:'center',
                    visible: true
                    //是否显示复选框
                }, {
                    field: 'barcode',
                    title: 'barcode',
                    // width:50,
                    align:'center',
                    sortable: true,
                    formatter:function () {
                        return barcode;
                    }
                },
                    {
                    field: 'padId',
                    title: 'padId',
                    // width:50,
                    align:'center',
                    sortable: true
                },{
                        field: 'padImage',
                        title:  'image',
                        align:'center',
                        sortable: true,
                        //  width:100,
                        events: operateEventslineDetailsLeftChart,
                        formatter: addFunctionAltylineDetailsLeftChart
                },{
                        field: 'position',
                        title:  'position',
                        align:'center',
                        sortable: true,
                        formatter:function (value,row,index) {
                            return row.arrayId+ '_'+row.componentId;
                        }
                    },{
                        field: 'padInspectResult',
                        title:  'result',
                        align:'center',
                        sortable: true,
                        formatter:function (value,row,index) {
                            switch (row.padInspectResult) {
                                case '0':
                                    return 'PASS';
                                case '1':
                                    return 'NG';
                                case '2':
                                    return 'REPASS';
                                default :
                                    return  'SKIP';
                            }
                           //return ;
                        }
                    },{
                        field: 'defectTypeName',
                        title:  'errorCode',
                        align:'center',
                        sortable: true,
                        //    width:100
                    },{
                    field: 'height',
                    title: 'height',
                    align:'center',
                    //  width:100,
                    sortable: true
                },{
                    field: 'area',
                    title: 'area',
                    align:'center',
                    sortable:true,
                    //  width:100
                    //formatter: linkFormatter
                }, {
                    field: 'volume',
                    title:  'volume',
                    align:'center',
                    sortable: true,
                    //  width:50
                    //events:operateEvents,
                }, {
                    field: 'offsetx',
                    title:  'offX',
                    align:'center',
                    sortable: true,
                    //  width:50
                    //events:operateEvents,
                },{
                    field: 'offsety',
                    title:  'offY',
                    align:'center',
                    sortable: true,
                    //  width:50
                    //events:operateEvents,
                }, ],
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
                onDblClickRow: function (row, $element) {
                },
            });
        };

        function  addFunctionAltylineDetailsLeftChart(value, row, index) {
            return ['<span id="TableNGImage"  style="cursor:pointer"  class="glyphicon glyphicon-picture"></span>'].join("");
        }
    </script>
</body>
</html>

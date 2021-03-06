<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" >
    <%--<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">--%>
    <title>pcbLineData</title>
    <link rel="stylesheet icon" href="${staticPath}/static/img/logo.jpg" type="image/x-icon" media="screen" />
    <style>
        body{
            margin: 0px;
            padding: 0px;
        }
        .row{
           /* text-align: center;
            margin: 0px;*/
        }
        .row-border{
            /*background-color: #F5F5F5;color: #F5F5F5;height: 25px*/
            margin-top: 15px;
        }

    </style>

</head>
<body>
<nav>
    <%@include file="../header.jsp"  %>
    <div class="row" style="text-align: left;">
        <div class="col-md-14" style="padding: 0px;">
            <ol class="breadcrumb" style="float: left;margin: 0px;">
                <li><a href="${basePath}/Home/pcbHome"><spring:message code="line.Home"></spring:message></a></li>
                <%--<li><a href="#">spi</a></li>--%>
                <li class="active"><spring:message code="line.spi"></spring:message></li>
                <li class="active"><spring:message code="barcode.BarcodeList"></spring:message></li>
            </ol>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4" ></div>
        <div class="col-md-4" >
            <div class="input-group">
                <input type="text" onkeydown="entersearch()" class="form-control input_barcode" placeholder="<spring:message code="barcode.placeholder"></spring:message>" style="height: 28px">
                <span class="input-group-btn" ><button type="button" class="btn btn-info"  onclick="refreshMainTable()">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                </button></span>
            </div>

        </div><!-- /.col-lg-6 -->
    </div><!-- /.row -->
    <div class="row">
        <div class="col-md-12" >
            <!-- 状态树状列表 -->
            <div id="table-list" ></div>
        </div>
    </div>
    <div class="row">
        <div class="modal fade  bs-example-modal-lg" id="barocdeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="exampleModalLabel">BARCODE MESSAGE</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-8">
                                <div id="pcbImage">
                                    <canvas id="fovImage-canvas" width="600" height="400" style="height: 400px;width: 100%">
                                    </canvas>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <p class="text-left"><strong>JobName:</strong><span class="span_job">JHUB-20125-UH</span></p>
                                <p class="text-left"><strong>Line:</strong><span class="span_job">SMT-S-1</span></p>
                                <p class="text-left"><strong>Barcode:</strong><span class="span_job">ASGAD234</span></p>
                                <p class="text-left"><strong>BoardResult:</strong><span class="span_job">NG</span></p>
                                <p class="text-left"><strong>NG-Position-Count:</strong><span class="span_job">1</span></p>
                                <p class="text-left"><strong>StartTime:</strong><span class="span_job">2021-01-29 12:00:01</span></p>
                                <p class="text-left"><strong>EndTime:</strong><span class="span_job">2021-01-29 12:00:44</span></p>
                                <p class="text-left"><strong>ArrayInfo:</strong><span class="span_job">1,ASGAD233,GOOD;2,ASGAD234,NG</span></p>
                                <hr style="border-top:1px dashed #987cb9;" width="100%" color="#987cb9" size=1>
                                <p class="text-left"><strong>PositionName:</strong><span class="span_job">F3_21</span></p>
                                <p class="text-left"><strong>PositionResult:</strong><span class="span_job">NG</span></p>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">

                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>
<script type="text/javascript">
    window.operateEventsSpiBarcode={
        "click #TableSpiBarcode" :function(e,value, row, index){
            //显示模拟框
            //picture
            showBarcodeImage('${basePath}');
            //content


            $('#barocdeModal').modal('show');
        }
    };
    window.operateEventsSpiPicture={
        "click #TableSpiPictureImage" :function(e,value, row, index){
            //显示模拟框
            //picture
            showBarcodeImage('${basePath}');
            //content
            $('#barocdeModal').modal('show');
        }
    };
    addFunctionAltySpiBarcode=function (value, row, index){
        return ['<button type="button" id="TableSpiBarcode"   class="btn btn-primary btn-xs">图表详情</button>'].join("");
    }
    addFunctionAltySpiPicture=function(value,row,index){
        //var pcbPath= '//127.0.0.1/AOI_DB/20201028205455/FOV/3_91.jpg';
        return ['<image id="TableSpiPictureImage" width="60px" height="40px" style="cursor: pointer" src='+'data:image/jpeg;base64,'+ row.remark+'  >'].join("");

    }
    function showBarcodeImage(basePath){
        var pcbPath= '//127.0.0.1/AOI_DB/20201028205455/FOV/3_91.jpg';
        $.ajax({
            url:basePath+"/aLine/getAoiImageByte",
            dataType:"json",
            async:true,
            data:{path:pcbPath},
            type:"GET",
            beforeSend:function(){
            },
            success:function(req){
                //$("<img class='svgImage' style='width: 100%;height:400px; object-fit: cover;' src='data:image/jpeg;base64,"+ req.data+"  '>").addClass("img-thumbnail").appendTo("#fovImage");
                //var pcbImgHtml = $('<img/>').addClass("img-thumbnail").attr("height","300px").attr("width","400px").attr("src",)
                var iSrc = 'data:image/jpeg;base64,'+ req.data;
                canvasFOVPart(iSrc);
                return req.data;
            }
        });
    }
    //读取input search
    let lBarcode = $('.input_barcode').val();

    function clickBarcode(){
        lBarcode = $('.input_barcode').val();
    }
    InitMainTable ();
    <!--   设备状态js代码  -->
    function InitMainTable () {
        $('#table-list').bootstrapTable({
            url: '${basePath}/Pcb/barcodeList'+
                "?barcode="+lBarcode,
            dataType:"json",
            method: 'GET',                      //请求方式（*）
            //toolbar: '#toolbar',              //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortName: 'updateTime',
            sortOrder: "desc",
            sortable: true,                     //是否启用排序
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
            pageSize: 25,                     //每页的记录行数（*）
            pageList: [25, 50, 100,'ALL'],        //可供选择的每页的行数（*）
            search: false,                      //是否显示表格搜索
            minimumCountColumns: 2,             //最少允许的列数
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列（选择显示的列）
            showRefresh: true,                  //是否显示刷新按钮
            clickToSelect: true,                //是否启用点击选中行
            height: 600,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            classes:'table  table-striped table-sm table-dark',
            showExport: true,  //是否显示导出按钮
            buttonsAlign:"right",  //按钮位置
            Icons:'glyphicon-export icon-share',
            exportOptions:{
                //ignoreColumn: [0],  //忽略某一列的索引
                fileName: "spi_"+$("#startTime").val()+ "_"+$("#endTime").val()+ "status__line",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: '缺陷',
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'],
                //onMsoNumberFormat: DoOnMsoNumberFormat
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
                     width:200,
                    align:'center',
                    sortable: true,
                    cellStyle: function (value, row, index){
                        return {css:{'font-size':'9px','padding':'0px'}}
                    }
                }, {
                    field: 'lineNo',
                    title: 'lineNo',
                    align:'center',
                    width:50,
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
                    },
                      width:200
                    //events:operateEvents,
                },  {
                    field: 'inspectResult',
                    title:  'pcbResult',
                    align:'center',
                    width:50,
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
                    },
                    cellStyle: function (value, row, index){
                        return {css:{'font-size':'9px','padding':'0px'}}
                    }
                }, {
                    field: 'arrayBarcode',
                    title:  'arrayInfo',
                    align:'center',
                    sortable: true,
                    formatter:function (value,row,index) {

                        var arrBarcode = row.arrayBarcode.split(';');
                        var arrResult = row.arrayinspectResult.split(';');
                        var resultHtml='<span>';
                        var tmp='';
                        if(arrBarcode.length>1){
                            for (var i=0;i<arrBarcode.length;i++){
                                if(arrResult.length>=arrBarcode.length){
                                    tmp =arrpcbResultToStandResult(arrResult[i]);
                                }else{
                                    tmp = 'PASS';
                                }
                                resultHtml +=(i+1) +';'+ arrBarcode[i]+';'+tmp+'<br>';
                            }
                        }else{
                            resultHtml= arrBarcode + ';' +arrResult;
                        }
                        resultHtml +='</span>';
                        return  resultHtml;

                    },
                    cellStyle: function (value, row, index){
                        return {css:{'font-size':'9px','padding':'0px'}}
                    },
                      width:200
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
                    },width:200
                },{
                    field: 'ngpadCount',
                    title:  'ngPadCount',
                    align:'center',
                    sortable: true,
                    width:50,
                    cellStyle: function (value, row, index){
                        return {css:{'font-size':'9px','padding':'0px'}}
                    }
                },
                {
                    field: 'laneNo',
                    title:  'laneNo',
                    align:'center',
                    sortable: true,
                    width:50,
                    cellStyle: function (value, row, index){
                        return {css:{'font-size':'9px','padding':'0px'}}
                    }
                },{
                    field: 'inspectStarttime',
                    title:  'inspectStarttime',
                    align:'center',
                    sortable: true,
                    width:200,
                    cellStyle: function (value, row, index){
                        return {css:{'font-size':'9px','padding':'0px'}}
                    }
                },
                {
                    field: 'inspectEndtime',
                    title:  'inspectEndtime',
                    align:'center',
                    sortable: true,
                    cellStyle: function (value, row, index){
                        return {css:{'font-size':'9px','padding':'0px'}}
                    },
                    width:150
                },
              /*  {
                    field:'detail',
                    title:"分析图列",
                    align:'center',
                    width:50,
                    events: operateEventsSpiBarcode,//给按钮注册事件
                    formatter: addFunctionAltySpiBarcode,//表格中增加按钮

                },*/
                {
                    field:'picture',
                    title:'picture',
                    align:'center',
                    width:150,
                    events:operateEventsSpiPicture,
                    formatter:addFunctionAltySpiPicture,
                    cellStyle: function (value, row, index){
                        return {css:{'font-size':'9px','padding':'3px'}}
                    },
                }
            ],
            formatLoadingMessage:function(){
                return "请稍等,正在加载中.........";
            },
            onLoadSuccess: function (data,$element) {

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
            },
            onClickRow: function (row, $element) {
                $('.danger').removeClass('danger');
                $($element).addClass('danger');
            },
        });
    };
    function refreshMainTable(){
        clickBarcode();
        var opt = {
            url: '${basePath}/Pcb/barcodeList'+
                "?barcode="+lBarcode,
            silent: true,
            query:{
                type:1,
                level:2
            }
        };
        $("#table-list").bootstrapTable('refresh', opt);
    }
    function entersearch(){
        var event = window.event || arguments.callee.caller.arguments[0];
        if (event.keyCode == 13)
        {
            refreshMainTable();
        }
    }
</script>

</body>
</html>
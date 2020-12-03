<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>three-point as close</title>
    <style type="text/css">

        body{
            margin: 0px;
            padding: 0px;
            text-align: center;
            /*background-color: #FFFFFF;*/
            /*  background: url("$staticPath}/static/img/home6.png")  ;*/
            /*  background-size:cover;*/
        }
        .col-md-4{
            padding: 0px;
        }

    </style>

</head>
<body>

<nav id="nav_div">
    <%@include file="../header.jsp" %>
    <div class="row" style="text-align: left;">
        <div class="col-md-14">
            <ol class="breadcrumb" style="float: left;margin: 0px;">
                <li><a href="${basePath}/Home/pcbHome">Home</a></li>
                <%--<li><a href="#">spi</a></li>--%>
                <li class="active">threePointClose</li>
            </ol>
            <div style="float: left;margin:4px 20%;padding: 0px" id="sandBox-container">
                <!-- glyphicon glyphicon-time   col-lg-offset-4-->
                <%--<span class="glyphicon glyphicon-time" aria-hidden="true"></span>--%>
                <span  style="margin-left: -19px;" class="glyphicon glyphicon-calendar"></span>
                <input size="16" type="text"  readonly  id="startTime" /> -
                <input  size="16"  type="text"   readonly  id="endTime"/>
                <!-- line-list -->
                <div class="btn-group" >
                    <input type="button" class="btn btn-info btn-xs" id="btn-line" value="SMT18"></input>
                    <button type="button" class="btn btn-info btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu list-group" id="line-ul" style="height: 0px;padding: 0px">
                        <%--<button type="button" class="btn btn-default btn-xs list-group-item " onclick="lineListGroup(this.value)" value="SMT1">SMT1</button>
                        <button type="button" class="btn btn-default btn-xs list-group-item" onclick="lineListGroup(this.value)" value="SMT2">SMT2</button>
                        <button type="button" class="btn btn-default btn-xs list-group-item" onclick="lineListGroup(this.value)" value="SMT3">SMT3</button>
                        <button type="button" class="btn btn-default btn-xs list-group-item" onclick="lineListGroup(this.value)" value="SMT4">SMT4</button>
                        <button type="button" class="btn btn-default btn-xs list-group-item" onclick="lineListGroup(this.value)" value="SMT5">SMT5</button>
                        --%>
                    </ul>
                </div>
                <!-- result -->
                <!-- Split button -->
                <div class="btn-group" >
                    <input type="button" class="btn btn-info btn-xs" id="result-tbn" value="ALL"></input>
                    <button type="button" class="btn btn-info btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu list-group" id="result-ul" style="height: 0px;padding: 0px">
                        <button type="button" class="list-group-item btn btn-default btn-xs "  onclick="resultListGroup(this.value)"  value="PASS">PASS</button>
                        <button type="button" class="list-group-item btn btn-default btn-xs "  onclick="resultListGroup(this.value)" value="REPASS">REPASS</button>
                        <button type="button" class="list-group-item btn btn-default btn-xs "  onclick="resultListGroup(this.value)" value="NG">NG</button>
                        <button type="button" class="list-group-item btn btn-default btn-xs "  onclick="resultListGroup(this.value)" value="ALL">ALL</button>
                    </ul>
                </div>
                <!-- glyphicon glyphicon-search-->
                <button type="button" class="btn  btn-info btn-xs"  onclick="showPcbList()">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 搜索
                </button>
            </div>

        </div>
    </div>
    <div class="row" >
        <!--  元器件图片及图片表格区域  -->
        <div class="col-md-11">
            <!-- 元器件图片 -->
            <div class="row">
                <!-- SPI -->
                <div class="col-md-4">
                    <div class="progress" style="margin: 0px">
                        <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                            <small id="span-spi-content-component" class="font-small">SPI</small>
                        </div>
                    </div>
                    <div id="spi-2D-Component" ></div>
                    <div id="spi-3D-Component" style="width: 100%;height:35vh;"></div>
                    <div id="spi-Component-table" ></div>
                </div>
                <!-- PRE-AOI -->
                <div class="col-md-4">
                    <div class="progress" style="margin: 0px">
                        <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                            <small  id="span-PRE-content-component" class="font-small">AOI-PRE</small>
                        </div>
                    </div>
                    <div id="preAoi-2D-Component" ></div>
                    <div id="preAoi-3D-Component" style="width: 100%;height:35vh;"></div>
                    <div id="proAoi-Component-table" ></div>
                </div>
                <!-- POST-AOI -->
                <div class="col-md-4">
                    <div class="progress" style="margin: 0px">
                        <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                            <small  id="span-POST-content-component" class="font-small">AOI-POST</small>
                        </div>
                    </div>
                    <div id="postAoi-2D-Component" ></div>
                    <div id="postAoi-3D-Component" style="width: 100%;height:35vh;"></div>
                    <div id="postAoi-Component-table"></div>
                </div>
            </div>
        </div>
        <!-- 右侧边线体-PCB导航栏  -->
        <div class="col-md-1" style="padding: 0px">
            <div class="row">
                <div class="col-md-14" style="margin-top: -6vh">
                    <div class="row">
                        <div id="pcbListTable"></div>
                    </div>
                    <div class="row">
                        <div id="componentListTable"></div>
                    </div>
                </div>
            </div>


        </div>
    </div>

</nav>

<script type="text/javascript">
    window.operateEventsThreePointAsClose = {
        "click #barcodeThreePointAsClose" :function(e,value, row, index) {
            //alert('Come here');
            var pcbID = '';//row.pcbIdLine.split('#')[0];
            pcbListBarcode = row.barcode;
            pcbListPcbID = pcbID;
            firstBarcode = row.barcode;
            firstSpiPcbID = row.spiPCBID;
            firstpreAoiPcbID = row.preAoiPCBID;
            firstpostAoiPcbID = row.postAoiPCBID;
            refreshComponentTable(StatusQueryUrl,dStart,dEnd,lineNo,pcbListBarcode,pcbID);
        }
    };
    var vFirstLineNo = '';
    function showPcbList(){
        dStart=$('#startTime').val();
        dEnd=$('#endTime').val();
        lineNo = $('#btn-line').val();
        refreshPcbTable( StatusQueryUrl,dStart,dEnd,lineNo);
    }
    //线体选择
    $.ajax({
        url:"${basePath}/threePointClose/getAllLine",
        dataType:"json",
        async:true,
        //data:{inspectStarttime:startTime,inspectEndtime:endTime,mode:iGroupMode,pcbType:ichoicePcb},
        scriptCharset: 'utf-8',
        type:"GET",
        beforeSend:function(){
        },
        success:function(req){
            $('.line-ul').empty();
            var vButton ='';
            vFirstLineNo = req.rows.length>0?req.rows[0].lineNo:'';
            $.each(req.rows,function (i,va) {
                //alert(va.lineNo);
                vButton =
                $('<button></button>').addClass('btn btn-default btn-xs list-group-item').attr('onclick','lineListGroup("'+va.lineNo+'")')
                    .attr("value",va.lineNo)
                 .attr("type","button").append(va.lineNo).appendTo('#line-ul');
            });
            lineListGroup(vFirstLineNo);
        }
    });
    //时间选择器
    var nowDate = new Date();
    var dStart = dateFomate( nowDate.setDate(nowDate.getDate()+0),'yyyy-MM-dd HH:mm:ss' );
    var dEnd = dateFomate( nowDate.setDate(nowDate.getDate()+1),'yyyy-MM-dd HH:mm:ss' );
    $("#startTime").datetimepicker({
        //minView:"month",
        format: 'yyyy-mm-dd hh:ii:ss',
        language:"zh-CN",
        todayHighlight:true,
        showMeridian: true,
        autoclose: true,
        todayBtn: true,
        //pickerPosition: "bottom-left"
        //minuteStep: 1
    });
    $("#endTime").datetimepicker({
        //minView:"month",
        format: 'yyyy-mm-dd hh:ii:ss',
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

    InitPreAoiComponentDetailTable(StatusQueryUrl,null);
    var lineNo = $('#btn-line').val();
    var StatusQueryUrl = '${basePath}';
    var StatusStaticPath = '${staticPath}';
    var firstBarcode = '';

    var firstSpiPcbID = '0';
    var firstpreAoiPcbID = '0';
    var firstpostAoiPcbID = '0';

    var pcbListBarcode = '';
    var pcbListPcbID= '';
    var firstPcbID = '';
    InitPcbListTable(StatusQueryUrl);
    function InitPcbListTable(basePath){
        dStart=$('#startTime').val();
        dEnd=$('#endTime').val();
        lineNo = $('#btn-line').val();
        //alert(lineNo);
       /* var startTime='2020-01-01 00:00:00';
        var enTime='2021-01-01 00:00:00';
        var lineNo = 'SMT18';*/
        $table = $('#pcbListTable').bootstrapTable({
            url: basePath + "/threePointClose/getPcbListWiththreePointAsClose?" +
                "inspectStarttime=" + dStart +
                "&inspectEndtime=" + dEnd +
                "&lineNo=" + lineNo,
            dataType: "json",
            method: 'GET',                      //请求方式（*）
            //data:{lane:0},
            //toolbar: '#toolbar',              //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            //width:3,
            //sortOrder: "desc",                   //排序方式
            //sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            //pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
            //pageSize: 5,                     //每页的记录行数（*）
            //pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                      //是否显示表格搜索
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列（选择显示的列）
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 400,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            classes: 'table table-striped table-hover',
            rowStyle: function (row, index) {
                if (index % 2 == 0) {
                    return {
                        classes: 'success',
                    }
                } else {
                    return {
                        classes: 'info',
                    }
                }
            },
            //得到查询的参数
            queryParams: function (params) {
                //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
                return {
                    pageSize: params.limit,               //页面大小
                    pageNumber: params.offset / params.limit + 1,  //页码
                    sort: params.sort,      //排序列名
                    sortOrder: params.order //排位命令（desc，asc）
                };
            },
            columns:
                [
                    {
                        field: 'Barcode',
                        title: 'Barcode-List',
                        align: 'center',
                        width: 50,
                        sortable: true,
                        visible: false,
                    },
                    {
                        field: 'Barcode',
                        title: 'Barcode-List',
                        align: 'center',
                        width: 50,
                        formatter: function (value, row, index) {
                            return [
                                '<input type="button" data-toggle="tooltip" data-placement="bottom" title="'+row.lstPCB[0].pcbstartTime+'-'+row.spiPCBID+'-'+row.preAoiPCBID+row.postAoiPCBID+'"  style="width:100%;font-size: 0.7em;" id="barcodeThreePointAsClose" class="btn btn-primary btn-xs" value="' + row.barcode + '">'];
                        },
                        cellStyle: function (value, row, index) {
                                return {css: {"background-color": "D9534F",
                                        "color": "#FF0000",
                                        "padding": 0
                                    }}
                        },
                        events:operateEventsThreePointAsClose,
                    },
                ],
            onLoadSuccess: function (sta) {
                if(sta.rows!=null && sta.rows.length>0){
                    firstBarcode = sta.rows[0].barcode;
                    firstSpiPcbID = sta.rows[0].spiPCBID;
                    firstpreAoiPcbID = sta.rows[0].preAoiPCBID;
                    firstpostAoiPcbID = sta.rows[0].postAoiPCBID;
                    //alert(firstBarcode+ '-' +firstPcbID);
                    //refreshComponentTable(StatusQueryUrl,dStart,dEnd,lineNo,firstBarcode,firstPcbID);
                    //refreshComponentTable(StatusQueryUrl,dStart,dEnd,lineNo,firstBarcode,firstPcbID);
                    InitComponentListTable(StatusQueryUrl,dStart,dEnd,lineNo,firstBarcode,firstPcbID);
                    refreshComponentTable(StatusQueryUrl,dStart,dEnd,lineNo,firstBarcode,firstPcbID);
                }
            },
            formatNoMatches: function () {
                return "无";
            },
            onClickRow: function (row, $element) {
                //$('input[name="lineList"]').attr("checked", true);
                // $('input[name="lineList"]').attr("checked", 'checked');

            }
        });
    }
    function refreshPcbTable(basePath,startTime,endTime,lineNo){
        var opt = {
            url: "${basePath}/threePointClose/getPcbListWiththreePointAsClose?" +
            "inspectStarttime="+startTime+
                "&inspectEndtime="+endTime+
                "&lineNo="+lineNo,
            silent: true,
            query:{
                type:1,
                level:2
            }
        };
        $("#pcbListTable").bootstrapTable('refresh', opt);
    }
    function InitComponentListTable(basePath,ComdStart,ComdEnd,ComlineNo,ComBarcode,ComPcbID){
        //var startTime='2020-01-01 00:00:00';
        //var enTime='2021-01-01 00:00:00';
        //var lineNo = 'SMT18';
        //记录页面bootstrap-table全局变量$table，方便应用
        $table = $('#componentListTable').bootstrapTable({
            url: basePath+"/threePointClose/getComponentListWiththreePointAsClose?" +
                "inspectStarttime="+ ComdStart+
                "&inspectEndtime="+ComdEnd+
                "&lineNo="+ComlineNo+
                "&pcbID="+ComPcbID+
                "&barcode="+ComBarcode,
            dataType:"json",
            method: 'GET',                      //请求方式（*）
            //data:{lane:0},
            //toolbar: '#toolbar',              //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            //width:3,
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
            height: 400,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            classes:'table table-striped table-hover',
            rowStyle: function(row, index) {
                if (index % 2 == 0 ) {
                    return {
                        classes: 'success',
                    }
                }else{
                    return {
                        classes: 'info',
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
            columns:
                [
                    {
                        field: 'CompID',
                        title: 'Component',
                        align:'center',
                        width:50,
                        sortable: true,
                        formatter: function(value,row,index){
                            return [
                                '<input type="button" data-toggle="tooltip" data-placement="bottom" title="'+'条码'+pcbListBarcode+'第'+row.ArrayID+'拼板'+'" style="width:100%" class="btn btn-primary btn-xs" value="'+row.CompID+'-'+row.ArrayID+'">'];
                        },
                        cellStyle: function (value, row, index){
                            if(row.status == 1) { return {css:{"background-color":"D9534F"}}  }
                        },
                        cellStyle:function(value,row,index){
                            //if(row.error==1) {
                            //    return {css: {"color": "#FF0000","padding":0}}
                           // }else{
                                return {css: {"color": "#333333","padding":0}}
                           // }
                        },
                    },
                ],
            onLoadSuccess: function (sta) {
                if(sta.rows!=null && sta.rows.length>0){
                    var fristComName = sta.rows[0].CompID;
                    var arrayId = sta.rows[0].ArrayID;
                    //show first spi api component picture 显示spi aoi三点照合
                    showtThreePointAsCloseComponentImageAndTable(2,StatusQueryUrl,
                        StatusStaticPath,
                        arrayId,
                        pcbListBarcode,
                        fristComName,
                        lineNo,
                        firstpostAoiPcbID,firstpreAoiPcbID,firstSpiPcbID
                    );
                    //showtPreAOIThreePointAsCloseComponentImage(StatusQueryUrl,StatusStaticPath);
                    //showtPostAOIThreePointAsCloseComponentImage(StatusQueryUrl,StatusStaticPath);
                   /* InitSpiComponentDetailTable(
                        StatusQueryUrl,
                        dStart,
                        dEnd,
                        lineNo,
                        pcbListBarcode,pcbListPcbID,fristComName);*/
                }
            },
            formatNoMatches:function(){
                return "无";
            },
            onClickRow: function (row, $element) {
                var arrayId = row.ArrayID;
                var fristComName = row.CompID;
                showtThreePointAsCloseComponentImageAndTable(1,StatusQueryUrl,
                    StatusStaticPath,
                    arrayId,
                    pcbListBarcode,
                    fristComName,
                    lineNo,
                    firstpostAoiPcbID,firstpreAoiPcbID,firstSpiPcbID
                );
                /*InitSpiComponentDetailTable(
                    StatusQueryUrl,
                    dStart,
                    dEnd,
                    lineNo,
                    pcbListBarcode,pcbListPcbID,row.CompID);*/

            }
        });

    }
    function refreshComponentTable(basePath,ComdStart,ComdEnd,ComlineNo,ComBarcode,ComPcbID){
        var opt = {
            url: basePath+"/threePointClose/getComponentListWiththreePointAsClose?" +
                "inspectStarttime="+ ComdStart+
                "&inspectEndtime="+ComdEnd+
                "&lineNo="+ComlineNo+
                "&pcbID="+ComPcbID+
                "&barcode="+ComBarcode,
            silent: true,
            query:{
                type:1,
                level:2
            }
        };
        $("#componentListTable").bootstrapTable('refresh', opt);
    }

    function InitSpiComponentDetailTable (basePath,datas) {
        //alert(lineNo);
        //记录页面bootstrap-table全局变量$table，方便应用
        if(datas==null) return;
        $("#spi-Component-table").bootstrapTable('destroy');
        $('#spi-Component-table').bootstrapTable({
            //url:
            //dataType:"json",
            //method: 'GET',                      //请求方式（*）
            data:datas,
            theadClasses: "thead-dark",//这里设置表头样式
            classes: "table table-bordered table-striped table-sm table-dark",
            striped: true,                      //是否显示行间隔色
            cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortName:'inspectStarttime',
            sortOrder: "desc",                   //排序方式
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
            pageSize: 4,                     //每页的记录行数（*）
            pageList: [4, 20, 50, 100,'ALL'],        //可供选择的每页的行数（*）
            search: false,                      //是否显示表格搜索
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列（选择显示的列）
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 200,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            showExport: false,  //是否显示导出按钮
            showLoading:true,
            Icons:'glyphicon-export',
            rowStyle: function(row, index) {
                if (index % 2 == 0 ) {
                    return {
                        classes:'info'
                    }
                }else{
                    return {
                        classes: 'warning'
                    }
                }
            },
            //得到查询的参数
            queryParams : function (params) {
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
                    field: 'PadID',
                    title: 'PadID',
                    width:20,
                    align:'center',
                    sortable: true,
                    cellStyle: function (value, row, index){
                        return {css:{"font-size":"9px","padding":"0px"}}
                    }
                },
                 {
                    field: 'JudgeRes',
                    title:  'padResult',
                    align:'center',
                    width:20,
                    sortable: true,
                    cellStyle: function (value, row, index){
                        return {css:{"font-size":"9px","padding":"0px"}}
                    },
                },{
                    field: 'DefectType',
                    title:  'DefectType',
                    align:'center',
                    width:20,
                    sortable: true,
                    cellStyle: function (value, row, index){
                        return {css:{"font-size":"9px","padding":"0px"}}
                    },

                },{
                    field: 'inspectResult',
                    title:  'position',
                    align:'center',
                    width:300,
                    sortable: true,
                    cellStyle: function (value, row, index){
                        return {css:{"font-size":"9px","padding":"0px"}}
                    },
                    formatter:function (value,row,index) {
                        return 'h:'+row.Height+'-v:'+row.Volume+'-a:'+row.Area;
                    }
                },
            ],
            formatLoadingMessage:function(){
                return "请稍等,正在加载中.........";
            },
            onLoadSuccess: function (data,$element) {
            },
            onLoadError: function (status,res) {
                console.log(res);
                console.log(status);
            },
            onDblClickRow: function (row, $element) {
            },
            onClickRow: function (row, $element) {
                //$('.danger').removeClass('danger');
                $($element).addClass('danger')
            },
        });


    };

    function InitPreAoiComponentDetailTable (basePath,datas) {
        //alert(lineNo);
        //记录页面bootstrap-table全局变量$table，方便应用
        if(datas==null) return;
        //$('#proAoi-Component-table');
        $("#proAoi-Component-table").bootstrapTable('destroy');
        var fovTable = $('#proAoi-Component-table').bootstrapTable({
            method: 'GET',                      //请求方式（*）
            data:datas,
            theadClasses: "thead-dark",//这里设置表头样式
            classes: "table table-bordered table-striped table-sm table-dark",
            striped: true,                      //是否显示行间隔色
            cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortName:'inspectStarttime',
            sortOrder: "desc",                   //排序方式
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
            pageSize: 4,                     //每页的记录行数（*）
            pageList: [4, 20, 50, 100,'ALL'],        //可供选择的每页的行数（*）
            search: false,                      //是否显示表格搜索
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列（选择显示的列）
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 200,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            //classes:'table table-bordered table-hover',
            showExport: false,  //是否显示导出按钮
            showLoading:true,
            Icons:'glyphicon-export',
            rowStyle: function(row, index) {
                if (index % 2 == 0 ) {
                    return {
                        classes:'info'
                    }
                }else{
                    return {
                        classes: 'warning'
                    }
                }
            },
            //得到查询的参数
            queryParams : function (params) {
                return {
                    pageSize: params.limit,               //页面大小
                    pageNumber: params.offset/params.limit+1,  //页码
                    sort: params.sort,      //排序列名
                    sortOrder: params.order //排位命令（desc，asc）
                };
            },
            columns: [
                {
                    field: 'WindowIndex',
                    title: 'WindowIndex',
                    width:300,
                    align:'center',
                    sortable: true,
                    cellStyle: function (value, row, index){
                        return {css:{"font-size":"9px","padding":"0px"}}
                    }
                },
                {
                    field: 'JudgeRes',
                    title:  'JudgeRes',
                    align:'center',
                    sortable: true,
                    cellStyle: function (value, row, index){
                        return {css:{"font-size":"9px","padding":"0px"}}
                    },
                    width:300,
                    //events:operateEvents,
                },
                {
                    field: 'DefectName',
                    title:  'DefectName',
                    align:'center',
                    width:300,
                    sortable: true,
                    cellStyle: function (value, row, index){
                        return {css:{"font-size":"9px","padding":"0px"}}
                    },
                },
            ],

            formatLoadingMessage:function(){
                return "请稍等,正在加载中.........";
            },
            onLoadSuccess: function (data,$element) {
            },
            onLoadError: function (status,res) {
                console.log(res);
                console.log(status);
            },
            onDblClickRow: function (row, $element) {
            },
            onClickRow: function (row, $element) {
                $('.danger').removeClass('danger');
                $($element).addClass('danger')
            },
        });

    };

    function InitPostAoiComponentDetailTable (basePath,datas) {
        //alert(lineNo);
        //记录页面bootstrap-table全局变量$table，方便应用
        if(datas==null) return;
        $("#postAoi-Component-table").bootstrapTable('destroy');
        var fovTable = $('#postAoi-Component-table').bootstrapTable({
            method: 'GET',                      //请求方式（*）
            data:datas,
            theadClasses: "thead-dark",//这里设置表头样式
            classes: "table table-bordered table-striped table-sm table-dark",
            striped: true,                      //是否显示行间隔色
            cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortName:'inspectStarttime',
            sortOrder: "desc",                   //排序方式
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
            pageSize: 4,                     //每页的记录行数（*）
            pageList: [4, 20, 50, 100,'ALL'],        //可供选择的每页的行数（*）
            search: false,                      //是否显示表格搜索
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列（选择显示的列）
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 200,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            showExport: false,  //是否显示导出按钮
            showLoading:true,
            Icons:'glyphicon-export',
            rowStyle: function(row, index) {
                if (index % 2 == 0 ) {
                    return {
                        classes:'info'
                    }
                }else{
                    return {
                        classes: 'warning'
                    }
                }
            },
            //得到查询的参数
            queryParams : function (params) {
                return {
                    pageSize: params.limit,               //页面大小
                    pageNumber: params.offset/params.limit+1,  //页码
                    sort: params.sort,      //排序列名
                    sortOrder: params.order //排位命令（desc，asc）
                };
            },
            columns: [
                {
                    field: 'WindowIndex',
                    title: 'window',
                    // width:50,
                    align:'center',
                    sortable: true,
                    cellStyle: function (value, row, index){
                        return {css:{"font-size":"9px","padding":"0px"}}
                    }
                }, {
                    field: 'WindowIndex',
                    title: 'WindowIndex',
                    // width:50,
                    align:'center',
                    sortable: true,
                    cellStyle: function (value, row, index){
                        return {css:{"font-size":"9px","padding":"0px"}}
                    }
                },
                {
                    field: 'JudgeRes',
                    title:  'JudgeRes',
                    align:'center',
                    sortable: true,
                    cellStyle: function (value, row, index){
                        return {css:{"font-size":"9px","padding":"0px"}}
                    }
                    //  width:50
                    //events:operateEvents,
                },
                {
                    field: 'DefectName',
                    title:  'DefectName',
                    align:'center',
                    //width:5,
                    sortable: true,
                    cellStyle: function (value, row, index){
                        return {css:{"font-size":"9px","padding":"0px"}}
                    },
                },
            ],
            formatLoadingMessage:function(){
                return "请稍等,正在加载中.........";
            },
            onLoadSuccess: function (data,$element) {
            },
            onLoadError: function (status,res) {
                console.log(res);
                console.log(status);
            },
            onDblClickRow: function (row, $element) {
            },
            onClickRow: function (row, $element) {
                $('.danger').removeClass('danger');
                $($element).addClass('danger')
            },
        });
    };
    function refreshPostAoiComponentDetailTable(basePath,datas){
        if(datas==null) return;
        var opt = {
            data:datas,
            silent: true,
            query:{
                type:1,
                level:2
            }
        };
        $("#postAoi-Component-table").bootstrapTable('refresh', opt);
    }
    //spicomponent
    function showtThreePointAsCloseComponentImageAndTable(clickMode,basePath,staticPath,ArrayID,Barcode,CompID,LineNo,PCBID_AOIB,PCBID_AOIF,PCBID_SPI){
        //var pcbPath= '//127.0.0.1/AOI_DB/20201104094650/Component/-C90-2spi.jpg';
        //var heigthPath='';////127.0.0.1/AOI_DB/20201104094650/Component/C4_ 1_130_92.3ddata_BF';
        $.ajax({
            url:basePath+"/threePointClose/getComponentPcsInfoWiththreePointAsClose",
            dataType:"json",
            async:true,
            data:{ArrayID:ArrayID,Barcode:Barcode,CompID:CompID,LineNo:LineNo,PCBID_AOIB:PCBID_AOIB,PCBID_AOIF:PCBID_AOIF,PCBID_SPI:PCBID_SPI},
            type:"GET",
            beforeSend:function(){
            },
            success:function(req){
                //spi
                $('#spi-2D-Component').empty();

                $("<img  style='width: 100%;height:35vh;' src='data:image/jpeg;base64,"+
                    req.data.CompData_SPI.ImgString+"  '>").addClass("img-thumbnail").appendTo("#spi-2D-Component");

                showSpi3DComponentImage(staticPath,req.data.CompData_SPI.ImgString);

                //preaoi
                $('#preAoi-2D-Component').empty();
                $("<img  style='width: 100%;height:35vh;' src='data:image/jpeg;base64,"+
                    req.data.CompData_AOIF.ImgString+"  '>").addClass("img-thumbnail").appendTo("#preAoi-2D-Component");
                showPreAoi3DComponentImage(staticPath,req.data.CompData_AOIF.ImgString,req.data.CompData_AOIF.Img3D);
               //postaoi
                $('#postAoi-2D-Component').empty();
                $("<img  style='width: 100%;height:35vh;' src='data:image/jpeg;base64,"+
                    req.data.CompData_AOIB.ImgString+"  '>").addClass("img-thumbnail").appendTo("#postAoi-2D-Component");
                //com-title
                $('#span-spi-content-component')[0].innerText = 'SPI   ID:'+CompID+'    ArrayID:'+ ArrayID + '   Re:';
                var vResHtml =  req.data.CompData_SPI.CompResult == 'NG'
                    ?$('<span></span>').attr('style','color:red').append(  req.data.CompData_SPI.CompResult)
                    :$('<span></span>').append( req.data.CompData_SPI.CompResult);
                vResHtml.appendTo($('#span-spi-content-component'));

                $('#span-PRE-content-component')[0].innerText = 'AOI-PRE   ID:'+CompID+'    ArrayID:'+ ArrayID + '   Re:';
                 vResHtml = req.data.CompData_AOIF.CompSpec.OPCinfirmed == 'NG'
                    ?$('<span></span>').attr('style','color:red').append( req.data.CompData_AOIF.CompSpec.OPCinfirmed)
                    :$('<span></span>').append( req.data.CompData_AOIF.CompSpec.OPCinfirmed);
                vResHtml.appendTo($('#span-PRE-content-component'));

                $('#span-POST-content-component')[0].innerText = 'AOI-POST   ID:'+CompID+'    ArrayID:'+ ArrayID + '   Re:';
                vResHtml = req.data.CompData_AOIB.CompSpec.OPCinfirmed == 'NG'
                    ?$('<span></span>').attr('style','color:red').append(  req.data.CompData_AOIB.CompSpec.OPCinfirmed)
                    :$('<span></span>').append( req.data.CompData_AOIB.CompSpec.OPCinfirmed);
                vResHtml.appendTo($('#span-POST-content-component'));

                showPostAoi3DComponentImage(staticPath,req.data.CompData_AOIB.ImgString,req.data.CompData_AOIB.Img3D);
                /*switch (clickMode) {
                    case 1:
                        refreshSpiComponentDetailTable(basePath,req.data.CompData_SPI.arrPadSpec);
                        refreshPreAoiComponentDetailTable(basePath,req.data.CompData_AOIF.CompSpec.LstWindowSpec);
                        refreshPostAoiComponentDetailTable(basePath,req.data.CompData_AOIB.CompSpec.LstWindowSpec);
                        break;
                    case 2:*/
                        InitSpiComponentDetailTable(basePath,req.data.CompData_SPI.arrPadSpec);
                        InitPreAoiComponentDetailTable(basePath,req.data.CompData_AOIF.CompSpec.LstWindowSpec);
                        InitPostAoiComponentDetailTable(basePath,req.data.CompData_AOIB.CompSpec.LstWindowSpec);
                       /* break;
                    default:
                        InitSpiComponentDetailTable(basePath,req.data.CompData_SPI.arrPadSpec);
                        InitPreAoiComponentDetailTable(basePath,req.data.CompData_AOIF.CompSpec.LstWindowSpec);
                        InitPostAoiComponentDetailTable(basePath,req.data.CompData_AOIB.CompSpec.LstWindowSpec);
                        break;
                }*/
            },
            error:function(req){
                alert(req);
            }
        });
    }
    //PreAOIcomponent
   /* function showtPreAOIThreePointAsCloseComponentImage(basePath,staticPath){
        var pcbPath= '//127.0.0.1/AOI_DB/20201104094650/Component/AMT4_ 1_437_92.jpg';
        var heigthPath='//127.0.0.1/AOI_DB/20201104094650/Component/AMT4_ 1_437_92.3ddata_BF';
        $.ajax({
            url:basePath+"/aLine/getAoiArray3DImage",
            dataType:"json",
            async:true,
            data:{path:pcbPath,heigthPath:heigthPath},
            type:"GET",
            beforeSend:function(){
            },
            success:function(req){
                $('#preAoi-2D-Component').empty();
                $("<img  style='width: 100%;height:35vh;' src='data:image/jpeg;base64,"+
                    req.data.base64Image+"  '>").addClass("img-thumbnail").appendTo("#preAoi-2D-Component");
                InitPreAoiComponentDetailTable(basePath);
                showPreAoi3DComponentImage(staticPath,req.data.base64Image,req.data.arrayHeightBy3D,req.data.dMaxHeight,req.data.dMinHeight);
            },
            error:function(req){
                alert(req);
            }
        });
    }
    //PostAOIcomponent
    function showtPostAOIThreePointAsCloseComponentImage(basePath,staticPath){
        var pcbPath= '//127.0.0.1/AOI_DB/20201104094650/Component/AMT4_ 1_437_92.jpg';
        var heigthPath='//127.0.0.1/AOI_DB/20201104094650/Component/AMT4_ 1_437_92.3ddata_BF';
        $.ajax({
            url:basePath+"/aLine/getAoiArray3DImage",
            dataType:"json",
            async:true,
            data:{path:pcbPath,heigthPath:heigthPath},
            type:"GET",
            beforeSend:function(){
            },
            success:function(req){
                $('#postAoi-2D-Component').empty();
                $("<img  style='width: 100%;height:35vh;' src='data:image/jpeg;base64,"+
                    req.data.base64Image+"  '>").addClass("img-thumbnail").appendTo("#postAoi-2D-Component");
                InitPostAoiComponentDetailTable(basePath);
                showPostAoi3DComponentImage(staticPath,req.data.base64Image,req.data.arrayHeightBy3D,req.data.dMaxHeight,req.data.dMinHeight);
            },
            error:function(req){
                alert(req);
            }
        });
    }
*/
    function resultListGroup(value){
        $('#result-tbn').val(value);
    }
    function lineListGroup(value) {
        $('#btn-line').val(value);
    }
    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    })
</script>
<style>
    .col-md-4{
        padding: 3px;
    }
.list-group-item{
    width: 70%;
    height: 35px;
}

</style>
</body>
</html>

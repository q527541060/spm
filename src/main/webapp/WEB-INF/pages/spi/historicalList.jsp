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
            text-align: center;
            margin: 0px;

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
            <div class="col-md-2" style="padding: 0px">
                <ol class="breadcrumb" style="float: left;margin: 0px;">
                    <li><a href="${basePath}/Home/pcbHome">Home</a></li>
                    <%--<li><a href="#">spi</a></li>--%>
                    <li class="active">spi</li>
                    <li class="active">Machine-historicalList</li>
                </ol>
            </div>
            <div class="col-md-8">
                <div style="text-align: center;padding: 5px" id="sandBox-container">
                    <%--<span class="glyphicon glyphicon-time" aria-hidden="true"></span>--%>
                    <span  style="margin-left: -19px;" class="glyphicon glyphicon-calendar"></span>
                    <input size="16" type="text"  readonly  id="startTime" /> -
                    <input  size="16"  type="text"   readonly  id="endTime"/>
                    <!-- glyphicon glyphicon-search-->
                    <button type="button" class="btn  btn-info btn-xs"  onclick="refreshMainTable()">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 搜索
                    </button>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="col-md-14">
                <h4>${lineNo}线设备状态历史记录分析</h4>
                <input id="lineNo" value="${lineNo}" type="hidden">

            </div>
        </div>
        <div class="row">
            <div class="col-md-10 col-md-offset-1" >
                <!-- 状态树状列表 -->
                <div id="table-list"></div>

            </div>
        </div>
    </nav>
<script type="text/javascript">
    var lineNo = $('#lineNo').val();

    //时间选择器
    var nowDate = new Date();
    var dStart = dateFomate( nowDate.setDate(nowDate.getDate()-1),'yyyy-MM-dd HH:mm:ss' );
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
    InitMainTable ();

    <!--   设备状态js代码  -->
    function InitMainTable () {
        $('#table-list').bootstrapTable({
            url: '${basePath}/sStatus/historicalMonitorJson'+
                "?lineNo="+lineNo+"&inspectStartTime="+dStart+
                "&inspectEndTime="+dEnd ,                    //请求后台的URL（*）
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
            pageSize: 15,                     //每页的记录行数（*）
            pageList: [15, 25, 50, 100,'ALL'],        //可供选择的每页的行数（*）
            search: false,                      //是否显示表格搜索
            minimumCountColumns: 2,             //最少允许的列数
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列（选择显示的列）
            showRefresh: true,                  //是否显示刷新按钮
            clickToSelect: true,                //是否启用点击选中行
            height: 550,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            classes:'table table-striped table-hover',
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

            columns: [{
                checkbox: true,
                align:'center',
                visible: true,

                //是否显示复选框
            }, {
                field: 'equipmentNo',
                title: 'Machine',
                //width:50,
                align:'center',
                sortable: true
            }, {
                field: 'lineNo',
                title: 'LaneNo',
                align:'center',
                width:200,

                sortable: true
            }, {
                field: 'status',
                title: 'Status',
                align:'center',
                width:200,
                sortable: true,
                cellStyle: function (value, row, index){
                    if(row.status == 1) { return {css:{"background-color":"D9534F"}}  }
                },
                formatter: function (value, row, index){
                    //row.status ==0?"停止":row.status==1?"故障":"运行";
                    if(row.error==1){
                        return "error";
                    }else  if(row.run==1 && row.start==1){
                        return "run";
                    }else if(row.idle==1){
                        return  "idel";
                    }else{
                        return  "stop";
                    }
                    /*switch (row.status) {
                        case 0:
                            return "run";
                        case  3:
                            return  "stop";
                        case 2:
                            return  "idel";
                        case  1:
                            return "error";
                        default :
                            return "run";
                    }*/
                    return
                },
                cellStyle:function(value,row,index){
                    if(row.error==1) {
                        return {css: {"color": "#FF0000"}}
                    }else{
                        return {css: {"color": "#333333"}}
                    }
                }

            },{
                field: 'factory',
                title: 'Model',
                align:'center',
                sortable:true,
                width:200
                //formatter: linkFormatter
            }, {
                field: 'errContent',
                title:  'Alarm',
                align:'center',
                sortable: true,
                width:350
                //events:operateEvents,

            },{
                field: 'updateTime',
                title:  'dateTime',
                align:'center',
                sortable: true,
                width:180
            }],
            onLoadSuccess: function (sta) {
            },
            onLoadError: function (status,res) {
            },
            onDblClickRow: function (row, $element) {
            },
            onClickRow: function (row, $element) {
            },
        });
    };
    function refreshMainTable(){
        //获取时间区间
        dStart = $("#startTime").val();
        dEnd = $("#endTime").val();
        var opt = {
            url: '${basePath}/sStatus/historicalMonitorJson'+
                "?lineNo="+lineNo+"&inspectStartTime="+dStart+
                "&inspectEndTime="+dEnd ,
            silent: true,
            query:{
                type:1,
                level:2
            }
        };
        $("#table-list").bootstrapTable('refresh', opt);
    }
</script>


</body>
</html>
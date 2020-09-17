<%--
  Created by IntelliJ IDEA.
  User: sinictek
  Date: 2020/7/15
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>setting</title>
    <style>
        body{
            margin: 0px;
            padding: 0px;
            /*background: url("$staticPath}/static/img/home6.png");*/
            /*background-size:cover;*/
        }
        .row{
            margin-bottom: 10px;
        }
        #table_line{
            width: 100%;
        }

        #selectPicker{
        }
    </style>
</head>
<body>
    <nav>
        <%@include file="header.jsp" %>
        <div class="row" style="text-align: left;margin-top: -15px; ">
            <div class="col-md-14">
                <ol class="breadcrumb">
                    <li><a href="${basePath}/Home/pcbHome">Home</a></li>
                    <%--<li><a href="#">spi</a></li>--%>
                    <li class="active">setting</li>
                </ol>
            </div>
        </div>
        <!--  Default参数设置 -->
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Default参数设置
                    </div>
                    <div class="panel-body">
                        <div id="table_default" ></div>
                    </div>
                </div>
            </div>
        </div>
        <!--  线体 设置  -->
        <div class="row" >
            <div class="col-md-8 col-md-offset-2" >
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        SPI线体信息变更
                    </div>
                    <div class="panel-body">
                        <div class="panel-body">
                            <div id="table_line"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        AOI线体信息变更
                    </div>
                    <div class="panel-body">
                        <div class="panel-body">
                           <%-- <div id="table_line"></div>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="modal fade" id="editSettingLineModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="exampleModalLabel">line message</h4>
                        </div>
                        <div class="modal-body">
                            <form id="form_settingLine">
                                <div class="form-group">
                                    <input type="hidden" name="idStr" id="lineNo_hidden">
                                    <label for="recipient-name" class="control-label">lineNo:</label>
                                    <input  name="LineNo" class="form-control lineNo" id="recipient-name" >
                                </div>
                                <div class="form-group">
                                    <label for="message-text" class="control-label">lineDetail:</label>
                                    <input  name="lineContent" class="form-control lineContent" id="message-text" >
                                </div>
                                <div class="form-group">
                                    <label for="message-text" class="control-label">lineCreateDate:</label>
                                    <input  name="createDateStr" class="form-control createDate" id="message-text" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="message-text" class="control-label">lineUpdateDate:</label>
                                    <input  name="updateDateStr" class="form-control updateDate" id="message-text" readonly>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">

                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" onclick="update_edit_SettingLine()" class="btn btn-primary">Save message</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="modal fade" id="editDefaultLineModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="exampleModalLabel1">Default Message</h4>
                        </div>
                        <div class="modal-body">
                            <form id="form_default">
                                <div class="form-group selectPicker">
                                   <%-- <select  class="form-control" data-style="btn-warning">
                                        &lt;%&ndash;<option  value="CT">Connecticut</option>
                                        <option value="NY">New York</option>
                                        <option value="MD">Maryland</option>
                                        <option value="VA">Virginia</option>&ndash;%&gt;
                                    </select>--%>
                                </div>

                                <div class="form-group">
                                    <label for="message-text" class="control-label">更新时间:</label>
                                    <input  name="updateTimeStr" class="form-control updateTime"  readonly>
                                    <input  name="idStr" class="defaultId" type="hidden" >
                                    <input  name="settingValue" class="settingValue" type="hidden" >
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" onclick="update_edit_Default()" class="btn btn-primary">Save Default</button>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </nav>

    <script type="text/javascript">
        window.operateEventsSettingLine={
            "click #EditSettingLine" :function(e,value, row, index){
                //alert(row.id);
                var lineId = row.id;
                show_edit_SettingLine(lineId);
            },
            "click #deleteSettingLine" :function(e,value, row, index){
                var lineId = row.id;
                if(confirm("确定删除吗?   删除后线体["+row.lineNo+"]的所有数据将清空!")){
                    delete_SettingLine(lineId);
                }else{
                    alert('取消删除!');
                }
            },"click #EditDefault" :function(e,value, row, index){
                var lineId = row.id;
                show_Default(lineId);
            },
        }

        InitEquipmentTable();
        InitDefaultTable();
        function show_Default(lineId) {
            $.ajax({
                url:"${basePath}/sDefaultsetting/default",
                dataType:"json",
                async:true,
                data:{lineId:lineId},
                type:"GET",
                beforeSend:function(){
                },
                success:function(req) {
                    $('.selectPicker').empty();
                    $('.defaultId').val(req.data.id);
                    var labelSettingValue = $('<label></label>').addClass('control-label').append(req.data.remark +":");
                    $('#form_default .selectPicker').prepend(labelSettingValue);
                    switch (req.data.settingName) {
                        case 'FPY':
                        case 'CPK':
                        case 'Product':
                        case 'autoDelete':
                        case 'DefaultTop5':
                        case 'boardView-chartMove':
                            {
                            var vselect =  $('<select ></select>').addClass("form-control"); //$('.selectPicker').append();
                            for (var i = 0; i <2 ; i++) {
                                var voption = $("<option></option>").addClass('option').append(i==1?'开启':'关闭').attr("value",i);
                                if(req.data.settingValue==i){
                                    voption.attr('selected','selected');
                                }
                                voption.appendTo(vselect);
                            }
                            $('.selectPicker').append(vselect);
                            break;
                        }
                        case 'boardMachineTimeLimit':
                        case 'boardMachineRefreshTime':{
                            var vselect = ($('<select ></select>').addClass("form-control"))  ; //$('<div></div>').addClass("col-sm-4").append
                            for (var i = 1; i <=60 ; i++) {
                                var voption = '';//$("<option></option>").addClass('option').append(i*5-1).attr("value",i*5-1);
                                /*if(req.data.settingValue==i*5-1){
                                    voption.attr('selected','selected');
                                }*/
                                if(i==60){
                                    voption = $("<option></option>").addClass('option').append(2).attr("value",2);
                                    if(req.data.settingValue==2){
                                        voption.attr('selected','selected');
                                    }
                                }else{
                                    voption = $("<option></option>").addClass('option').append(i*5-1).attr("value",i*5-1);
                                    if(req.data.settingValue==i*5-1){
                                        voption.attr('selected','selected');
                                    }
                                }
                                voption.appendTo(vselect);
                            }
                            $('.selectPicker').append(vselect);
                            break;
                        }
                        case 'defaultType':{
                            var vselect = ($('<select ></select>').addClass("form-control"))  ; //$('<div></div>').addClass("col-sm-4").append
                            for (var i = 0; i <12 ; i++) {

                                var voption = $("<option></option>").addClass('option').
                                append(padDefaultType(i)+';'+padDefaultType(i+1)+';'+padDefaultType(i+2)+';'+padDefaultType(i+3)+';'+padDefaultType(i+4)).
                                attr("value",(i)+';'+(i+1)+';'+(i+2)+';'+(i+3)+';'+(i+4));
                                if(req.data.settingValue==(i)+';'+(1+1)+';'+(i+2)+';'+(i+3)+';'+(i+4)){
                                    voption.attr('selected','selected');
                                }
                                voption.appendTo(vselect);
                            }
                            $('.selectPicker').append(vselect);
                            break;
                        }
                        case 'hChartColor':{
                            var vselect = ($('<select ></select>').addClass("form-control"))  ; //$('<div></div>').addClass("col-sm-4").append
                            for (var i = 0; i <12 ; i++) {
                                var voption = $("<option></option>").addClass('option').
                                append(hChartColorToStr(i+'')).
                                attr("value",i);
                                if(req.data.settingValue==i){
                                    voption.attr('selected','selected');
                                }
                                voption.appendTo(vselect);
                            }
                            $('.selectPicker').append(vselect);
                            break;
                        }
                        case 'backgroundColor':{
                            var vselect = ($('<select ></select>').addClass("form-control"))  ; //$('<div></div>').addClass("col-sm-4").append
                            for (var i = 0; i <8; i++) {
                                var voption = $("<option></option>").addClass('option').
                                append(backgroundColorToStr(i+'')).
                                attr("value",i);
                                if(req.data.settingValue==i){
                                    voption.attr('selected','selected');
                                }
                                voption.appendTo(vselect);
                            }
                            $('.selectPicker').append(vselect);

                            break;
                        }
                        case 'autoDelete-MaxDays':
                        case 'Frequency-start':
                        case 'standCPK':
                        case 'passPcbYeild':
                        case 'autoDeleteDays':{
                            var vselectinput = $('<input class="form-control deleteDaysValue" value="'+req.data.settingValue+'">').append(req.data.settingValue);
                            //$('.selectPicker').append(vselectinput);
                            vselectinput.appendTo('.selectPicker');
                            //$('.deleteDaysValue').val(req.data.settingValue);
                            break;
                        }
                       /* case 'standCPK':{
                            var vselectinput =$('<input class="form-control deleteDaysValue" value="'+req.data.settingValue+'">').append(req.data.settingValue);
                            vselectinput.appendTo('.selectPicker');
                            break;
                        }*/
                    }
                    $('#form_default .updateTime').val(req.data.updateTime);
                    //$('.selectpicker').combobox();

                }
            });
            $('#editDefaultLineModal').modal({
                backdrop:'static',keyboard:false
            });
        }

        /*function update_headHtml_hchartColor(iParm){
            $('#chartColor').val(iParm);
        }*/
        function backgroundColorToStr(i){
            var result = '';
            switch (i) {
                case '0':
                    result='大理石纹色';
                    break;
                case '1':
                    result= '白色';
                    break;
                case '2':
                    result= '淡蓝色';
                    break;
                case '3':
                    result= '淡绿色';
                    break;
                case '4':
                    result= '蓝色';
                    break;
                case '5':
                    result= '灰色1';
                    break;
                case '6':
                    result= '灰色2';
                    break;
                case '7':
                    result= '绿色';
                    break;
                default :
                    result='大理石纹色';
                    break;
            }
            return result;
        }
        function hChartColorToStr(i) {
            var result ='';
            switch (i) {
                case '0':
                    result = 'grid-light';
                    break;
                case '1':
                    result='dark-blue';
                    break;
                case '2':
                    result='dark-green';
                    break;
                case '3':
                    result='dark-unica';
                    break;
                case '4':
                    result='gray';
                    break;
                case '5':
                    result='grid';
                    break;
                case '6':
                    result='grid-light';
                    break;
                case '7':
                    result='high-contrast-dark';
                    break;
                case '8':
                    result='high-contrast-light';
                    break;
                case '9':
                    result='sand-signika';
                    break;
                case '10':
                    result='skies';
                    break;
                case '11':
                    result='sunset';
                    break;
                default :{
                    result='sunset';
                    break;
                }

            }
            return result;
        }
        function update_edit_Default(){
            var selectValue = $('.selectPicker select option:selected').val();
            if(selectValue==null || selectValue==''){
                selectValue = $('.deleteDaysValue').val();
            }
            $('.settingValue').val(selectValue);
            $('#editDefaultLineModal').modal('hide');
            $.ajax({
                url:"${basePath}/sDefaultsetting/updatedefault",
                data:$('#form_default').serialize(),
                //dataType:"application/x-www-form-urlencoded",
                type:"POST",
                beforeSend:function(){
                },
                success:function(data) {
                    refreshDefaultTable();
                    alert(data.message);
                    var idStr = $('.defaultId').val();
                    //alert(idStr);
                    if(idStr == '14' ){
                        window.location.href="${basePath}/sDefaultsetting/setting";
                    }
                },
                error:function (data) {
                    //alert(data.message);
                }
            });


        }
        function padDefaultType(x) {
            var padDefultType = '';

            switch (x) {
                case 0:
                    padDefultType = 'Missing';
                    break;
                case  1:
                    padDefultType = 'Insufficient';
                    break;
                case  2:
                    padDefultType = 'Excess';
                    break;
                case  3:
                    padDefultType = 'OverHeight';
                    break;
                case  4:
                    padDefultType = 'LowHeight';
                    break;
                case  5:
                    padDefultType = 'OverArea';
                    break;
                case  6:
                    padDefultType = 'LowArea';
                    break;
                case  7:
                    padDefultType = 'ShiftX';
                    break;
                case  8:
                    padDefultType = 'ShiftY';
                    break;
                case  9:
                    padDefultType = 'Bridge';
                    break;
                case  10:
                    padDefultType = 'ShapeError';
                    break;
                case  11:
                    padDefultType = 'Smeared';
                    break;
                case  12:
                    padDefultType = 'Coplanarity';
                    break;
                case  13:
                    padDefultType = 'PreBridge';
                    break;
                case  14:
                    padDefultType = 'PadAreaError';
                    break;
                case  15:
                    padDefultType = 'WarpError';
                    break;
                default :
                    padDefultType = 'Missing';
                    break;

            }
            return padDefultType;
        }
        function show_edit_SettingLine(lineId) {
            //full data
            $.ajax({
                url:"${basePath}/sDefaultsetting/lineSetting",
                dataType:"json",
                async:true,
                data:{lineId:lineId},
                type:"GET",
                beforeSend:function(){
                },
                success:function(req) {
                    $('.lineNo').val(req.data.lineNo);
                    $('.lineContent').val(req.data.lineContent);
                    $('.createDate').val(req.data.createDate);
                    $('.updateDate').val(req.data.updateDate);
                    $('#lineNo_hidden').val(req.data.id);
                }
            });

            // show modal
            $('#editSettingLineModal').modal({
                backdrop:'static',keyboard:false   //锁定界面
            });
        }
        function update_edit_SettingLine() {
            $('#editSettingLineModal').modal('hide');
            $.ajax({
                url:"${basePath}/sDefaultsetting/updateLineSetting",
                data:$('#form_settingLine').serialize(),
                //dataType:"application/x-www-form-urlencoded",
                type:"POST",
                beforeSend:function(){
                },
                success:function(data) {
                    refreshEquipmentTable();
                        alert(data.message);
                },
                error:function (data) {
                    //alert(data.message);
                }
            });


        }
        function delete_SettingLine(lineId) {
            $.ajax({
                url:"${basePath}/sDefaultsetting/deleteLineSetting",
                data:{id:lineId},
                type:"GET",
                beforeSend:function(){
                },
                success:function(data) {
                    refreshEquipmentTable();
                    alert(data.message);
                },
                error:function (data) {
                    //alert(data.message);
                }
            });
        }

        function InitEquipmentTable () {
            //alert(lineNo);
            //记录页面bootstrap-table全局变量$table，方便应用
            var $Equipmenttable = $('#table_line').bootstrapTable({
                url:"${basePath}/sDefaultsetting/listline",
                dataType:"json",
                method: 'GET',                      //请求方式（*）
                striped: true,                      //是否显示行间隔色
                cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                sortOrder: "desc",                   //排序方式
                sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
                pageSize: 5,                     //每页的记录行数（*）
                pageList: [15, 20, 50, 100],        //可供选择的每页的行数（*）
                search: false,                      //是否显示表格搜索
                strictSearch: false,
                showColumns: false,                  //是否显示所有的列（选择显示的列）
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: false,                //是否启用点击选中行
                //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                  //是否显示父子表
                classes:'table table-striped table-hover',
                showLoading:false,
                showExport:false,
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
                            classes: 'warning'
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
                    checkbox: false,
                    align:'center',
                    visible: false
                    //是否显示复选框
                },  {
                    field: 'id',
                    title: '序号',
                    align:'center',
                      width:50,
                    sortable: true,
                    formatter:function (value,row,index) {
                        return index+1;
                    }
                },{
                    field: 'lineNo',
                    title: 'lineNo',
                    align:'center',
                      width:300,
                    sortable: true,
                }, {
                        field: 'lineContent',
                        title:  'lineContent',
                        align:'center',
                         width:300,
                        sortable: true,
                }, {
                    field: 'createDate',
                    title:  'createDate',
                    align:'center',
                    sortable: true,
                    width:300,
                }, {
                    field: 'updateDate',
                    title:  'updateDate',
                    align:'center',
                    sortable: true,
                    width:300,
                },{
                    field: 'oper',
                    title:  'op',
                    align:'center',
                    width:300,
                    events: operateEventsSettingLine,//给按钮注册事件
                    formatter: addFunctionAltySettingLine//表格中增加按钮
                }
                ],
                formatLoadingMessage:function(){
                    return "请稍等,正在加载中.........";
                },
                onLoadSuccess: function (data,$element) {
                },
                onLoadError: function (status,res) {
                }
            });
        };
        function refreshEquipmentTable(){
            var opt = {
                url:"${basePath}/sDefaultsetting/listline",
                silent: true,
                query:{
                    type:1,
                    level:2
                }
            };
            $("#table_line").bootstrapTable('refresh', opt);
        }

        function addFunctionAltySettingLine(){
            return ['<button type="button" id="EditSettingLine"   class="btn btn-info btn-xs">编辑</button>' +
            '  <button type="button" id="deleteSettingLine"   class="btn btn-danger btn-xs">删除</button>' +
            ''].join("");
        }
        function addFunctionAltyDefault(){
            return ['<button type="button" id="EditDefault"   class="btn btn-info btn-xs">编辑</button>' +
            ''].join("");
        }

        //addFunctionAltyDefault

        function InitDefaultTable () {
            //alert(lineNo);
            //记录页面bootstrap-table全局变量$table，方便应用
            var $Equipmenttable = $('#table_default').bootstrapTable({
                url:"${basePath}/sDefaultsetting/getDefaultJson",
                dataType:"json",
                method: 'GET',                      //请求方式（*）
                striped: true,                      //是否显示行间隔色
                cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                sortOrder: "desc",                   //排序方式
                sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
                pageSize: 6,                     //每页的记录行数（*）
                pageList: [6,15, 20, 50, 100,'ALL'],        //可供选择的每页的行数（*）
                search: false,                      //是否显示表格搜索
                strictSearch: false,
                showColumns: false,                  //是否显示所有的列（选择显示的列）
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: false,                //是否启用点击选中行
                //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                  //是否显示父子表
                classes:'table table-striped table-hover',
                showLoading:false,
                showExport:false,
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
                            classes: 'warning'
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
                    checkbox: false,
                    align:'center',
                    visible: false
                    //是否显示复选框
                },  {
                    field: 'id',
                    title: '序号',
                    align:'center',
                    width:50,
                    sortable: true,
                    formatter:function (value,row,index) {
                        return index+1;
                    }
                },{
                    field: 'settingName',
                    title: 'settingName',
                    align:'center',
                    width:300,
                    sortable: true,
                }, {
                    field: 'settingValue',
                    title:  'settingValue',
                    align:'center',
                    width:300,
                    sortable: true,
                    formatter:function (value,row,index) {
                        var result = '';
                        switch (row.settingName) {
                            case 'FPY':
                            case 'CPK':
                            case 'Product':
                            case 'autoDelete':
                            case 'boardView-chartMove':
                            case 'DefaultTop5': {
                                result = row.settingValue=='1'?'开启':'关闭';
                                break;
                            }
                            case 'defaultType':{
                                if(row.settingValue=='0;1;2;3;4'){
                                    result = 'Missing;Insufficient;Excess;OverHeight;LowHeight';
                                }else if(row.settingValue=='1;2;3;4;5'){
                                    result = 'Insufficient;Excess;OverHeight;LowHeight;OverArea';
                                }else if(row.settingValue=='2;3;4;5;6'){
                                    result = 'Excess;OverHeight;LowHeight;OverArea;LowArea';
                                }else if(row.settingValue=='3;4;5;6;7'){
                                    result = 'OverHeight;LowHeight;OverArea;LowArea;ShiftX';
                                }else if(row.settingValue=='4;5;6;7;8'){
                                    result = 'LowHeight;OverArea;LowArea;ShiftX;ShiftY';
                                }else if(row.settingValue=='5;6;7;8;9'){
                                    result = 'OverArea;LowArea;ShiftX;ShiftY;Bridge';
                                }else if(row.settingValue=='6;7;8;9;10'){
                                    result = 'LowArea;ShiftX;ShiftY;Bridge;ShapeError';
                                }else if(row.settingValue=='7;8;9;10;11'){
                                    result = 'ShiftX;ShiftY;Bridge;ShapeError;Smeared';
                                }else if(row.settingValue=='8;9;10;11;12'){
                                    result = 'ShiftY;Bridge;ShapeError;Smeared;Coplanarity';
                                }else if(row.settingValue=='9;10;11;12;13'){
                                    result = 'Bridge;ShapeError;Smeared;Coplanarity;PreBridge';
                                }else if(row.settingValue=='10;11;12;13;14'){
                                    result = 'ShapeError;Smeared;Coplanarity;PreBridge;PadAreaError';
                                }else if(row.settingValue=='11;12;13;14;15'){
                                    result = 'Smeared;Coplanarity;PreBridge;PadAreaError;WarpError';
                                }
                                break;
                            }case 'hChartColor':{
                                result = hChartColorToStr(row.settingValue);
                                break;
                            }
                            case 'backgroundColor':{
                                result =backgroundColorToStr(row.settingValue);
                                break;
                            }case 'boardMachineTimeLimit':{
                                result ='[此设置停止使用]';
                                break;
                            }
                            default :{
                                result =row.settingValue;
                            }
                        }
                        return result;
                    }
                }, {
                    field: 'updateTime',
                    title:  'updateTime',
                    align:'center',
                    sortable: true,
                    width:300,
                }, {
                    field: 'remark',
                    title:  '说明',
                    align:'center',
                    sortable: true,
                    width:300,
                },{
                    field: 'oper',
                    title:  'op',
                    align:'center',
                    width:300,
                    events: operateEventsSettingLine,//给按钮注册事件
                    formatter: addFunctionAltyDefault//表格中增加按钮
                }
                ],
                formatLoadingMessage:function(){
                    return "请稍等,正在加载中.........";
                },
                onLoadSuccess: function (data,$element) {
                },
                onLoadError: function (status,res) {
                }
            });
        };
        function refreshDefaultTable(){
            var opt = {
                url:"${basePath}/sDefaultsetting/getDefaultJson",
                silent: true,
                query:{
                    type:1,
                    level:2
                }
            };
            $("#table_default").bootstrapTable('refresh', opt);
        }


    </script>

    <style>
        .form-control{
            text-align: center;
            text-align-last: center;
        }
        .option{
            text-align: center;
            text-align-last: center;
        }
    </style>
</body>
</html>

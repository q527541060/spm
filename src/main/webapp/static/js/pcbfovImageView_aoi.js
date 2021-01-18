// pcb图
const img = document.getElementById("svgImage")
function showPcbImage(basePath){
    var pcbPath= '//127.0.0.1/AOI_DB/WholeImage/BoardImage.jpg';
    $.ajax({
        url:basePath+"/aLine/getAoiImageByte",
        dataType:"json",
        async:true,
        data:{path:pcbPath},
        type:"GET",
        beforeSend:function(){
        },
        success:function(req){

           /*var img = $("<img class='svgImage' style='width: 100%;height:400px; object-fit: cover;' src='data:image/jpeg;base64,"+ req.data+"  '>")
                .addClass("img-thumbnail");*/
               // .appendTo("#pcbImage");
            var iSrc = 'data:image/jpeg;base64,'+ req.data;

            canvasPCBPart(iSrc);
            InitPcbTable(basePath);

        },
        error:function(req){
            alert(req);
        }
    });

}
function canvasPCBPart(srcImg) {
    var canvas = document.getElementById("pcbImage-canvas");
    var ctx = canvas.getContext("2d");
    var img = new Image();
    // 这里可以放 图片路径 "./test.jpg"  || base64图片 || 图片链接
    img.src = srcImg;
    //console.log(img, 'SRC', srcImg);
    img.onload = function () {
        //算出压缩比
        let xRate = canvas.width/img.width;
        let yRate = canvas.height/img.height;
        // 设置图片在canvas上 前面两个0,0是边距, 后面是宽高
        //'width: 100%;height:400px;
        ctx.drawImage(img, 0, 0,img.width*xRate,img.height*yRate);

        // 添加文字 后面两个数字是坐标
        ctx.font = "20px sans-serif"
        ctx.fillStyle = '#e22018'
        ctx.fillText("ArrayID_02", 135, 55);

        // 画矩形 前两个数字是坐标, 后面是矩形的宽高 fillRect是填充的
        ctx.strokeStyle = '#e22018'
        ctx.strokeRect(125, 10, 115, 119);

        /*ctx.strokeStyle = 'pink'
        ctx.strokeRect(240, 245, 248, 248);*/
    }
}
//fov
function showFovImage(basePath){
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
           InitFovTable(basePath);
        },
        error:function(req){
            alert(req);
        }

    });
}
function canvasFOVPart(srcImg) {
    var canvas = document.getElementById("fovImage-canvas");
    var ctx = canvas.getContext("2d");
    var img = new Image();
    // 这里可以放 图片路径 "./test.jpg"  || base64图片 || 图片链接
    img.src = srcImg;
    const path1 = new Path2D();
    const path2 = new Path2D();
    img.onload = function () {
        //算出压缩比
        let xRate = canvas.width/img.width;
        let yRate = canvas.height/img.height;
        // 设置图片在canvas上 前面两个0,0是边距, 后面是宽高
        //'width: 100%;height:400px;
        ctx.drawImage(img, 0, 0,img.width*xRate,img.height*yRate);
        // 添加文字 后面两个数字是坐标
        ctx.font = "18px sans-serif";
        ctx.fillStyle = '#e22018';
        ctx.fillText("F3_21", 370, 130);
        //画实例矩形
        ctx.strokeStyle = '#e22018';
        path1.rect(375, 135, 25,15);
        ctx.stroke(path1);
        ctx.closePath();

        //画对角线
        ctx.beginPath();
        //ctx.beginPath();
        path2.moveTo(375,135);
        path2.lineTo(400,150);
        path2.moveTo(375,150);
        path2.lineTo(400,135);
        ctx.stroke(path2);
        ctx.closePath();
        // 画矩形 前两个数字是坐标, 后面是矩形的宽高 fillRect是填充的
        //ctx.strokeStyle = '#e22018';
        //ctx.strokeRect(375, 135, 25, 15);

        //console.log(ctx.isPointInPath(path1,10,10));
        canvas.addEventListener('click', function (e) {
            var p = getEventPosition(e);
            if(ctx.isPointInPath(path1,p.x+28,p.y)){
                alert('F3_21-NG-50pcs');
            }else{
                //alert('我是矩形外面勒');
            };
        },false);
    };

}
function getEventPosition(ev){
    var x, y;
    if (ev.layerX || ev.layerX == 0) {
        x = ev.layerX;
        y = ev.layerY;
    } else if (ev.offsetX || ev.offsetX == 0) { // Opera
        x = ev.offsetX;
        y = ev.offsetY;
    }
    return {x: x, y: y};
}
//component
function showComponentImage(basePath,staticPath){
    var pcbPath= '//127.0.0.1/AOI_DB/20201104094650/Component/C4_ 1_130_92.jpg';
    var heigthPath='//127.0.0.1/AOI_DB/20201104094650/Component/C4_ 1_130_92.3ddata_BF';
    $.ajax({
        url:basePath+"/aLine/getAoiArray3DImage",
        dataType:"json",
        async:true,
        data:{path:pcbPath,heigthPath:heigthPath},
        type:"GET",
        beforeSend:function(){
        },
        success:function(req){
            $("<img  style='width: 100%;height:35vh;' src='data:image/jpeg;base64,"+
                req.data.base64Image+"  '>").addClass("img-thumbnail").appendTo("#2D-Component");
            InitComponentTable(basePath);
            show3DComponentImage(staticPath,req.data.base64Image,req.data.arrayHeightBy3D,req.data.dMaxHeight,req.data.dMinHeight);
        },
        error:function(req){
            alert(req);
        }
    });
}

//show 3D
function show3DComponentImage(staticPath,base64Image,arrayHeightBy3D,dMaxHeight,dMinHeight){
    staticPath = staticPath + '/echart/canyon.hdr';
    var com3DChart = echarts.init(document.getElementById('3D-Component'));
    com3DChart.clear();
    var img = new Image();
    var canvas = document.createElement('canvas');
    var ctx = canvas.getContext('2d');

    img.onload = function () {
        var width = canvas.width = img.width;
        var height = canvas.height = img.height;
        ctx.drawImage(img, 0, 0, width, height);
        var imgData = ctx.getImageData(0, 0, width, height);

        var data = [];
        var dArray=arrayHeightBy3D;
        for (var i = 0; i < imgData.data.length / 4; i++) {
            var r = imgData.data[i * 4];
            var g = imgData.data[i * 4 + 1];
            var b = imgData.data[i * 4 + 2];

           var lum = 255 - (0.2125 * r + 0.7154 * g + 0.0721 * b);
            lum = (lum - 125) / 10 + 50;
            data.push([i % width, height - Math.floor(i / width), dArray[i]]);
        }
        com3DChart.setOption(option = {
            tooltip: {},
            backgroundColor: '#fff',
            xAxis3D: {
                type: 'value'
            },
            yAxis3D: {
                type: 'value'
            },
            zAxis3D: {
                type: 'value',
                min: dMinHeight,
                max: dMaxHeight
            },
            grid3D: {
                environment :new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0, color: '#00aaff' // 天空颜色
                }, {
                    offset: 0.7, color: '#c2cdaa' // 地面颜色
                }, {
                    offset: 1, color: '#c2cdaa' // 地面颜色
                }], false),
                axisPointer: {
                    show: false
                },
                viewControl: {
                    distance: 100
                },
                postEffect: {
                    enable: true
                },
                light: {
                    main: {
                        shadow: true,
                        intensity: 2,

                    },
                    ambientCubemap: {
                       // texture: staticPath,
                        exposure: 2,
                        diffuseIntensity: 0.2,
                        specularIntensity: 1
                    }
                }
            },
            series: [{
                type: 'surface',
                silent: true,
                wireframe: {
                    show: false
                },
                itemStyle: {
                    color: function (params) {
                        var i = params.dataIndex;
                        var r = imgData.data[i * 4];
                        var g = imgData.data[i * 4 + 1];
                        var b = imgData.data[i * 4 + 2];
                        return 'rgb(' + [r, g, b].join(',') + ')';
                    }
                },
                data: data
            }]
        });
    }
    img.src='data:image/jpeg;base64,'+ base64Image+'';

}

function InitPcbTable (basePath) {
    //alert(lineNo);
    //修改表格placeholder
    var $Pcbtable = $('#pcbTable').bootstrapTable({
        url:basePath+"/sLine/lineDetailsLeftChart?lineNo=SMT01"+
            "&inspectStarttime=2020-01-01 00:00:00"+
            "&inspectEndtime=2020-12-01 00:00:00"+
            "&pcbResult="+'0,1,2,4'+                      //请求后台的URL（*）
            "&pcbType=1",
        dataType:"json",
        method: 'GET',                      //请求方式（*）
        //data:{lineNo:lineNo,inspectStarttime:inspectStarttime,inspectEndtime:inspectEndtime,pcbResult:pcbResult},
        toolbarAlign:'left',
        striped: true,                      //是否显示行间隔色
        cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortName:'inspectStarttime',
        sortOrder: "desc",                   //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [15, 20, 50, 100,'ALL'],        //可供选择的每页的行数（*）
        search: true,                      //是否显示表格搜索
        //data-search=true,
        strictSearch: false,
        showColumns: true,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        height: 350,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        //uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        exportDataType:'all',
        showExport: false,  //是否显示导出按钮
        showLoading:true,
        classes:'table table-striped table-hover table-condensed',
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
                sortOrder: params.order, //排位命令（desc，asc）
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
                    return {css:{"font-size":"9px"}}
                }
            }, {
                field: 'lineNo',
                title: 'lineNo',
                align:'center',
                width:10,
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
            },
            {
                field: 'boardBarcode',
                title:  'boardBarcode',
                align:'center',
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
                //  width:50
                //events:operateEvents,
            },  {
                field: 'inspectResult',
                title:  'pcbResult',
                align:'center',
                width:5,
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                },
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
                    return {css:{"font-size":"9px"}}
                }
                //  width:50
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
                    return {css:{"font-size":"9px"}}
                }
            },{
                field: 'ngpadCount',
                title:  'ngPadCount',
                align:'center',
                sortable: true,
                width:10,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
            },
            {
                field: 'laneNo',
                title:  'laneNo',
                align:'center',
                sortable: true,
                width:2,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
            },{
                field: 'inspectStarttime',
                title:  'inspectStarttime',
                align:'center',
                sortable: true,
                width:10,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
            },
            {
                field: 'inspectEndtime',
                title:  'inspectEndtime',
                align:'center',
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
                //   width:50
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
            $($element).addClass('danger');
        },
    });
};

function InitFovTable (basePath) {
    //alert(lineNo);
    //记录页面bootstrap-table全局变量$table，方便应用
    var fovTable = $('#fovTable').bootstrapTable({
        url:basePath+"/sLine/lineDetailsLeftChart?lineNo=SMT01"+
            "&inspectStarttime=2020-01-01 00:00:00"+
            "&inspectEndtime=2020-12-01 00:00:00"+
            "&pcbResult="+'0,1,2,4'+                      //请求后台的URL（*）
            "&pcbType=1",
        dataType:"json",
        method: 'GET',                      //请求方式（*）
        //data:{lineNo:lineNo,inspectStarttime:inspectStarttime,inspectEndtime:inspectEndtime,pcbResult:pcbResult},
        toolbar: '#pcbToolbar',              //工具按钮用哪个容器
        toolbarAlign:'left',
        buttonsAlign:'left',
        striped: true,                      //是否显示行间隔色
        cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortName:'inspectStarttime',
        sortOrder: "desc",                   //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 9,                     //每页的记录行数（*）
        pageList: [9, 20, 50, 100,'ALL'],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: false,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        height: 350,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        classes:'table table-striped table-hover table-condensed',
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
                field: 'id',
                title: 'FovIndex',
                // width:50,
                align:'center',
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
            }, {
                field: 'lineNo',
                title: 'lineNo',
                align:'center',
                width:10,
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
            },{
                field: 'inspectResult',
                title:  'componentCount',
                align:'center',
                width:5,
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                },
                formatter:function (value,row,index) {
                    switch (row.inspectResult) {
                        case '0':
                            return '10';
                        case '1':
                            return '5';
                        case '2':
                            return '1';
                        default :
                            return  '1';
                    }
                }
            },
            {
                field: 'boardBarcode',
                title:  'boardBarcode',
                align:'center',
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
                //  width:50
                //events:operateEvents,
            }, /* {
                field: 'inspectResult',
                title:  'pcbResult',
                align:'center',
                width:5,
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                },
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
            },*/ /*{
                field: 'ngpadCount',
                title:  'ngPadCount',
                align:'center',
                sortable: true,
                width:10,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
            },*/
            {
                field: 'laneNo',
                title:  'laneNo',
                align:'center',
                sortable: true,
                width:2,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
            },/*{
                field: 'inspectStarttime',
                title:  'inspectStarttime',
                align:'center',
                sortable: true,
                width:10,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
            },
            {
                field: 'inspectEndtime',
                title:  'inspectEndtime',
                align:'center',
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
                //   width:50
            },*/
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

function InitComponentTable (basePath) {
    //alert(lineNo);
    //记录页面bootstrap-table全局变量$table，方便应用
    var fovTable = $('#componentTable').bootstrapTable({
        url:basePath+"/sLine/lineDetailsLeftChart?lineNo=SMT01"+
            "&inspectStarttime=2020-01-01 00:00:00"+
            "&inspectEndtime=2020-12-01 00:00:00"+
            "&pcbResult="+'0,1,2,4'+                      //请求后台的URL（*）
            "&pcbType=1",
        dataType:"json",
        method: 'GET',                      //请求方式（*）
        //data:{lineNo:lineNo,inspectStarttime:inspectStarttime,inspectEndtime:inspectEndtime,pcbResult:pcbResult},
        toolbar: '#pcbToolbar',              //工具按钮用哪个容器
        toolbarAlign:'left',
        buttonsAlign:'left',
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
        classes:'table table-striped  table-hover table-condensed',
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
                field: 'id',
                title: 'PartNo',
                // width:50,
                align:'center',
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
            }, {
                field: 'lineNo',
                title: 'ComponentName',
                align:'center',
                width:10,
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
            },
            {
                field: 'boardBarcode',
                title:  'boardBarcode',
                align:'center',
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
                //  width:50
                //events:operateEvents,
            },  {
                field: 'inspectResult',
                title:  'pcbResult',
                align:'center',
                width:5,
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                },
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
                field: 'ngpadCount',
                title:  'ngPadCount',
                align:'center',
                sortable: true,
                width:10,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
            },
            {
                field: 'laneNo',
                title:  'laneNo',
                align:'center',
                sortable: true,
                width:2,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
            },{
                field: 'inspectStarttime',
                title:  'inspectStarttime',
                align:'center',
                sortable: true,
                width:10,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
            },
            {
                field: 'inspectEndtime',
                title:  'inspectEndtime',
                align:'center',
                sortable: true,
                cellStyle: function (value, row, index){
                    return {css:{"font-size":"9px"}}
                }
                //   width:50
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

function InitLineDataMainTable(basePath){
    var startTime = '2020-01-01 00:00:00';//$("#startTime").val().toString();
    var endTime = '2020-12-01 00:00:00';//$("#endTime").val().toString();
    $('#lineTable').bootstrapTable({
        url: basePath+"/sLine/pcbTableLine",//?inspectStarttime="+startTime+"&inspectEndtime="+endTime,                      //请求后台的URL（*）
        dataType:"json",
        method: 'GET',                      //请求方式（*）
        data:{inspectStarttime:startTime,inspectEndtime:endTime},
        toolbar: '#lineToolbar',              //工具按钮用哪个容器
        toolbarAlign:'right',
        striped: true,                      //是否显示行间隔色
        cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                   //是否显示分页（*）
        sortable: true,
        sortName:'Date',//是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [15, 20, 50, 100,'ALL'],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        showHeader:false,      //不显示表头
        strictSearch: false,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: false,                  //是否显示刷新按钮
        //showColumnsToggleAll:true,
        // minimumCountColumns: 0,             //最少允许的列数
        clickToSelect: false,                //是否启用点击选中行
        height: 700,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
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
                inspectStarttime:startTime,inspectEndtime:endTime
            };
        },
        columns: [
            {
                field: 'lineNo',
                title: 'lineNo',
                width: 200,
                align:'center',
                sortable: true
            }],
        onLoadSuccess: function (sta) {
        },
        onLoadError: function (status,res) {
            console.log(res);
            console.log(status);
        },
        onDblClickRow: function (row, $element) {

        },
        onClickRow:function(row,$element){
            $('.danger').removeClass('danger');
            $($element).addClass('danger');
        }
    });
}



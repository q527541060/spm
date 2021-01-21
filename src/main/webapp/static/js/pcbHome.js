//刷新Monitoring
function homeTimeInterver(basePath){
    $.ajax({
        url: basePath+"/Home/homeTimeInterver",
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
//show 3D
function showPreAoi3DComponentImage(staticPath,base64Image,arrayHeightBy3D,dMaxHeight,dMinHeight){
    staticPath = staticPath + '/echart/canyon.hdr';
    var com3DChart = echarts.init(document.getElementById('preAoi-3D-Component'));
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
        if(dArray==null){
            dMinHeight=0;
            dMaxHeight=0;
        }else{
            dMinHeight = Math.min.apply(null,arrayHeightBy3D);
            dMaxHeight = Math.max.apply(null,arrayHeightBy3D);
            dMinHeight = dMinHeight - dMaxHeight*6;
            dMaxHeight = dMaxHeight+ dMaxHeight*6;
        }

        for (var i = 0; i < imgData.data.length / 4; i++) {
            var r = imgData.data[i * 4];
            var g = imgData.data[i * 4 + 1];
            var b = imgData.data[i * 4 + 2];

            var lum = 255 - (0.2125 * r + 0.7154 * g + 0.0721 * b);
            lum = (lum - 125) / 10 + 50;
            if(dArray==null){
                if(dMinHeight>lum){
                    dMinHeight=lum;
                }
                if(dMaxHeight<lum){
                    dMaxHeight=lum;
                }
                data.push([i % width, height - Math.floor(i / width), lum]);
            }else{
                data.push([i % width, height - Math.floor(i / width), dArray[i]]);
            }
        }
        if(dArray==null){
            dMinHeight= dMinHeight-dMaxHeight*3;
            dMaxHeight= dMaxHeight+dMaxHeight*3;
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
                    //alpha:60,
                    minAlpha:-12000,
                    maxAlpha:12000,
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
function showPostAoi3DComponentImage(staticPath,base64Image,arrayHeightBy3D,dMaxHeight,dMinHeight){
    staticPath = staticPath + '/echart/canyon.hdr';
    var com3DChart = echarts.init(document.getElementById('postAoi-3D-Component'));
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
        if(dArray==null){
            dMinHeight=0;
            dMaxHeight=0;
        }else{
            dMinHeight = Math.min.apply(null,arrayHeightBy3D);
            dMaxHeight = Math.max.apply(null,arrayHeightBy3D);
            dMinHeight = dMinHeight - dMaxHeight*6;
            dMaxHeight = dMaxHeight+ dMaxHeight*6;
        }

        for (var i = 0; i < imgData.data.length / 4; i++) {
            var r = imgData.data[i * 4];
            var g = imgData.data[i * 4 + 1];
            var b = imgData.data[i * 4 + 2];

            var lum = 255 - (0.2125 * r + 0.7154 * g + 0.0721 * b);
            lum = (lum - 125) / 10 + 50;
            if(dArray==null){
                if(dMinHeight>lum){
                    dMinHeight=lum;
                }
                if(dMaxHeight<lum){
                    dMaxHeight=lum;
                }
                data.push([i % width, height - Math.floor(i / width), lum]);
            }else{
                data.push([i % width, height - Math.floor(i / width), dArray[i]]);
            }
        }
        if(dArray==null){
            dMinHeight= dMinHeight-dMaxHeight*3;
            dMaxHeight= dMaxHeight+dMaxHeight*3;
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
                    //alpha:60,
                    minAlpha:-12000,
                    maxAlpha:12000,
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
function showSpi3DComponentImage(staticPath,base64Image){
    staticPath = staticPath + '/echart/canyon.hdr';
    var com3DChart = echarts.init(document.getElementById('spi-3D-Component'));
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
       // var dArray=arrayHeightBy3D;
        var minHeight = 0;
        var maxHeight = 0;
        for (var i = 0; i < imgData.data.length / 4; i++) {
            var r = imgData.data[i * 4];
            var g = imgData.data[i * 4 + 1];
            var b = imgData.data[i * 4 + 2];
            var lum = 355 - (0.2125 * r + 0.7154 * g + 0.0721 * b);
            lum = (lum - 125) / 10 + 50 ;
            if(minHeight>lum){
                minHeight=lum;
            }
            if(maxHeight<lum){
                maxHeight=lum;
            }
            data.push([i % width, height - Math.floor(i / width), lum]);//dArray[i]
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
                min: minHeight-maxHeight*3,
                max: maxHeight+maxHeight*3
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
                    minAlpha:-12000,
                    maxAlpha:12000,
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
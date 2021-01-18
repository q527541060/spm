String.prototype.myReplace=function(f,e){//吧f替换成e
    var reg=new RegExp(f,"g"); //创建正则RegExp对象
    return this.replace(reg,e);
}

function arrpcbResultToStandResult(arrResult){
    var result ='';
    switch (arrResult) {
        case 'NG':
            result = 'NG';
            break;
        case  'Pass':
            result = 'REPASS';
            break;
        case 'Good':
            result = 'PASS';
            break;
        default :
            break;
    }
    return result;
}

function arrpcbResultToStandResult_WITH_AOI(arrResult){
    var result ='';
    switch (arrResult) {
        case '1':
        case 1:
            result = 'NG';
            break;
        case  '2':
        case  2:
            result = 'REPASS';
            break;
        case '0':
        case 0:
            result = 'PASS';
            break;
        default :
            break;
    }
    return result;
}
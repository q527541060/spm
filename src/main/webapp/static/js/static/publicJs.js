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

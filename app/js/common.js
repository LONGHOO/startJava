//服务器地址
var baseUrl = "http://localhost:8077";
jQuery.ajaxSettings.traditional = true;
//为所有AJAX请求设置默认属性
$.ajaxSetup({crossDomain: true, xhrFields: {withCredentials: true}});

//获取url上的请求参数
function getParams() {
    //获取问号及问号后面的内容
    var url = window.location.search;
    var params = new Object();
    if (url.indexOf("?") != -1) {
        //截取问号后面的内容,再使用&分割多个属性
        var arr = url.substr(1).split("&");
        for (var i = 0; i < arr.length; i++) {
            //使用=分割为keyvalue
            var keyValue = arr[i].split("=");
            params[keyValue[0]] = keyValue[1];
        }
    }
    return params;
}

function getHref(item,value) {
    var url = $(item).data("href");
    $(item).attr('href',url+value);
}

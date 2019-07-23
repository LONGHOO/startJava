jQuery.ajaxSettings.traditional = true;
resultHandle = new Object({
    saveHandle:function(response,url,msg){
        resultAlert(response,url,msg === undefined ? "保存成功！":msg);
    },
    deleteHandle:function (response,url,msg) {
        resultAlert(response,url,"删除成功！",msg === undefined ? "保存成功！":msg);
    }
})

function resultAlert(response,url,message){
    if(response.success){
        $.messager.alert("提示信息",message);
        setTimeout(function(){
            if(url){
                window.location.href = url;
                return ;
            }
            window.location.reload();

        },2000);
    }else{
        $.messager.alert("提示信息",response.errorMsg);
    }
}

$(function () {

    $(".btn-delete").click(function () {
        var url = $(this).data("url");
        $.messager.confirm("操作提醒","您确定要删除当前记录？",function () {
            $.get(url,function(response){
                if(response.success){
                    $.messager.alert("提示信息","删除成功");
                    setTimeout(function(){
                        window.location.reload();
                    },2000);
                }else{
                    $.messager.alert("提示信息",response.errorMsg);
                }
            })
        })
    })

    $(function(){
        $(".btn_redirect").click(function () {
            var url = $(this).data("url");
            window.location.href = url;
        })
    })
})

//
// (function($) {
//     $.fn.sayHello = function () {
//         alert('test')
//     }
// })(jQuery);

$(function () {
    var params = getParams();
    var currentPage = 1;
    var totalPage = 1;
    var pageData = [];
    //查询当前的攻略信息
    $.get(baseUrl + "/strategys/" + params.id, function (response) {
        $(".strategies").renderValues(response.data);
    })
    var index = 1;
    //查询当前大攻略的攻略目录
    $.get(baseUrl + "/strategys/" + params.id + "/catalogs", function (response) {
        $(".catalogs").renderValues(response,{
            getHref:function(node,value){
                var url = $(node).data("href");
                $(node).attr("href", url + "?id=" + value);
            },
            formartIndex:function(node,value){
                if(index < 10){
                    $(node).html("0"+index+"/"+value);
                }else{
                    $(node).html(index+"/"+value);
                }
                index ++;
            }
        });
    })
    //驴友点评tab被点击的时候
    $("#pills-comment-tab").click(function () {
        //获取当前热门的标签
        $.get(baseUrl+"/tags/hotTags",function (response) {

            var tags = "";
            $.each(response.data,function (index,value) {
                tags +="<span>"+value.name+"</span>";
            })
            $(".tag div").html(tags);
        })
        query();
    })

    //点评按钮事件
    $("#commentBtn").click(function () {
        window.location.href = "/mine/addComment.html?id="+params.id;
    })

    //防止在请求未响应的时候重复提交请求，做个标记，true：表示当前请求为作响应
    var loading = false;
    var query = function(){
        //如果当前正在作请求操作，不能发起新的请求
        if(loading){
            return ;
        }
        //发送请求之前将状态设为true，不能重复发送请求
        loading = true;
        //获取用户的评论信息
        $.get(baseUrl+"/strategyComments/hotStrategyComments",
            {currentPage:currentPage,pageSize:6,strategyId:params.id},function (response) {
                $.merge(pageData,response.list);
                $("#comment").renderValues({list:pageData},{
                    getImages:function(node,value){
                        var imgs = "";
                        $.each(value,function (index, data) {
                            imgs += "<li><img src='"+data+"' class='clickImg'/></li>";
                        })
                        $(node).html(imgs);
                    },
                    getStar:function (node,value) {
                        var starts = $("#stars").clone(true);
                        for(var i = 5;i>value;i--){
                            $(starts).find("i:eq("+(i-1)+")").removeClass("fa-star").addClass("fa-star-o");
                        }
                        $(node).html($(starts).html());
                    }
                });
                //响应结束后允许发送新的请求
                loading = false;
                currentPage ++;
                totalPage = response.pages;
                $(".clickImg").click(function () {
                   var src = $(this).attr("src");
                    $("#modalImg").attr("src", src);
                   $("#imageModal").modal('show');
                })
            })
    }

    //添加屏幕滚动监听器
    $(window).scroll(function(){
        //如果当前屏幕距离屏幕底部小于50px时，发送请求
        if($(document).scrollTop()+$(window).height() >= $(document).height()-50){
            if(currentPage <= totalPage && params.id){
                query();
            }else if(currentPage === (totalPage +1)){
                currentPage ++;
                $(document).dialog({
                    content: "已经触发到底线了"
                });
            }

        }
    })
})
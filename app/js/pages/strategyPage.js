$(function () {
    var regionId;
    var currentPage = 2;
    var totalPage = 2;
    //获取推荐地区列表
    $.get(baseUrl+"/ragions/state/1",function (response) {
        if(response.success){
            var regins = "";
            var content = "";
            $.each(response.data,function(index,value){
                regins += 
                    "<li class='nav-item' data-id='"+value.id+"'><a class='nav-link' data-toggle='pill' href='#pills-"+value.id+"'>"+value.name+"</a></li>"
                content += "<div class='tab-pane fade' id='pills-"+value.id+"'></div>"
            })
            $("#pills-tab").append(regins);
            $("#pills-tabContent").append(content);
            //为推荐区域添加点击事件
            $(".nav-item[data-id]").click(function () {
                currentPage = 2;
                totalPage = 2;
                regionId = $(this).data("id");
                //获取对应地区的内容
                $.get(baseUrl+"/strategys/hot/"+regionId,function (response) {
                    $("#pills-" + regionId).html(response);
                },'html')
            })
        }
    })
    //查询推荐最新的一条
    $.get(baseUrl+"/travelCommends/type/2",{currentPage:1,pageSize:1},function (response){
        if(response.success){
            $(".commend").renderValues(response.data,{
                getHref:function(node,value){
                    var href = $(node).data("href");
                    $(node).attr("href",href+"?id="+value);
                }
            });
        }
    })
    //查询推荐列表下所有的当季推荐
    $.get(baseUrl+"/strategys/state/1",function (response){
        $(".strategyCommend").renderValues(response,{
            getHref:function(node,value){
                var href = $(node).data("href");
                $(node).attr("href",href+"?id="+value);
            }
        });
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
        $.get(baseUrl+"/strategys/hot/"+regionId,{currentPage:currentPage},function(response){
            $.each(response.list,function(index,value){
                var node = $('#template').find(".mb").clone(true);
                $(node).find("img").attr("src",value.coverUrl);
                $(node).find("span").html(value.title);
                $("#pills-"+regionId+" .classify").append(node);
            })
            //响应结束后允许发送新的请求
            loading = false;
            currentPage ++;
            totalPage = response.pages;
        },'json')
    }
    //添加屏幕滚动监听器
    $(window).scroll(function(){
        //如果当前屏幕距离屏幕底部小于50px时，发送请求
        if($(document).scrollTop()+$(window).height() >= $(document).height()-50){
            if(currentPage <= totalPage & regionId){
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
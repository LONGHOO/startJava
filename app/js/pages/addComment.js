$(function () {
    var params = getParams();
    //评分
    var starNum = 0;

    //获取当前热门的标签
    $.get(baseUrl + "/tags/hotTags", function (response) {

        var tags = "";
        $.each(response.data, function (index, value) {
            tags += "<label class='btn btn-outline-danger '>" +
                "<input type='checkbox' autocomplete='off' value='" + value.name + "'> " + value.name + "</label>";
        })
        $("#tags").html(tags);
    })
    //评分星星添加点击事件
    var starts = "";
    for (var i = 1; i <= 5; i++) {
        var start = "<i class='fa fa-star-o fa-lg clickStart' data-num='" + i + "'></i>";
        starts += start;
    }
    $(".star").append(starts);
    //为每一课星星添加点击事件
    $(".clickStart").click(function () {
        starNum = $(this).data("num");
        $(".comment .star i").removeClass("fa-star").addClass("fa-star-o");
        for (var i = 0; i < starNum; i++) {
            $(".comment .star i:eq(" + i + ")").removeClass("fa-star-o").addClass("fa-star");
        }
    })
    var currentRect;
    //上传图片点击事件
    $(".row .rect").click(function () {
        currentRect = $(this);
        $("#uploadFile").click();
    })
    //上传图片空间的变化监听器
    $("#uploadFile").change(function () {
        if ($(this).val()) {
            //上传图片到服务器
            $("#uploadForm").ajaxSubmit({
                url:baseUrl+"/image/upload",
                success:function(response) {
                    if (response.status == 1) {
                        currentRect.html("<img src='" + response.url + "'/>");
                    }
                }
            })
        }
    })

    //提交评价功能
    $("#submitBtn").click(function () {
        if(starNum < 1){
            alert("请先评分");
            return ;
        }
        //获取被选中的星星内容
        var checkedTags = [];
        $.each($("#tags label.active"),function (index, data) {
            checkedTags.push($(data).find("input").val());
        })
        //获取自己的标签
        var mytagVal = $("input[name=myTags]").val();
        $.each(mytagVal.split(" "),function (index, data) {
            if(data){
                checkedTags.push(data);
            }
        })
        var myComment = $("#myComment").val();
        var images = "";
        $.each($(".row .rect img"),function (index, data) {
            images += $(data).attr("src")+";";
        })
        console.log(images);
       var data = {
            starNum:starNum,
            tags:checkedTags,
            content:myComment,
            imgUrls:images

        }
        $.post(baseUrl+"/strategyComments/"+params.id,data,function (response) {
            if(response.success){
                window.location.href = "../strategyCatalogs.html";
            }
        })
    })
})
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#include "../common/header.ftl"/>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
            <#assign menu="role"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">角色编辑</h1>
                </div>
            </div>
            <div class="row col-sm-10">
                <form class="form-horizontal" action="/role/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${(entity.id)!}" name="id">
                    <div class="form-group" >
                        <label class="col-sm-2 control-label">角色名称：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" value="${(entity.name)!}" placeholder="请输入角色名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色编码：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="sn" value="${(entity.sn)!}" placeholder="请输入角色编码">
                        </div>
                    </div>

                    <div class="form-group" id="role">
                        <div>
                            <label class="control-label" style="margin-left: 60px">权限：</label>
                        </div>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-4 col-sm-offset-1">
                                <select multiple class="form-control allPermissions" size="15">
                                    <#--系统中拥有的所有权限-->
                                    <#list permission as p>
                                        <option value="${p.id}">${p.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="col-sm-2" style="margin-top: 60px;" align="center">
                                <div >
                                    <a type="button" class="btn btn-info  "  style="margin-top: 10px"
                                       onclick="moveSelected('allPermissions', 'selfPermissions')">&nbsp;&gt;&nbsp;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveSelected('selfPermissions', 'allPermissions')">&nbsp;&lt;&nbsp;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveAll('allPermissions', 'selfPermissions')">&gt;&gt;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveAll('selfPermissions', 'allPermissions')">&lt;&lt;</a>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <select multiple class="form-control selfPermissions" size="15" name="ids">
                                    <#--回显当前角色拥有的权限-->
                                    <#if entity??>
                                        <#list entity.permissions as p>
                                            <option value="${p.id}">${p.name}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="btn_submit" type="button" class="btn btn-success">
                                <span class="glyphicon glyphicon-saved"></span> 保存
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    $(function(){
        $("#btn_submit").click(function () {
            //自动选右边所有option
            $(".selfPermissions option").prop("selected",true);
            $("#editForm").ajaxSubmit(function (data) {
                if (data.success){//success在JSONResult工具类里默认操作成功
                    $.messager.alert("MMP","已经做好了.");
                    setTimeout(function () {
                        //成功后刷新页面
                        window.location.href = "/role/list.do";
                    },1000)
                }else{
                    $.messager.alert("TMD",data.msg);
                }
            })
        })
    });
    //列表移动功能---角色权限信息左移右移
    function moveSelected(src,target){
        $("."+src+" option:selected").appendTo("."+target);
    }
    function moveAll(src,target){
        $("."+src+" option").appendTo("."+target);
    }
    //选项去重
    $(".selfPermissions option").val(function(i,ele){
        $(".allPermissions option[value='"+ele+"']").remove();
        return ele;
    });
</script>
</body>
</html>
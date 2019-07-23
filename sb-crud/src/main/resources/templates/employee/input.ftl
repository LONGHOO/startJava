<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#include "../common/header.ftl"/>
</head>
<script>
    $(function () {
        $("#editForm").validate({
            rules:{
                name:{
                    required:true,
                    minlength:2
                },
                password:{
                    required:true,
                    minlength:2
                },
                repwd:{
                    required:true,
                    equalTo:"#pwd"
                },
                email:{
                    required: true,
                    email: true
                },
                age:{
                    required: true,
                    digits:true,
                    min:1
                }
            },
            messages: {
                name: {
                    required: "用户名必填",
                    minlength: "最小长度为2"
                },
                password: {
                    required: "密码必填",
                    minlength: "最小长度为2"
                },
                repwd:{
                    required:"验证密码必填",
                    equalTo:"密码不一致"
                },
                email:{
                    required:"邮箱必填",
                    email:"格式不正确"
                },
                age:{
                    required: "年龄必填",
                    digits:"必须是整数",
                    min:"年龄最小是1"
                }
            },
            submitHandler:function (form) {
                $(form).ajaxSubmit(function (data) {
                    if (data.success){//success在JSONResult工具类里默认操作成功
                        $.messager.alert("MMP","已经做好了.");
                        setTimeout(function () {
                            //成功后刷新页面
                            window.location.href = "/employee/list.do";
                        },1000)
                    }else{
                        $.messager.alert("TMD",data.msg);
                    }
                })
            }
        })
    })
</script>
<body>
<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
            <#assign menu="employee"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">员工编辑</h1>
                </div>
            </div>
            <div class="row col-sm-10">
                <form class="form-horizontal" action="/employee/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${(entity.id)!}" name="id">
                    <div class="form-group" >
                        <label class="col-sm-2 control-label">用户名：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" id="name" name="name" value="${(entity.name)!}" placeholder="请输入用户名">
                        </div>
                    </div>

                    <#if !entity??>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">密码：</label>
                            <div class="col-sm-6">
                            <input type="password" class="form-control" id="pwd"  name="password" value="${(entity.password)!}" placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">验证密码：</label>
                            <div class="col-sm-6">
                            <input type="password" class="form-control" name="repwd" value="${(entity.password)!}" placeholder="再输入一遍密码">
                            </div>
                        </div>
                    </#if>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">邮箱：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="email" value="${(entity.email)!}" placeholder="请输入邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">年龄：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="age" value="${(entity.age)!}" placeholder="请输入年龄">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门：</label>
                        <div class="col-sm-6">
                        <select class="form-control" name="dept.id">
                            <#list depts as d>
                                <option value="${d.id}" >${d.name}</option>
                            </#list>
                        </select>
                        </div>
                        <script>
                        	//员工信息保存后,部门回显在页面
                            $("#editForm select[name='dept.id']").val(${(entity.dept.id)!})
                        </script>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">超级管理员：</label>
                        <label class="checkbox-inline" style="margin-left: 15px;">
                            <input type="checkbox" id="admin" name="admin">
                        </label>
                    </div>
                    <div class="form-group" id="role">
                        <div>
                            <label class="control-label" style="margin-left: 60px">角色：</label>
                        </div>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-4 col-sm-offset-1">
                                <#--显示系统中所有的角色-->
                                <select multiple class="form-control allRoles" size="15">
                                    <#list roles as r>
                                        <option value="${r.id}">${r.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="col-sm-2" style="margin-top: 60px;" align="center">
                                <div >
                                    <a type="button" class="btn btn-info  "  style="margin-top: 10px"
                                       onclick="moveSelected('allRoles', 'selfRoles')">&nbsp;&gt;&nbsp;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveSelected('selfRoles', 'allRoles')">&nbsp;&lt;&nbsp;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveAll('allRoles', 'selfRoles')">&gt;&gt;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveAll('selfRoles', 'allRoles')">&lt;&lt;</a>
                                </div>
                            </div>

                            <div class="col-sm-4">
                                <select multiple class="form-control selfRoles" size="15" name="ids">
                                    <#--显示当前用户拥有的角色-->
                                    <#if entity??><#--判断是否有值-->
                                        <#list entity.roles as r>
                                            <option value="${r.id}">${r.name}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="btn_submit" type="submit" class="btn btn-success">
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
    var roles;
    $(function () {
        //表单提交点击事件
        $("#btn_submit").click(function () {
            //自动选右边所有option
            $(".selfRoles option").prop("selected",true);
            //$("#editForm").submit();
            /*$("#editForm").ajaxSubmit(function (data) {
                if (data.success){//success在JSONResult工具类里默认操作成功
                    $.messager.alert("MMP","已经做好了.");
                    setTimeout(function () {
                        //成功后刷新页面
                        window.location.href = "/employee/list.do";
                    },1000)
                }else{
                    $.messager.alert("TMD",data.msg);
                }
            })*/
        })
        //超管的勾选和还原处理
        $("#admin").click(function () {
            if(this.checked){
                //角色信息的表单id是role,如果勾选了超管,去掉角色信息,示有所有权限
                roles = $("#role").remove();
            }else{
                //否则还原角色信息
                $(this).closest("div").after(roles);
            }
        })
    });
    //14:列表移动功能---角色信息左移右移
    function moveSelected(src,target){
        $("."+src+" option:selected").appendTo("."+target);
    }
    function moveAll(src,target){
        $("."+src+" option").appendTo("."+target);
    }
    //前面只是有做到勾选超管和还原,这是回显超管
    <#if entity?? && entity.admin>
        $("#admin").prop("checked",true);
        roles = $("#role").remove();
    </#if>
    //选项去重
    $(".selfRoles option").val(function(i,ele){
        $(".allRoles option[value='"+ele+"']").remove();
        return ele;
    });
</script>
</body>
</html>
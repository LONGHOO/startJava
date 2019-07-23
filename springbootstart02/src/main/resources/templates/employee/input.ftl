<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>

    <#include "../common/link.ftl">
    <style>
        .form-control+label{
            color:red;
        }
    </style>
    <script>
        $(function () {

            var empId = $("input[name=id]").val();
            if(empId){
                $("#name").attr("readonly",true);

            }else{
                $("#name").attr("readonly",false);
            }
            //表单验证
            $("#editForm").validate({
                rules:{
                    name:{
                        required:true,
                        remote:{
                            url:"/employee/checkName.do",
                            data:{
                                name:function(){
                                    return $("#name").val();
                                }
                            },
                            dataFilter:function (data) {
                                var result = JSON.parse(data);
                                if(empId){
                                    return true;
                                }
                                return result.success;
                            }
                        }
                    },
                    password:{
                        required:true,
                        minlength:6
                    },
                    repassword:{
                        required:true,
                        equalTo:"#password"
                    },
                    email:{
                        required:true,
                        email:true
                    },
                    age:{
                        required:true,
                        min:0,
                        max:120
                    }

                },
                messages:{
                    name:{
                        required: "用户名不能为空",
                        remote:"用户名已存在"
                    },
                    password:{
                        required:"密码不能为空",
                        minlength:"密码长度不能少于6位数"
                    },
                    repassword:{
                        required:"确认密码不能为空",
                        equalTo:"两次密码不一致"
                    },
                    email:{
                        required:"邮箱不能为空",
                        email:"请输入正确的邮箱格式"
                    },
                    age:{
                        required:"年龄不能为空",
                        min:"年龄不能为负数",
                        max:"请输入正确的年龄"
                    }

                },
                submitHandler:function(){
                    $(".selfRoles option").prop("selected", true);
                    $("#editForm").ajaxSubmit(function (response) {
                        resultHandle.saveHandle(response,"/employee/list.do")
                    })
                },
                errorClass: "invalid"
            })


            // $(".btn-save").click(function () {
            //
            // })

            //排重
            var ids = $.map($(".selfRoles option"), function (item) {
                return item.value;
            })

            $.each($(".allRoles option"), function (index, item) {
                if ($.inArray(item.value, ids) >= 0) {
                    $(item).remove();
                }
            });



            // //提交表单
            // $("#submitBtn").click(function () {
            //
            //     $("#editForm").submit();
            // })
            var role = "";
            //超级管理员
            $("#admin").change(function () {
                if (this.checked) {
                    role = $("#role").detach();
                } else {
                    $(this).closest(".form-group").after(role);
                }
            })
            //编辑时候， 根据是否是超级管理员决定是否显示下拉框
            <#if (employee.admin)?? &&  employee.admin>
            role = $("#role").detach();
            </#if>
        })

        //移动选中
        function moveSelected(srcCls, targetCls) {
            $("." + srcCls + " option:selected").appendTo($("." + targetCls));
        }

        //移动全部
        function moveAll(srcCls, targetCls) {
            $("." + srcCls + " option").appendTo($("." + targetCls))
        }
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="employee">
    <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/employee/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${(employee.id)!''}" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" value="${(employee.name)!''}"
                                   placeholder="请输入用户名">
                        </div>
                    </div>
                    <#if !employee??>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码：</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="repassword" class="col-sm-2 control-label">验证密码：</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="repassword" name="repassword"
                                       placeholder="再输入一遍密码">
                            </div>
                        </div>
                    </#if>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">电子邮箱：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="email" name="email"
                                   value="${(employee.email)!''}" placeholder="请输入邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="age" name="age" value="${(employee.age)!''}"
                                   placeholder="请输入年龄">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept" class="col-sm-2 control-label">部门：</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="dept" name="dept.id">

                                <#list depts as d>
                                    <#if (employee.dept.id)?? && d.id == employee.dept.id>
                                        <option value="${d.id}" selected>${d.name}</option>
                                    <#else>
                                        <option value="${d.id}">${d.name}</option>
                                    </#if>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="admin" class="col-sm-2 control-label">超级管理员：</label>
                        <div class="col-sm-6" style="margin-left: 15px;">
                            <input type="checkbox" id="admin" name="admin" class="checkbox">
                            <#if employee?? && employee.admin >
                                <script>
                                    $("#admin").prop("checked", true);
                                </script>
                            </#if>
                        </div>
                    </div>
                    <div class="form-group " id="role">
                        <label for="role" class="col-sm-2 control-label">分配角色：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allRoles" size="15">
                                    <#list roles as r>
                                        <option value="${r.id}">${r.name}</option>
                                    </#list>
                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary  " style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <select multiple class="form-control selfRoles" size="15" name="ids">
                                    <#if (employee.roles)??>
                                        <#list employee.roles as r>
                                            <option value="${r.id}">${r.name}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button type="submit" class="btn btn-primary btn-save">保存</button>
                            <button type="reset" class="btn btn-danger">重置</button>
                        </div>
                    </div>

                </form>

            </div>
        </section>
    </div>
    <#include "../common/footer.ftl">
</div>

</body>
</html>

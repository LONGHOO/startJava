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
            <#assign menu="department"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">部门编辑</h1>
                </div>
            </div>
            <div class="row col-sm-10">
                <form class="form-horizontal" action="/department/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" name="id" value="${(entity.id)!}">
                    <div class="form-group" >
                        <label class="col-sm-2 control-label">部门名称：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" value="${(entity.name)!}"
                               placeholder="请输入部门的名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门编号：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="sn" value="${(entity.sn)!}"
                               placeholder="请输入部门编号">
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
</body>
</html>
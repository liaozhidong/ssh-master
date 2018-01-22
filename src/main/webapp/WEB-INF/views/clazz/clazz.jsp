<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>班级管理</title>
    <%@include file="../master/header.jsp" %>
</head>
<body>
<!-- 分页列表-->
<table id="list" class="easyui-datagrid"
       data-options="
			toolbar:'#tb',
			rownumbers:true,
			border:false,
			singleSelect:true,
			pagination:true,
			pageSize:10,
			url:'<%=path %>/clazz/pager_criteria',
			method:'post'">
    <thead>
    <tr>
        <th data-options="field:'id',width:80,checkbox:true">编号</th>
        <th data-options="field:'title',width:100">名称</th>
    </tr>
    </thead>
</table>
<!-- 实现多条件的模糊查询-->
<div id="tb" style="height: auto">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" onclick="openWin('addWin');">添加</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEditWin('editWin', 'list', 'editForm', 'clazz')">修改</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" onclick="del();">删除</a>
    <div>
        <form id="searchForm">
            <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入班级名称',
						required:false,
						novalidate:true" name="clazz.title"/>
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch('list', 'searchForm');">搜索</a>
        </form>
    </div>
</div>
<!-- 新增班级-->
<div id="addWin" class="easyui-window normal_win" data-options="title:'添加班级', closed:true">
    <form id="addForm">
        <table>
            <tr>
                <td>名称</td>
                <td>
                    <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入名称',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="clazz.title"/>
                </td>
            </tr>
            <tr>
                <td><a class="easyui-linkbutton" onclick="closeWin('addWin')">取消</a></td>
                <td><a class="easyui-linkbutton" onclick="save('/clazz/save', 'addForm', 'addWin', 'list');">确认</a></td>
            </tr>
        </table>
    </form>
</div>
<!-- 修改班级-->
<div id="editWin" class="easyui-window normal_win" data-options="title:'编辑班级', closed:true">
    <form id="editForm">
        <input type="hidden" name="clazz.id" />
        <table>
            <tr>
                <td>名称</td>
                <td>
                    <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入名称',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="clazz.title"/>
                </td>
            </tr>
            <tr>
                <td><a class="easyui-linkbutton" onclick="closeWin('editWin')">取消</a></td>
                <td><a class="easyui-linkbutton" onclick="edit('/clazz/update', 'editForm', 'editWin', 'list');">确认</a></td>
            </tr>
        </table>
    </form>
</div>

</body>
<%@include file="../master/footer.jsp" %>
<script>
    //删除数据
    function del(){
        var row=$('#list').datagrid('getSelected');
        if(row){
            $.messager.confirm("系统提示","您确定要删除这条记录吗?",function(r){
                if(r){
                    $.post(contextPath+'/clazz/remove?clazz.id='+row.id,function(result){
                        if(result.result==="success"){
                            $.messager.alert("系统提示","删除成功!");
                            $("#list").datagrid("reload");
                        }else{
                            $.messager.alert("系统提示",result.message);
                        }
                    },'json');
                }
            });
        }
    }
</script>
</html>
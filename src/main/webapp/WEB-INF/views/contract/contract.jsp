<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>联系人管理</title>
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
			url:'<%=path %>/contract/pager_criteria',
			method:'post'">
    <thead>
    <tr>
        <th data-options="field:'id',width:80,checkbox:true">编号</th>
        <th data-options="field:'realName',width:100">姓名</th>
        <th data-options="field:'phone',width:100">手机号</th>
    </tr>
    </thead>
</table>
<!-- 实现多条件的模糊查询-->
<div id="tb" style="height: auto">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" onclick="openWin('addWin');">添加</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEditWin('editWin', 'list', 'editForm', 'contract')">修改</a>
    <div>
        <form id="searchForm">
            <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入姓名',
						required:false,
						novalidate:true" name="contract.realName"/>
            <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入手机号码',
						required:false,
						novalidate:true" name="contract.phone"/>
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch('list', 'searchForm');">搜索</a>
        </form>
    </div>
</div>
<!--新增联系人 -->
<div id="addWin" class="easyui-window normal_win" data-options="title:'添加联系人', closed:true">
    <form id="addForm">
        <table>
            <tr>
                <td>姓名</td>
                <td>
                    <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入名称',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="contract.realName"/>
                </td>
            </tr>
            <tr>
                <td>手机号</td>
                <td>
                    <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入手机号码',
						required:true,
						validType:['length[11,20]'],
						novalidate:true" name="contract.phone"/>
                </td>
            </tr>
            <tr>
                <td><a class="easyui-linkbutton" onclick="closeWin('addWin')">取消</a></td>
                <td><a class="easyui-linkbutton" onclick="save('/contract/save', 'addForm', 'addWin', 'list');">确认</a></td>
            </tr>
        </table>
    </form>
</div>
<!--修改联系人 -->
<div id="editWin" class="easyui-window normal_win" data-options="title:'编辑联系人', closed:true">
    <form id="editForm">
        <input type="hidden" name="contract.id" />
        <table>
            <tr>
                <td>姓名</td>
                <td>
                    <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入名称',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="contract.realName"/>
                </td>
            </tr>
            <tr>
                <td>手机号</td>
                <td>
                    <input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入手机号码',
						required:true,
						validType:['length[11,20]'],
						novalidate:true" name="contract.phone"/>
                </td>
            </tr>
            <tr>
                <td><a class="easyui-linkbutton" onclick="closeWin('editWin')">取消</a></td>
                <td><a class="easyui-linkbutton" onclick="edit('/contract/update', 'editForm', 'editWin', 'list');">确认</a></td>
            </tr>
        </table>
    </form>
</div>

</body>
<%@include file="../master/footer.jsp" %>
<script>
//    function del(){
//        var row=$('#list').datagrid('getSelected');
//        if(row){
//            $.messager.confirm("系统提示","您确定要删除这条记录吗?",function(r){
//                if(r){
//                    $.post(contextPath+'/contract/remove?contract.id='+row.id,function(result){
//                        if(result.result==="success"){
//                            $.messager.alert("系统提示","删除成功!");
//                            $("#list").datagrid("reload");
//                        }else{
//                            $.messager.alert("系统提示",result.message);
//                        }
//                    },'json');
//                }
//            });
//        }
//    }
</script>
</html>
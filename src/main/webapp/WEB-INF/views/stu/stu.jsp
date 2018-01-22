<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生管理</title>
	<%@include file="../master/header.jsp" %>
</head>
<body>
	<!--分页列表-->
	<table id="list" class="easyui-datagrid"
		data-options="
			toolbar:'#tb',
			rownumbers:true,
			border:false,
			singleSelect:true,
			pagination:true,
			pageSize:10,
			url:'<%=path %>/stu/pager_criteria',
			method:'post'">
		<thead>
			<tr>
				<th data-options="field:'id',width:80,checkbox:true">编号</th>
				<th data-options="field:'num',width:100">学号</th>
				<th data-options="field:'realName',width:100">姓名</th>
				<th data-options="field:'clazzId',width:100,hidden:true,formatter:formatterClazzId">班级编号</th>
				<th data-options="field:'clazzTitle',width:100,formatter:formatterClazzTitle">班级</th>
			</tr>
		</thead>
	</table>

	<!--实现多条件模糊查询-->
	<div id="tb" style="height: auto">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" onclick="openWin('addWin');">添加</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" onclick="openStuEditWin();">修改</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" onclick="openDetailWin();">查看详情</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" onclick="openContractWin();">分配联系人</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" onclick="openContractInfoWin();">查看联系人</a>
		<div>
			<form id="searchForm">
				<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入学号',
						required:false,
						novalidate:true" name="stu.num"/>
				<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入真实姓名',
						required:false,
						novalidate:true" name="stu.realName"/>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch('list', 'searchForm');">搜索</a>
			</form>
		</div>
	</div>

	<!--新增学生基本信息-->
	<div id="addWin" class="easyui-window normal_win" data-options="title:'添加学生', closed:true">
		<form id="addForm">
			<table>
				<tr>
					<td>学号</td>
					<td>
						<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入学号',
						required:true,
						validType:['length[6,20]'],
						novalidate:true" name="stu.num"/>
					</td>
				</tr>
				<tr>
					<td>姓名</td>
					<td>
						<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入真实姓名',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="stu.realName"/>
					</td>
				</tr>
				<tr>
					<td>班级</td>
					<td>
						<input id="clazz" class="easyui-combobox easyui-validatebox" data-options="prompt:'请选择班级',
						required:true,
						validType:['length[1,20]'],
						novalidate:true" name="stu.clazz.id"/>
					</td>
				</tr>
				<tr>
					<td><a class="easyui-linkbutton" onclick="closeWin('addWin')">取消</a></td>
					<td><a class="easyui-linkbutton" onclick="save('/stu/save', 'addForm', 'addWin', 'list');">确认</a></td>
				</tr>
			</table>
		</form>
	</div>

	<!-- 修改学生基本信息-->
	<div id="editWin" class="easyui-window normal_win" data-options="title:'编辑学生', closed:true">
		<form id="editForm">
			<input type="hidden" name="stu.id" />
			<table>
				<tr>
					<td>学号</td>
					<td>
						<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入学号',
						required:true,
						validType:['length[6,20]'],
						novalidate:true" name="stu.num"/>
					</td>
				</tr>
				<tr>
					<td>姓名</td>
					<td>
						<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入真实姓名',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="stu.realName"/>
					</td>
				</tr>
				<tr>
					<td>班级</td>
					<td>
						<input id="clazzEdit" class="easyui-combobox easyui-validatebox" data-options="prompt:'请选择班级',
						required:true,
						validType:['length[1,20]'],
						novalidate:true" name="stu.clazz.id"/>
					</td>
				</tr>
				<tr>
					<td><a class="easyui-linkbutton" onclick="closeWin('editWin')">取消</a></td>
					<td><a class="easyui-linkbutton" onclick="edit('/stu/update', 'editForm', 'editWin', 'list');">确认</a></td>
				</tr>
			</table>
		</form>
	</div>

	<!--查看并修改学生详情-->
	<div id="editDetailWin" class="easyui-window normal_win" data-options="title:'学生详情', closed:true">
		<form id="editDetailForm">
			<input id="stuId" type="hidden" name="stuDetail.stu.id" />
			<input type="hidden" name="stuDetail.id" />
			<table>
				<tr>
					<td>性别</td>
					<td>
						<input class="easyui-combobox easyui-validatebox" data-options="
						prompt:'请选择性别',
						required:true,
						validType:['length[1,20]'],
						novalidate:true,
						valueField:'id',
						textField:'text',
						data:[
							{id:'0', text:'请选择性别'},
							{id:'1', text:'男'},
							{id:'2', text:'女'}
							]
						" name="stuDetail.gender"/>
					</td>
				</tr>
				<tr>
					<td>手机号</td>
					<td>
						<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入手机号',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="stuDetail.phone"/>
					</td>
				</tr>
				<tr>
					<td>身份证</td>
					<td>
						<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入身份证号',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="stuDetail.identityNo"/>
					</td>
				</tr>
				<tr>
					<td><a class="easyui-linkbutton" onclick="closeWin('editDetailWin')">取消</a></td>
					<td><a class="easyui-linkbutton" onclick="edit('/stuDetail/update', 'editDetailForm', 'editDetailWin', 'list');">确认</a></td>
				</tr>
			</table>
		</form>
	</div>
	<!--分配联系人，列出联系人t_contract表里的数据-->
	<div id="contractWin" class="easyui-window large_win" data-options="title:'选择联系人', closed:true">
		<table id="contractList" class="easyui-datagrid"
			   data-options="
			toolbar:'#contractTb',
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

		<div id="contractTb" style="height: auto">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" onclick="confirmContract();">确认</a>
			<div>
				<form id="contractSearchForm">
					<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入姓名',
						required:false,
						novalidate:true" name="contract.realName"/>
					<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入手机号码',
						required:false,
						novalidate:true" name="contract.phone"/>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch('contractList', 'contractSearchForm');">搜索</a>
				</form>
			</div>
		</div>
	</div>
	<!-- 查看已分配的学生联系人详情-->
	<div id="contractInfoWin" class="easyui-window normal_win" data-options="title:'联系人信息', closed:true">
		<table id="contractInfoList" class="easyui-datagrid"
			   data-options="
				rownumbers:true,
				border:false,
				singleSelect:false,
				pagination:false,
				pageSize:10">
			<thead>
			<tr>
				<th data-options="field:'id',width:80,checkbox:true">编号</th>
				<th data-options="field:'realName',width:100">姓名</th>
				<th data-options="field:'phone',width:100">手机号码</th>
			</tr>
			</thead>
		</table>
	</div>

</body>
<%@include file="../master/footer.jsp" %>
<script>
    //查询下拉选项框中的班级列表
    $(function() {
        $('#clazz').combobox({
            url:contextPath + '/clazz/all',
            valueField:'id',
            textField:'text'
        });
		//关闭窗口并清除缓存数据
        $("#editDetailWin").window(
            {
                onClose:function() {
                    $("#editDetailForm").form("clear");
                }
            }
        );
    });


    function formatterClazzId(value, row, index) {
        return row.clazz.id;
    }

    /**
     * @param value 是列的数据
     * @param row 整行的数据
     * @param index 行的索引
     */
    function formatterClazzTitle(value, row, index) {
        return row.clazz.title;
    }

    //点击修改弹出修改窗口修改数据
    function openStuEditWin() {
        var row = $("#list").datagrid("getSelected");
        if (row) {
            $("#editForm").form("load", datagridRowHandler(row, "stu"));
            $('#clazzEdit').combobox({
                url:contextPath + '/clazz/all?clazzId=' + row.clazz.id,
                valueField:'id',
                textField:'text'
            });
            openWin('editWin');
        } else {
            showInfoAlert("请选择需要修改的数据")
        }
    }

    //查看学生详情数据并进行修改
    function openDetailWin() {
        var row = $("#list").datagrid("getSelected");
        if (row) {
            $.post(contextPath + "/stuDetail/get",
                {'stuId':row.id},
                function (data) {
                    $("#editDetailForm").form('load', datagridRowHandler(data, "stuDetail"));
                    $("#stuId").val(row.id);
                    openWin("editDetailWin");
                },
                'json'
            );
        } else {
            showInfoAlert("请选择需要查看详情的数据或者双击行查看详情")
        }
    }

    //分配学生联系人
    function openContractWin() {
        var row = $("#list").datagrid("getSelected");
        if (row) {
            openWin("contractWin");
            var options = pagerOptions("contractList");
            $("#contractList").datagrid(
                {
                    url: contextPath + '/contract/pager_criteria',
                    method: 'post',
                    pageSize: options.pageSize
                }
            );
        } else {
            showInfoAlert("请选择需要分配联系人的学生")
        }
    }

	//查看已经分配的学生联系人
    function confirmContract() {
        var row = $("#list").datagrid("getSelected");
        var rows = $("#contractList").datagrid("getSelections");
        if (rows !== null && rows.length > 0) {
            var contractIds = "";
            for (var i = 0, len = rows.length; i < len; i++) {
                if (contractIds === "") {
                    contractIds = rows[i].id;
                } else {
                    contractIds = contractIds + "," + rows[i].id
                }
            }
            $.post(contextPath + "/stu/save_contract",
                {
                    'stuId':row.id,
                    'contractIds':contractIds
                },
                function (data) {
                    if (data.result === "success") {
                        closeWin("contractWin");
                    } else {
                        showInfoAlert(data.message);
                    }
                },
                'json'
            );
        } else {
            showInfoAlert("请选择联系人");
        }
    }

    //打开该窗口分配学生联系人
    function openContractInfoWin() {
        var row = $("#list").datagrid("getSelected");
        if (row) {
            openWin("contractInfoWin");
            $("#contractInfoList").datagrid(
                {
                    url: contextPath + '/stu/contract',
                    method: 'post',
                    pageSize:100,
                    pageNumber:1,
                    queryParams:{
                        stuId:row.id
                    }
                }
            );
        } else {
            showInfoAlert("请选择需要查看学生的联系人")
        }
    }
</script>
</html>
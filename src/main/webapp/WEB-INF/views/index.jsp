<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
	<%@include file="master/header.jsp" %>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 60px;">
		<div id="wel_msg">欢迎您：用户;
			<a href="javascript:void(0);" onclick="alert('退出成功');">安全退出</a>
		</div>
		<p class="clear"></p>
	</div>
	
	<div data-options="region:'south',split:true" style="height: 50px;">Copyright&copy;zzh</div>
	<div data-options="region:'west',split:true" title="主菜单" style="width: 250px;">
		<div class="easyui-accordion">
			<div title="班级管理" data-options="iconCls:'icon-ok'" style="padding: 10px;">
				<ul>
					<li><a href="javascript:void(0);" src="<%=path%>/clazz/class_page"
						class="site-navi-tab">所有班级</a></li>
				</ul>
			</div>
			<div title="教师管理" data-options="iconCls:'icon-ok'" style="padding: 10px;">
				<ul>
					<li><a href="javascript:void(0);" src="<%=path%>/teacher/teacher_page"
						   class="site-navi-tab">所有教师</a></li>
				</ul>
			</div>
			<div title="学生管理" data-options="iconCls:'icon-ok'" style="padding: 10px;">
				<ul>
					<li><a href="javascript:void(0);" src="<%=path%>/stu/stu_page"
						   class="site-navi-tab">所有学生</a></li>
				</ul>
			</div>
			<div title="联系人管理" data-options="iconCls:'icon-ok'" style="padding: 10px;">
				<ul>
					<li><a href="javascript:void(0);" src="<%=path%>/contract/contract_page"
						   class="site-navi-tab">所有联系人</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="tabs" class="easyui-tabs" data-options="region:'center'">
			<div title="主页" style="padding: 10px">
				<div class="easyui-panel my-panel" title="系统状态">
					系统状态
				</div>
				<div class="easyui-panel my-panel" title="待办任务">
					待办任务
				</div>
			</div>
		</div>
		
		<div id="mm" class="easyui-menu">
		    <div id="mm-tabupdate">刷新</div>
		    <div class="menu-sep"></div>
		    <div id="mm-tabclose">关闭</div>
		    <div id="mm-tabcloseother">关闭其他</div>
		    <div id="mm-tabcloseall">关闭全部</div>
		</div>
		
</body>
<%@include file="master/footer.jsp" %>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/include/adminlte.jsp" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/adminlte.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/weixin/weixinMenu/">菜单列表</a></li>
		<li class="active"><a href="${ctx}/weixin/weixinMenu/form?id=${weixinMenu.id}">菜单<shiro:hasPermission name="weixin:weixinMenu:edit">${not empty weixinMenu.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="weixin:weixinMenu:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="weixinMenu" action="${ctx}/weixin/weixinMenu/save" method="post" class="col-md-8" role="form">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="type" class="form-control ">
					<form:options items="${fns:getDictList('weixin_menu_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">事件：</label>
			<div class="controls">
				<form:input path="mkey" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">链接：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">父级菜单：</label>
			<div class="controls">
				<form:select path="parent" items="${menuSelect}" class="form-control ">
					<%--<form:options items="${fns:getDictList('weixin_menu_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">顺序：</label>
			<div class="controls">
				<form:input path="morder" htmlEscape="false" maxlength="11" class="form-control " hint="数字越小,优化级越高"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="weixin:weixinMenu:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/include/adminlte.jsp" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
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
		<li><a href="${ctx}/weixin/weixinUser/">用户列表</a></li>
		<li class="active"><a href="${ctx}/weixin/weixinUser/form?id=${weixinUser.id}">用户<shiro:hasPermission name="weixin:weixinUser:edit">${not empty weixinUser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="weixin:weixinUser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="weixinUser" action="${ctx}/weixin/weixinUser/save" method="post" class="col-md-8" role="form">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<form:select path="gender" class="form-control ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="type" class="form-control ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('weixin_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">注册手机号：</label>
			<div class="controls">
				<form:input path="registerPhone" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">微信的用户唯一标识：</label>
			<div class="controls">
				<form:input path="weixinOpenid" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="weixin:weixinUser:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
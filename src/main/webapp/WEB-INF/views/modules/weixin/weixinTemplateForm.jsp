<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/include/adminlte.jsp" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模板消息管理</title>
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
		<li><a href="${ctx}/weixin/weixinTemplate/">模板消息列表</a></li>
		<li class="active"><a href="${ctx}/weixin/weixinTemplate/form?id=${weixinTemplate.id}">模板消息<shiro:hasPermission name="weixin:weixinTemplate:edit">${not empty weixinTemplate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="weixin:weixinTemplate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="weixinTemplate" action="${ctx}/weixin/weixinTemplate/save" method="post" class="col-md-8" role="form">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		

		<div class="form-group">
			<label class="control-label">用户openid：</label>
			<div class="controls">
				<form:input path="openid" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">模板ID：</label>
			<div class="controls">
				<form:select path="templateId" class="form-control ">
					<form:options items="${fns:getDictList('weixin_template')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="40" class="form-control "/>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label">链接：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label">快递公司：</label>
			<div class="controls">
				<form:input path="deliverName" htmlEscape="false" maxlength="255" class="form-control" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label">快递单号：</label>
			<div class="controls">
				<form:input path="orderName" htmlEscape="false" maxlength="255" class="form-control" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label">商品名称：</label>
			<div class="controls">
				<form:input path="productName" htmlEscape="false" maxlength="255" class="form-control" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label">商品数量：</label>
			<div class="controls">
				<form:input path="productCount" htmlEscape="false" maxlength="255" class="form-control" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="weixin:weixinTemplate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
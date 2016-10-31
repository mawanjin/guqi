<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/include/adminlte.jsp" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信用户管理</title>
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
		<li><a href="${ctx}/weixin/weixinSubscriber/">微信用户列表</a></li>
		<li class="active"><a href="${ctx}/weixin/weixinSubscriber/form?id=${weixinSubscriber.id}">微信用户<shiro:hasPermission name="weixin:weixinSubscriber:edit">${not empty weixinSubscriber.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="weixin:weixinSubscriber:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="weixinSubscriber" action="${ctx}/weixin/weixinSubscriber/save" method="post" class="col-md-8" role="form">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		

		<div class="form-group">
			<label class="control-label">关注者：</label>
			<div class="controls">
				<form:input path="fromUserName" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">关注时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="form-control  "
					value="<fmt:formatDate value="${weixinSubscriber.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="weixin:weixinSubscriber:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
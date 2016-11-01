<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/include/adminlte.jsp" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客服管理</title>
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
		<li><a href="${ctx}/weixin/weixinCustom/">客服列表</a></li>
		<li class="active"><a href="${ctx}/weixin/weixinCustom/form?id=${weixinCustom.id}">客服<shiro:hasPermission name="weixin:weixinCustom:edit">${not empty weixinCustom.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="weixin:weixinCustom:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="weixinCustom" action="${ctx}/weixin/weixinCustom/save" method="post" class="col-md-8" role="form">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="control-label">昵称：</label>
			<div class="controls">
				<form:input path="kfNick" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">账号：</label>
			<div class="controls">
				<form:input path="kfAccount" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">工号：</label>
			<div class="controls">
				<form:input path="kfId" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label">密码：</label>
			<div class="controls">
				<form:input path="password" htmlEscape="false" type="password" maxlength="45" class="form-control "/>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label">头像：</label>
			<div class="controls">
				<img src="${weixinCustom.kfHeadimgurl}" />
				<form:hidden id="kfHeadimgurl" path="kfHeadimgurl" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="kfHeadimgurl" type="files" uploadPath="/weixin/weixinCustom" selectMultiple="true"/>
			</div>
		</div>

		<%--<c:if test="${not empty weixinCustom.id}">--%>
			<%--<div class="form-group">--%>
				<%--<label class="control-label">创建时间：</label>--%>
				<%--<div class="controls">--%>
					<%--<input name="createTime" type="text" readonly="readonly" maxlength="20" class="form-control  "--%>
						   <%--value="<fmt:formatDate value="${weixinCustom.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
						   <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</c:if>--%>

		<div class="form-actions">
			<shiro:hasPermission name="weixin:weixinCustom:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
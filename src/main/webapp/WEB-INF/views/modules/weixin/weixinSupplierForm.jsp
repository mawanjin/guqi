<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/include/adminlte.jsp" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理</title>
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
		<li><a href="${ctx}/weixin/weixinSupplier/">供应商列表</a></li>
		<li class="active"><a href="${ctx}/weixin/weixinSupplier/form?id=${weixinSupplier.id}">供应商<shiro:hasPermission name="weixin:weixinSupplier:edit">${not empty weixinSupplier.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="weixin:weixinSupplier:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="weixinSupplier" action="${ctx}/weixin/weixinSupplier/save" method="post" class="col-md-8" role="form">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="control-label">手机号：</label>
			<div class="controls">
				<form:input path="registerPhone" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">昵称：</label>
			<div class="controls">
				<form:input path="nickName" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">所属企业：</label>
			<div class="controls">
				<form:input path="company" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">角色：</label>
			<div class="controls">
				<form:select path="type" class="form-control ">
					<form:options items="${fns:getDictList('weixin_msg_customer_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">公司地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">资质类型：</label>
			<div class="controls">
				<form:select path="cerType" class="form-control ">
					<form:options items="${fns:getDictList('weixin_supplier_credit_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="form-group">
			<label class="control-label">开户行：</label>
			<div class="controls">
				<form:input path="bank" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">户名：</label>
			<div class="controls">
				<form:input path="bankAccount" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">卡号：</label>
			<div class="controls">
				<form:input path="card" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label">资质:</label>
			<div class="controls">
				<form:hidden id="nameImage" path="cerFile1" htmlEscape="false" maxlength="255" class="form-control "/>
				<sys:ckfinder input="nameImage" type="images" uploadPath="/weixin/customer/photo" selectMultiple="true"/>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="weixin:weixinSupplier:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
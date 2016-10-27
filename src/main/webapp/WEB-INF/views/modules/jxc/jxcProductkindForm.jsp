<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品类别管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/adminlte.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")){
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
		<li><a href="${ctx}/jxc/jxcProductkind/">商品类别列表</a></li>
		<li class="active"><a href="${ctx}/jxc/jxcProductkind/form?id=${jxcProductkind.id}&parent.id=${jxcProductkindparent.id}">商品类别<shiro:hasPermission name="jxc:jxcProductkind:edit">${not empty jxcProductkind.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="jxc:jxcProductkind:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="jxcProductkind" action="${ctx}/jxc/jxcProductkind/save" method="post" class="col-md-8" role="form">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
			<div class="form-group">
			<label class="control-label">商品类别：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
		</div>
			<div class="form-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="form-control "/>
			</div>
		</div>
			<div class="form-group">
			<label class="control-label">上级父级编号：</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id" value="${jxcProductkind.parent.id}" labelName="parent.name" labelValue="${jxcProductkind.parent.name}"
					title="父级编号" url="/jxc/jxcProductkind/treeData" extId="${jxcProductkind.id}" cssClass="form-control" allowClear="true"/>
			</div>
		</div>
			<div class="form-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" class="form-control required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="jxc:jxcProductkind:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
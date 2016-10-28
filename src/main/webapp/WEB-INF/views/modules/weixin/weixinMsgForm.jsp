<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/include/adminlte.jsp" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消息管理</title>
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
		<li><a href="${ctx}/weixin/weixinMsg/">消息列表</a></li>
		<li class="active"><a href="${ctx}/weixin/weixinMsg/form?id=${weixinMsg.id}">消息<shiro:hasPermission name="weixin:weixinMsg:edit">${not empty weixinMsg.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="weixin:weixinMsg:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="weixinMsg" action="${ctx}/weixin/weixinMsg/save" method="post" class="col-md-8" role="form">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="control-label">接收方：</label>
			<div class="controls">
				<form:input path="toUserName" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">发送方：</label>
			<div class="controls">
				<form:input path="fromUserName" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="form-control  "
					value="<fmt:formatDate value="${weixinMsg.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">消息类型：</label>
			<div class="controls">
				<form:select path="msgType" class="form-control ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('weixin_msg_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">文本消息内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">消息id：</label>
			<div class="controls">
				<form:input path="msgId" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">图片链接：</label>
			<div class="controls">
				<form:input path="picUrl" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">图片媒体id：</label>
			<div class="controls">
				<form:input path="mediaId" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">语音格式：</label>
			<div class="controls">
				<form:input path="format" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">经度：</label>
			<div class="controls">
				<form:input path="locationX" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">纬度：</label>
			<div class="controls">
				<form:input path="locationY" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">缩放大小：</label>
			<div class="controls">
				<form:input path="scale" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">位置信息：</label>
			<div class="controls">
				<form:input path="label" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">消息标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="55" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">消息描述：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">消息链接：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="weixin:weixinMsg:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
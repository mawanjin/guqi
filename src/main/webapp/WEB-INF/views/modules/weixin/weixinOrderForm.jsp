<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/include/adminlte.jsp" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<title>订单管理</title>
<head>
	<script src="${ctxStatic}/jquery-select2/3.4/select2.js"></script>
	<link href="${ctxStatic}/jquery-select2/3.4/select2.css" rel="stylesheet" />

	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

			$(".select2").select2();

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
		<li><a href="${ctx}/weixin/weixinOrder/">订单列表</a></li>
		<li class="active"><a href="${ctx}/weixin/weixinOrder/form?id=${weixinOrder.id}">订单<shiro:hasPermission name="weixin:weixinOrder:edit">${not empty weixinOrder.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="weixin:weixinOrder:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="weixinOrder" action="${ctx}/weixin/weixinOrder/save" method="post"  class="col-md-8" role="form">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="control-label">订单号：</label>
			<div class="controls">
				<input type="hidden" name="orderId" value="${weixinOrder.orderId}" />
				<form:input path="orderId" htmlEscape="false" maxlength="45" class="form-control" disabled="true" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">订单类型：</label>
			<div class="controls">
				<input  htmlEscape="false" maxlength="45" class="form-control" disabled="false" value="意向单" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label">客户：</label>
			<div class="controls">
				<select class="select2" path="weixinUser.id" name="weixinUser.id"  data-live-search="true" data-style="btn-primary" style="width: 400px;" >
					<c:forEach items="${weixinUsers}" var="weixinUser">
						<option value="${weixinUser.id}">${weixinUser.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label">初始接单人员：</label>
			<div class="controls">

				<select class="select2" path="firstKf.id" name="firstKf.id"  data-live-search="true" data-style="btn-primary" style="width: 400px;" >
					<c:forEach items="${weixinCustomers}" var="weixinCustomer">
						<option value="${weixinCustomer.id}">${weixinCustomer.kfNick}</option>
					</c:forEach>
				</select>
				<%--<form:input path="firstKf" name ="firstKf" htmlEscape="false" maxlength="45" class="form-control" disabled="true" />--%>
			</div>
		</div>


		<div class="form-group">
			<label class="control-label">给到客户的报价：</label>
			<div class="controls">
				<form:input path="priceCustomer" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">给到设计 人员的报价：</label>
			<div class="controls">
				<form:input path="priceExpert" htmlEscape="false" maxlength="45" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">截稿日期：</label>
			<div class="controls">
				<input name="expireTime" type="text" readonly="readonly" maxlength="20" class="form-control  "
					value="<fmt:formatDate value="${weixinOrder.expireTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">发布类型：</label>
			<div class="controls">
				<form:radiobuttons path="auctionType" items="${fns:getDictList('weixin_auction_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">选定的专家：</label>
			<div class="controls">
				<form:input path="expert.name" htmlEscape="false" maxlength="64" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">选定的供应商：</label>
			<div class="controls">
				<form:input path="supplier" htmlEscape="false" maxlength="64" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">采购类型：</label>
			<div class="controls">
				<form:select path="purchaseType"  style="width: 400px;">
					<form:options items="${fns:getDictList('weixin_purchase_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label">说明：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="form-control "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="weixin:weixinOrder:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
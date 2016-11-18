<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/adminlte.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/weixin/weixinOrder/">订单列表</a></li>
		<shiro:hasPermission name="weixin:weixinOrder:edit"><li><a href="${ctx}/weixin/weixinOrder/form">订单添加</a></li></shiro:hasPermission>
	</ul>

	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="weixinOrder" action="${ctx}/weixin/weixinOrder/" method="post" class="row form-horizontal well" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">订单号：</label>
                <div class="col-sm-8">
				<form:input path="orderId" htmlEscape="false" maxlength="45" class="form-control"/>
			    </div>
            </div>
			<input id="btnSubmit" class="btn btn-primary pull-right" type="submit" value="查询"/>


	</form:form>

	<table id="contentTable" style="margin-top: 10px" class="table table-hover">
		<thead>
			<tr>
				<th>订单号</td>
				<th>创建时间</td>
				<th>采购类型</td>
				<th>客户电话</td>
				<th>微信昵称</td>
				<th>所属企业</td>
				<th>初始接单人员</td>
				<th>更新时间</td>
				<th>备注</td>
				<shiro:hasPermission name="weixin:weixinOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinOrder">
			<tr>
				<td>
					${weixinOrder.orderId}
				</td>
				<td>
					<fmt:formatDate value="${weixinOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(weixinOrder.purchaseType, 'weixin_purchase_type', '')}
				</td>
				<td>
					${weixinOrder.weixinUser.registerPhone}
				</td>
				<td>
					${weixinOrder.weixinUser.nickName}
				</td>
				<td>
					${weixinOrder.weixinUser.company}
				</td>
				<td>
					${weixinOrder.firstKf.kfNick}
				</td>

				<td>
					<fmt:formatDate value="${weixinOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>

				<td>
						${weixinOrder.remarks}
				</td>
				<shiro:hasPermission name="weixin:weixinOrder:edit"><td>
    				<a href="${ctx}/weixin/weixinOrder/detail?id=${weixinOrder.id}">查看详情</a>
					<a href="${ctx}/weixin/weixinOrder/delete?id=${weixinOrder.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="box-tools">${page}</div>
</body>
</html>
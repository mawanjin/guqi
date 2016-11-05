<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信用户管理</title>
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
		<li class="active"><a href="${ctx}/weixin/weixinSubscriber/">微信用户列表</a></li>
		<shiro:hasPermission name="weixin:weixinSubscriber:edit"><li><a href="${ctx}/weixin/weixinSubscriber/form">微信用户添加</a></li></shiro:hasPermission>
	</ul>

	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="weixinSubscriber" action="${ctx}/weixin/weixinSubscriber/" method="post" class="row form-horizontal well" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">关注者：</label>
                <div class="col-sm-8">
				<form:input path="fromUserName" htmlEscape="false" maxlength="45" class="form-control"/>
			    </div>
            </div>
			<input id="btnSubmit" class="btn btn-primary pull-right" type="submit" value="查询"/>


	</form:form>

	<table id="contentTable" style="margin-top: 10px" class="table table-hover">
		<thead>
			<tr>
				<th>关注者</td>
				<th>关注时间</td>
				<shiro:hasPermission name="weixin:weixinSubscriber:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinSubscriber">
			<tr>
				<td>
					${weixinSubscriber.fromUserName}
				</td>
				<td>
					<fmt:formatDate value="${weixinSubscriber.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="weixin:weixinSubscriber:edit"><td>
    				<a href="${ctx}/weixin/weixinSubscriber/form?id=${weixinSubscriber.id}">修改</a>
					<a href="${ctx}/weixin/weixinSubscriber/delete?id=${weixinSubscriber.id}" onclick="return confirmx('确认要删除该微信用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="box-tools">${page}</div>
</body>
</html>
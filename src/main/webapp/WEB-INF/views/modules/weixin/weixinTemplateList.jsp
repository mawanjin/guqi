<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模板消息管理</title>
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
		<li class="active"><a href="${ctx}/weixin/weixinTemplate/">模板消息列表</a></li>
		<shiro:hasPermission name="weixin:weixinTemplate:edit"><li><a href="${ctx}/weixin/weixinTemplate/form">模板消息添加</a></li></shiro:hasPermission>
	</ul>

	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="weixinTemplate" action="${ctx}/weixin/weixinTemplate/" method="post" class="row form-horizontal well" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

			<%--<div class="form-group col-sm-4">--%>
                <%--<label class="col-sm-4 control-label">模板名称：</label>--%>
                <%--<div class="col-sm-8">--%>
				<%--<form:input path="name" htmlEscape="false" maxlength="45" class="form-control"/>--%>
			    <%--</div>--%>
            <%--</div>--%>
			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">用户：</label>
                <div class="col-sm-8">
				<form:input path="openid" htmlEscape="false" maxlength="45" class="form-control"/>
			    </div>
            </div>
			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">模板ID：</label>
                <div class="col-sm-8">
				<form:select path="templateId" class="form-control">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('weixin_template')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			    </div>
            </div>
			<input id="btnSubmit" class="btn btn-primary pull-right" type="submit" value="查询"/>


	</form:form>

	<table id="contentTable" style="margin-top: 10px" class="table table-hover">
		<thead>
			<tr>
				<th>用户</td>
				<th>模板ID</td>
				<th>链接</td>
				<th>创建时间</td>
				<th>状态</td>
				<th>消息内容</td>
				<th>送达时间</td>
				<shiro:hasPermission name="weixin:weixinTemplate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinTemplate">
			<tr>
				<td>
					${weixinTemplate.openid}
				</td>

				<td>
					${fns:getDictLabel(weixinTemplate.templateId, 'weixin_template', '')}
				</td>

				<td>
						${weixinTemplate.url}
				</td>

				<td>
					<fmt:formatDate value="${weixinTemplate.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${fns:getDictLabel(weixinTemplate.status, 'weixin_template_status', '')}
				</td>
				<td>
					<a href="#">点击查看</a>
				</td>
				<td>
					<fmt:formatDate value="${weixinTemplate.successDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="weixin:weixinTemplate:edit"><td>
    				<a href="${ctx}/weixin/weixinTemplate/form?id=${weixinTemplate.id}">修改</a>
					<a href="${ctx}/weixin/weixinTemplate/delete?id=${weixinTemplate.id}" onclick="return confirmx('确认要删除该模板消息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="box-tools">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
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
		<li class="active"><a href="${ctx}/weixin/weixinMenu/">菜单列表</a></li>
		<shiro:hasPermission name="weixin:weixinMenu:edit"><li><a href="${ctx}/weixin/weixinMenu/form">菜单添加</a></li></shiro:hasPermission>
	</ul>

	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="weixinMenu" action="${ctx}/weixin/weixinMenu/" method="post" class="row form-horizontal well" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">名称：</label>
                <div class="col-sm-8">
				<form:input path="name" htmlEscape="false" maxlength="45" class="form-control"/>
			    </div>
            </div>
			<input id="btnSubmit" class="btn btn-primary pull-right" type="submit" value="查询"/>


	</form:form>

	<table id="contentTable" style="margin-top: 10px" class="table table-hover">
		<thead>
			<tr>
				<th>名称</td>
				<th>等级</td>
				<th>类型</td>
				<th>事件</td>
				<th>链接</td>
				<th>父级菜单</td>
				<shiro:hasPermission name="weixin:weixinMenu:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinMenu">
			<tr>
				<td><a href="${ctx}/weixin/weixinMenu/form?id=${weixinMenu.id}">
					${weixinMenu.name}
				</a></td>

				<td>
						<c:choose>
							<c:when test="${weixinMenu.parent eq '0'}">
								一级菜单
							</c:when>
							<c:otherwise>
								二级菜单
							</c:otherwise>
						</c:choose>
				</td>

				<td>
					${fns:getDictLabel(weixinMenu.type, 'weixin_menu_type', '')}
				</td>
				<td>
					${weixinMenu.mkey}
				</td>
				<td>
					${weixinMenu.url}
				</td>
				<td>
						${weixinMenu.parentName}
				</td>
				<shiro:hasPermission name="weixin:weixinMenu:edit"><td>
    				<a href="${ctx}/weixin/weixinMenu/form?id=${weixinMenu.id}">修改</a>
					<a href="${ctx}/weixin/weixinMenu/delete?id=${weixinMenu.id}" onclick="return confirmx('确认要删除该菜单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="box-tools">${page}</div>
</body>
</html>
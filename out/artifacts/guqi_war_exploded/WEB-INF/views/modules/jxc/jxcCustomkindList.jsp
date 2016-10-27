<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户类别管理</title>
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
		<li class="active"><a href="${ctx}/jxc/jxcCustomkind/">客户类别列表</a></li>
		<shiro:hasPermission name="jxc:jxcCustomkind:edit"><li><a href="${ctx}/jxc/jxcCustomkind/form">客户类别添加</a></li></shiro:hasPermission>
	</ul>

	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="jxcCustomkind" action="${ctx}/jxc/jxcCustomkind/" method="post" class="row form-horizontal well" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">客户类别：</label>
                <div class="col-sm-8">
				<form:input path="name" htmlEscape="false" maxlength="100" class="form-control"/>
			    </div>
            </div>
			<input id="btnSubmit" class="btn btn-primary pull-right" type="submit" value="查询"/>


	</form:form>

	<table id="contentTable" style="margin-top: 10px" class="table table-hover">
		<thead>
			<tr>
				<th>客户类别</td>
				<th>更新时间</td>
				<th>备注信息</td>
				<shiro:hasPermission name="jxc:jxcCustomkind:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="jxcCustomkind">
			<tr>
				<td><a href="${ctx}/jxc/jxcCustomkind/form?id=${jxcCustomkind.id}">
					${jxcCustomkind.name}
				</a></td>
				<td>
					<fmt:formatDate value="${jxcCustomkind.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${jxcCustomkind.remarks}
				</td>
				<shiro:hasPermission name="jxc:jxcCustomkind:edit"><td>
    				<a href="${ctx}/jxc/jxcCustomkind/form?id=${jxcCustomkind.id}">修改</a>
					<a href="${ctx}/jxc/jxcCustomkind/delete?id=${jxcCustomkind.id}" onclick="return confirmx('确认要删除该客户类别吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="box-tools">${page}</div>
</body>
</html>
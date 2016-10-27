<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收入类别管理</title>
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
		<li class="active"><a href="${ctx}/jxc/jxcIncomekind/">收入类别列表</a></li>
		<shiro:hasPermission name="jxc:jxcIncomekind:edit"><li><a href="${ctx}/jxc/jxcIncomekind/form">收入类别添加</a></li></shiro:hasPermission>
	</ul>

	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="jxcIncomekind" action="${ctx}/jxc/jxcIncomekind/" method="post" class="row form-horizontal well" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">收入类别：</label>
                <div class="col-sm-8">
				<form:input path="name" htmlEscape="false" maxlength="100" class="form-control"/>
			    </div>
            </div>
			<input id="btnSubmit" class="btn btn-primary pull-right" type="submit" value="查询"/>


	</form:form>

	<table id="contentTable" style="margin-top: 10px" class="table table-hover">
		<thead>
			<tr>
				<th>收入类别</td>
				<th>更新时间</td>
				<th>备注信息</td>
				<shiro:hasPermission name="jxc:jxcIncomekind:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="jxcIncomekind">
			<tr>
				<td><a href="${ctx}/jxc/jxcIncomekind/form?id=${jxcIncomekind.id}">
					${jxcIncomekind.name}
				</a></td>
				<td>
					<fmt:formatDate value="${jxcIncomekind.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${jxcIncomekind.remarks}
				</td>
				<shiro:hasPermission name="jxc:jxcIncomekind:edit"><td>
    				<a href="${ctx}/jxc/jxcIncomekind/form?id=${jxcIncomekind.id}">修改</a>
					<a href="${ctx}/jxc/jxcIncomekind/delete?id=${jxcIncomekind.id}" onclick="return confirmx('确认要删除该收入类别吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="box-tools">${page}</div>
</body>
</html>
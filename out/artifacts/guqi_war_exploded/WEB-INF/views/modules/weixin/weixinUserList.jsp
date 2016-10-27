<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
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
		<li class="active"><a href="${ctx}/weixin/weixinUser/">用户列表</a></li>
		<shiro:hasPermission name="weixin:weixinUser:edit"><li><a href="${ctx}/weixin/weixinUser/form">用户添加</a></li></shiro:hasPermission>
	</ul>

	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="weixinUser" action="${ctx}/weixin/weixinUser/" method="post" class="row form-horizontal well" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">名称：</label>
                <div class="col-sm-8">
				<form:input path="name" htmlEscape="false" maxlength="45" class="form-control"/>
			    </div>
            </div>
			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">性别：</label>
                <div class="col-sm-8">
				<form:select path="gender" class="form-control">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			    </div>
            </div>
			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">类型：</label>
                <div class="col-sm-8">
				<form:select path="type" class="form-control">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('weixin_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			    </div>
            </div>
			<input id="btnSubmit" class="btn btn-primary pull-right" type="submit" value="查询"/>


	</form:form>

	<table id="contentTable" style="margin-top: 10px" class="table table-hover">
		<thead>
			<tr>
				<th>名称</td>
				<th>性别</td>
				<th>类型</td>
				<th>注册手机号</td>
				<th>微信的用户唯一标识</td>
				<th>邮箱</td>
				<th>del_flag</td>
				<th>备注信息</td>
				<th>更新者</td>
				<th>创建时间</td>
				<th>更新时间</td>
				<shiro:hasPermission name="weixin:weixinUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinUser">
			<tr>
				<td><a href="${ctx}/weixin/weixinUser/form?id=${weixinUser.id}">
					${weixinUser.name}
				</a></td>
				<td>
					${fns:getDictLabel(weixinUser.gender, 'sex', '')}
				</td>
				<td>
					${fns:getDictLabel(weixinUser.type, 'weixin_user_type', '')}
				</td>
				<td>
					${weixinUser.registerPhone}
				</td>
				<td>
					${weixinUser.weixinOpenid}
				</td>
				<td>
					${weixinUser.email}
				</td>
				<td>
					${fns:getDictLabel(weixinUser.delFlag, 'del_flag', '')}
				</td>
				<td>
					${weixinUser.remarks}
				</td>
				<td>
					${weixinUser.updateBy.id}
				</td>
				<td>
					<fmt:formatDate value="${weixinUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${weixinUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="weixin:weixinUser:edit"><td>
    				<a href="${ctx}/weixin/weixinUser/form?id=${weixinUser.id}">修改</a>
					<a href="${ctx}/weixin/weixinUser/delete?id=${weixinUser.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="box-tools">${page}</div>
</body>
</html>
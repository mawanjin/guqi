<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>临时用户管理</title>
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
		<li class="active"><a href="${ctx}/weixin/weixinTempUser/">临时用户列表</a></li>
		<shiro:hasPermission name="weixin:weixinTempUser:edit"><li><a href="${ctx}/weixin/weixinTempUser/form">临时用户添加</a></li></shiro:hasPermission>
	</ul>

	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="weixinTempUser" action="${ctx}/weixin/weixinTempUser/" method="post" class="row form-inline well" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

			<div class="form-group ">
                <label class=" control-label" for="nickName" >昵称：</label>
				<form:input id="nickName" path="nickName" htmlEscape="false" maxlength="45" class="form-control"/>
            </div>
			<div class="form-group ">
                <label class="control-label" for="mobile">手机号：</label>
				<form:input id="mobile" path="mobile" htmlEscape="false" maxlength="45" class="form-control"/>
            </div>
			<div class="form-group">
                <label class="control-label" for="name">姓名：</label>
				<form:input id="name" path="name" htmlEscape="false" maxlength="45" class="form-control"/>
            </div>

			<div class="form-group">
                <label class="control-label" for="beginCreateDate">注册时间：</label>
				<input id="beginCreateDate" name="beginCreateDate" class="form-control" type="text" readonly="readonly" maxlength="20"
					   value="<fmt:formatDate value="${weixinTempUser.beginCreateDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>

			<div class="form-group">
                <label class="sr-only" for="labelRegisterMark">-</label>
				<label class="control-label" id="labelRegisterMark">-</label>
			</div>

		<div class="form-group">
			<label class="sr-only" for="endCreateDate"></label>
			<input id="endCreateDate" name="endCreateDate" class="form-control" type="text" readonly="readonly" maxlength="20"
				   value="<fmt:formatDate value="${weixinTempUser.beginCreateDate}" pattern="yyyy-MM-dd"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		</div>
		<input id="btnSubmit" class="btn btn-primary " type="submit" value="查询"/>

		<!--下载总量(24小时)-->
		<div class="text-right">24小时内注册:<font color="red">${registerCount}</font>个</div>
	</form:form>


	<table id="contentTable" style="margin-top: 10px" class="table table-hover">
		<thead>
			<tr>
				<th>微信标识</td>
				<th>昵称</td>
				<th>角色</td>
				<th>手机号</td>
				<th>姓名</td>
				<th>更新时间</td>
				<th>备注</td>
				<shiro:hasPermission name="weixin:weixinTempUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinTempUser">
			<tr>
				<td><a href="${ctx}/weixin/weixinTempUser/form?id=${weixinTempUser.id}">
					${weixinTempUser.openid}
				</a></td>
				<td>
					${weixinTempUser.nickName}
				</td>
				<td>
					${fns:getDictLabel(weixinTempUser.role, 'weixin_user_type', '')}
				</td>
				<td>
					${weixinTempUser.mobile}
				</td>
				<td>
					${weixinTempUser.name}
				</td>
				<td>
					<fmt:formatDate value="${weixinTempUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${weixinTempUser.remarks}
				</td>
				<shiro:hasPermission name="weixin:weixinTempUser:edit"><td>
    				<a href="${ctx}/weixin/weixinTempUser/form?id=${weixinTempUser.id}">修改</a>
					<a href="${ctx}/weixin/weixinTempUser/delete?id=${weixinTempUser.id}" onclick="return confirmx('确认要删除该临时用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="box-tools">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客服管理</title>
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
		<li class="active"><a href="${ctx}/weixin/weixinCustom/">客服列表</a></li>
		<shiro:hasPermission name="weixin:weixinCustom:edit"><li><a href="${ctx}/weixin/weixinCustom/form">客服添加</a></li></shiro:hasPermission>
	</ul>

	<sys:message content="${message}"/>


	<table id="contentTable" style="margin-top: 10px" class="table table-hover" valign="middle">
		<thead>
			<tr>
				<th>头像</td>
				<th>昵称</td>
				<th>帐号</td>
				<th>工号</td>
				<th>在线状态</td>
				<th>最大接入数</td>
				<th>当前会话数</td>
				<shiro:hasPermission name="weixin:weixinCustom:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinCustom">
			<tr>

				<td style="vertical-align:middle;">
						<img src="${weixinCustom.kfHeadimgurl}" width="50" height="50" />
				</td>
				<td style="vertical-align:middle;"><a href="${ctx}/weixin/weixinCustom/form?id=${weixinCustom.id}">
					${weixinCustom.kfNick}
				</a></td>
				<td style="vertical-align:middle;">
					${weixinCustom.kfAccount}
				</td>
				<td style="vertical-align:middle;">
					${weixinCustom.kfId}
				</td>

				<td style="vertical-align:middle;">
					${fns:getDictLabel(weixinMsg.status, 'weixin_custom_online_status', '未在线')}
				</td>

				<td style="vertical-align:middle;">
						${weixinCustom.autoAccept}
				</td>

				<td style="vertical-align:middle;">
						${weixinCustom.acceptedCase}
				</td>

				<shiro:hasPermission name="weixin:weixinCustom:edit"><td style="vertical-align:middle;">
    				<a href="${ctx}/weixin/weixinCustom/form?id=${weixinCustom.id}">修改</a>
					<a href="${ctx}/weixin/weixinCustom/delete?id=${weixinCustom.id}" onclick="return confirmx('确认要删除该客服吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="box-tools">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消息管理</title>
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
		<li class="active"><a href="${ctx}/weixin/weixinMsg/">消息列表</a></li>
		<shiro:hasPermission name="weixin:weixinMsg:edit"><li><a href="${ctx}/weixin/weixinMsg/form">消息添加</a></li></shiro:hasPermission>
	</ul>

	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="weixinMsg" action="${ctx}/weixin/weixinMsg/" method="post" class="row form-horizontal well" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">接收方：</label>
                <div class="col-sm-8">
				<form:input path="toUserName" htmlEscape="false" maxlength="45" class="form-control"/>
			    </div>
            </div>
			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">发送方：</label>
                <div class="col-sm-8">
				<form:input path="fromUserName" htmlEscape="false" maxlength="45" class="form-control"/>
			    </div>
            </div>
			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">创建时间：</label>
                <div class="col-sm-8">
				<input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="form-control"
					value="<fmt:formatDate value="${weixinMsg.beginCreateTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> -
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="form-control"
					value="<fmt:formatDate value="${weixinMsg.endCreateTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			    </div>
            </div>
			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">消息类型：</label>
                <div class="col-sm-8">
				<form:select path="msgType" class="form-control">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('weixin_msg_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			    </div>
            </div>
			<div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">消息标题：</label>
                <div class="col-sm-8">
				<form:input path="title" htmlEscape="false" maxlength="55" class="form-control"/>
			    </div>
            </div>
			<input id="btnSubmit" class="btn btn-primary pull-right" type="submit" value="查询"/>


	</form:form>

	<table id="contentTable" style="margin-top: 10px" class="table table-hover">
		<thead>
			<tr>
				<th>接收方</td>
				<th>发送方</td>
				<th>创建时间</td>
				<th>消息类型</td>
				<th>文本消息内容</td>
				<th>消息id</td>
				<th>消息标题</td>
				<shiro:hasPermission name="weixin:weixinMsg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinMsg">
			<tr>
				<td><a href="${ctx}/weixin/weixinMsg/form?id=${weixinMsg.id}">
					${weixinMsg.toUserName}
				</a></td>
				<td>
					${weixinMsg.fromUserName}
				</td>
				<td>
					<fmt:formatDate value="${weixinMsg.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(weixinMsg.msgType, 'weixin_msg_type', '')}
				</td>
				<td>
					${weixinMsg.content}
				</td>
				<td>
					${weixinMsg.msgId}
				</td>
				<td>
					${weixinMsg.title}
				</td>
				<shiro:hasPermission name="weixin:weixinMsg:edit"><td>
    				<a href="${ctx}/weixin/weixinMsg/form?id=${weixinMsg.id}">修改</a>
					<a href="${ctx}/weixin/weixinMsg/delete?id=${weixinMsg.id}" onclick="return confirmx('确认要删除该消息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="box-tools">${page}</div>
</body>
</html>
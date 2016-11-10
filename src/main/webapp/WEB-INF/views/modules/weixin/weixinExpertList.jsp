<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专家管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/adminlte.jsp" %>
	<script type="text/javascript">

		var data = new Array();
		<c:forEach items="${page.list}" var="weixinExpert">
			data.push(${weixinExpert.jsonData});
		</c:forEach>

		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		function showDetail(_index){

			$('#detail_phone').html(data[_index].registerPhone);

			$('#detail_name').html(data[_index].name);
			$('#detail_email').html(data[_index].email);
			$('#detail_address').html(data[_index].address);
			$('#detail_company').html(data[_index].company);
//			var cers = data[_index].cerFile1;
//			var imgs = cers.split("|");
//
//			for(var i=0;i<imgs.length;i++){
//				$('#detail_cer').append('<img src="'+imgs[i]+'" width="100px" height="100px" />');
//			}

			$('#myModal').modal();
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/weixin/weixinExpert/">专家列表</a></li>
		<shiro:hasPermission name="weixin:weixinExpert:edit"><li><a href="${ctx}/weixin/weixinExpert/form">专家添加</a></li></shiro:hasPermission>
	</ul>

	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="weixinExpert" action="${ctx}/weixin/weixinExpert/" method="post" class="row form-horizontal well" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<div class="form-group col-sm-3">
			<label class="col-sm-4 control-label">手机号：</label>
			<div class="col-sm-8">
				<form:input path="registerPhone" htmlEscape="false" maxlength="45" class="form-control"/>
			</div>
		</div>
			<div class="form-group col-sm-3">
                <label class="col-sm-4 control-label">姓名：</label>
                <div class="col-sm-8">
				<form:input path="name" htmlEscape="false" maxlength="45" class="form-control"/>
			    </div>
            </div>
			<input id="btnSubmit" class="btn btn-primary pull-right" type="submit" value="查询"/>
		<div class="form-group col-sm-3">
			<label class="col-sm-4 control-label">职位：</label>
			<div class="col-sm-8">
				<form:select path="type" class="form-control">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('weixin_msg_customer_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>

	</form:form>

	<table id="contentTable" style="margin-top: 10px" class="table table-hover">
		<thead>
			<tr>
				<th>专家手机号</td>
				<th>姓名</td>
				<th>昵称</td>
				<th>所属企业</td>
				<th>累计提现</td>
				<th>暂不能结算金额</td>
				<th>欠账金额</td>
				<th>更新时间</td>
				<shiro:hasPermission name="weixin:weixinExpert:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinExpert" varStatus="status">
			<tr>

				<td><a href="${ctx}/weixin/weixinExpert/form?id=${weixinExpert.id}">
					${weixinExpert.registerPhone}
				</a></td>
				<td>
						${weixinExpert.name}
				</td>
				<td>
					${weixinExpert.nickName}
				</td>
				<td>
					${weixinExpert.company}
				</td>
				<td>
					${weixinExpert.withdraw}
				</td>
				<td>
					${weixinExpert.deposite}
				</td>
				<td>
					${weixinExpert.loan}
				</td>
				<td>
					<fmt:formatDate value="${weixinExpert.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="weixin:weixinExpert:edit"><td>
					<a href="#" data-toggle="modal" onclick="showDetail(${status.index});">查看详情</a>
    				<a href="${ctx}/weixin/weixinExpert/form?id=${weixinExpert.id}">修改</a>
					<a href="${ctx}/weixin/weixinExpert/delete?id=${weixinExpert.id}" onclick="return confirmx('确认要删除该专家吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="box-tools">${page}</div>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					<h4 class="modal-title" id="myModalLabel">个人资料</h4>
				</div>
				<div class="modal-body">
					<table width="100%">
						<tr>
							<td width="30%"><div style="margin-bottom: 10px;">注册手机号 :</div></td>
							<td><label id="detail_phone" style="padding-left: 5px;"></label></td>
						</tr>

						<tr>
							<td><div style="margin-bottom: 10px;">客户姓名 :</div></td>
							<td><label id="detail_name" style="padding-left: 5px;"></label></td>
						</tr>

						<tr>
							<td><div style="margin-bottom: 10px;">邮箱 :</div></td>
							<td><label id="detail_email" style="padding-left: 5px;"></label></td>
						</tr>

						<tr>
							<td><div style="margin-bottom: 10px;">居住区域 :</div></td>
							<td><label id="detail_address" style="padding-left: 5px;"></label></td>
						</tr>

						<tr>
							<td><div style="margin-bottom: 10px;">企业名称 :</div></td>
							<td><label id="detail_company" style="padding-left: 5px;"></label></td>
						</tr>

						<%--<tr>--%>
							<%--<td><div style="margin-bottom: 10px;">三证合一 :</div></td>--%>
							<%--<td><div id="detail_cer" style="padding-left: 5px;"></div></td>--%>
						<%--</tr>--%>
					</table>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
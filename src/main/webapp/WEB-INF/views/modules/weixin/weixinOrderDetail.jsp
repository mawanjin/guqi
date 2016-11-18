<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/include/adminlte.jsp" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<title>订单详情</title>
<head>
	<script src="${ctxStatic}/jquery-select2/3.4/select2.js"></script>
	<link href="${ctxStatic}/jquery-select2/3.4/select2.css" rel="stylesheet" />

	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnModifyForDetail').on('click',function(){
				$('#modal_label').modal();
			});
		});
	</script>
</head>
<body>
<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
	<div class="panel panel-default">
		<div class="panel-heading" role="tab" id="headingOne">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
					当前状态:收到需求
				</a>
			</h4>
		</div>
		<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
			<div class="panel-body">
				Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading" role="tab" id="headingTwo">
			<h4 class="panel-title">
				<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
					需求详情
				</a>
			</h4>
		</div>
		<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
			<div class="panel-body">
				<%--todo 根据用户选择的大分类 显示录入的用户具体需求--%>
					<button id="btnModifyForDetail" type="button" class="btn btn-default">修改或新建</button>
			</div>
		</div>
	</div>
</div>


<!-- Modal -->
<%--标签类--%>
<div class="modal fade" id="modal_label" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
						class="sr-only">Close</span></button>
				<h4 class="modal-title" id="myModalLabel">标签类</h4>
			</div>
			<div class="modal-body">
				<form:form id="inputForm" modelAttribute="weixinOrder" action="${ctx}/weixin/weixinOrder/save" method="post"  class="col-md-8" role="form">
					<div class="form-group">
						<label class="control-label">选择类目：</label>
						<div class="controls">
							<form:select path="subType"  style="width: 400px;">
								<form:options items="${fns:getDictList('weixin_purchase_sub_type_label')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>



				</form:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

</body>
</html>
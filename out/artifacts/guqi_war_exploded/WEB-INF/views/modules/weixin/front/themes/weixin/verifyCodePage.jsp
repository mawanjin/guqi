<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/modules/weixin/front/themes/weixin/include/head.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<title>手机号登录</title>
	<meta name="decorator" content="cms_default_${site.theme}" />
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />

	<script>
		$(document).ready(function() {
			$("#btnVerifyCode").on('click',function(){
				$.getJSON("${ctx}/weixin/get_verify_code?phone="+$('#phone').val(), function(json){
					alert(json.result);
				});
			});
		});
	</script>

</head>
<body>
<div id="test"></div>
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12 col-md-8 text-center" styl e="background-color: #1E9300;color: white; height: 40px;line-height: 40px;">验证码登录</div>
	</div>
</div>
<p class="text-center" style="margin-top: 25px;margin-bottom:25px;font-size: larger;">验收手机号</p>

	<form action="" class="form-horizontal" role="form" style="margin-left: 50px;margin-right: 50px;">

		<input type="hidden" id="phone" name="phone" value="${phone}">

		<div class="form-group">
			<div class="col-xs-7">
				<input type="number" class="form-control" id="verifyCode" placeholder="请输入验证吗">
			</div>
			<div class="col-xs-1">
				<button id="btnVerifyCode" type="button" class="btn btn-success">发送验证吗</button>
			</div>
		</div>
		<div class="form-group text-center" style="margin-top: 50px;">
			 <button type="button" class="btn btn-success">登录</button>
		</div>

	</form>


<!--底部-->
<div style="position: fixed;bottom: 0; margin-bottom: 30px;width: 100%; text-align: center;" >
	查看《iGoach用户协议》
</div>

</body>
</html>
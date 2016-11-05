<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/modules/weixin/front/themes/weixin/include/head.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="cms_default_${site.theme}" />
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />
	<style>
		body{
			margin: 0;
			padding:0;
		}
	</style>

	<script>
		$(document).ready(function() {
			$("#frm").validate({
				rules: {
					phone: {
						required: true,
						minlength: "11"
					}
				},
				messages: {
					phone: {
						required: "请输入手机号",
						minlength: "请输入正确位数的手机号"
					}
				}
			});
		});

	</script>

</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12 col-md-8 text-center" style="background-color: #1E9300;color: white; height: 40px;line-height: 40px;">登录</div>
	</div>
</div>

<p class="text-center" style="padding-top: 25px;padding-bottom:25px;font-size: larger;">验收手机号</p>

	<form id="frm" action="verifyIndex" class="form-horizontal" role="form" style="margin-left: 50px;margin-right: 50px;" method="post" >

		<div class="form-group ">
				<input type="number" class="form-control" name="phone" placeholder="请输入手机号(数字)"  maxlength="11"  />
		</div>
		<div class="form-group text-center" style="margin-top: 50px;">
			 <button type="submit" class="btn btn-success">下一步</button>
		</div>

	</form>

<!--底部-->
<div style="position: fixed;bottom: 0; margin-bottom: 30px;width: 100%; text-align: center;" >
	<img src="${ctxStatic}/images/userinfo.jpg" />
</div>

</body>
</html>
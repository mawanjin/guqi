<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/modules/weixin/front/weixin/include/head.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<title>手机号登录</title>
	<meta name="decorator" content="cms_default_${site.theme}" />
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />

	<script>
		$(document).ready(function() {

			$('#btnBack').on('click',function(){
				window.location.href="login";
			});

			$("#btnVerifyCode").on('click',sendCode);
		});

		function sendCode(){

			$('#btnVerifyCode').removeClass("btn-success");
			$('#btnVerifyCode').addClass("btn-default");
			$('#btnVerifyCode').unbind("click");
			$.getJSON("${ctx}/weixin/get_verify_code?phone="+$('#phone').val(), function(json){

				alert('验证码已经发送');
				var i = 60;
				//启动倒计时
				var countDown = setInterval(function(){

					$('#btnVerifyCode').html(i+"秒");
					i--;
					if(i==0){
						$('#btnVerifyCode').html("发送验证码");
						clearInterval(countDown);
						$('#btnVerifyCode').addClass("btn-success");
						$("#btnVerifyCode").on('click',sendCode);
					}
				},1000);
			});
		}
	</script>

</head>
<body>

<div id="btnBack" class="weixin_back_btn">返回</div>
<div class="container-fluid">
	<div class="row title_bg">

		<table width="100%" height="100%" style="color: white;">
			<tr>
				<td align="center">登录</td>
			</tr>
		</table>
	</div>
</div>

<p class="text-center" style="margin-top: 25px;margin-bottom:25px;font-size: larger;">验收手机号</p>

<form action="do_verify_code" class="form-horizontal" role="form" style="margin-left: 50px;margin-right: 50px;" method="post">

	<input type="hidden" id="phone" name="phone" value="${phone}">

	<div class="form-group">
		<div class="col-xs-7">
			<input type="number" class="form-control" id="verifyCode" name="verify_code" placeholder="请输入验证吗" required>
		</div>
		<div class="col-xs-1">
			<button id="btnVerifyCode" type="button" class="btn btn-success">发送验证码</button>
		</div>
	</div>
	<div class="form-group text-center" style="margin-top: 50px;">
		 <button type="submit" class="btn btn-success">登录</button>
	</div>

</form>

<sys:message content="${message}"/>

<!--底部-->
<div style="position: fixed;bottom: 0; margin-bottom: 30px;width: 100%; text-align: center;" >
	查看<a href="user_terms"> 《iGoach用户协议》</a>
</div>

</body>
</html>
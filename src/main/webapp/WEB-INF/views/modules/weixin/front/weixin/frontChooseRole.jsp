<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/modules/weixin/front/weixin/include/head.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<title>角色选择</title>
	<meta name="decorator" content="cms_default_${site.theme}" />
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />
	<style>
		body{
			margin: 0;
			padding:0;
		}
	</style>

</head>
<body>
<div id="btnBack" class="weixin_back_btn" onclick="window.history.back();">返回</div>
<div class="container-fluid">
	<div class="row title_bg">

		<table width="100%" height="100%" style="color: white;">
			<tr>
				<td align="center">登录</td>
			</tr>
		</table>
	</div>
</div>
<h1 class="text-center">请选择您的角色</h1>

<table width="100%" height="100px"  >
	<!--我是客户-->
	<tr>
		<td align="center">
			<div class="round">
				<table width="100%" height="100%" border="0">
					<tr>
						<td>
							<table height="100%" border="0">
								<tr>
									<td style="color: #000033;font-size: larger;">我是客户</td>
								</tr>
								<tr>
									<td style="color: #333333;">我来采购包装物品或定制方案</td>
								</tr>
							</table>
						</td>
						<td style="color: white;font-size: 30px;">
							>
						</td>
					</tr>
				</table>
			</div>
		</td>
	</tr>

	<!--我是专家-->
	<tr>
		<td align="center" style="padding-top: 40px;">
			<div class="round" style="background-color: #339933;">
				<table width="100%" height="100%" border="0">
					<tr>
						<td>
							<table height="100%" border="0">
								<tr>
									<td style="color: #000033;font-size: larger;">我是专家</td>
								</tr>
								<tr>
									<td style="color: #333333;">我来采购包装物品或定制方案</td>
								</tr>
							</table>
						</td>
						<td style="color: white;font-size: 30px;">
							>
						</td>
					</tr>
				</table>
			</div>
		</td>
	</tr>

	<!--我是供应商-->
	<tr>
		<td align="center" style="padding-top: 40px;">
			<div class="round" style="background-color: #CC6633;">
				<table width="100%" height="100%" border="0">
					<tr>
						<td>
							<table height="100%" border="0">
								<tr>
									<td style="color: #000033;font-size: larger;">我是供应商</td>
								</tr>
								<tr>
									<td style="color: #333333;">我来采购包装物品或定制方案</td>
								</tr>
							</table>
						</td>
						<td style="color: white;font-size: 30px;">
							>
						</td>
					</tr>
				</table>
			</div>
		</td>
	</tr>
</table>

</body>
</html>
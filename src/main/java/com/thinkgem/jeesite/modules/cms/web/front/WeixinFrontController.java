/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web.front;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.servlet.ValidateCodeServlet;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.*;
import com.thinkgem.jeesite.modules.cms.service.*;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 网站Controller
 * @author ThinkGem
 * @version 2013-5-29
 */
@Controller
@RequestMapping(value = "${frontPath}/weixin")
public class WeixinFrontController extends BaseController{
	
	/**
	 * 网站首页
	 */
	@RequestMapping("/login")
	public String index(Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("isIndex", true);
		return "modules/weixin/front/themes/weixin/frontIndex";
	}

	/**
	 * 来到发送验证码页面
	 */
	@RequestMapping("/verifyIndex")
	public String verifyCodePage(Model model,@RequestParam(value = "phone",required = true) String phone) {

		model.addAttribute("phone", phone);
		return "modules/weixin/front/themes/weixin/verifyCodePage";
	}


	@RequestMapping(value = "/get_verify_code",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getVerifyCode(@RequestParam(value = "phone",required = true) String phone){

		return "{ \"result\": \"0\", \"code\": \"1001\"}";
	}

}

/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web.front;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.*;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
		return "modules/weixin/front/weixin/frontIndex";
	}

	/**
	 * 来到发送验证码页面
	 */
	@RequestMapping("/verifyIndex")
	public String verifyCodePage(Model model,@RequestParam(value = "phone",required = true) String phone) {
		model.addAttribute("phone", phone);
		return "modules/weixin/front/weixin/verifyCodePage";
	}

	/**
	 * 调取短信接口发送验证码给用户
	 */
	@RequestMapping(value = "/get_verify_code",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getVerifyCode(@RequestParam(value = "phone",required = true) String phone){
		String code = "1001";
		CacheUtils.put(CacheUtils.Weixin_CACHE_VERIFY_CODE,code);
		return "{ \"result\": \"0\", \"code\": \"1001\"}";
	}

	/**
	 * 验证验证码
	 */
	@RequestMapping("/do_verify_code")
	public String doVerifyCode(Model model,@RequestParam(value = "phone",required = true) String phone,@RequestParam(value = "verify_code",required = true) String code) {
		String oCode = CacheUtils.get(CacheUtils.Weixin_CACHE_VERIFY_CODE).toString();
		if(oCode.equals(code)){//登录成功
			Session session = UserUtils.getSession();
			session.setAttribute("login",true);
		}
		return "modules/weixin/front/weixin/verifyCodePage";
	}

	/**
	 * 协议页面
	 */
	@RequestMapping("/user_terms")
	public String userTerms() {
		return "modules/weixin/front/weixin/user_terms";
	}

}

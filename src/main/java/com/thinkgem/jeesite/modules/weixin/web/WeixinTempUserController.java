/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinTempUser;
import com.thinkgem.jeesite.modules.weixin.service.WeixinTempUserService;

/**
 * 临时用户表，关注并注册了，但未完善资料Controller
 * @author mawj
 * @version 2016-11-09
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinTempUser")
public class WeixinTempUserController extends BaseController {

	@Autowired
	private WeixinTempUserService weixinTempUserService;
	
	@ModelAttribute
	public WeixinTempUser get(@RequestParam(required=false) String id) {
		WeixinTempUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinTempUserService.get(id);
		}
		if (entity == null){
			entity = new WeixinTempUser();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixinTempUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinTempUser weixinTempUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinTempUser> page = weixinTempUserService.findPage(new Page<WeixinTempUser>(request, response), weixinTempUser);
		//取24小时内注册了多少个

		model.addAttribute("registerCount", weixinTempUserService.getRegisterCount());
		model.addAttribute("page", page);
		return "modules/weixin/weixinTempUserList";
	}

	@RequiresPermissions("weixin:weixinTempUser:view")
	@RequestMapping(value = "form")
	public String form(WeixinTempUser weixinTempUser, Model model) {
		model.addAttribute("weixinTempUser", weixinTempUser);
		return "modules/weixin/weixinTempUserForm";
	}

	@RequiresPermissions("weixin:weixinTempUser:edit")
	@RequestMapping(value = "save")
	public String save(WeixinTempUser weixinTempUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinTempUser)){
			return form(weixinTempUser, model);
		}
		weixinTempUserService.save(weixinTempUser);
		addMessage(redirectAttributes, "保存临时用户成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinTempUser/?repage";
	}
	
	@RequiresPermissions("weixin:weixinTempUser:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinTempUser weixinTempUser, RedirectAttributes redirectAttributes) {
		weixinTempUserService.delete(weixinTempUser);
		addMessage(redirectAttributes, "删除临时用户成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinTempUser/?repage";
	}

}
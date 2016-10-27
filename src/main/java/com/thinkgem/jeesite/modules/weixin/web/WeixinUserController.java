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
import com.thinkgem.jeesite.modules.weixin.entity.WeixinUser;
import com.thinkgem.jeesite.modules.weixin.service.WeixinUserService;

/**
 * 用户主表Controller
 * @author mawj
 * @version 2016-10-27
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinUser")
public class WeixinUserController extends BaseController {

	@Autowired
	private WeixinUserService weixinUserService;
	
	@ModelAttribute
	public WeixinUser get(@RequestParam(required=false) String id) {
		WeixinUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinUserService.get(id);
		}
		if (entity == null){
			entity = new WeixinUser();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixinUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinUser weixinUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinUser> page = weixinUserService.findPage(new Page<WeixinUser>(request, response), weixinUser); 
		model.addAttribute("page", page);
		return "modules/weixin/weixinUserList";
	}

	@RequiresPermissions("weixin:weixinUser:view")
	@RequestMapping(value = "form")
	public String form(WeixinUser weixinUser, Model model) {
		model.addAttribute("weixinUser", weixinUser);
		return "modules/weixin/weixinUserForm";
	}

	@RequiresPermissions("weixin:weixinUser:edit")
	@RequestMapping(value = "save")
	public String save(WeixinUser weixinUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinUser)){
			return form(weixinUser, model);
		}
		weixinUserService.save(weixinUser);
		addMessage(redirectAttributes, "保存用户成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinUser/?repage";
	}
	
	@RequiresPermissions("weixin:weixinUser:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinUser weixinUser, RedirectAttributes redirectAttributes) {
		weixinUserService.delete(weixinUser);
		addMessage(redirectAttributes, "删除用户成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinUser/?repage";
	}

}
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
import com.thinkgem.jeesite.modules.weixin.entity.WeixinExpert;
import com.thinkgem.jeesite.modules.weixin.service.WeixinExpertService;

/**
 * 专家表Controller
 * @author mawj
 * @version 2016-11-10
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinExpert")
public class WeixinExpertController extends BaseController {

	@Autowired
	private WeixinExpertService weixinExpertService;
	
	@ModelAttribute
	public WeixinExpert get(@RequestParam(required=false) String id) {
		WeixinExpert entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinExpertService.get(id);
		}
		if (entity == null){
			entity = new WeixinExpert();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixinExpert:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinExpert weixinExpert, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinExpert> page = weixinExpertService.findPage(new Page<WeixinExpert>(request, response), weixinExpert); 
		model.addAttribute("page", page);
		return "modules/weixin/weixinExpertList";
	}

	@RequiresPermissions("weixin:weixinExpert:view")
	@RequestMapping(value = "form")
	public String form(WeixinExpert weixinExpert, Model model) {
		model.addAttribute("weixinExpert", weixinExpert);
		return "modules/weixin/weixinExpertForm";
	}

	@RequiresPermissions("weixin:weixinExpert:edit")
	@RequestMapping(value = "save")
	public String save(WeixinExpert weixinExpert, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinExpert)){
			return form(weixinExpert, model);
		}
		weixinExpertService.save(weixinExpert);
		addMessage(redirectAttributes, "保存专家成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinExpert/?repage";
	}
	
	@RequiresPermissions("weixin:weixinExpert:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinExpert weixinExpert, RedirectAttributes redirectAttributes) {
		weixinExpertService.delete(weixinExpert);
		addMessage(redirectAttributes, "删除专家成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinExpert/?repage";
	}

}
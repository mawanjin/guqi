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
import com.thinkgem.jeesite.modules.weixin.entity.WeixinSubscriber;
import com.thinkgem.jeesite.modules.weixin.service.WeixinSubscriberService;

/**
 * 已关注微信用户Controller
 * @author mawj
 * @version 2016-10-31
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinSubscriber")
public class WeixinSubscriberController extends BaseController {

	@Autowired
	private WeixinSubscriberService weixinSubscriberService;
	
	@ModelAttribute
	public WeixinSubscriber get(@RequestParam(required=false) String id) {
		WeixinSubscriber entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinSubscriberService.get(id);
		}
		if (entity == null){
			entity = new WeixinSubscriber();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixinSubscriber:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinSubscriber weixinSubscriber, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinSubscriber> page = weixinSubscriberService.findPage(new Page<WeixinSubscriber>(request, response), weixinSubscriber); 
		model.addAttribute("page", page);
		return "modules/weixin/weixinSubscriberList";
	}

	@RequiresPermissions("weixin:weixinSubscriber:view")
	@RequestMapping(value = "form")
	public String form(WeixinSubscriber weixinSubscriber, Model model) {
		model.addAttribute("weixinSubscriber", weixinSubscriber);
		return "modules/weixin/weixinSubscriberForm";
	}

	@RequiresPermissions("weixin:weixinSubscriber:edit")
	@RequestMapping(value = "save")
	public String save(WeixinSubscriber weixinSubscriber, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinSubscriber)){
			return form(weixinSubscriber, model);
		}
		weixinSubscriberService.save(weixinSubscriber);
		addMessage(redirectAttributes, "保存微信用户成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinSubscriber/?repage";
	}
	
	@RequiresPermissions("weixin:weixinSubscriber:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinSubscriber weixinSubscriber, RedirectAttributes redirectAttributes) {
		weixinSubscriberService.delete(weixinSubscriber);
		addMessage(redirectAttributes, "删除微信用户成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinSubscriber/?repage";
	}

}
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
import com.thinkgem.jeesite.modules.weixin.entity.WeixinMsg;
import com.thinkgem.jeesite.modules.weixin.service.WeixinMsgService;

/**
 * 所有微信的消息收发表Controller
 * @author mawj
 * @version 2016-10-28
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinMsg")
public class WeixinMsgController extends BaseController {

	@Autowired
	private WeixinMsgService weixinMsgService;
	
	@ModelAttribute
	public WeixinMsg get(@RequestParam(required=false) String id) {
		WeixinMsg entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinMsgService.get(id);
		}
		if (entity == null){
			entity = new WeixinMsg();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixinMsg:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinMsg weixinMsg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinMsg> page = weixinMsgService.findPage(new Page<WeixinMsg>(request, response), weixinMsg); 
		model.addAttribute("page", page);
		return "modules/weixin/weixinMsgList";
	}

	@RequiresPermissions("weixin:weixinMsg:view")
	@RequestMapping(value = "form")
	public String form(WeixinMsg weixinMsg, Model model) {
		model.addAttribute("weixinMsg", weixinMsg);
		return "modules/weixin/weixinMsgForm";
	}

	@RequiresPermissions("weixin:weixinMsg:edit")
	@RequestMapping(value = "save")
	public String save(WeixinMsg weixinMsg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinMsg)){
			return form(weixinMsg, model);
		}
		weixinMsgService.save(weixinMsg);
		addMessage(redirectAttributes, "保存消息成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinMsg/?repage";
	}
	
	@RequiresPermissions("weixin:weixinMsg:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinMsg weixinMsg, RedirectAttributes redirectAttributes) {
		weixinMsgService.delete(weixinMsg);
		addMessage(redirectAttributes, "删除消息成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinMsg/?repage";
	}

}
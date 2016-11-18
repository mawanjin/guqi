/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.weixin.exception.WeiXinException;
import com.thinkgem.jeesite.modules.weixin.service.WeixinAPIService;
import com.thinkgem.jeesite.modules.weixin.service.WeixinMsgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.thinkgem.jeesite.modules.weixin.entity.WeixinCustom;
import com.thinkgem.jeesite.modules.weixin.service.WeixinCustomService;

/**
 * 微信客服信息表Controller
 * @author mawj
 * @version 2016-10-31
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinCustom")
public class WeixinCustomController extends BaseController {

	@Autowired
	private WeixinCustomService weixinCustomService;
	@Autowired
	WeixinAPIService weixinAPIService;
	
	@ModelAttribute
	public WeixinCustom get(@RequestParam(required=false) String id) {
		WeixinCustom entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinCustomService.get(id);
		}
		if (entity == null){
			entity = new WeixinCustom();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixinCustom:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinCustom weixinCustom, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinCustom> page = null;
		try {
			page = weixinCustomService.getList(weixinCustom,weixinAPIService.getOrNewToken());
			model.addAttribute("page", page);
		} catch (WeiXinException e) {
			e.printStackTrace();
		}
		return "modules/weixin/weixinCustomList";
	}

	@RequiresPermissions("weixin:weixinCustom:view")
	@RequestMapping(value = "form")
	public String form(WeixinCustom weixinCustom, Model model) {
		model.addAttribute("weixinCustom", weixinCustom);
		return "modules/weixin/weixinCustomForm";
	}

	@RequiresPermissions("weixin:weixinCustom:edit")
	@RequestMapping(value = "save")
	public String save(WeixinCustom weixinCustom, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinCustom)){
			return form(weixinCustom, model);
		}

		try {
			weixinCustomService.save(weixinCustom,weixinAPIService.getOrNewToken());
			addMessage(redirectAttributes, "保存客服成功");
		} catch (WeiXinException e) {
			e.printStackTrace();
			addMessage(redirectAttributes, e.getMessage());
		}

		return "redirect:"+Global.getAdminPath()+"/weixin/weixinCustom/?repage";
	}
	
	@RequiresPermissions("weixin:weixinCustom:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinCustom weixinCustom, RedirectAttributes redirectAttributes) {
		try {
			weixinCustomService.delete(weixinCustom,weixinAPIService.getOrNewToken());
			addMessage(redirectAttributes, "删除客服成功");
		} catch (WeiXinException e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "删除客服失败");
		}

		return "redirect:"+Global.getAdminPath()+"/weixin/weixinCustom/?repage";
	}

}
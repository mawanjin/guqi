/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.modules.weixin.http.WeixinHttpCore;
import com.thinkgem.jeesite.modules.weixin.service.WeixinAPIService;
import com.thinkgem.jeesite.modules.weixin.vo.TemplateItemEMS;
import com.thinkgem.jeesite.modules.weixin.vo.WeixinTemplateMsg;
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
import com.thinkgem.jeesite.modules.weixin.entity.WeixinTemplate;
import com.thinkgem.jeesite.modules.weixin.service.WeixinTemplateService;

import java.util.List;

/**
 * 模板消息Controller
 * @author mawj
 * @version 2016-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinTemplate")
public class WeixinTemplateController extends BaseController {

	@Autowired
	WeixinHttpCore weixinHttpCore;

	@Autowired
	WeixinAPIService weixinAPIService;

	@Autowired
	private WeixinTemplateService weixinTemplateService;
	
	@ModelAttribute
	public WeixinTemplate get(@RequestParam(required=false) String id) {
		WeixinTemplate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinTemplateService.get(id);
		}
		if (entity == null){
			entity = new WeixinTemplate();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixinTemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinTemplate weixinTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinTemplate> page = weixinTemplateService.findPage(new Page<WeixinTemplate>(request, response), weixinTemplate);
		List<WeixinTemplate> data = page.getList();
		for(WeixinTemplate template:data){
			weixinTemplateService.wrapForPage(template);
		}

		model.addAttribute("page", page);
		return "modules/weixin/weixinTemplateList";
	}

	@RequiresPermissions("weixin:weixinTemplate:view")
	@RequestMapping(value = "form")
	public String form(WeixinTemplate weixinTemplate, Model model) {
		weixinTemplateService.wrapForPage(weixinTemplate);
		model.addAttribute("weixinTemplate", weixinTemplate);
		return "modules/weixin/weixinTemplateForm";
	}

	@RequiresPermissions("weixin:weixinTemplate:edit")
	@RequestMapping(value = "save")
	public String save(WeixinTemplate weixinTemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinTemplate)){
			return form(weixinTemplate, model);
		}
		weixinTemplateService.save(weixinTemplate);
		addMessage(redirectAttributes, "保存模板消息成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinTemplate/?repage";
	}
	
	@RequiresPermissions("weixin:weixinTemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinTemplate weixinTemplate, RedirectAttributes redirectAttributes) {
		weixinTemplateService.delete(weixinTemplate);
		addMessage(redirectAttributes, "删除模板消息成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinTemplate/?repage";
	}

	/**
	 * 向微信服务器发送发送请求
	 */
	public void syn(WeixinTemplate weixinTemplate){
		WeixinTemplateMsg msg = new WeixinTemplateMsg();
		msg.setUrl(weixinTemplate.getUrl());
		msg.setTemplateId(weixinTemplate.getTemplateId());
		msg.setTopcolor("#000033");
		msg.setTouser(weixinTemplate.getOpenid());
		if(weixinTemplate.getTemplateId().equals("Hz9CBDxAX8iwbTxWXu3ec9DXGhe4U_iOGsWtCpml9Pg")){
			TemplateItemEMS itemEMS = new TemplateItemEMS();
			itemEMS.getFirst().setValue("亲,你的货物已经启程了,好想快点来到你身边");
			itemEMS.getDelivername().setValue(weixinTemplate.getDeliverName());
			itemEMS.getOrdername().setValue(weixinTemplate.getOrderName());
			itemEMS.getProductName().setValue(weixinTemplate.getProductName());
			itemEMS.getProductCount().setValue(weixinTemplate.getProductCount());
			itemEMS.getRemark().setValue(weixinTemplate.getRemark());
			msg.setData(itemEMS);
		}

		weixinHttpCore.sendTemplateMsg(weixinAPIService.getOrNewToken(),msg);
	}
}
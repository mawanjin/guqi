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
import com.thinkgem.jeesite.modules.weixin.entity.WeixinSupplier;
import com.thinkgem.jeesite.modules.weixin.service.WeixinSupplierService;

/**
 * 供应商表Controller
 * @author mawj
 * @version 2016-11-10
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinSupplier")
public class WeixinSupplierController extends BaseController {

	@Autowired
	private WeixinSupplierService weixinSupplierService;
	
	@ModelAttribute
	public WeixinSupplier get(@RequestParam(required=false) String id) {
		WeixinSupplier entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinSupplierService.get(id);
		}
		if (entity == null){
			entity = new WeixinSupplier();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixinSupplier:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinSupplier weixinSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinSupplier> page = weixinSupplierService.findPage(new Page<WeixinSupplier>(request, response), weixinSupplier); 
		model.addAttribute("page", page);
		return "modules/weixin/weixinSupplierList";
	}

	@RequiresPermissions("weixin:weixinSupplier:view")
	@RequestMapping(value = "form")
	public String form(WeixinSupplier weixinSupplier, Model model) {
		model.addAttribute("weixinSupplier", weixinSupplier);
		return "modules/weixin/weixinSupplierForm";
	}

	@RequiresPermissions("weixin:weixinSupplier:edit")
	@RequestMapping(value = "save")
	public String save(WeixinSupplier weixinSupplier, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinSupplier)){
			return form(weixinSupplier, model);
		}
		weixinSupplierService.save(weixinSupplier);
		addMessage(redirectAttributes, "保存供应商成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinSupplier/?repage";
	}
	
	@RequiresPermissions("weixin:weixinSupplier:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinSupplier weixinSupplier, RedirectAttributes redirectAttributes) {
		weixinSupplierService.delete(weixinSupplier);
		addMessage(redirectAttributes, "删除供应商成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinSupplier/?repage";
	}

}
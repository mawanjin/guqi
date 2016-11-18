/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinUser;
import com.thinkgem.jeesite.modules.weixin.service.WeixinCustomService;
import com.thinkgem.jeesite.modules.weixin.service.WeixinUserService;
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
import com.thinkgem.jeesite.modules.weixin.entity.WeixinOrder;
import com.thinkgem.jeesite.modules.weixin.service.WeixinOrderService;

/**
 * 订单基础表Controller
 * @author mawj
 * @version 2016-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinOrder")
public class WeixinOrderController extends BaseController {

	@Autowired
	private WeixinOrderService weixinOrderService;
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private WeixinCustomService weixinCustomService;
	
	@ModelAttribute
	public WeixinOrder get(@RequestParam(required=false) String id) {
		WeixinOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinOrderService.get(id);
		}
		if (entity == null){
			entity = new WeixinOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixinOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinOrder weixinOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinOrder> page = weixinOrderService.findPage(new Page<WeixinOrder>(request, response), weixinOrder); 
		model.addAttribute("page", page);
		return "modules/weixin/weixinOrderList";
	}

	@RequiresPermissions("weixin:weixinOrder:view")
	@RequestMapping(value = "form")
	public String form(WeixinOrder weixinOrder, Model model) {

		String orderId = IdGen.randomLong()+"";
		if(weixinOrder.getOrderId()==null){
			weixinOrder = new WeixinOrder();
			weixinOrder.setOrderId(orderId);
			weixinOrder.setAuctionType("0");
			model.addAttribute("weixinUsers", weixinUserService.findAllList());
			model.addAttribute("weixinCustomers", weixinCustomService.findAllList());
		}
		model.addAttribute("weixinOrder", weixinOrder);

		return "modules/weixin/weixinOrderForm";
	}

	@RequiresPermissions("weixin:weixinOrder:edit")
	@RequestMapping(value = "save")
	public String save(WeixinOrder weixinOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinOrder)){
			return form(weixinOrder, model);
		}
		weixinOrderService.save(weixinOrder);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinOrder/?repage";
	}
	
	@RequiresPermissions("weixin:weixinOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinOrder weixinOrder, RedirectAttributes redirectAttributes) {
		weixinOrderService.delete(weixinOrder);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinOrder/?repage";
	}

	@RequiresPermissions("weixin:weixinOrder:edit")
	@RequestMapping(value = "detail")
	public String detail(WeixinOrder weixinOrder, Model model) {
		model.addAttribute("weixinOrder", weixinOrder);
		return "modules/weixin/weixinOrderDetail";
	}


}
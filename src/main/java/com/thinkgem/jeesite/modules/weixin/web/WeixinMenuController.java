/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.weixin.exception.WeiXinException;
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
import com.thinkgem.jeesite.modules.weixin.entity.WeixinMenu;
import com.thinkgem.jeesite.modules.weixin.service.WeixinMenuService;

import java.util.List;

/**
 * 菜单Controller
 * @author mawj
 * @version 2016-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinMenu")
public class WeixinMenuController extends BaseController {

	@Autowired
	private WeixinMenuService weixinMenuService;
	
	@ModelAttribute
	public WeixinMenu get(@RequestParam(required=false) String id) {
		WeixinMenu entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinMenuService.get(id);
		}
		if (entity == null){
			entity = new WeixinMenu();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixinMenu:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinMenu weixinMenu, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinMenu> page = null;
		try {
			page = weixinMenuService.findPageMenu(new Page<WeixinMenu>(request, response), weixinMenu);
			List<WeixinMenu> menus = page.getList();
			for(WeixinMenu menu:menus){

				if(menu.getParent().equals("0")){
					menu.setParentName("-");
					continue;
				}

				for(WeixinMenu _m : menus){
					if(menu.getParent().equals(_m.getId())){
						menu.setParentName(_m.getName());
						break;
					}
				}
			}

			List<WeixinMenu> list = Lists.newArrayList();
			WeixinMenu.sortList(list, page.getList(), "0");
			page.setList(list);
			model.addAttribute("page", page);
		} catch (WeiXinException e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		return "modules/weixin/weixinMenuList";
	}

	@RequiresPermissions("weixin:weixinMenu:view")
	@RequestMapping(value = "form")
	public String form(WeixinMenu weixinMenu, Model model) {

		model.addAttribute("menuSelect",  weixinMenuService.getMenuSelect(weixinMenu.getId()));
		model.addAttribute("weixinMenu", weixinMenu);
		return "modules/weixin/weixinMenuForm";
	}

	@RequiresPermissions("weixin:weixinMenu:edit")
	@RequestMapping(value = "save")
	public String save(WeixinMenu weixinMenu, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinMenu)){
			return form(weixinMenu, model);
		}
		weixinMenuService.save(weixinMenu,true);
		addMessage(redirectAttributes, "保存菜单成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinMenu/?repage";
	}
	
	@RequiresPermissions("weixin:weixinMenu:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinMenu weixinMenu, RedirectAttributes redirectAttributes) {
		weixinMenuService.delete(weixinMenu);

		addMessage(redirectAttributes, "删除菜单成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinMenu/?repage";
	}


	@RequestMapping(value = "syn")
	public String syn(RedirectAttributes redirectAttributes) {
		if(weixinMenuService.synMenu())
			addMessage(redirectAttributes, "同步菜单成功");
		else
			addMessage(redirectAttributes, "同步菜单失败");

		return "redirect:"+Global.getAdminPath()+"/weixin/weixinMenu/?repage";
	}



}
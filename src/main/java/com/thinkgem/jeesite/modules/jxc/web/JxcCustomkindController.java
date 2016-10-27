/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jxc.web;

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
import com.thinkgem.jeesite.modules.jxc.entity.JxcCustomkind;
import com.thinkgem.jeesite.modules.jxc.service.JxcCustomkindService;

/**
 * 客户类别Controller
 * @author 马万进
 * @version 2016-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/jxc/jxcCustomkind")
public class JxcCustomkindController extends BaseController {

	@Autowired
	private JxcCustomkindService jxcCustomkindService;
	
	@ModelAttribute
	public JxcCustomkind get(@RequestParam(required=false) String id) {
		JxcCustomkind entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = jxcCustomkindService.get(id);
		}
		if (entity == null){
			entity = new JxcCustomkind();
		}
		return entity;
	}
	
	@RequiresPermissions("jxc:jxcCustomkind:view")
	@RequestMapping(value = {"list", ""})
	public String list(JxcCustomkind jxcCustomkind, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<JxcCustomkind> page = jxcCustomkindService.findPage(new Page<JxcCustomkind>(request, response), jxcCustomkind); 
		model.addAttribute("page", page);
		return "modules/jxc/jxcCustomkindList";
	}

	@RequiresPermissions("jxc:jxcCustomkind:view")
	@RequestMapping(value = "form")
	public String form(JxcCustomkind jxcCustomkind, Model model) {
		model.addAttribute("jxcCustomkind", jxcCustomkind);
		return "modules/jxc/jxcCustomkindForm";
	}

	@RequiresPermissions("jxc:jxcCustomkind:edit")
	@RequestMapping(value = "save")
	public String save(JxcCustomkind jxcCustomkind, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, jxcCustomkind)){
			return form(jxcCustomkind, model);
		}
		jxcCustomkindService.save(jxcCustomkind);
		addMessage(redirectAttributes, "保存客户类别成功");
		return "redirect:"+Global.getAdminPath()+"/jxc/jxcCustomkind/?repage";
	}
	
	@RequiresPermissions("jxc:jxcCustomkind:edit")
	@RequestMapping(value = "delete")
	public String delete(JxcCustomkind jxcCustomkind, RedirectAttributes redirectAttributes) {
		jxcCustomkindService.delete(jxcCustomkind);
		addMessage(redirectAttributes, "删除客户类别成功");
		return "redirect:"+Global.getAdminPath()+"/jxc/jxcCustomkind/?repage";
	}

}
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
import com.thinkgem.jeesite.modules.jxc.entity.JxcIncomekind;
import com.thinkgem.jeesite.modules.jxc.service.JxcIncomekindService;

/**
 * 收入类别Controller
 * @author 马万进
 * @version 2016-07-23
 */
@Controller
@RequestMapping(value = "${adminPath}/jxc/jxcIncomekind")
public class JxcIncomekindController extends BaseController {

	@Autowired
	private JxcIncomekindService jxcIncomekindService;
	
	@ModelAttribute
	public JxcIncomekind get(@RequestParam(required=false) String id) {
		JxcIncomekind entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = jxcIncomekindService.get(id);
		}
		if (entity == null){
			entity = new JxcIncomekind();
		}
		return entity;
	}
	
	@RequiresPermissions("jxc:jxcIncomekind:view")
	@RequestMapping(value = {"list", ""})
	public String list(JxcIncomekind jxcIncomekind, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<JxcIncomekind> page = jxcIncomekindService.findPage(new Page<JxcIncomekind>(request, response), jxcIncomekind); 
		model.addAttribute("page", page);
		return "modules/jxc/jxcIncomekindList";
	}

	@RequiresPermissions("jxc:jxcIncomekind:view")
	@RequestMapping(value = "form")
	public String form(JxcIncomekind jxcIncomekind, Model model) {
		model.addAttribute("jxcIncomekind", jxcIncomekind);
		return "modules/jxc/jxcIncomekindForm";
	}

	@RequiresPermissions("jxc:jxcIncomekind:edit")
	@RequestMapping(value = "save")
	public String save(JxcIncomekind jxcIncomekind, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, jxcIncomekind)){
			return form(jxcIncomekind, model);
		}
		jxcIncomekindService.save(jxcIncomekind);
		addMessage(redirectAttributes, "保存收入类别成功");
		return "redirect:"+Global.getAdminPath()+"/jxc/jxcIncomekind/?repage";
	}
	
	@RequiresPermissions("jxc:jxcIncomekind:edit")
	@RequestMapping(value = "delete")
	public String delete(JxcIncomekind jxcIncomekind, RedirectAttributes redirectAttributes) {
		jxcIncomekindService.delete(jxcIncomekind);
		addMessage(redirectAttributes, "删除收入类别成功");
		return "redirect:"+Global.getAdminPath()+"/jxc/jxcIncomekind/?repage";
	}

}
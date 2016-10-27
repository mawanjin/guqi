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
import com.thinkgem.jeesite.modules.jxc.entity.JxcOutcomekind;
import com.thinkgem.jeesite.modules.jxc.service.JxcOutcomekindService;

/**
 * 支出类别Controller
 * @author 马万进
 * @version 2016-07-23
 */
@Controller
@RequestMapping(value = "${adminPath}/jxc/jxcOutcomekind")
public class JxcOutcomekindController extends BaseController {

	@Autowired
	private JxcOutcomekindService jxcOutcomekindService;
	
	@ModelAttribute
	public JxcOutcomekind get(@RequestParam(required=false) String id) {
		JxcOutcomekind entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = jxcOutcomekindService.get(id);
		}
		if (entity == null){
			entity = new JxcOutcomekind();
		}
		return entity;
	}
	
	@RequiresPermissions("jxc:jxcOutcomekind:view")
	@RequestMapping(value = {"list", ""})
	public String list(JxcOutcomekind jxcOutcomekind, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<JxcOutcomekind> page = jxcOutcomekindService.findPage(new Page<JxcOutcomekind>(request, response), jxcOutcomekind); 
		model.addAttribute("page", page);
		return "modules/jxc/jxcOutcomekindList";
	}

	@RequiresPermissions("jxc:jxcOutcomekind:view")
	@RequestMapping(value = "form")
	public String form(JxcOutcomekind jxcOutcomekind, Model model) {
		model.addAttribute("jxcOutcomekind", jxcOutcomekind);
		return "modules/jxc/jxcOutcomekindForm";
	}

	@RequiresPermissions("jxc:jxcOutcomekind:edit")
	@RequestMapping(value = "save")
	public String save(JxcOutcomekind jxcOutcomekind, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, jxcOutcomekind)){
			return form(jxcOutcomekind, model);
		}
		jxcOutcomekindService.save(jxcOutcomekind);
		addMessage(redirectAttributes, "保存支出类别成功");
		return "redirect:"+Global.getAdminPath()+"/jxc/jxcOutcomekind/?repage";
	}
	
	@RequiresPermissions("jxc:jxcOutcomekind:edit")
	@RequestMapping(value = "delete")
	public String delete(JxcOutcomekind jxcOutcomekind, RedirectAttributes redirectAttributes) {
		jxcOutcomekindService.delete(jxcOutcomekind);
		addMessage(redirectAttributes, "删除支出类别成功");
		return "redirect:"+Global.getAdminPath()+"/jxc/jxcOutcomekind/?repage";
	}

}
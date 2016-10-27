/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.web;

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
import com.thinkgem.jeesite.modules.test.entity.NewTable;
import com.thinkgem.jeesite.modules.test.service.NewTableService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2016-08-31
 */
@Controller
@RequestMapping(value = "${adminPath}/test/newTable")
public class NewTableController extends BaseController {

	@Autowired
	private NewTableService newTableService;
	
	@ModelAttribute
	public NewTable get(@RequestParam(required=false) String id) {
		NewTable entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = newTableService.get(id);
		}
		if (entity == null){
			entity = new NewTable();
		}
		return entity;
	}
	
	@RequiresPermissions("test:newTable:view")
	@RequestMapping(value = {"list", ""})
	public String list(NewTable newTable, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<NewTable> page = newTableService.findPage(new Page<NewTable>(request, response), newTable); 
		model.addAttribute("page", page);
		return "modules/test/newTableList";
	}

	@RequiresPermissions("test:newTable:view")
	@RequestMapping(value = "form")
	public String form(NewTable newTable, Model model) {
		model.addAttribute("newTable", newTable);
		return "modules/test/newTableForm";
	}

	@RequiresPermissions("test:newTable:edit")
	@RequestMapping(value = "save")
	public String save(NewTable newTable, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, newTable)){
			return form(newTable, model);
		}
		newTableService.save(newTable);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/test/newTable/?repage";
	}
	
	@RequiresPermissions("test:newTable:edit")
	@RequestMapping(value = "delete")
	public String delete(NewTable newTable, RedirectAttributes redirectAttributes) {
		newTableService.delete(newTable);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/test/newTable/?repage";
	}

}
/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jxc.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.jxc.entity.JxcProductkind;
import com.thinkgem.jeesite.modules.jxc.service.JxcProductkindService;

/**
 * 商品类别Controller
 * @author 马万进
 * @version 2016-07-23
 */
@Controller
@RequestMapping(value = "${adminPath}/jxc/jxcProductkind")
public class JxcProductkindController extends BaseController {

	@Autowired
	private JxcProductkindService jxcProductkindService;
	
	@ModelAttribute
	public JxcProductkind get(@RequestParam(required=false) String id) {
		JxcProductkind entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = jxcProductkindService.get(id);
		}
		if (entity == null){
			entity = new JxcProductkind();
		}
		return entity;
	}
	
	@RequiresPermissions("jxc:jxcProductkind:view")
	@RequestMapping(value = {"list", ""})
	public String list(JxcProductkind jxcProductkind, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<JxcProductkind> list = jxcProductkindService.findList(jxcProductkind); 
		model.addAttribute("list", list);
		return "modules/jxc/jxcProductkindList";
	}

	@RequiresPermissions("jxc:jxcProductkind:view")
	@RequestMapping(value = "form")
	public String form(JxcProductkind jxcProductkind, Model model) {
		if (jxcProductkind.getParent()!=null && StringUtils.isNotBlank(jxcProductkind.getParent().getId())){
			jxcProductkind.setParent(jxcProductkindService.get(jxcProductkind.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(jxcProductkind.getId())){
				JxcProductkind jxcProductkindChild = new JxcProductkind();
				jxcProductkindChild.setParent(new JxcProductkind(jxcProductkind.getParent().getId()));
				List<JxcProductkind> list = jxcProductkindService.findList(jxcProductkind); 
				if (list.size() > 0){
					jxcProductkind.setSort(list.get(list.size()-1).getSort());
					if (jxcProductkind.getSort() != null){
						jxcProductkind.setSort(jxcProductkind.getSort() + 30);
					}
				}
			}
		}
		if (jxcProductkind.getSort() == null){
			jxcProductkind.setSort(30);
		}
		model.addAttribute("jxcProductkind", jxcProductkind);
		return "modules/jxc/jxcProductkindForm";
	}

	@RequiresPermissions("jxc:jxcProductkind:edit")
	@RequestMapping(value = "save")
	public String save(JxcProductkind jxcProductkind, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, jxcProductkind)){
			return form(jxcProductkind, model);
		}
		jxcProductkindService.save(jxcProductkind);
		addMessage(redirectAttributes, "保存商品类别成功");
		return "redirect:"+Global.getAdminPath()+"/jxc/jxcProductkind/?repage";
	}
	
	@RequiresPermissions("jxc:jxcProductkind:edit")
	@RequestMapping(value = "delete")
	public String delete(JxcProductkind jxcProductkind, RedirectAttributes redirectAttributes) {
		jxcProductkindService.delete(jxcProductkind);
		addMessage(redirectAttributes, "删除商品类别成功");
		return "redirect:"+Global.getAdminPath()+"/jxc/jxcProductkind/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<JxcProductkind> list = jxcProductkindService.findList(new JxcProductkind());
		for (int i=0; i<list.size(); i++){
			JxcProductkind e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}
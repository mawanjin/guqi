/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.modules.weixin.exception.WeiXinException;
import com.thinkgem.jeesite.modules.weixin.http.WeixinHttpCore;
import com.thinkgem.jeesite.modules.weixin.vo.WeixinInquiryMenu;
import com.thinkgem.jeesite.modules.weixin.vo.WeixinMenuItemMeta;
import com.thinkgem.jeesite.modules.weixin.vo.WeixinMenuMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinMenu;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinMenuDao;

/**
 * 菜单Service
 * @author mawj
 * @version 2016-11-02
 */
@Service
@Transactional(readOnly = true)
public class WeixinMenuService extends CrudService<WeixinMenuDao, WeixinMenu> {

	@Autowired
	WeixinHttpCore weixinHttpCore;

	@Autowired
	WeixinAPIService weixinAPIService;

	public WeixinMenu get(String id) {
		return super.get(id);
	}
	
	public List<WeixinMenu> findList(WeixinMenu weixinMenu) {
		return super.findList(weixinMenu);
	}
	@Transactional(readOnly = false)
	public Page<WeixinMenu> findPageMenu(Page<WeixinMenu> page, WeixinMenu weixinMenu) throws WeiXinException{
		return super.findPage(page, weixinMenu);
	}

	private WeixinMenu getByName(String name) {
		return dao.getByName(name);
	}

	public boolean synMenu(){
		//同步到微信服务器
		WeixinMenuMeta menuMeta = new WeixinMenuMeta();
		List<WeixinMenuItemMeta> itemMetas = new ArrayList<>(0);
		menuMeta.setButton(itemMetas);

		List<WeixinMenu> menus = findList(null);
		if(menus.isEmpty()){
			String rep = weixinHttpCore.deleteMenu(weixinAPIService.getOrNewToken());
			if(rep.equals("{\"errcode\":0,\"errmsg\":\"ok\"}"))return true;
			return false;
		}

		for(WeixinMenu menu:menus){

			if(menu.getParent().equals("0")){
				continue;
			}

			for(WeixinMenu _m : menus){
				if(menu.getParent().equals(_m.getId())){
					menu.setParentName(_m.getName());
					break;
				}
			}
		}


		for (WeixinMenu menu:menus){
			if(menu.getParent().equals("0")){
				WeixinMenuItemMeta item = new WeixinMenuItemMeta();
				item.setKey(menu.getMkey());
				item.setName(menu.getName());
				item.setType(menu.getType());
				item.setUrl(menu.getUrl());
				itemMetas.add(item);
			}
		}

		for(WeixinMenu menu:menus){
			if(!menu.getParent().equals("0")){
				WeixinMenuItemMeta item = new WeixinMenuItemMeta();
				item.setKey(menu.getMkey());
				item.setName(menu.getName());
				item.setType(menu.getType());
				item.setUrl(menu.getUrl());

				for(WeixinMenuItemMeta itemMeta: itemMetas){
					if(itemMeta.getName().equals(menu.getParentName())){
						List<WeixinMenuItemMeta> subButton = itemMeta.getSub_button();
						if(subButton==null){
							subButton = new ArrayList<>(0);
							itemMeta.setSub_button(subButton);
						}
						subButton.add(item);
					}
				}
			}
		}

		String menusJson = JsonMapper.getInstance().toJson(menuMeta);
		String rep = weixinHttpCore.menuCreate(weixinAPIService.getOrNewToken(),menusJson);
		if(rep.equals("{\"errcode\":0,\"errmsg\":\"ok\"}"))return true;
		return false;
	}

	@Transactional(readOnly = false)
	public void save(WeixinMenu weixinMenu,boolean syn) {
		//保存到数据库
		super.save(weixinMenu);
//		if(!syn)return;
//		synMenu();
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinMenu weixinMenu) {
		dao.deleteIncludeChildren(weixinMenu);
//		super.delete(weixinMenu);
//		//同步到微信
//		synMenu();
	}

	public List<WeixinMenu> findFirstLevelList(){
		return dao.findFirstLevelList();
	}

	public  Map<String, String> getMenuSelect(String id){

		List<WeixinMenu> menus = findFirstLevelList();
		Map<String, String> map = new HashMap<String, String>();
		map.put("0", "一级菜单");

		if(menus!=null&&menus.size()>0){
			for(WeixinMenu menu:menus){
				if(menu.getId().equals(id))continue;
				map.put(menu.getId(),menu.getName());
			}
		}
		return map;
	}
	
}
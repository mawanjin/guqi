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
		int i=0;
		//weixinHttpCore
		String menuJson = weixinHttpCore.getMenu(weixinAPIService.getOrNewToken());
		if(menuJson.contains("{\"errcode\"")){
			throw new WeiXinException("获取菜单失败,请稍候重试");
		}
		if(menuJson!=null){
			WeixinMenuMeta menuMeta = JsonMapper.getInstance().fromJson(menuJson,WeixinMenuMeta.class);
			//先删除数据库中的菜单
			dao.deleteAll();
			//先保存一级菜单
			List<WeixinMenuItemMeta> itemMetas = menuMeta.getButton();
			for(WeixinMenuItemMeta itemMeta:itemMetas){
				i++;
				WeixinMenu entity = new WeixinMenu();
				entity.setName(itemMeta.getName());
				entity.setType(itemMeta.getType());
				entity.setUrl(itemMeta.getUrl());
				entity.setMkey(itemMeta.getKey());
				entity.setMorder(i);
				save(entity);
			}

			//再保存二级菜单
			for(WeixinMenuItemMeta itemMeta:itemMetas){

				List<WeixinMenuItemMeta> subItem = itemMeta.getSub_button();
				if(subItem!=null){
					i++;
					WeixinMenu entity = new WeixinMenu();
					entity.setName(itemMeta.getName());
					entity.setType(itemMeta.getType());
					entity.setUrl(itemMeta.getUrl());
					entity.setMkey(itemMeta.getKey());
					entity.setMorder(i);
					save(entity);
				}

			}

		}

		page.setOrderBy("morder");
		return super.findPage(page, weixinMenu);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinMenu weixinMenu) {
		//保存到数据库
		super.save(weixinMenu);
		//同步到微信服务器
		WeixinMenuMeta menuMeta = new WeixinMenuMeta();
		List<WeixinMenuItemMeta> itemMetas = new ArrayList<>(0);
		menuMeta.setButton(itemMetas);

		List<WeixinMenu> menus = findList(null);
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
		weixinHttpCore.menuCreate(weixinAPIService.getOrNewToken(),menusJson);

	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinMenu weixinMenu) {
		super.delete(weixinMenu);
	}

	public List<WeixinMenu> findFirstLevelList(){
		return dao.findFirstLevelList();
	}

	public  Map<String, String> getMenuSelect(){

		List<WeixinMenu> menus = findFirstLevelList();
		Map<String, String> map = new HashMap<String, String>();
		map.put("0", "一级菜单");

		if(menus!=null&&menus.size()>0){
			for(WeixinMenu menu:menus){
				map.put(menu.getId(),menu.getName());
			}
		}
		return map;
	}
	
}
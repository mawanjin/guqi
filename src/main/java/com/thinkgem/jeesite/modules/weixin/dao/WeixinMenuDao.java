/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinMenu;

import java.util.List;

/**
 * 菜单DAO接口
 * @author mawj
 * @version 2016-11-02
 */
@MyBatisDao
public interface WeixinMenuDao extends CrudDao<WeixinMenu> {
	List<WeixinMenu> findFirstLevelList();
	void deleteAll();
}
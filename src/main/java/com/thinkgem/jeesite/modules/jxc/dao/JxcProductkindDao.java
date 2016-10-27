/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jxc.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.jxc.entity.JxcProductkind;

/**
 * 商品类别DAO接口
 * @author 马万进
 * @version 2016-07-23
 */
@MyBatisDao
public interface JxcProductkindDao extends TreeDao<JxcProductkind> {
	
}
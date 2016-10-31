/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinSubscriber;

/**
 * 已关注微信用户DAO接口
 * @author mawj
 * @version 2016-10-31
 */
@MyBatisDao
public interface WeixinSubscriberDao extends CrudDao<WeixinSubscriber> {
	
}
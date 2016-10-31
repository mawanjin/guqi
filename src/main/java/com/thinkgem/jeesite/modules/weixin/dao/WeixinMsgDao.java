/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinMsg;

/**
 * 所有微信的消息收发表DAO接口
 * @author mawj
 * @version 2016-10-28
 */
@MyBatisDao
public interface WeixinMsgDao extends CrudDao<WeixinMsg> {
	
}
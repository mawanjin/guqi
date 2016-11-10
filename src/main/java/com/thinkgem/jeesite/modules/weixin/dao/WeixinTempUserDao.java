/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinTempUser;

/**
 * 临时用户表，关注并注册了，但未完善资料DAO接口
 * @author mawj
 * @version 2016-11-09
 */
@MyBatisDao
public interface WeixinTempUserDao extends CrudDao<WeixinTempUser> {
    int getRegisterCount();
}
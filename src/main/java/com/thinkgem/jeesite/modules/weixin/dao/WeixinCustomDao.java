/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinCustom;

import java.util.List;

/**
 * 微信客服信息表DAO接口
 * @author mawj
 * @version 2016-10-31
 */
@MyBatisDao
public interface WeixinCustomDao extends CrudDao<WeixinCustom> {
    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     * @return
     */
    public int deleteAll();

    List<WeixinCustom> findListByKFAccount(String kfAccount);
}
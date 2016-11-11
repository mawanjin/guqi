/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinOrder;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinOrderDao;

/**
 * 订单基础表Service
 * @author mawj
 * @version 2016-11-11
 */
@Service
@Transactional(readOnly = true)
public class WeixinOrderService extends CrudService<WeixinOrderDao, WeixinOrder> {

	
	public WeixinOrder get(String id) {
		WeixinOrder weixinOrder = super.get(id);
		return weixinOrder;
	}
	
	public List<WeixinOrder> findList(WeixinOrder weixinOrder) {
		return super.findList(weixinOrder);
	}
	
	public Page<WeixinOrder> findPage(Page<WeixinOrder> page, WeixinOrder weixinOrder) {
		return super.findPage(page, weixinOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinOrder weixinOrder) {
		super.save(weixinOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinOrder weixinOrder) {
		super.delete(weixinOrder);
	}
	
}
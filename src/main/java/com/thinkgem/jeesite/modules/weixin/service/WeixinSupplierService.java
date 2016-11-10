/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinSupplier;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinSupplierDao;

/**
 * 供应商表Service
 * @author mawj
 * @version 2016-11-10
 */
@Service
@Transactional(readOnly = true)
public class WeixinSupplierService extends CrudService<WeixinSupplierDao, WeixinSupplier> {

	public WeixinSupplier get(String id) {
		return super.get(id);
	}
	
	public List<WeixinSupplier> findList(WeixinSupplier weixinSupplier) {
		return super.findList(weixinSupplier);
	}
	
	public Page<WeixinSupplier> findPage(Page<WeixinSupplier> page, WeixinSupplier weixinSupplier) {
		return super.findPage(page, weixinSupplier);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinSupplier weixinSupplier) {
		super.save(weixinSupplier);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinSupplier weixinSupplier) {
		super.delete(weixinSupplier);
	}
	
}
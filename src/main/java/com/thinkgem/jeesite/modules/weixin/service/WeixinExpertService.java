/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinExpert;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinExpertDao;

/**
 * 专家表Service
 * @author mawj
 * @version 2016-11-10
 */
@Service
@Transactional(readOnly = true)
public class WeixinExpertService extends CrudService<WeixinExpertDao, WeixinExpert> {

	public WeixinExpert get(String id) {
		return super.get(id);
	}
	
	public List<WeixinExpert> findList(WeixinExpert weixinExpert) {
		return super.findList(weixinExpert);
	}
	
	public Page<WeixinExpert> findPage(Page<WeixinExpert> page, WeixinExpert weixinExpert) {
		return super.findPage(page, weixinExpert);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinExpert weixinExpert) {
		super.save(weixinExpert);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinExpert weixinExpert) {
		super.delete(weixinExpert);
	}
	
}
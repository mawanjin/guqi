/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinTempUser;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinTempUserDao;

/**
 * 临时用户表，关注并注册了，但未完善资料Service
 * @author mawj
 * @version 2016-11-09
 */
@Service
@Transactional(readOnly = true)
public class WeixinTempUserService extends CrudService<WeixinTempUserDao, WeixinTempUser> {

	public WeixinTempUser get(String id) {
		return super.get(id);
	}
	
	public List<WeixinTempUser> findList(WeixinTempUser weixinTempUser) {
		return super.findList(weixinTempUser);
	}
	
	public Page<WeixinTempUser> findPage(Page<WeixinTempUser> page, WeixinTempUser weixinTempUser) {
		return super.findPage(page, weixinTempUser);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinTempUser weixinTempUser) {
		super.save(weixinTempUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinTempUser weixinTempUser) {
		super.delete(weixinTempUser);
	}

	public int getRegisterCount(){
		return dao.getRegisterCount();
	}
}
/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jxc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.jxc.entity.JxcPurchasekind;
import com.thinkgem.jeesite.modules.jxc.dao.JxcPurchasekindDao;

/**
 * 客户类别Service
 * @author 马万进
 * @version 2016-07-19
 */
@Service
@Transactional(readOnly = true)
public class JxcPurchasekindService extends CrudService<JxcPurchasekindDao, JxcPurchasekind> {

	public JxcPurchasekind get(String id) {
		return super.get(id);
	}
	
	public List<JxcPurchasekind> findList(JxcPurchasekind jxcPurchasekind) {
		return super.findList(jxcPurchasekind);
	}
	
	public Page<JxcPurchasekind> findPage(Page<JxcPurchasekind> page, JxcPurchasekind jxcPurchasekind) {
		return super.findPage(page, jxcPurchasekind);
	}
	
	@Transactional(readOnly = false)
	public void save(JxcPurchasekind jxcPurchasekind) {
		super.save(jxcPurchasekind);
	}
	
	@Transactional(readOnly = false)
	public void delete(JxcPurchasekind jxcPurchasekind) {
		super.delete(jxcPurchasekind);
	}
	
}
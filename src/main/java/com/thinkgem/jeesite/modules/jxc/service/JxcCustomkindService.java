/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jxc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.jxc.entity.JxcCustomkind;
import com.thinkgem.jeesite.modules.jxc.dao.JxcCustomkindDao;

/**
 * 客户类别Service
 * @author 马万进
 * @version 2016-07-19
 */
@Service
@Transactional(readOnly = true)
public class JxcCustomkindService extends CrudService<JxcCustomkindDao, JxcCustomkind> {

	public JxcCustomkind get(String id) {
		return super.get(id);
	}
	
	public List<JxcCustomkind> findList(JxcCustomkind jxcCustomkind) {
		return super.findList(jxcCustomkind);
	}
	
	public Page<JxcCustomkind> findPage(Page<JxcCustomkind> page, JxcCustomkind jxcCustomkind) {
		return super.findPage(page, jxcCustomkind);
	}
	
	@Transactional(readOnly = false)
	public void save(JxcCustomkind jxcCustomkind) {
		super.save(jxcCustomkind);
	}
	
	@Transactional(readOnly = false)
	public void delete(JxcCustomkind jxcCustomkind) {
		super.delete(jxcCustomkind);
	}
	
}
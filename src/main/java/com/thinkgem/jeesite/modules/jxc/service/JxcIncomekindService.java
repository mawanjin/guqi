/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jxc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.jxc.entity.JxcIncomekind;
import com.thinkgem.jeesite.modules.jxc.dao.JxcIncomekindDao;

/**
 * 收入类别Service
 * @author 马万进
 * @version 2016-07-23
 */
@Service
@Transactional(readOnly = true)
public class JxcIncomekindService extends CrudService<JxcIncomekindDao, JxcIncomekind> {

	public JxcIncomekind get(String id) {
		return super.get(id);
	}
	
	public List<JxcIncomekind> findList(JxcIncomekind jxcIncomekind) {
		return super.findList(jxcIncomekind);
	}
	
	public Page<JxcIncomekind> findPage(Page<JxcIncomekind> page, JxcIncomekind jxcIncomekind) {
		return super.findPage(page, jxcIncomekind);
	}
	
	@Transactional(readOnly = false)
	public void save(JxcIncomekind jxcIncomekind) {
		super.save(jxcIncomekind);
	}
	
	@Transactional(readOnly = false)
	public void delete(JxcIncomekind jxcIncomekind) {
		super.delete(jxcIncomekind);
	}
	
}
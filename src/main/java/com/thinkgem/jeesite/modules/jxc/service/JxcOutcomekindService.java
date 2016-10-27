/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jxc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.jxc.entity.JxcOutcomekind;
import com.thinkgem.jeesite.modules.jxc.dao.JxcOutcomekindDao;

/**
 * 支出类别Service
 * @author 马万进
 * @version 2016-07-23
 */
@Service
@Transactional(readOnly = true)
public class JxcOutcomekindService extends CrudService<JxcOutcomekindDao, JxcOutcomekind> {

	public JxcOutcomekind get(String id) {
		return super.get(id);
	}
	
	public List<JxcOutcomekind> findList(JxcOutcomekind jxcOutcomekind) {
		return super.findList(jxcOutcomekind);
	}
	
	public Page<JxcOutcomekind> findPage(Page<JxcOutcomekind> page, JxcOutcomekind jxcOutcomekind) {
		return super.findPage(page, jxcOutcomekind);
	}
	
	@Transactional(readOnly = false)
	public void save(JxcOutcomekind jxcOutcomekind) {
		super.save(jxcOutcomekind);
	}
	
	@Transactional(readOnly = false)
	public void delete(JxcOutcomekind jxcOutcomekind) {
		super.delete(jxcOutcomekind);
	}
	
}
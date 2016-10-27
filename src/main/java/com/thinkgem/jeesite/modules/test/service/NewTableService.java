/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.test.entity.NewTable;
import com.thinkgem.jeesite.modules.test.dao.NewTableDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2016-08-31
 */
@Service
@Transactional(readOnly = true)
public class NewTableService extends CrudService<NewTableDao, NewTable> {

	public NewTable get(String id) {
		return super.get(id);
	}
	
	public List<NewTable> findList(NewTable newTable) {
		return super.findList(newTable);
	}
	
	public Page<NewTable> findPage(Page<NewTable> page, NewTable newTable) {
		return super.findPage(page, newTable);
	}
	
	@Transactional(readOnly = false)
	public void save(NewTable newTable) {
		super.save(newTable);
	}
	
	@Transactional(readOnly = false)
	public void delete(NewTable newTable) {
		super.delete(newTable);
	}
	
}
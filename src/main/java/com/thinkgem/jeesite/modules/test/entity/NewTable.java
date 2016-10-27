/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2016-08-31
 */
public class NewTable extends DataEntity<NewTable> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	
	public NewTable() {
		super();
	}

	public NewTable(String id){
		super(id);
	}

	@Length(min=0, max=45, message="name长度必须介于 0 和 45 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
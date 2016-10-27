/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jxc.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 支出类别Entity
 * @author 马万进
 * @version 2016-07-23
 */
public class JxcOutcomekind extends DataEntity<JxcOutcomekind> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 支出类别
	
	public JxcOutcomekind() {
		super();
	}

	public JxcOutcomekind(String id){
		super(id);
	}

	@Length(min=1, max=100, message="支出类别长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
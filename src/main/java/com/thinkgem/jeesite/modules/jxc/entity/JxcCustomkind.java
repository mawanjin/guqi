/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jxc.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 客户类别Entity
 * @author 马万进
 * @version 2016-07-19
 */
public class JxcCustomkind extends DataEntity<JxcCustomkind> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 客户类别
	
	public JxcCustomkind() {
		super();
	}

	public JxcCustomkind(String id){
		super(id);
	}

	@Length(min=0, max=100, message="客户类别长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
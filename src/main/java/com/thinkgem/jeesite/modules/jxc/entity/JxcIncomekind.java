/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jxc.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 收入类别Entity
 * @author 马万进
 * @version 2016-07-23
 */
public class JxcIncomekind extends DataEntity<JxcIncomekind> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 收入类别
	
	public JxcIncomekind() {
		super();
	}

	public JxcIncomekind(String id){
		super(id);
	}

	@Length(min=0, max=100, message="收入类别长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 菜单Entity
 * @author mawj
 * @version 2016-11-02
 */
public class WeixinMenu extends DataEntity<WeixinMenu> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String type;		// 类型
	private String mkey;		// 事件
	private String url;		// url
	private String parent;		// parent
	private String parentName;		// parent
	private int morder;

	public WeixinMenu() {
		super();
	}

	public WeixinMenu(String id){
		super(id);
	}

	@Length(min=0, max=45, message="名称长度必须介于 0 和 45 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=45, message="类型长度必须介于 0 和 45 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=45, message="事件长度必须介于 0 和 45 之间")
	public String getMkey() {
		return mkey;
	}

	public void setMkey(String mkey) {
		this.mkey = mkey;
	}
	
	@Length(min=0, max=255, message="url长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=64, message="parent长度必须介于 0 和 64 之间")
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public int getMorder() {
		return morder;
	}

	public void setMorder(int morder) {
		this.morder = morder;
	}
}
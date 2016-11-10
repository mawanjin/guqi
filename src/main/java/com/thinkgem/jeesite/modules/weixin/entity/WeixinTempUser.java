/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 临时用户表，关注并注册了，但未完善资料Entity
 * @author mawj
 * @version 2016-11-09
 */
public class WeixinTempUser extends DataEntity<WeixinTempUser> {
	
	private static final long serialVersionUID = 1L;
	private String openid;		// 微信标识
	private String nickName;		// 昵称
	private String role;		// 角色
	private String mobile;		// 手机号
	private String lable;		// 标签
	private String name;		// 姓名
	private Date beginCreateDate;		// 开始 注册时间
	private Date endCreateDate;		// 结束 注册时间
	
	public WeixinTempUser() {
		super();
	}

	public WeixinTempUser(String id){
		super(id);
	}

	@Length(min=0, max=45, message="微信标识长度必须介于 0 和 45 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Length(min=0, max=45, message="昵称长度必须介于 0 和 45 之间")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Length(min=0, max=11, message="角色长度必须介于 0 和 11 之间")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Length(min=0, max=45, message="手机号长度必须介于 0 和 45 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=45, message="标签长度必须介于 0 和 45 之间")
	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}
	
	@Length(min=0, max=45, message="姓名长度必须介于 0 和 45 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}
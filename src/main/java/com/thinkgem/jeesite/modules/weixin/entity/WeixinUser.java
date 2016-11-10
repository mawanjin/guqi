/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 客户主表Entity
 * @author mawj
 * @version 2016-10-27
 */
public class WeixinUser extends DataEntity<WeixinUser> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String nickName;		// 昵称
	private String company;		// 所属企业
	private String gender;		// 性别
	private String type;		// 类型
	private String registerPhone;		// 注册手机号
	private String weixinOpenid;		// 微信的用户唯一标识
	private String email;		// 邮箱
	private String cerFile1;		// 企业认证资料
	private String cerFile2;		// 企业认证资料
	private String cerFile3;		// 企业认证资料

	@JsonIgnore
	private String jsonData;

	public WeixinUser() {
		super();
	}

	public WeixinUser(String id){
		super(id);
	}

	@Length(min=0, max=45, message="名称长度必须介于 0 和 45 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Length(min=0, max=45, message="昵称长度必须介于 0 和 45 之间")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=45, message="注册手机号长度必须介于 0 和 45 之间")
	public String getRegisterPhone() {
		return registerPhone;
	}

	public void setRegisterPhone(String registerPhone) {
		this.registerPhone = registerPhone;
	}
	
	@Length(min=0, max=45, message="微信的用户唯一标识长度必须介于 0 和 45 之间")
	public String getWeixinOpenid() {
		return weixinOpenid;
	}

	public void setWeixinOpenid(String weixinOpenid) {
		this.weixinOpenid = weixinOpenid;
	}
	
	@Length(min=0, max=45, message="邮箱长度必须介于 0 和 45 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(min=0, max=45, message="企业长度必须介于 0 和 45 之间")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJsonData() {
		return JsonMapper.toJsonString(this);
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getCerFile1() {
		return cerFile1;
	}

	public void setCerFile1(String cerFile1) {
		this.cerFile1 = cerFile1;
	}

	public String getCerFile2() {
		return cerFile2;
	}

	public void setCerFile2(String cerFile2) {
		this.cerFile2 = cerFile2;
	}

	public String getCerFile3() {
		return cerFile3;
	}

	public void setCerFile3(String cerFile3) {
		this.cerFile3 = cerFile3;
	}
}
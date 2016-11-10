/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 供应商表Entity
 * @author mawj
 * @version 2016-11-10
 */
public class WeixinSupplier extends DataEntity<WeixinSupplier> {
	
	private static final long serialVersionUID = 1L;
	private String registerPhone;		// 手机号
	private String name;		// 姓名
	private String nickName;		// 昵称
	private String company;		// 所属企业
	private String type;		// 角色
	private String email;		// 邮箱
	private String address;		// 公司地址
	private String cerType;		// 资质类型
	private String bank;		// 开户行
	private String bankAccount;		// 户名
	private String card;		// 卡号
	private String cerFile1;		// 资质

	@JsonIgnore
	private String jsonData;
	
	public WeixinSupplier() {
		super();
	}

	public WeixinSupplier(String id){
		super(id);
	}

	@Length(min=0, max=45, message="手机号长度必须介于 0 和 45 之间")
	public String getRegisterPhone() {
		return registerPhone;
	}

	public void setRegisterPhone(String registerPhone) {
		this.registerPhone = registerPhone;
	}
	
	@Length(min=0, max=45, message="姓名长度必须介于 0 和 45 之间")
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
	
	@Length(min=0, max=45, message="所属企业长度必须介于 0 和 45 之间")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	@Length(min=0, max=1, message="角色长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=45, message="邮箱长度必须介于 0 和 45 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=100, message="公司地址长度必须介于 0 和 100 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=1, message="资质类型长度必须介于 0 和 1 之间")
	public String getCerType() {
		return cerType;
	}

	public void setCerType(String cerType) {
		this.cerType = cerType;
	}
	
	@Length(min=0, max=45, message="开户行长度必须介于 0 和 45 之间")
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
	@Length(min=0, max=45, message="户名长度必须介于 0 和 45 之间")
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	@Length(min=0, max=45, message="卡号长度必须介于 0 和 45 之间")
	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getCerFile1() {
		return cerFile1;
	}

	public void setCerFile1(String cerFile1) {
		this.cerFile1 = cerFile1;
	}

	public String getJsonData() {
		return JsonMapper.toJsonString(this);
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
}
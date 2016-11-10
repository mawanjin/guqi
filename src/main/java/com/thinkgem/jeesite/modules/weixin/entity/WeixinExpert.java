/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 专家表Entity
 * @author mawj
 * @version 2016-11-10
 */
public class WeixinExpert extends DataEntity<WeixinExpert> {
	
	private static final long serialVersionUID = 1L;
	private String registerPhone;		// 手机号
	private String name;		// 姓名
	private String nickName;		// 昵称
	private String company;		// 所属企业
	private int withdraw;		// 累计提现
	private int deposite;		// 暂不能结算金额
	private int loan;		// 欠账金额
	private String email;		// 邮箱
	private String address;		// 居住区域
	private String type;		// 角色

	@JsonIgnore
	private String jsonData;

	public WeixinExpert() {
		super();
	}

	public WeixinExpert(String id){
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
	
	public int getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(int withdraw) {
		this.withdraw = withdraw;
	}
	
	public int getDeposite() {
		return deposite;
	}

	public void setDeposite(int deposite) {
		this.deposite = deposite;
	}
	
	public int getLoan() {
		return loan;
	}

	public void setLoan(int loan) {
		this.loan = loan;
	}
	
	@Length(min=0, max=45, message="邮箱长度必须介于 0 和 45 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=45, message="居住区域长度必须介于 0 和 45 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=1, message="角色长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJsonData() {
		return JsonMapper.getInstance().toJson(this);
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
}
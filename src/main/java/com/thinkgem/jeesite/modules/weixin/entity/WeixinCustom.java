/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信客服信息表Entity
 * @author mawj
 * @version 2016-10-31
 */
public class WeixinCustom extends DataEntity<WeixinCustom> {
	
	private static final long serialVersionUID = 1L;
	private String kfNick;		// kf_nick
	private String kfAccount;		// kf_account
	private String kfId;		// kf_id
	private String kfHeadimgurl;		// kf_headimgurl
	private Date createTime;		// create_time
	private String password;
	private int status;
	private int autoAccept;
	private int acceptedCase;

	public WeixinCustom() {
		super();
	}

	public WeixinCustom(String id){
		super(id);
	}

	@Length(min=0, max=45, message="kf_nick长度必须介于 0 和 45 之间")
	public String getKfNick() {
		return kfNick;
	}

	public void setKfNick(String kfNick) {
		this.kfNick = kfNick;
	}
	
	@Length(min=0, max=45, message="kf_account长度必须介于 0 和 45 之间")
	public String getKfAccount() {
		return kfAccount;
	}

	public void setKfAccount(String kfAccount) {
		this.kfAccount = kfAccount;
	}
	
	@Length(min=0, max=45, message="kf_id长度必须介于 0 和 45 之间")
	public String getKfId() {
		return kfId;
	}

	public void setKfId(String kfId) {
		this.kfId = kfId;
	}
	
	@Length(min=0, max=255, message="kf_headimgurl长度必须介于 0 和 255 之间")
	public String getKfHeadimgurl() {
		return kfHeadimgurl;
	}

	public void setKfHeadimgurl(String kfHeadimgurl) {
		this.kfHeadimgurl = kfHeadimgurl;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Length(min=0, max=45, message="客服登录密码")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAutoAccept() {
		return autoAccept;
	}

	public void setAutoAccept(int autoAccept) {
		this.autoAccept = autoAccept;
	}

	public int getAcceptedCase() {
		return acceptedCase;
	}

	public void setAcceptedCase(int acceptedCase) {
		this.acceptedCase = acceptedCase;
	}
}
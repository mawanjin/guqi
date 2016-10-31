/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 已关注微信用户Entity
 * @author mawj
 * @version 2016-10-31
 */
public class WeixinSubscriber extends DataEntity<WeixinSubscriber> {
	
	private static final long serialVersionUID = 1L;
	private String toUserName;		// 被关注者
	private String fromUserName;		// 关注者
	private Date createTime;		// 关注时间
	
	public WeixinSubscriber() {
		super();
	}

	public WeixinSubscriber(String id){
		super(id);
	}

	@Length(min=0, max=45, message="被关注者长度必须介于 0 和 45 之间")
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	@Length(min=0, max=45, message="关注者长度必须介于 0 和 45 之间")
	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
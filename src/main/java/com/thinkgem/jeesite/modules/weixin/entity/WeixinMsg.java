/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 所有微信的消息收发表Entity
 * @author mawj
 * @version 2016-10-28
 */
public class WeixinMsg extends DataEntity<WeixinMsg> {
	
	private static final long serialVersionUID = 1L;
	private String toUserName;		// 接收方
	private String fromUserName;		// 发送方
	private Date createTime;		// 创建时间
	private String msgType;		// 消息类型
	private String content;		// 文本消息内容
	private String msgId;		// 消息id
	private String picUrl;		// 图片链接
	private String mediaId;		// 图片媒体id
	private String format;		// 语音格式
	private String locationX;		// 经度
	private String locationY;		// 纬度
	private String scale;		// 缩放大小
	private String label;		// 位置信息
	private String title;		// 消息标题
	private String description;		// 消息描述
	private String url;		// 消息链接
	private Date beginCreateTime;		// 开始 创建时间
	private Date endCreateTime;		// 结束 创建时间
	
	public WeixinMsg() {
		super();
	}

	public WeixinMsg(String id){
		super(id);
	}

	@Length(min=0, max=45, message="接收方长度必须介于 0 和 45 之间")
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	@Length(min=0, max=45, message="发送方长度必须介于 0 和 45 之间")
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
	
	@Length(min=0, max=45, message="消息类型长度必须介于 0 和 45 之间")
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	@Length(min=0, max=255, message="文本消息内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=100, message="消息id长度必须介于 0 和 100 之间")
	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	@Length(min=0, max=255, message="图片链接长度必须介于 0 和 255 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	@Length(min=0, max=255, message="图片媒体id长度必须介于 0 和 45 之间")
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	@Length(min=0, max=45, message="语音格式长度必须介于 0 和 45 之间")
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	@Length(min=0, max=45, message="经度长度必须介于 0 和 45 之间")
	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}
	
	@Length(min=0, max=45, message="纬度长度必须介于 0 和 45 之间")
	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}
	
	@Length(min=0, max=45, message="缩放大小长度必须介于 0 和 45 之间")
	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}
	
	@Length(min=0, max=255, message="位置信息长度必须介于 0 和 255 之间")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Length(min=0, max=55, message="消息标题长度必须介于 0 和 55 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="消息描述长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=255, message="消息链接长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}
	
	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
		
}